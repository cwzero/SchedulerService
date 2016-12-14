package com.liquidforte.scheduler.entity;

import com.liquidforte.scheduler.SchedulerService;
import com.liquidforte.scheduler.database.GroupEventTable;
import com.liquidforte.scheduler.database.GroupTable;
import com.liquidforte.scheduler.database.UserGroupTable;

public class GroupDAO {
	private GroupTable groupTable = SchedulerService.getDatabase().getGroupTable();
	private GroupEventTable groupEventTable = SchedulerService.getDatabase().getGroupEventTable();
	private UserGroupTable userGroupTable = SchedulerService.getDatabase().getUserGroupTable();

	public GroupDAO() {

	}

	public GroupTable getGroupTable() {
		return groupTable;
	}

	public void setGroupTable(GroupTable groupTable) {
		this.groupTable = groupTable;
	}

	public GroupEventTable getGroupEventTable() {
		return groupEventTable;
	}

	public void setGroupEventTable(GroupEventTable groupEventTable) {
		this.groupEventTable = groupEventTable;
	}

	public UserGroupTable getUserGroupTable() {
		return userGroupTable;
	}

	public void setUserGroupTable(UserGroupTable userGroupTable) {
		this.userGroupTable = userGroupTable;
	}

	public Group load(String name) {
		Group group = new Group();

		group.setName(name);
		group.setOwner(groupTable.getOwnerName(name));
		group.setDesc(groupTable.getGroupDesc(name));
		group.setEvents(groupEventTable.getEvents(name));
		group.setUsers(userGroupTable.getUsers(name));

		return group;
	}

	public void save(Group group) {
		groupTable.insertGroup(group.getName(), group.getDesc(), group.getOwner());

		for (String event : group.getEvents()) {
			groupEventTable.insertGroupEvent(group.getName(), event);
		}

		for (String user : group.getUsers()) {
			userGroupTable.insertUserGroup(user, group.getName());
		}
	}
}
