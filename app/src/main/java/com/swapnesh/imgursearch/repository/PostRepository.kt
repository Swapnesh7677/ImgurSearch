package com.swapnesh.imgursearch.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swapnesh.imgursearch.model.BaseApiResponse

import com.swapnesh.imgursearch.model.BaseData
import com.swapnesh.imgursearch.myApplication.MyApplication
import com.swapnesh.imgursearch.retrofit.ImgurAPI
import com.swapnesh.imgursearch.utils.AppUtils
import com.swapnesh.imgursearch.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class PostRepository @Inject constructor(private val ImagurAPI: ImgurAPI): BaseApiResponse() {

  /*  private val _postComments = MutableLiveData<NetworkResult<BaseData>>()
    val comments: LiveData<NetworkResult<BaseData>>
        get() = _postComments*/
  suspend fun getImages(search: String): Flow<NetworkResult<BaseData>> {
        return flow<NetworkResult<BaseData>> {
            emit(safeApiCall { ImagurAPI.getSearchList(search) })
        }.flowOn(Dispatchers.IO)
    }

   /* suspend fun getImages(search: String){
        if(MyApplication.getAppContext()?.let { AppUtils().isInterConnectionIsAvailable(it) } == true){

            flow<NetworkResult<BaseData>> {
                emit(safeApiCall { ImagurAPI.getSearchList(search) })
            }.flowOn(Dispatchers.IO)


        }
    }*/

}