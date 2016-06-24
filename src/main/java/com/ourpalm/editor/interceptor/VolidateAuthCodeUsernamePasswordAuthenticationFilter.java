package com.ourpalm.editor.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 扩展UsernamePasswordAuthenticationFilter加上验证码的功能
 * 
 * @author Administrator
 * 
 */
public class VolidateAuthCodeUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		System.out.println("进入了VolidateAuthCodeUsernamePasswordAuthenticationFilter" + request.getParameter("j_username"));
		// 这里可以进行验证验证码的操作

		Authentication attemptAuthentication = super.attemptAuthentication(request, response);
		System.out.println(attemptAuthentication);
		return attemptAuthentication;
	}

}