package com.passwordmanager.common

import com.passwordmanager.domain.Credential
import java.time.LocalDateTime

data class CredentialCreateRequest(
    val userId: String?,
    val username: String,
    val password: String,
    val website: String?,
    val url: String,
    val tags: String?,
    val expiresInDays: Int
)
data class CredentialRequest(
    override val userId: String,
    override val username: String,
    override val password: String,
    override val website: String?,
    override val url: String,
    override val expirationDate: LocalDateTime?,
    override val id: String? = null,
    override val tags: String? = "",
    override val isDeleted: Boolean? = false,
) : ICredentialRequest

interface ICredentialRequest{
    val id: String?
    val userId: String?
    val username: String?
    val password: String?
    val website: String?
    val url: String?
    val tags: String?
    val expirationDate: LocalDateTime?
    val isDeleted: Boolean?
}

fun ICredentialRequest.convert(): Credential {
    return Credential(
        this.id!!,
        this.userId!!,
        this.username!!,
        this.password!!,
        this.website!!,
        this.url!!,
        this.tags!!,
        this.expirationDate!!,
        this.isDeleted!!
    )
}

fun CredentialCreateRequest.convert(): Credential {
    return Credential(
        null,
        this.userId!!,
        this.username,
        this.password,
        this.website ?: getDomainName(this.url)!!,
        this.url!!,
        this.tags?: "",
        expirationDate = LocalDateTime.now().plusDays(this.expiresInDays.toLong())
    )
}

fun CredentialCreateRequest.addUserId(userId: String): CredentialCreateRequest {
    return CredentialCreateRequest(
        userId,
        this.username,
        this.password,
        this.website ?: getDomainName(this.url)!!,
        this.url,
        this.tags,
        this.expiresInDays
    )
}