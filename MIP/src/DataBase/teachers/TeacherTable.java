package DataBase.teachers;

import DataBase.subject.Subject;

public class TeacherTable {

    private String Name;
    private String CNP;
    private String SubjectName;

    public TeacherTable(Teacher teacher, Subject subject) {
        this.Name = teacher.getTeacherName();
        this.CNP = teacher.getTeacherEmail();
        this.SubjectName = subject.getSubjectName();
    }

    public String getCNP() {
        return CNP;
    }

    public String getName() {
        return Name;
    }

    public String getSubjectName() {
        return SubjectName;
    }
}
