package com.lacolinares.androidchatgpt.utils

import kotlinx.serialization.json.Json

object AppJson {

    val Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
}