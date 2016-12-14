package com.liquidforte.scheduler.database;

import java.util.Set;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface UserEventTable {
	@SqlUpdate("CREATE TABLE User_Event (User_ID INTEGER NOT NULL, Event_ID INTEGER NOT NULL, FOREIGN KEY (User_ID) REFERENCES User(User_ID), FOREIGN KEY (Event_ID) REFERENCES Event(Event_ID), PRIMARY KEY (User_ID, Event_ID))")
	void createUserEventTable();

	@SqlUpdate("INSERT INTO User_Event (User_ID, Event_ID) VALUES ((SELECT User_ID FROM User WHERE Name = :user), (SELECT Event_ID FROM Event WHERE Name = :event))")
	void insertUserEvent(@Bind("user") String user, @Bind("event") String event);
	
	@SqlQuery("SELECT u.Name FROM Event e, User u, User_Event ue WHERE e.Name = :event AND ue.User_ID = u.User_ID AND ue.Event_ID = e.Event_ID")
	Set<String> getUsers(@Bind("event") String event);

	@SqlQuery("SELECT e.Name FROM Event e, User u, User_Event ue WHERE u.Name = :user AND ue.User_ID = u.User_ID AND ue.Event_ID = e.Event_ID")
	Set<String> getEvents(@Bind("user") String user);

	void close();
}
