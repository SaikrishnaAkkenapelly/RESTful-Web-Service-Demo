package com.sai.rest.binder;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.sai.rest.dao.UserDao;
import com.sai.rest.daoImpl.UserDaoImpl;

//We need our own binder class to bind objects to the references that we are injecting as dependency injection in our resource class
//This class has to be created by extending AbstractBinder or by implementing Binder class
public class UserManagementBinder extends AbstractBinder
{
	//We need to override the configure method to configure our own custom binding
	@Override
	protected void configure()
	{
		//We are binding daoimpl class to dao interface, such that we get a daoimpl class object inside dao reference and get's injected into resource
		bind(UserDaoImpl.class).to(UserDao.class);
	}
}
