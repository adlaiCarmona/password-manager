package com.passwordmanager.service

import com.passwordmanager.common.UserCreateRequest
import com.passwordmanager.common.UserRequest
import com.passwordmanager.domain.Credential
import com.passwordmanager.domain.Setting
import com.passwordmanager.domain.User
import reactor.core.publisher.Mono

interface IUserService {

    suspend fun createUser(user: UserCreateRequest): Mono<User>?

    suspend fun modifyUser(user: UserCreateRequest): Mono<User>?

    suspend fun getUser(userId: String): Mono<User>?

    suspend fun deleteUser(user: UserRequest): Int?

    suspend fun deleteUserById(userId: String): Int?

    suspend fun getCredentials(userId: String): List<Mono<Credential>>?

    fun getSettings(userId: String): Setting

    suspend fun getSettingPasswordDuration(userId: String): Int?
}