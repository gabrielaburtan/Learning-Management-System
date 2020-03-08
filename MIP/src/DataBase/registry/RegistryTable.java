package DataBase.registry;

public class RegistryTable {
    private String studentName;
    private String teacherName;
    private String subjectName;
    private String date;
    private String grade;

    public RegistryTable(String student, String teacher, String subject, String date, String grade) {
        studentName = student;
        teacherName = teacher;
        subjectName = subject;
        this.date = date;
        this.grade = grade;
    }

    public String getDate() {
        return date;
    }

    public String getGrade() {
        return grade;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
