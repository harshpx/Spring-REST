package com.learn.RestWithDatabase.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfigs {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
    return https
        .csrf(customizer -> customizer.disable())
        .authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
        // .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
  }

  // @Bean
  // public UserDetailsService userDetailsService() {
  //   UserDetails user1 = User.withDefaultPasswordEncoder()
  //                           .username("harsh")
  //                           .password("h@123")
  //                           .build();
  //   return new InMemoryUserDetailsManager(user1);
  // }
  // @Bean
  // public AuthenticationProvider authenticationProvider() {
  //   DaoAuthenticationProvider authProvider =  new DaoAuthenticationProvider();
  //   authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
  //   return authProvider;
  // }
}
