package com.passwordmanager.common

import java.net.URI
import java.net.URISyntaxException
import java.sql.Timestamp
import java.util.*


fun getUuidAsString() = UUID.randomUUID().toString()

fun getDateCreated() = Timestamp(System.currentTimeMillis())

@Throws(URISyntaxException::class)
fun getDomainName(url: String?): String? {
    val uri = URI(url)
    val domain = uri.host
    return if (domain.startsWith("www.")) domain.substring(4) else domain
}