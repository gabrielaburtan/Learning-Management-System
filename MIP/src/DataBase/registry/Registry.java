package DataBase.registry;

import DataBase.student.Student;
import DataBase.subject.Subject;
import DataBase.teachers.Teacher;

public class Registry {

    private Student student;
    private Teacher teacher;
    private Subject subject;
    private String date;
    private String grade;

    Registry(Student student, Teacher teacher, Subject subject, String date, String grade) {
        this.date = date;
        this.grade = grade;
        this.student = student;
        this.teacher = teacher;
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public String getGrade() {
        return grade;
    }

    public String getDate() {
        return date;
    }

    public Subject getSubject() {
        return subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
