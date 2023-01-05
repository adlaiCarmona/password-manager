package com.passwordmanager.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp

@Table("CREDENTIALS")
data class Credential(
    @Id
    @Column("id")
    var id: String,
    @Column("user_id")
    var userId: String,
    @Column("username")
    var username: String,
    @Column("password")
    var password: String,
    @Column("url")
    var url: String,
    @Column("tags")
    var tags: String,
    @Column("expiration_date")
    var expirationDate: Timestamp,
    @Column("is_deleted")
    var isDeleted: Boolean
)