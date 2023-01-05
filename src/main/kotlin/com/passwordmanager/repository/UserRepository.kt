package com.passwordmanager.repository

import com.passwordmanager.domain.User
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Repository
class UserRepository (private val client: DatabaseClient) {

    suspend fun save( user:User ) =
        client.sql("INSERT INTO Users (email, password, hashed_password, firstname, lastname")
            .bind(0, user.email)
            .bind(1, user.password)
            .bind(2, user.hashedPassword)
            .bind(3, user.firstname)
            .bind(4, user.lastname)
            .then()
            .awaitFirstOrNull()

//    suspend fun delete( user: User ) =
//        client.sql()

}