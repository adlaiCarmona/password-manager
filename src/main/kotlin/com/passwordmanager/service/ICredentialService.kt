package com.passwordmanager.service

import com.passwordmanager.common.CredentialCreateRequest
import com.passwordmanager.common.CredentialRequest
import com.passwordmanager.domain.Credential
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.Flux

@Service
interface ICredentialService {

    suspend fun createCredential(credential: CredentialCreateRequest): Mono<Credential>?

    suspend fun modifyCredential(credential: CredentialRequest): Mono<Credential>?

    suspend fun deleteCredential(credential: CredentialRequest): Int?

    suspend fun getCredentialsByUserIdEquals(userId: String): Flux<Credential>?

    suspend fun getCredentials(): Flux<Credential>?
}