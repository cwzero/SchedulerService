package com.liquidforte.scheduler.database;

import java.util.List;
import java.util.Set;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface UserTable {
	@SqlUpdate("CREATE TABLE User (User_ID INTEGER PRIMARY KEY IDENTITY, Name VARCHAR(16), Password_Hash VARCHAR(256), Password_Salt VARCHAR(256))")
	void createUserTable();

	@SqlUpdate("INSERT INTO User (Name, Password_Hash, Password_Salt) VALUES (:name, :password_hash, :password_salt)")
	void insertUser(@Bind("name") String name, @Bind("password_hash") String passwordHash,
			@Bind("password_salt") String passwordSalt);

	@SqlUpdate("DELETE FROM User WHERE Name = :name")
	void deleteUser(@Bind("name") String name);
	
	@SqlQuery("SELECT User_ID FROM User WHERE Name = :name")
	int getUserID(@Bind("name") String name);
	
	@SqlQuery("SELECT Password_Hash FROM User WHERE Name = :name")
	String getPasswordHash(@Bind("name") String name);
	
	@SqlQuery("SELECT Password_Salt FROM User WHERE Name = :name")
	String getPasswordSalt(@Bind("name") String name);
	
	@SqlQuery("SELECT User_ID FROM User")
	List<Integer> getUserIDs();
	
	@SqlQuery("SELECT Name FROM User")
	Set<String> getUsers();

	void close();
}
