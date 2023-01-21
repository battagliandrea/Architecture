package com.ab21.data.network.env

import com.ab21.data.env.Environment

class ProdEnvironment : Environment {

    override val defaultBaseUrl = "https://api.jikan.moe/v4/"

    override val imagesBaseUrl = "https://pokeres.bastionbot.org/images/pokemon/"

}
