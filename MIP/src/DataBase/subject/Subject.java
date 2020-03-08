package DataBase.subject;

public class Subject {

    private SubjectTypeId subjectTypeId;
    private String subjectName;
    private int credits;

    public Subject(SubjectTypeId SubjectTypeId, String Name, int Credits) {
        subjectTypeId = SubjectTypeId;
        subjectName = Name;
        credits = Credits;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getCredits() {
        return credits;
    }

    public SubjectTypeId getSubjectTypeId() {
        return subjectTypeId;
    }
}
