package com.passwordmanager.common

import net.minidev.json.JSONObject
import java.util.Base64

data class MyJwt (
    val credential: String,
    val g_csrf_token: String
)

fun MyJwt.decode() {
    val chunks = this.credential.split('.')

    val header = decodeBase64(chunks[0])
    val payload = decodeBase64(chunks[1])
    var signature: String? = null
    try {
        signature = decodeBase64(chunks[2])
    } catch (e: IllegalArgumentException){
        println("No signature in jwt")
    }

    println(header)
    println(payload)
    println(signature)
}

fun decodeBase64(encoded: String): String {
    return String(Base64.getDecoder().decode(encoded))
}