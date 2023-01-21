package com.ab21.data.network.env

import com.ab21.data.env.Environment

object Environments {

    private const val KEY_FLAVOR_PROD = "production"

    @JvmStatic
    fun fromName(name: String): Environment = when (name) {
        KEY_FLAVOR_PROD -> ProdEnvironment()
        else -> throw IllegalArgumentException("Invalid environment: $name")
    }
}
