# Create your views here.
from datetime import datetime

from django.http import JsonResponse
from rest_framework import status
from rest_framework.generics import CreateAPIView, RetrieveUpdateAPIView, ListAPIView, ListCreateAPIView
from rest_framework.response import Response
from rest_framework.views import APIView

from .models import Student, Attendance
from .serializers import StudentSerializer, AttendanceSerializer, StudentWithoutPwdSerializer


class StudentsListView(ListAPIView):
    queryset = Student.objects.all()
    serializer_class = StudentWithoutPwdSerializer


class CreateStudentView(CreateAPIView):
    queryset = Student.objects.all()
    serializer_class = StudentSerializer

    def post(self, request, *args, **kwargs):

        request.data["date_of_reg"] = datetime.now()
        self.create(request, *args, **kwargs)
        return Response(request.data, status=status.HTTP_201_CREATED)


class RetrieveUpdateStudentInfoView(RetrieveUpdateAPIView):
    queryset = Student.objects.all()
    serializer_class = StudentWithoutPwdSerializer


class AttendanceView(ListCreateAPIView):
    queryset = Attendance.objects.all()
    serializer_class = AttendanceSerializer

    def post(self, request, *args, **kwargs):
        request.data["date_of_check"] = datetime.now()
        self.create(request, *args, **kwargs)
        return Response(request.data, status=status.HTTP_201_CREATED)

    def get(self, request, *args, **kwargs):
        self.queryset = self.queryset.filter(student=kwargs['id_stud'])
        return self.list(request, *args, **kwargs)


class LoginValidateView(APIView):
    queryset = Student.objects.all()

    def post(self, request):
        student = self.queryset.filter(email=request.data["email"], password=request.data["password"])
        if student.count() != 0:
            return JsonResponse({"response": "Success"}, status=status.HTTP_200_OK, safe=False)
        return JsonResponse({
            "response": "Student not found "
        }, status=status.HTTP_404_NOT_FOUND, safe=False)
