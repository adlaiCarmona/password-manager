package com.passwordmanager.repository

import com.passwordmanager.domain.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface IUserRepository: ReactiveCrudRepository<User, String> {

    fun findByEmail(email: String): Mono<User>

    @Query("select * from Users u where u.id = :userId")
    fun queryUserByIdEquals(userId: String)

}