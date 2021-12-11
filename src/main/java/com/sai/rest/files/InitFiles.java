package com.sai.rest.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.stream.Collectors;

public class InitFiles
{
	private static InitFiles object = null;
	private File usersFile = null;
	private HashSet<String> clientKeys = null;

	public static InitFiles getObject()
	{
		if(object != null)
		{
			return object;
		}
		else
		{
			object = new InitFiles();
			return object;
		}
	}
	
	private InitFiles()
	{
		//Private constructor to make this class as a singleton class
	}
	
	public File getUsersFile()
	{
		return usersFile;
	}
	
	public HashSet<String> getKeys()
	{
		return clientKeys;
	}
	
	public void loadUsersFiles(String path)
	{
		usersFile = new File(path);
	}
	
	public void loadClientKeys(String path)
	{
		try
		{
			clientKeys = (HashSet)Files.lines(Paths.get(path)).collect(Collectors.toSet());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}