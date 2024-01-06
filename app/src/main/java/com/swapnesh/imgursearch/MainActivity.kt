package com.swapnesh.imgursearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.swapnesh.imgursearch.adapter.ImgurImageAdapter
import com.swapnesh.imgursearch.databinding.ActivityMainBinding
import com.swapnesh.imgursearch.model.BaseData
import com.swapnesh.imgursearch.model.Data
import com.swapnesh.imgursearch.utils.NetworkResult
import com.swapnesh.imgursearch.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel :MainViewModel
    var layoutManager: LinearLayoutManager?=null

    private val dataList: ArrayList<Data> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerView.setHasFixedSize(true)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

       fetchImages("india")
        binding.itemUserInput.switchBtn.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {
                val layoutManager = GridLayoutManager(this, 2)
                binding.recyclerView.layoutManager = layoutManager
            } else {
                layoutManager!!.orientation = LinearLayoutManager.VERTICAL
                binding.recyclerView.layoutManager = layoutManager
            }
        }
        binding.itemUserInput.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                searchFromInput()

                return false
            }
        })
    }

    private fun fetchImages(query: String) {
        binding.recyclerView.visibility = View.GONE
        binding.shimmerlayout.visibility = View.VISIBLE
        binding.shimmerlayout.startShimmer()
            mainViewModel.getImages(query)
        mainViewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        //response.data.data?.get(0)?.images?.size

                      //  Log.d("tag",response.data.data.toString())
                        response.data.data?.let { it1 -> dataList.addAll(it1) }
                      //  Log.d("tag--size", dataList.size.toString())
                        setUI(response.data)
                    }

                }

                is NetworkResult.Error -> {
                    Log.d("tag",response.message.toString())
                    Toast.makeText(
                        this,
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {

                }
            }
        }
    }
    private fun searchFromInput() {
        binding.itemUserInput.searchView.query.trim().toString().let { it ->
            if (it.isNotEmpty()) {
                Log.d("search query", it.toString())

                fetchImages(it)
            }
        }
    }
    private fun setUI(it: BaseData) {
        dataList.clear()
        binding.recyclerView.visibility = View.VISIBLE
        binding.shimmerlayout.visibility = View.GONE
        binding.shimmerlayout.stopShimmer()

        it.data?.let { it1 -> dataList.addAll(it1) }
        binding.recyclerView.adapter = ImgurImageAdapter(dataList)
    }

}