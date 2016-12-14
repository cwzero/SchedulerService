package com.liquidforte.scheduler.database;

import java.util.Set;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface GroupEventTable {
	@SqlUpdate("CREATE TABLE Group_Event (Group_ID INTEGER NOT NULL, Event_ID INTEGER NOT NULL, FOREIGN KEY (Group_ID) REFERENCES \"Group\"(Group_ID), FOREIGN KEY (Event_ID) REFERENCES Event(Event_ID), PRIMARY KEY (Group_ID, Event_ID))")
	void createGroupEventTable();
	
	@SqlUpdate("INSERT INTO Group_Event (Group_ID, Event_ID) VALUES((SELECT Group_ID FROM \"Group\" WHERE Name = :group_name), (SELECT Event_ID FROM EVENT WHERE Name = :event_name))")
	void insertGroupEvent(@Bind("group_name") String group, @Bind("event_name") String event);
	
	@SqlQuery("SELECT e.Name FROM Event e, \"Group\" g, Group_Event ge WHERE g.Name = :group_name AND g.Group_ID = ge.Group_ID AND e.Event_ID = ge.Event_ID")
	Set<String> getEvents(@Bind("group_name") String group);
	
	@SqlQuery("SELECT g.Name FROM Event e, \"Group\" g, Group_Event ge WHERE e.Name = :event_name AND g.Group_ID = ge.Group_ID AND e.Event_ID = ge.Event_ID")
	Set<String> getGroups(@Bind("event_name") String event);
	
	void close();
}
