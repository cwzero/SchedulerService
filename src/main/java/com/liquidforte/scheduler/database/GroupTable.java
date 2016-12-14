package com.liquidforte.scheduler.database;

import java.util.List;
import java.util.Set;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface GroupTable {
	@SqlUpdate("CREATE TABLE \"Group\" (Group_ID INTEGER PRIMARY KEY IDENTITY, Name VARCHAR(256) NOT NULL UNIQUE, Desc VARCHAR(256), Owner_User_ID INTEGER NOT NULL, FOREIGN KEY (Owner_User_ID) REFERENCES User(User_ID))")
	void createGroupTable();
	
	@SqlUpdate("INSERT INTO \"Group\" (Name, Desc, Owner_User_ID) VALUES (:name, :desc, (SELECT User_ID FROM User u WHERE Name = :owner))")
	void insertGroup(@Bind("name") String name, @Bind("desc") String desc, @Bind("owner") String owner);

	@SqlUpdate("DELETE FROM \"Group\" WHERE Name = :name")
	void deleteGroup(@Bind("name") String name);
	
	@SqlQuery("SELECT Group_ID FROM \"Group\" WHERE Name = :name")
	int getGroupID(@Bind("name") String name);
	
	@SqlQuery("SELECT Desc FROM \"Group\" WHERE Name = :name")
	String getGroupDesc(@Bind("name") String name);
	
	@SqlQuery("SELECT Owner_User_ID FROM \"Group\" WHERE Name = :name")
	int getOwnerUserId(@Bind("name") String name);
	
	@SqlQuery("SELECT u.Name FROM \"Group\" g, User u, Group_User gu WHERE g.Name = :name AND g.Group_ID = gu.Group_ID AND u.User_ID = gu.User_ID")
	String getOwnerName(@Bind("name") String name);
	
	@SqlQuery("SELECT Group_ID FROM \"Group\"")
	List<Integer> getGroupIDs();
	
	@SqlQuery("SELECT Name FROM \"Group\"")
	Set<String> getGroups();
	
	void close();
}