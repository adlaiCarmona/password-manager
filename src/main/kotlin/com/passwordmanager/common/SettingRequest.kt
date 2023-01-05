package com.passwordmanager.common

import com.passwordmanager.domain.Setting


data class SettingRequest(
    override val userId: String,
    override val id: String? = null,
    override val passwordDuration: Int? = 60
) : ISettingRequest

data class SettingModifyRequest(
    override val userId: String,
    override val id: String? = null,
    override val passwordDuration: Int? = null
) : ISettingRequest

interface ISettingRequest{
    val id: String?
    val userId: String?
    val passwordDuration: Int?
}

fun ISettingRequest.convert(): Setting{
    return Setting(
        this.id!!,
        this.userId!!,
        this.passwordDuration!!
    )
}