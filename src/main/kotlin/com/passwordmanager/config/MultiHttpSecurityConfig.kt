package com.passwordmanager.config

import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class MultiHttpSecurityConfig {
    @Bean
    fun userDetailsService(): UserDetailsService {
        val users = User.withDefaultPasswordEncoder()
        val manager = InMemoryUserDetailsManager()
        manager.createUser(users.username("user").password("password").roles("USER").build())
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build())
        return manager
    }

    @Order(1)
    @Bean
    fun apiFilterChain(http: HttpSecurity): SecurityFilterChain {
        //TODO add authentication to api?
//        http.antMatcher("/api/**").authorizeRequests { authorize -> authorize.anyRequest().hasRole("ADMIN") }
//            .httpBasic()
        http
            .csrf().disable()
            .antMatcher("/api/**")
            .authorizeRequests { authorize -> authorize.anyRequest().permitAll() }
        // CSRF token disabled for ease of use, but probably should be implemented; Talked a bit in:
        // https://stackoverflow.com/questions/19468209/spring-security-configuration-http-403-error

        return http.build()
    }

    @Order(2)
    @Bean
    fun publicFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.antMatcher("/").authorizeRequests { authorize -> authorize.anyRequest().permitAll() }
            .httpBasic()

        return http.build()
    }

    // Normal log in; TODO find why there is no google account now
    @Bean
    fun formLoginFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorize -> authorize.anyRequest().authenticated() }
            .formLogin(Customizer.withDefaults())

        return http.build()
    }

//    @Bean
//    fun configure(http: HttpSecurity) {
//        http.httpBasic().disable();
//        http.authorizeRequests().anyRequest().authenticated();
//    }
}