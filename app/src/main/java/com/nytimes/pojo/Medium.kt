package com.nytimes.pojo

import com.google.gson.annotations.SerializedName


class Medium {
    var type: String? = null
    var subtype: String? = null
    var caption: String? = null
    var copyright: String? = null
    var approved_for_syndication = 0
    @SerializedName("media-metadata")
    var media_metadata: List<MediaMetadata>? = null
}