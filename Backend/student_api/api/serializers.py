from rest_framework.serializers import ModelSerializer

from .models import Student, Attendance


class StudentSerializer(ModelSerializer):
    class Meta:
        model = Student
        fields = ('id_stud', 'email', 'password', 'firstname', 'lastname', 'group', 'date_of_reg')


class AttendanceSerializer(ModelSerializer):
    class Meta:
        model = Attendance
        fields = ('id_attend', 'student', 'date_of_check')


class StudentWithoutPwdSerializer(ModelSerializer):
    class Meta:
        model = Student
        fields = ('id_stud', 'email', 'firstname', 'lastname', 'group', 'date_of_reg')