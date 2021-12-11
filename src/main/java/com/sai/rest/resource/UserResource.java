package com.sai.rest.resource;

import java.util.List;

import org.glassfish.jersey.server.ManagedAsync;

import com.sai.rest.dao.UserDao;
import com.sai.rest.pojo.User;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/userResource")

public class UserResource
{
	//@Inject is used to inject our own objects into this class, anyway object needs to be binded explicitly on startup
	@Inject UserDao userDao;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/ping")
	public void ping(@Suspended final AsyncResponse asyncResponse)
	{
		asyncResponse.resume("Ping Successfull");
	}
	
	@GET
	@ManagedAsync
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/usersAsync")
	public void getUsersAsync(@Suspended final AsyncResponse asyncResponse)
	{
		asyncResponse.resume(Response.status(200).entity(userDao.getUsers()).build());
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/usersSync")
	public List<User> getUsersSync()
	{
		return userDao.getUsers();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/users/{key}")
	public void getUsersDetails(@PathParam("key") String key,@Suspended final AsyncResponse asyncResponse)
	{
		asyncResponse.resume(userDao.getUsersDetails(key));
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/createUser")
	public void createUser(User user,@Suspended final AsyncResponse asyncResponse)
	{
		asyncResponse.resume(userDao.addUser(user));
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteUsers/{key}")
	public void deleteUsers(@PathParam("key") String key,@Suspended final AsyncResponse asyncResponse)
	{
		asyncResponse.resume(userDao.deleteUsers(key));
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateUsers")
	public void updateUsers(User user,@Suspended final AsyncResponse asyncResponse)
	{
		asyncResponse.resume(userDao.updateUsers(user));
	}
}