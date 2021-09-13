package com.nytimes.pojo

import com.google.gson.annotations.SerializedName


class MostViewedResponse {
    @SerializedName("status")
    var status: String? = null
    @SerializedName("copyright")
    var copyright: String? = null
    @SerializedName("num_results")
    var num_results = 0
    @SerializedName("results")
    var results: List<Result>? = null
}