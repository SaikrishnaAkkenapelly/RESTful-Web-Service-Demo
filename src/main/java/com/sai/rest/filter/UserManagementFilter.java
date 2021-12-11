package com.sai.rest.filter;

import java.io.IOException;

import com.sai.rest.files.InitFiles;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//A filter is an object that is invoked at the pre processing and post processing of a request
public class UserManagementFilter implements jakarta.servlet.Filter
{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest httpServletRequest =  (HttpServletRequest) request;
		HttpServletResponse httpServletResponse =  (HttpServletResponse) response;
		
		if(InitFiles.getObject().getKeys().contains(httpServletRequest.getHeader("clientKey")))
		{
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
		else
		{
			httpServletResponse.sendError(401, "Unauthorized Request");
		}
	}
}
