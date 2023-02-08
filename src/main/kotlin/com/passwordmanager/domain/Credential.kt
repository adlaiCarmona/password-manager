package com.passwordmanager.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("CREDENTIALS")
data class Credential(
    @Id
    @Column("id")
    var id: String?,
    @Column("user_id")
    var userId: String,
    @Column("username")
    var username: String,
    @Column("password")
    var password: String,
    @Column("website")
    var website: String,
    @Column("url")
    var url: String,
    @Column("tags")
    var tags: String = "", // change this to array (table for tags)
    @Column("expiration_date")
    var expirationDate: LocalDateTime = LocalDateTime.now().plusDays(60),
    @Column("provider")
    var provider:String = "local",
    @Column("is_deleted")
    var isDeleted: Boolean = false
)