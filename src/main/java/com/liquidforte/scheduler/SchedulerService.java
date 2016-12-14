package com.liquidforte.scheduler;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.liquidforte.scheduler.database.SchedulerDatabase;
import com.liquidforte.scheduler.entity.EventDAO;
import com.liquidforte.scheduler.entity.GroupDAO;
import com.liquidforte.scheduler.entity.UserDAO;

@ApplicationPath("/api/")
public class SchedulerService extends ResourceConfig {
	private static final SchedulerDatabase database = new SchedulerDatabase();
	private static final EventDAO eventDao = new EventDAO();
	private static final GroupDAO groupDao = new GroupDAO();
	private static final UserDAO userDao = new UserDAO();

	public static SchedulerDatabase getDatabase() {
		return database;
	}
	
	public static EventDAO getEventDAO() {
		return eventDao;
	}
	
	public static GroupDAO getGroupDAO() {
		return groupDao;
	}
	
	public static UserDAO getUserDAO() {
		return userDao;
	}

	public SchedulerService() {
		register(JacksonFeature.class);
		register(JacksonJaxbJsonProvider.class);
		packages("com.liquidforte.scheduler");
	}
}
