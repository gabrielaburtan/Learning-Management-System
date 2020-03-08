package DataBase.student;

import DataBase.group.Group;

public class StudentGroup {

    private Student student;
    private Group group;
    private String startDate;
    private String endDate;

    public StudentGroup(Student Student, Group Group, String StartDate, String EndDate) {
        student = Student;
        group = Group;
        startDate = StartDate;
        endDate = EndDate;
    }

    public Student getStudent() {
        return student;
    }

    public Group getGroup() {
        return group;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}


