package com.nytimes.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Result : Serializable {
    var uri: String? = null
    var url: String? = null
    var id: Any? = null
    var asset_id: Any? = null
    var source: String? = null
    var published_date: String? = null
    var updated: String? = null
    var section: String? = null
    var subsection: String? = null
    var nytdsection: String? = null
    var adx_keywords: String? = null
    var column: Any? = null
    var byline: String? = null
    var type: String? = null
    var title: String? = null
    @SerializedName("abstract")
    var abstruct: String? = null
    var des_facet: List<String>? = null
    var org_facet: List<String>? = null
    var per_facet: List<String>? = null
    var geo_facet: List<String>? = null
    var media: List<Medium>? = null
    var eta_id = 0
}
