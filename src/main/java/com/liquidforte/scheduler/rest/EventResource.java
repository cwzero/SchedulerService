package com.liquidforte.scheduler.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.liquidforte.scheduler.SchedulerService;
import com.liquidforte.scheduler.entity.Event;

@Path("events")
public class EventResource {
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<String> getEvents(@Context UriInfo uri) {
		List<String> result = new ArrayList<>();
		result.addAll(SchedulerService.getEventDAO().getEventTable().getEvents());
		return result;
	}
	
	@GET
	@Path("{name}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Event getEvent(@PathParam("name") String name, @Context UriInfo uri) {
		return SchedulerService.getEventDAO().load(name);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public void createEvent(Event event) {
		SchedulerService.getEventDAO().save(event);
	}
}
