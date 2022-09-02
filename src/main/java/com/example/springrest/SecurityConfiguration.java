package com.example.springrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests((httpz)->
                        httpz.antMatchers("/users*").hasRole("ROLE1")
                                .antMatchers("/users/*").hasRole("ROLE2")
        ).httpBasic();

        return http.build();

    }
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("1")
                .roles("ROLE1")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("user2")
                .password("2")
                .roles("ROLE2")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
