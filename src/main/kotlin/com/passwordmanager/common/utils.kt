package com.passwordmanager.common

import java.sql.Timestamp
import java.util.UUID

fun getUuidAsString() = UUID.randomUUID().toString()

fun getDateCreated() = Timestamp(System.currentTimeMillis())