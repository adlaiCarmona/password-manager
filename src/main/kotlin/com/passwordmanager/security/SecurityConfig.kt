//package com.passwordmanager.security
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.security.web.authentication.logout.LogoutHandler
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher
//
//
//// from: https://auth0.com/docs/quickstart/backend/java-spring-security5/01-authorization
//@Configuration
//@EnableWebSecurity
//class SecurityConfig {
//
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        return http.oauth2Login()
//            .and().build();
////        http.authorizeRequests()
////            .requestMatchers("/css/**").permitAll()
////            .requestMatchers("/user/**").hasAuthority("ROLE_USER")
////            .and()
////            .formLogin().loginPage("/log-in")
////        return http.build()
//    }
//}