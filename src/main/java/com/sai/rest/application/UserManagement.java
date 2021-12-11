package com.sai.rest.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.sai.rest.binder.UserManagementBinder;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.core.Context;

//Here we need an application class to configure our rest application, so we have created this class and this is the startup class for your application
//An application class should extend either Application class or ResourceConfig class to configure
//The simpler way is to extend ResourceConfig which is actually a child class of Application class
//This application class constructor is called for the first request and configuration is set thats it no more calls for this constructor

public class UserManagement extends ResourceConfig
{
	//An object of ServletContext is created by the web container at time of deploying the project(generally created while initializing servlet)
	//This object can be used to get configuration information from web.xml file.
	//There is only one ServletContext object per web application.
	//By using @Context annotation we are injecting ServletContext object   
	public UserManagement(@Context ServletContext servletContext)
	{
		//Configuring the packages
		super.packages("com");
		//Configuring the newly created binder instance, it's configure method is called internally for configuration
		super.register(new UserManagementBinder());
	}
}
