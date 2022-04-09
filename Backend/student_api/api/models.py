from django.db import models


# Create your models here.

class Student(models.Model):
    id_stud = models.AutoField(primary_key=True)
    email = models.EmailField(max_length=200, unique=True)
    password = models.CharField(max_length=50)
    firstname = models.CharField(max_length=50)
    lastname = models.CharField(max_length=50)
    group = models.CharField(max_length=10)
    date_of_reg = models.DateTimeField(blank=True, null=True)

    def __str__(self):
        return self.email


class Attendance(models.Model):
    id_attend = models.AutoField(primary_key=True)
    student = models.ForeignKey(Student, on_delete=models.CASCADE)
    date_of_check = models.DateTimeField(blank=True, null=True)

    def __str__(self):
        return f"{self.student} - {self.date_of_check}"

