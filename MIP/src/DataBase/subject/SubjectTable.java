package DataBase.subject;

public class SubjectTable {

    private String subjectTypeName;
    private String subjectName;
    private int credits;

    public SubjectTable(Subject subject) {
        subjectTypeName = subject.getSubjectTypeId().getSubjectTypeName();
        subjectName = subject.getSubjectName();
        credits = subject.getCredits();
    }

    public int getCredits() {
        return credits;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectTypeName() {
        return subjectTypeName;
    }
}
