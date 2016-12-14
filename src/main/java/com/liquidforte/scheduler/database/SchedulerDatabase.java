package com.liquidforte.scheduler.database;

import java.util.Properties;

import org.hsqldb.jdbc.JDBCDataSource;
import org.hsqldb.jdbc.JDBCDataSourceFactory;
import org.skife.jdbi.v2.DBI;

public class SchedulerDatabase {
	private JDBCDataSource dataSource;
	private DBI dbi;

	private EventTable eventTable;
	private GroupEventTable groupEventTable;
	private GroupTable groupTable;
	private UserEventTable userEventTable;
	private UserGroupTable userGroupTable;
	private UserTable userTable;

	public SchedulerDatabase() {
		Properties dsProps = new Properties();
		dsProps.setProperty("url", "jdbc:hsqldb:file:db/scheduler/scheduler;create=true");
		dsProps.setProperty("username", "administrator");
		dsProps.setProperty("password", "administrator");
		try {
			dataSource = (JDBCDataSource) JDBCDataSourceFactory.createDataSource(dsProps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dbi = new DBI(dataSource);

		createTableHandles();
		createTables();
	}

	public void createTableHandles() {
		eventTable = dbi.open(EventTable.class);
		userTable = dbi.open(UserTable.class);
		groupTable = dbi.open(GroupTable.class);

		groupEventTable = dbi.open(GroupEventTable.class);
		userEventTable = dbi.open(UserEventTable.class);
		userGroupTable = dbi.open(UserGroupTable.class);
	}

	public void createTables() {
		eventTable.createEventTable();
		userTable.createUserTable();
		groupTable.createGroupTable();

		groupEventTable.createGroupEventTable();
		userEventTable.createUserEventTable();
		userGroupTable.createUserGroupTable();
	}

	public EventTable getEventTable() {
		return eventTable;
	}

	public GroupEventTable getGroupEventTable() {
		return groupEventTable;
	}

	public GroupTable getGroupTable() {
		return groupTable;
	}

	public UserEventTable getUserEventTable() {
		return userEventTable;
	}

	public UserGroupTable getUserGroupTable() {
		return userGroupTable;
	}

	public UserTable getUserTable() {
		return userTable;
	}
}
