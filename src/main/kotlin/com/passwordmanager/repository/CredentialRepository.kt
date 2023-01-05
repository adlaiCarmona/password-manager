package com.passwordmanager.repository

import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Repository
class CredentialRepository(private val client: DatabaseClient) {

}