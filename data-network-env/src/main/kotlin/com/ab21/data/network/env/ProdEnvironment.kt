package com.ab21.data.network.env

internal class ProdEnvironment : Environment {

    override val defaultBaseUrl = "https://api.jikan.moe/v4/"

    override val imagesBaseUrl = "https://pokeres.bastionbot.org/images/pokemon/"

}
