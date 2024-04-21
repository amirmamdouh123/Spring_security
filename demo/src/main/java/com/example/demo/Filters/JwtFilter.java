package com.example.demo.Filters;

import com.example.demo.Entities.UserApp;
import com.example.demo.Services.JwtService;
import com.example.demo.Services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JwtFilter extends OncePerRequestFilter {

    Logger log=Logger.getLogger("JwtFilter");

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String jwt;

        // Skip JWT validation for /user/login endpoint
        if (request.getRequestURI().equals("/user/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("UnAuthorized Sorry..");
            return;
        }
        jwt =authHeader.substring(7); //token

        System.out.println(jwtService.extractUsername(jwt));

        String jwtUsername= jwtService.extractUsername(jwt);
        UserApp appUser = (UserApp)userService.loadUserByUsername(jwtUsername);
        boolean isValid= jwtService.TokenIsValid(jwt,appUser.getUsername());
        if(isValid){
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    appUser.getUsername(),
                    appUser.getPassword(),
                    appUser.getAuthorities()
            );
//            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request,response);
    }
}
