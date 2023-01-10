package com.passwordmanager.repository

import com.passwordmanager.domain.Credential
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface ICredentialRepository: ReactiveCrudRepository<Credential, String> {

    @Query("select * from Credentials where user_id = :userId")
    fun getCredentialsByUserIdEquals(@Param("userId") id: String): List<Mono<Credential>>?
}