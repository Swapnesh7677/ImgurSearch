package com.swapnesh.imgursearch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.swapnesh.imgursearch.R
import com.swapnesh.imgursearch.model.Data
import com.swapnesh.imgursearch.utils.AppUtils


class ImgurImageAdapter  (val dataList: ArrayList<Data>

) : RecyclerView.Adapter<ImgurImageAdapter.ViewHolder>() {

    var ctx: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ctx = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_imgur, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = dataList[position]
        holder.title.text =ItemsViewModel.title
        holder.datetime.text =AppUtils().getDate(ItemsViewModel.datetime.toLong(),"dd-MM-yyyy hh:mm aa")

        holder.imagelayout.removeAllViews();

        if( ItemsViewModel.images != null && ItemsViewModel.images?.size!! > 0){
            for( image in ItemsViewModel.images!!){
                if(image.type.equals("image/jpeg")){
                    val imageView = ImageView(ctx)
                    var layoutParams: ViewGroup.LayoutParams =  ViewGroup.LayoutParams(200, 200)

                    imageView.setPadding(10,10,10,10)
                    imageView.layoutParams = layoutParams
                    imageView.scaleType = ImageView.ScaleType.FIT_XY
                    imageView.adjustViewBounds  = true
                    ctx?.let {
                        Glide.with(it)
                            .load(image.link)
                            .into(imageView)
                    }
                    holder.imagelayout.addView(imageView)
                }
            }
        }



    }


    override fun getItemCount(): Int {
        return dataList.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val title: TextView = itemView.findViewById(R.id.textview_title)
        val datetime: TextView = itemView.findViewById(R.id.textview_date)
        val imagelayout: LinearLayout  = itemView.findViewById(R.id.imagelayout)
    }
}