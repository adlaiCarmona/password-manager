package com.passwordmanager.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("SETTINGS")
data class Setting(
    @Id
    @Column("id")
    var id: String,
    @Column("user_id")
    var userId: String,
    @Column("password_duration")
    var passwordDuratino: Int = 60 // in sql table default is 60, check which one it takes
)
