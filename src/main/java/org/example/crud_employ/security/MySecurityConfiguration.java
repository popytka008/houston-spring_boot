package org.example.crud_employ.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
public class MySecurityConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager( dataSource );
    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManage(){
//
//        UserDetails details1 = User.builder()
//                .username("ivan")
//                .password("{noop}test")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails details2 = User.builder()
//                .username("oleg")
//                .password("{noop}test")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails details3 = User.builder()
//                .username("inna")
//                .password("{noop}test")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(
//                details1, details2, details3
//        );
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests( config ->
                config
            .requestMatchers( HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
            .requestMatchers( HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
            .requestMatchers( HttpMethod.POST, "/api/employees").hasRole("MANAGER")
            .requestMatchers( HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
            .requestMatchers( HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));

        http.httpBasic( Customizer.withDefaults() );
        http.csrf(AbstractHttpConfigurer::disable);


        return http.build();
    }
}
