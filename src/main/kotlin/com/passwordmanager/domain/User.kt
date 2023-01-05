package com.passwordmanager.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("USERS")
data class User(
    @Id
    @Column("id")
    var id: String?,
//    @Column("settings_id")
//    var settingsId: String,
    @Column("email")
    var email: String,
    @Column("password")
    var password: String,
    @Column("hashed_password")
    var hashedPassword: Int,
    @Column("firstname")
    var firstname: String,
    @Column("lastname")
    var lastname: String,
    @Column("date_created")
    var dateCreated: LocalDateTime = LocalDateTime.now(),
    @Column("last_login")
    var lastLogin: LocalDateTime = LocalDateTime.now(),
    @Column("is_deleted")
    var isDeleted: Boolean? = false,
    @Column("password_duration")
    var passwordDuration: Int? = 60
)
