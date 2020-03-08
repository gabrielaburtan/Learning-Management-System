package DataBase.student;

public class StudentTable {
    private String nameStudent;
    private String emailStudent;
    private String groupName;
    private String groupTypeName;
    private String startDate;
    private String endDate;

    public StudentTable(StudentGroup studentGroup) {
        nameStudent = studentGroup.getStudent().getName();
        emailStudent = studentGroup.getStudent().getEmail();
        startDate = studentGroup.getStartDate();
        endDate = studentGroup.getEndDate();
        groupName = studentGroup.getGroup().getGroupName();
        groupTypeName = studentGroup.getGroup().getGroupTypeId().getGroupTypeName();
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupTypeName() {
        return groupTypeName;
    }

    public String getEmailStudent() {
        return emailStudent;
    }
}

