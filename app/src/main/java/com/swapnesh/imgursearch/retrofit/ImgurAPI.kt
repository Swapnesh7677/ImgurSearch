package com.swapnesh.imgursearch.retrofit


import com.swapnesh.imgursearch.model.BaseData
import com.swapnesh.imgursearch.utils.Constants
import retrofit2.Call
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ImgurAPI {

    @Headers("Authorization:CLIENT-ID ${Constants.IMGUR_API_KEY}")
    @GET("gallery/search/top/week")
    suspend fun getSearchList( @Query("q") q: String): Response<BaseData>

}