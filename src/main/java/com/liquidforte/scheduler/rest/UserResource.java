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
import com.liquidforte.scheduler.entity.User;

@Path("users")
public class UserResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getUsers(@Context UriInfo uri) {
		List<String> result = new ArrayList<>();
		result.addAll(SchedulerService.getUserDAO().getUserTable().getUsers());
		return result;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{name}")
	public User getUser(@PathParam("name") String name, @Context UriInfo uri) {
		return SchedulerService.getUserDAO().load(name);
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public void createUser(User user) {
		SchedulerService.getUserDAO().save(user);
	}
}
