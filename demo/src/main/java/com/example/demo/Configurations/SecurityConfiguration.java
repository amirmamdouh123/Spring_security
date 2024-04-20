package com.example.demo.Configurations;

//import com.example.demo.Filters.AuthFilter;
import com.example.demo.CustomAuthenticationEntry.CustomAuthenticationEntryPoint;
import com.example.demo.Filters.AuthFilter;
import com.example.demo.Filters.TXFilter;
//import com.example.demo.Filters.UserPasswordFilter;
import com.example.demo.Services.UserService;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Autowired
    UserService userService;

//    @Autowired
//    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

//    @Autowired
//    DataSource dataSource;


//    @Bean
//    public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
//        UsernamePasswordAuthenticationFilter authenticationFilter = new UsernamePasswordAuthenticationFilter();
//        authenticationFilter.setAuthenticationManager(authenticationManager());
//        return authenticationFilter;
//    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user= User.withUsername("amir").password(passwordEncoder().encode("1234")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests(
                        (request) -> request.requestMatchers("/user/login").permitAll()
                                .anyRequest().authenticated());
//                .httpBasic(Customizer.withDefaults())
//                .logout(Customizer.withDefaults());// Redirect to login page after logout;)
//                .authenticationProvider(dao());
//                .formLogin(Customizer.withDefaults())         //add UsernamePasswordAuthenticationFilter to SecurityChain
//                .addFilterBefore(new UserPasswordFilter(), UsernamePasswordAuthenticationFilter.class);
//                .addFilterBefore(new TXFilter(),UsernamePasswordAuthenticationFilter.class);
//                .exceptionHandling((exception)-> exception.authenticationEntryPoint(customAuthenticationEntryPoint))
        return http.build();
    }

    @Bean
    public ProviderManager providerManager(){
        return new ProviderManager( dao() );
    }

    @Bean
    public AuthenticationProvider dao(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




}









