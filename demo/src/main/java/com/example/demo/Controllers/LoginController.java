package com.example.demo.Controllers;

import com.example.demo.Entities.LoginBody;
import com.example.demo.Entities.UserApp;
import com.example.demo.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;
import java.util.logging.Logger;

@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    ProviderManager providerManager;

    @Autowired
    UserRepo userRepo;

    Logger logger=Logger.getLogger("LoginController");


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginBody credentials) throws AuthenticationException {
        System.out.println("aa");
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        UsernamePasswordAuthenticationToken user=new UsernamePasswordAuthenticationToken(username,password);

        try {
            Authentication auth = providerManager.authenticate(user);

            SecurityContextHolder.getContext().setAuthentication(auth);
            return ResponseEntity.ok().body("Login Succeed");
        }
        catch (AuthenticationException e){
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }



}


