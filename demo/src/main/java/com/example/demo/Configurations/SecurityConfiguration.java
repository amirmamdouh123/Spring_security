package com.example.demo.Configurations;

//import com.example.demo.Filters.AuthFilter;
import com.example.demo.Filters.JwtFilter;
//import com.example.demo.Filters.UserPasswordFilter;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Autowired
    UserService userService;


    @Autowired
    JwtFilter jwtFilter;


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
                                .anyRequest().authenticated())
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
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









