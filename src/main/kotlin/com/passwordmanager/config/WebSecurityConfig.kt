//package com.passwordmanager.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.security.config.Customizer
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.security.web.SecurityFilterChain
//
//@EnableWebSecurity
//class WebSecurityConfig {
//    private val WHITE_LIST_URLS = arrayOf(
//        "/hello",
//        "/register",
//        "/verifyRegistration*",
//        "/resendVerifyToken*"
//    )
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder? {
//        return BCryptPasswordEncoder(11)
//    }
//
//    @Bean
//    @Throws(Exception::class)
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
//        http
//            .cors()
//            .and()
//            .csrf()
//            .disable()
//            .authorizeHttpRequests()
//            .antMatchers(*WHITE_LIST_URLS).permitAll()
//            .antMatchers("/api/**").authenticated()
//            .and()
//            .oauth2Login { oauth2login: OAuth2LoginConfigurer<HttpSecurity?> ->
//                oauth2login.loginPage(
//                    "/oauth2/authorization/api-client-oidc"
//                )
//            }
//            .oauth2Client(Customizer.withDefaults())
//        return http.build()
//    }
//}