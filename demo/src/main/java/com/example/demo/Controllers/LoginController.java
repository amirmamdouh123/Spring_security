package com.example.demo.Controllers;

import com.example.demo.Entities.LoginBody;
import com.example.demo.Services.JwtService;
import com.example.demo.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    ProviderManager providerManager;

    @Autowired
    JwtService jwtService;

    Logger logger=Logger.getLogger("LoginController");


    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginBody credentials) throws AuthenticationException {
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        UsernamePasswordAuthenticationToken user=new UsernamePasswordAuthenticationToken(username,password);

        try {
            Authentication principles = providerManager.authenticate(user);

            String token = jwtService.generateToken(principles.getName());

            SecurityContextHolder.getContext().setAuthentication(principles);

            return ResponseEntity.ok().body(token);
        }
        catch (AuthenticationException e){
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }



}


