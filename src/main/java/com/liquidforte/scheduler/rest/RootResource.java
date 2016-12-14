package com.liquidforte.scheduler.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.liquidforte.scheduler.entity.Link;
import com.liquidforte.scheduler.entity.ObjectList;

@Path("/")
public class RootResource {
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public ObjectList getLinks(@Context UriInfo uri) {
		ObjectList result = new ObjectList();
		
		String root = uri.getBaseUri().toString();
		String users = root + "users/";
		String groups = root + "groups/";
		String events = root + "events/";

		result.getLinks().add(new Link("self", root));
		result.getLinks().add(new Link("users", users));
		result.getLinks().add(new Link("groups", groups));
		result.getLinks().add(new Link("events", events));

		return result;
	}

}
