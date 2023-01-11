package com.passwordmanager.repository

import com.passwordmanager.domain.Credential
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface ICredentialRepository: ReactiveCrudRepository<Credential, String> {

    @Query("select * from Credentials where user_id = :userId")
    fun getCredentialsByUserIdEquals(@Param("userId") id: String)

    @Query("select * from Credentials where user_id = $1")
    fun findByUserId(userId: String)

    @Query("select * from Credentials u where u.user_id = :userId")
    fun queryCredentialByUserId(userId: String)
}