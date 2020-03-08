package DataBase.group;

public class Group {

    private GroupTypeId groupTypeId;
    private String groupName;

    public Group(GroupTypeId GroupTypeId, String Name) {
        groupTypeId = GroupTypeId;
        groupName = Name;
    }

    public String getGroupName() {
        return groupName;
    }

    public GroupTypeId getGroupTypeId() {
        return groupTypeId;
    }
}
