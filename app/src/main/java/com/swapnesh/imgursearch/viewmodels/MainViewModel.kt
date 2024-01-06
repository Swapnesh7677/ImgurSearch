package com.swapnesh.imgursearch.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swapnesh.imgursearch.model.BaseData
import com.swapnesh.imgursearch.repository.PostRepository
import com.swapnesh.imgursearch.utils.NetworkResult

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {


    private val _response: MutableLiveData<NetworkResult<BaseData>> = MutableLiveData()
    val response: LiveData<NetworkResult<BaseData>> = _response

    fun getImages(search: String) = viewModelScope.launch {
        repository.getImages(search).collect { values ->
            _response.value = values
        }
    }



   /* val postCommentsLiveData : MutableLiveData<NetworkResult<BaseData>>
        get() = repository.comments

    fun getImages(search: String): LiveData<BaseData> {
        viewModelScope.launch {
            repository.getImages(search)
        }
        return postCommentsLiveData
    }*/
}
