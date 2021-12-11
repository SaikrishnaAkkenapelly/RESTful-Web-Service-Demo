package com.sai.rest.dao;

import java.io.File;
import java.util.List;

import com.sai.rest.files.InitFiles;
import com.sai.rest.pojo.User;

public interface UserDao
{
	public final File usersFile = InitFiles.getObject().getUsersFile();;
	public List<User> getUsers();
	public List<User> getUsersDetails(String key);
	public String addUser(User user);
	public String deleteUsers(String name);
	public String updateUsers(User user);
}
