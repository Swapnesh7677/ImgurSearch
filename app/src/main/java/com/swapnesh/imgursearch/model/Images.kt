package com.swapnesh.imgursearch.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Images(
    @SerializedName("title") var title: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("datetime") var datetime: Int?,
    @SerializedName("type") var type: String = "",
    @SerializedName("animated") var animated: Boolean = false,
    @SerializedName("width") var width: Int = 0,
    @SerializedName("height") var height: Int = 0,
    @SerializedName("size") var size: Int = 0,
    @SerializedName("link") val link: String = "",
    @SerializedName("search_content")

    var search_content: String = "",

    ) : Serializable {

    @SerializedName("id")
    var id: String = ""
    var indexInResponse: Int = -1
    var pageNumber: Int = 1
}
