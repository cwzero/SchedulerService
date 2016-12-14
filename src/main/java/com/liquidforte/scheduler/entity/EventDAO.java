package com.liquidforte.scheduler.entity;

import com.liquidforte.scheduler.SchedulerService;
import com.liquidforte.scheduler.database.EventTable;
import com.liquidforte.scheduler.database.GroupEventTable;
import com.liquidforte.scheduler.database.UserEventTable;

public class EventDAO {
	private EventTable eventTable = SchedulerService.getDatabase().getEventTable();
	private GroupEventTable groupEventTable = SchedulerService.getDatabase().getGroupEventTable();
	private UserEventTable userEventTable = SchedulerService.getDatabase().getUserEventTable();

	public EventDAO() {

	}

	public EventTable getEventTable() {
		return eventTable;
	}

	public void setEventTable(EventTable eventTable) {
		this.eventTable = eventTable;
	}

	public GroupEventTable getGroupEventTable() {
		return groupEventTable;
	}

	public void setGroupEventTable(GroupEventTable groupEventTable) {
		this.groupEventTable = groupEventTable;
	}

	public UserEventTable getUserEventTable() {
		return userEventTable;
	}

	public void setUserEventTable(UserEventTable userEventTable) {
		this.userEventTable = userEventTable;
	}

	public void save(Event event) {
		eventTable.insertEvent(event.getName(), event.getDesc(), event.getDate(), event.getReoccurType().ordinal());

		for (String group : event.getGroups()) {
			groupEventTable.insertGroupEvent(group, event.getName());
		}

		for (String user : event.getUsers()) {
			userEventTable.insertUserEvent(user, event.getName());
		}
	}

	public Event load(String name) {
		Event event = new Event();

		event.setName(name);
		event.setDesc(eventTable.getDesc(name));
		event.setDate(eventTable.getDate(name));
		event.setReoccurType(ReoccurType.values()[eventTable.getReoccurType(name)]);
		event.setUsers(userEventTable.getUsers(name));
		event.setGroups(groupEventTable.getGroups(name));

		return event;
	}
}
