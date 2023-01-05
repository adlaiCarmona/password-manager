package com.passwordmanager.common

import com.passwordmanager.domain.User
import java.sql.Timestamp
import java.time.LocalDateTime

data class UserCreateRequest(
    val email: String,
    val password: String,
    val firstname: String,
    val lastname: String
)

data class UserRequest(
    override val email: String,
    override val password: String,
    override val firstname: String,
    override val lastname: String,
    override val id: String? = null,
    override val settingsId: String? = null,
    override val hashedPassword: Int? = null,
    override val isDeleted: Boolean = false,
    override val dateCreated: LocalDateTime? = null,
    override val lastLogin: LocalDateTime? = null,
    override val passwordDuration: Int?
) : IUserRequest

interface IUserRequest{
    val id: String?
    val settingsId: String?
    val email: String?
    val password: String?
    val hashedPassword: Int?
    val firstname: String?
    val lastname: String?
    val lastLogin: LocalDateTime?
    val isDeleted: Boolean?
    val dateCreated: LocalDateTime?
    val passwordDuration: Int?
}

fun IUserRequest.convert(): User{
    return User(
        this.id!!,
        //this.settingsId!!,
        this.email!!,
        this.password!!,
        this.password!!.hashCode(),
        this.firstname!!,
        this.lastname!!,
        this.dateCreated!!,
        this.dateCreated!!,
        this.isDeleted!!,
        this.passwordDuration!!
    )
}

fun UserCreateRequest.convert(): User{
    return User(
        null,
        this.email,
        this.password,
        this.password.hashCode(),
        this.firstname,
        this.lastname
    )
}