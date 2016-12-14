package com.liquidforte.scheduler.entity;

import com.liquidforte.scheduler.SchedulerService;
import com.liquidforte.scheduler.database.UserEventTable;
import com.liquidforte.scheduler.database.UserGroupTable;
import com.liquidforte.scheduler.database.UserTable;

public class UserDAO {
	private UserEventTable userEventTable = SchedulerService.getDatabase().getUserEventTable();
	private UserGroupTable userGroupTable = SchedulerService.getDatabase().getUserGroupTable();
	private UserTable userTable = SchedulerService.getDatabase().getUserTable();

	public UserDAO() {

	}

	public UserTable getUserTable() {
		return userTable;
	}

	public void setUserTable(UserTable userTable) {
		this.userTable = userTable;
	}

	public UserEventTable getUserEventTable() {
		return userEventTable;
	}

	public void setUserEventTable(UserEventTable userEventTable) {
		this.userEventTable = userEventTable;
	}

	public UserGroupTable getUserGroupTable() {
		return userGroupTable;
	}

	public void setUserGroupTable(UserGroupTable userGroupTable) {
		this.userGroupTable = userGroupTable;
	}

	public User load(String name) {
		User user = new User();

		user.setName(name);
		user.setPasswordSalt(userTable.getPasswordSalt(name));
		user.setPasswordHash(userTable.getPasswordHash(name));
		user.setEvents(userEventTable.getEvents(name));
		user.setGroups(userGroupTable.getGroups(name));

		return user;
	}

	public void save(User user) {
		userTable.insertUser(user.getName(), user.getPasswordHash(), user.getPasswordSalt());

		for (String event : user.getEvents()) {
			userEventTable.insertUserEvent(user.getName(), event);
		}
		
		for (String group : user.getGroups()) {
			userGroupTable.insertUserGroup(user.getName(), group);
		}
	}
}
