package com.passwordmanager.repository

import com.passwordmanager.domain.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface IUserRepository: ReactiveCrudRepository<User, String> {

    @Query("select * from Users u where u.id = :userId")
    fun queryUserByIdEquals(userId: String)

}