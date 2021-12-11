package com.sai.rest.daoImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sai.rest.dao.UserDao;
import com.sai.rest.pojo.User;

public class UserDaoImpl implements UserDao
{
	@Override
	public List<User> getUsers()
	{
		List<User> usersList = new ArrayList<>();
		
		try(FileInputStream fileInputStream = new FileInputStream(usersFile);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);)
		{
			usersList = (List<User>) objectInputStream.readObject();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return usersList;
	}
	
	@Override
	public List<User> getUsersDetails(String key)
	{
		if(key == null)
		{
			return null;
		}
		else
		{
			List<User> users = getUsers();
			List<User> filteredUsers = new ArrayList<>();
			
			filteredUsers = users.parallelStream().filter((User existingUser) ->
			{
				return key.equals(existingUser.getFirstName()) || key.equals(existingUser.getLastName());
			}).collect(Collectors.toList());
			
			return filteredUsers;
		}
	}
	
	@Override
	public String addUser(User user)
	{
		List<User> allUsers = getUsers();
		List<User> filteredUsers = new ArrayList<>();
		
		filteredUsers = allUsers.parallelStream().filter((User exsistingUser) ->
		{
			return exsistingUser.equals(user);
		}).collect(Collectors.toList());
		
		if(filteredUsers.isEmpty())
		{
			allUsers.add(user);
			saveUsers(allUsers);
			return "User added succesfully..";
		}
		else
		{
			return "User already Exsists";
		}
	}
	
	@Override
	public String deleteUsers(String name)
	{
		List<User> allUsers = getUsers();
		List<User> allUsersCopy = new ArrayList<>(allUsers);
		
		for (User exsistingUser : allUsers)
		{
			if(exsistingUser.getFirstName().equals(name) || exsistingUser.getLastName().equals(name))
			{
				allUsersCopy.remove(exsistingUser);
			}
		}
		
		int deletedUsers = allUsers.size() - allUsersCopy.size();
		
		if(deletedUsers == 0)
		{
			return "No users found.. with name : "+name;
		}
		else
		{
			saveUsers(allUsersCopy);
			return deletedUsers+" User(s) deleted successfully..";
		}
	}
	
	@Override
	public String updateUsers(User user)
	{
		List<User> allUsers = getUsers();
		List<User> allUsersCopy = new ArrayList<>(allUsers);
		boolean isUserUpdated = false;
		
		for (User existingUser : allUsers)
		{
			if(existingUser.equals(user))
			{
				return "User already Exsists and can not update";
			}
			else if(existingUser.getFirstName().equals(user.getFirstName()) || existingUser.getLastName().equals(user.getLastName()))
			{
				allUsersCopy.remove(existingUser);
				allUsersCopy.add(user);
				isUserUpdated = true;
			}
		}
		
		if(isUserUpdated)
		{
			saveUsers(allUsersCopy);
			return " Users updated successfully..";
		}
		else
		{
			return "No user found with the given details";
		}
	}
	
	private void saveUsers(List<User> usersList)
	{
		try(FileOutputStream fileOutputStream = new FileOutputStream(usersFile);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);)
		{
			objectOutputStream.writeObject(usersList);
			objectOutputStream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
