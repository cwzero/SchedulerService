package com.liquidforte.scheduler.database;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface EventTable {
	@SqlUpdate("CREATE TABLE Event (Event_ID INTEGER PRIMARY KEY IDENTITY, Name VARCHAR(256) NOT NULL UNIQUE, Desc VARCHAR(256), Date Datetime NOT NULL, Reoccur_Type INTEGER NOT NULL)")
	void createEventTable();

	@SqlUpdate("INSERT INTO Event (Name, Desc, Date, Reoccur_Type) VALUES (:name, :desc, :date, :reoccur_type)")
	void insertEvent(@Bind("name") String name, @Bind("desc") String desc, @Bind("date") Date date, @Bind("reoccur_type") int reoccurType);

	@SqlUpdate("DELETE FROM Event WHERE Name = :name")
	void deleteEvent(@Bind("name") String name);
	
	@SqlQuery("SELECT Desc FROM Event WHERE Name = :name")
	String getDesc(@Bind("name") String name);
	
	@SqlQuery("SELECT Date FROM Event WHERE Name = :name")
	Date getDate(@Bind("name") String name);
	
	@SqlQuery("SELECT Reoccur_Type FROM Event WHERE Name = :name")
	int getReoccurType(@Bind("name") String name);
	
	@SqlQuery("SELECT Event_ID FROM Event")
	List<Integer> getEventIDs();
	
	@SqlQuery("SELECT Name FROM Event")
	Set<String> getEvents();
	
	void close();
}
