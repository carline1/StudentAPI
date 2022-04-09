from django.template.defaulttags import url
from django.urls import path

from .views import StudentsListView, AttendanceView, LoginValidateView, RetrieveUpdateStudentInfoView, \
    CreateStudentView

urlpatterns = [
    path('api/students', StudentsListView.as_view()),
    path('api/students/', StudentsListView.as_view()),
    path('api/registration', CreateStudentView.as_view()),
    path('api/registration/', CreateStudentView.as_view()),
    path('api/students/<int:pk>', RetrieveUpdateStudentInfoView.as_view()),
    path('api/students/<int:pk>/', RetrieveUpdateStudentInfoView.as_view()),
    path('api/validate_login', LoginValidateView.as_view()),
    path('api/validate_login/', LoginValidateView.as_view()),
    path('api/attendance', AttendanceView.as_view()),
    path('api/attendance/', AttendanceView.as_view()),
    path('api/attendance/<int:id_stud>', AttendanceView.as_view()),
    path('api/attendance/<int:id_stud>/', AttendanceView.as_view())
]
