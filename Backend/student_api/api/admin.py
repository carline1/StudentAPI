from django.contrib import admin
from .models import Student, Attendance


# Register your models here.

@admin.register(Student)
class StudentAdmin(admin.ModelAdmin):
    list_display = ('id_stud', 'email', 'password', 'firstname', 'lastname', 'group', 'date_of_reg')
    list_display_links = ('id_stud', 'email', 'firstname', 'lastname', 'group')
    search_fields = ('id_stud', 'email', 'firstname', 'lastname', 'group')
    list_filter = ('group',)


@admin.register(Attendance)
class AttendanceAdmin(admin.ModelAdmin):
    list_display = ('id_attend', 'student', 'date_of_check')

