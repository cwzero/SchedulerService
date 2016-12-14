package com.liquidforte.scheduler.database;

import java.util.Set;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface UserGroupTable {
	@SqlUpdate("CREATE TABLE User_Group (User_ID INT NOT NULL, Group_ID INT NOT NULL, FOREIGN KEY (User_ID) REFERENCES User (User_ID), FOREIGN KEY (Group_ID) REFERENCES \"Group\"(Group_ID), PRIMARY KEY (User_ID, Group_ID))")
	void createUserGroupTable();

	@SqlUpdate("INSERT INTO User_Group (User_ID, Group_ID) VALUES ((SELECT User_ID FROM User WHERE Name = :user_name), (SELECT Group_ID FROM \"Group\" WHERE Name = :group_name))")
	void insertUserGroup(@Bind("user_name") String user, @Bind("group_name") String group);

	@SqlQuery("SELECT u.Name FROM User u, \"Group\" g, User_Group ug WHERE g.Name = :group_name AND ug.User_ID = u.User_ID AND ug.Group_ID = g.Group_ID")
	Set<String> getUsers(@Bind("group_name") String group);

	@SqlQuery("SELECT g.Name FROM User u, \"Group\" g, User_Group ug WHERE u.Name = :user_name AND ug.User_ID = u.User_ID AND ug.Group_ID = g.Group_ID")
	Set<String> getGroups(@Bind("user_name") String user);

	void close();
}
