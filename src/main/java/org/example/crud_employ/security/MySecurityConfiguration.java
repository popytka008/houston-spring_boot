package org.example.crud_employ.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
public class MySecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManage(){

        UserDetails details1 = User.builder()
                .username("ivan")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails details2 = User.builder()
                .username("oleg")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails details3 = User.builder()
                .username("inna")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(
                details1, details2, details3
        );
    }
}
