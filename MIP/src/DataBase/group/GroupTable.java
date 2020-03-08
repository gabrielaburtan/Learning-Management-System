package DataBase.group;

public class GroupTable {

    private String groupName;
    private String groupType;

    public GroupTable(Group group) {
        groupName = group.getGroupName();
        groupType = group.getGroupTypeId().getGroupTypeName();
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupType() {
        return groupType;
    }
}
