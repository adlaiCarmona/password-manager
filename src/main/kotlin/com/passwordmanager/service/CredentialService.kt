package com.passwordmanager.service

import com.passwordmanager.common.CredentialRequest
import com.passwordmanager.common.convert
import com.passwordmanager.domain.Credential
import com.passwordmanager.domain.User
import com.passwordmanager.repository.ICredentialRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CredentialService(val credentialRepository: ICredentialRepository): ICredentialService {
    override suspend fun createCredential(credential: CredentialRequest): Mono<Credential>? {
        try {
            return credentialRepository.save( credential.convert() )
        } catch ( e: IllegalArgumentException  ){
            println("Error creating Credential\n Setting to save was null\n $e")
        } catch ( e: Exception ){
            println("Error creating Credential\n $e")
        }

        return null
    }

    override suspend fun modifyCredential(credential: CredentialRequest): Mono<Credential>? {
        try {
            return credentialRepository.save( credential.convert() )
        } catch ( e: IllegalArgumentException  ){
            println("Error creating Credential\n Setting to modify was null\n $e")
        } catch ( e: Exception ){
            println("Error creating Credential\n $e")
        }

        return null
    }

    override suspend fun deleteCredential(credential: CredentialRequest): Int? {
        try {
            credentialRepository.delete( credential.convert() )
            return 0
        } catch ( e: IllegalArgumentException  ){
            println("Error deleting Credential\n Setting id was null\n $e")
        } catch ( e: Exception ){
            println("Error deleting Credential\n $e")
        }

        return null
    }

    override suspend fun getCredentialsByUserIdEquals(userId: String): List<Credential>? {
        try {
            return credentialRepository.getCredentialsByUserIdEquals(userId)
        } catch (e: Exception){
            println("Error getting Credentials of User with id:$userId\n $e")
        }
        return null
    }


}