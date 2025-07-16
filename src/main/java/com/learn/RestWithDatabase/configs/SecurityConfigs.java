package com.learn.RestWithDatabase.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigs {
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
    https
      .csrf(customizer -> customizer.disable())
      .authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
      // .formLogin(Customizer.withDefaults())
      .httpBasic(Customizer.withDefaults())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return https.build();
  }
}
