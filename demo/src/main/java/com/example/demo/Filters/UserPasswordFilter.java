//package com.example.demo.Filters;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//public class UserPasswordFilter extends AbstractAuthenticationProcessingFilter {
//
//    @Autowired
//    ProviderManager providerManager;
//
//    public UserPasswordFilter() {
//        super(new AntPathRequestMatcher("/login","POST"));
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//
//        String username=request.getParameter("username");
//        String password=request.getParameter("password");
//
//        UsernamePasswordAuthenticationToken user=new UsernamePasswordAuthenticationToken(username,password);
//
//
//
//        return providerManager.authenticate(user);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//                                            FilterChain chain, Authentication authResult)
//            throws IOException, ServletException {
//
//        // Generate a JWT token, create a session, or any post-authentication actions
//        // Here we can also redirect the user to a success page or return a success message
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//                                              AuthenticationException failed)
//            throws IOException, ServletException {
//
//        // Handle authentication failure, send an error response or redirect
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
//    }
//}
