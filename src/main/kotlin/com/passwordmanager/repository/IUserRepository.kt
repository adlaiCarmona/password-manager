package com.passwordmanager.repository

import com.passwordmanager.domain.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface IUserRepository: ReactiveCrudRepository<User, String> {
}