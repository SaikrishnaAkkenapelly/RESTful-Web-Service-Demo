package com.sai.rest.listener;

import com.sai.rest.files.InitFiles;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

//ServletContextListener is an interface which extends EventListener
public class UserManagementListener implements ServletContextListener
{
	//ServletContextListener will have 2 default methods in it, contextInitialized and contextInitialized which were called
	//On startup of ServletContext
	//Just before shutdown of ServletContext
	
	//Receives notification that the web application initialization process is starting.
	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		//Calling the parent interface default method 
		ServletContextListener.super.contextInitialized(sce);
		//TODO add code based on application requirement
		InitFiles initFiles = InitFiles.getObject();
		ServletContext context = sce.getServletContext(); 
		
		String clientKeysPath = context.getRealPath(context.getInitParameter("clientKeysFile"));
		String usersFilePath = context.getRealPath(context.getInitParameter("usersFile"));
		
		initFiles.loadUsersFiles(usersFilePath);
		initFiles.loadClientKeys(clientKeysPath);
	}
	
	//Receives notification that the ServletContext is about to be shut down
	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		//Calling the parent interface default method
		ServletContextListener.super.contextDestroyed(sce);
		//TODO add code based on application requirement
	}
}
