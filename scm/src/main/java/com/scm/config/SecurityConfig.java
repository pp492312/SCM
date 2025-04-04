package com.scm.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.scm.controllers.PageControllers;
import com.scm.services.impl.SecurityCustomUserDetailService;


/* @Configuration
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    private final PageControllers pageControllers;

    private final DaoAuthenticationProvider authenticationProvider;


    // user create and login using java code with in memory service
    // @Bean
    // public UserDetailsService userDetailsService(){

    //     UserDetails user1= User
    //     .withDefaultPasswordEncoder()
    //     .username("admin123")
    //     .password("admin123")
    //     .roles("ADMIN","USER")
    //     .build();

    //     UserDetails user2= User
    //     .withDefaultPasswordEncoder()
    //     .username("user123")
    //     .password("user123")
    //     //.roles("ADMIN","USER")
    //     .build();

    //     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,user2);
    //     return inMemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    SecurityConfig( DaoAuthenticationProvider authenticationProvider, PageControllers pageControllers, PasswordEncoder passwordEncoder) {
        this.authenticationProvider = authenticationProvider;
        this.pageControllers = pageControllers;
        this.passwordEncoder = passwordEncoder;
    }
    // SecurityConfig(PageControllers pageControllers){
    //     this.pageControllers=pageControllers;
    // }

    // Configuraition of authentication provider for spring security
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // user detail service ka object:
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // password encoder ka object:
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // configuration

        httpSecurity.authorizeHttpRequests(authorize->{
            authorize.requestMatchers("/home").permitAll();
        });
        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}  */

// chatgpt
@Configuration
public class SecurityConfig {

    private final SecurityCustomUserDetailService userDetailService;
    private final PageControllers pageControllers;

    @Autowired
    public SecurityConfig(SecurityCustomUserDetailService userDetailService, @Lazy PageControllers pageControllers) {
        this.userDetailService = userDetailService;
        this.pageControllers = pageControllers;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/home").permitAll()
                );
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

