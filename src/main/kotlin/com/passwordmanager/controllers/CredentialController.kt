package com.passwordmanager.controllers

import com.passwordmanager.common.CredentialCreateRequest
import com.passwordmanager.common.addUserId
import com.passwordmanager.service.ICredentialService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/credentials"])
class CredentialController() {

    @Autowired
    lateinit var credentialService: ICredentialService

    @GetMapping("")
    suspend fun getCredentials() = credentialService.getCredentials()

    @GetMapping("{userId}")
    suspend fun getCredentialsOfUser(@PathVariable userId: String) = credentialService.getCredentialsByUserIdEquals(userId)

    @PostMapping("{userId}")
    suspend fun addCredentialToUser(@RequestBody credential: CredentialCreateRequest, @PathVariable userId: String) = credentialService.createCredential(credential.addUserId(userId))

    @PostMapping("/")
    suspend fun addCredentialToUserInRequest(@RequestBody credential: CredentialCreateRequest) = credentialService.createCredential(credential)

}