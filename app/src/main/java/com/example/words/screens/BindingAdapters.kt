package com.example.words.screens

import android.opengl.Visibility
import android.view.ActionProvider
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.words.R
import com.example.words.entity.Word
import com.example.words.screens.overview.DictWordsListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Word>?) {
    val adapter = recyclerView.adapter as DictWordsListAdapter
    adapter.submitList(data)
}


@BindingAdapter("imageName")
fun bindImage(imgView: ImageView, imgName: String?) {
    // The imgName parameter will be auto-substituted in the imageUrl string
    val imageUrlString = "https://www.merriam-webster.com/assets/mw/static/art/dict/$imgName.gif"
    if (null != imgName) {
        imgView.visibility = View.VISIBLE
        val imgUri = imageUrlString.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    } else {
        imgView.visibility = View.GONE
    }
}