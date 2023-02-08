package com.passwordmanager.repository

import com.passwordmanager.domain.Credential
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface ICredentialRepository: ReactiveCrudRepository<Credential, String> {

    fun findByUserId(userId: String): Flux<Credential>

    @Query("select * from Credentials where user_id = :userId")
    fun queryCredentialByUserId()
}