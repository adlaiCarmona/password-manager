package com.passwordmanager.controllers

import com.nimbusds.jose.Algorithm
import com.passwordmanager.common.CredentialRequest
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class SiteController {

    @GetMapping("/")
    fun index(): String {
        return "overview"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/signup")
    fun signup(): String {
        return "signup"
    }

    @GetMapping("/overview")
    fun overview(): String {
        return "overview"
    }

    @GetMapping("/generator")
    fun generator(): String {
        return "generator"
    }

    @GetMapping("/account")
    fun account() = "account"

//    @PostMapping("/login/oauth2/code/google")
//    fun loginGoogle(@RequestBody jwt: Jwt): String {
//        val chunks = null
//
//        return "overview"
//    }
}