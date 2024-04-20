package com.example.demo.CustomAuthenticationEntry;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	static Logger logger = Logger.getLogger(CustomAuthenticationEntryPoint.class.getName());
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) throws IOException, ServletException {

		logger.info("CustomAuthenticationEntryPoint is executed");
		// Send a 401 Unauthorized response with a custom message
		response.sendRedirect("/login"); // Redirect to login page

	}

}