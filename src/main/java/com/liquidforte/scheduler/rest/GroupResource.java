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
import com.liquidforte.scheduler.entity.Group;

@Path("groups")
public class GroupResource {
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<String> getGroups(@Context UriInfo uri) {
		List<String> result = new ArrayList<>();
		result.addAll(SchedulerService.getGroupDAO().getGroupTable().getGroups());
		return result;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{name}")
	public Group getGroup(@PathParam("name") String name, @Context UriInfo uri) {
		return SchedulerService.getGroupDAO().load(name);
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public void createGroup(Group group) {
		SchedulerService.getGroupDAO().save(group);
	}
}
