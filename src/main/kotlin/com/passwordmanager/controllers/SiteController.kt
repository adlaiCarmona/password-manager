package com.passwordmanager.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.ui.Model;

@Controller
class SiteController {
//    @GetMapping("/")
//    fun home(model: Model, @AuthenticationPrincipal principal: OidcUser) = "index"

    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @GetMapping("/user/index")
    fun userIndex(): String {
        return "user/index"
    }

    @GetMapping("/log-in")
    fun login(): String {
        return "login"
    }
}