package com.passwordmanager.service

import com.passwordmanager.common.CredentialRequest
import com.passwordmanager.domain.Credential
import com.passwordmanager.domain.User
import reactor.core.publisher.Mono

interface ICredentialService {

    suspend fun createCredential(credential: CredentialRequest): Mono<Credential>?

    suspend fun modifyCredential(credential: CredentialRequest): Mono<Credential>?

    suspend fun deleteCredential(credential: CredentialRequest): Int?

    suspend fun getCredentialsByUserIdEquals(userId: String): List<Credential>?
}