package com.passwordmanager.service

import com.passwordmanager.common.CredentialCreateRequest
import com.passwordmanager.common.CredentialRequest
import com.passwordmanager.common.convert
import com.passwordmanager.domain.Credential
import com.passwordmanager.repository.ICredentialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CredentialService/*(val credentialRepository: ICredentialRepository)*/: ICredentialService {

    @Autowired
    lateinit var credentialRepository: ICredentialRepository

    private val logger = KotlinLogging.logger {  }

    override suspend fun createCredential(credential: CredentialCreateRequest): Mono<Credential>? {
        try {
            return credentialRepository.save( credential.convert() )
        } catch ( e: IllegalArgumentException  ){
            logger.error { "Error creating Credential\n Setting to save was null\n $e" }
        } catch ( e: Exception ){
            logger.error { "Error creating Credential\n $e" }
        }
        return null
    }

    override suspend fun modifyCredential(credential: CredentialRequest): Mono<Credential>? {
        try {
            return credentialRepository.save( credential.convert() )
        } catch ( e: IllegalArgumentException  ){
            logger.error { "Error creating Credential\n Setting to modify was null\n $e" }
        } catch ( e: Exception ){
            logger.error { "Error creating Credential\n $e" }
        }
        return null
    }

    override suspend fun deleteCredential(credential: CredentialRequest): Int? {
        try {
//            val response = withContext(Dispatchers.IO) {
                credentialRepository.delete(credential.convert())//.block()
//            }
//            println("Debug deleteCredential response:\n $response")
            return 0
        } catch ( e: IllegalArgumentException  ){
            logger.error { "Error deleting Credential\n Setting id was null\n $e" }
        } catch ( e: Exception ){
            logger.error { "Error deleting Credential\n $e" }
        }

        return null
    }

    override suspend fun getCredentials(): Flux<Credential>? {
        try {
            return credentialRepository.findAll()
        } catch (e: IllegalArgumentException){
            logger.error { "Error getting Credentials\n $e" }
        }
        return null
    }

    override suspend fun getCredentialsByUserIdEquals(userId: String): Any? {
        try {
            return credentialRepository.findByUserId(userId)
        } catch (e: IllegalArgumentException){
            println("Entered in Exception")
            logger.error { "Error getting Credentials of User with id:$userId\n $e" }
        }
        return null
    }


}