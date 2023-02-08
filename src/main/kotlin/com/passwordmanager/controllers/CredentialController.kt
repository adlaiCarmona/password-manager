package com.passwordmanager.controllers

import com.passwordmanager.common.CredentialCreateRequest
import com.passwordmanager.common.CredentialRequest
import com.passwordmanager.common.UserRequest
import com.passwordmanager.common.addUserId
import com.passwordmanager.service.ICredentialService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/credentials"])
class CredentialController() {

    @Autowired
    lateinit var credentialService: ICredentialService

    @GetMapping("/", produces = [APPLICATION_JSON_VALUE])
    suspend fun getCredentials() = credentialService.getCredentials()

    @GetMapping("{userId}", produces = [APPLICATION_JSON_VALUE])
    suspend fun getCredentialsOfUser(@PathVariable userId: String) = credentialService.getCredentialsByUserIdEquals(userId)

    @PostMapping("{userId}")
    suspend fun addCredentialToUser(@RequestBody credential: CredentialCreateRequest, @PathVariable userId: String) = credentialService.createCredential(credential.addUserId(userId))

    @PostMapping("/")
    suspend fun addCredentialToUserInRequest(@RequestBody credential: CredentialCreateRequest) = credentialService.createCredential(credential)

    @PatchMapping("/")
    suspend fun patchCredential(@RequestBody credential: CredentialRequest) = credentialService.modifyCredential(credential)

    @PostMapping("/delete")
    suspend fun deleteCredential(@RequestBody credential: CredentialRequest) = credentialService.deleteCredential(credential)
}