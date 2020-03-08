package DataBase.teachers;

public class Teacher {

    private String teacherName;
    private String teacherEmail;

    public Teacher(String name, String email) {
        this.teacherName = name;
        this.teacherEmail = email;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }
}
