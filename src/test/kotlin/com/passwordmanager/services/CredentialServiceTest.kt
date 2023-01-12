package com.passwordmanager.services

import com.passwordmanager.common.CredentialCreateRequest
import com.passwordmanager.common.CredentialRequest
import com.passwordmanager.common.convert
import com.passwordmanager.repository.ICredentialRepository
import com.passwordmanager.service.CredentialService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import reactor.core.publisher.Mono
import java.time.LocalDateTime

class CredentialServiceTest {
    @Mock
    lateinit var credentialRepository: ICredentialRepository

    private lateinit var credentialService: CredentialService

    private val credentialCreateRequest = CredentialCreateRequest("12345-678-90", "Test", "pass123", "www.test.com")
    private val credentialUpdateRequest = CredentialRequest("12345-678-90", "Test", "word456", "www.test2.com", LocalDateTime.now(),"0987")


    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        credentialService = CredentialService(credentialRepository)
    }

    @Test
    fun testCreateCredential(){
        runBlocking {
            `when`(credentialRepository.save(any())).then { Mono.just(credentialCreateRequest.convert()) }
            val response = credentialService.createCredential(credentialCreateRequest)

            println("Test Create Credential:\n response = $response")

            assert(response != null)
        }
    }

    @Test
    fun testUpdateCredential(){
        runBlocking {
            `when`(credentialRepository.save(any())).then { Mono.just(credentialCreateRequest.convert()) }
            val response = credentialService.modifyCredential(credentialUpdateRequest)

            println("Test Update Credential:\n response = $response")

            assert(response != null)
        }
    }

    @Test
    fun testDeleteCredential(){
        runBlocking {
            `when`(credentialRepository.delete(any())).then { Mono.just(0) }
            val response = credentialService.deleteCredential(credentialUpdateRequest)

            println("Test Update Credential:\n response = $response")

            assert(response != null)
        }
    }

    @Test
    fun testGetCredentialsOfUser(){
        runBlocking {
            val userId = "12345-678-90"
            `when`(credentialRepository.save(any())).then { Mono.just(credentialCreateRequest.convert()) }
            `when`(credentialRepository.getCredentialsByUserIdEquals(anyString())).then {  }

            credentialService.createCredential(credentialCreateRequest)
            credentialService.createCredential(credentialCreateRequest)
            credentialService.createCredential(credentialCreateRequest)

            val response = credentialService.getCredentialsByUserIdEquals(userId)

            println("Test Get Credentials of User:\n response = $response")

            assert(response != null)
        }
    }
}