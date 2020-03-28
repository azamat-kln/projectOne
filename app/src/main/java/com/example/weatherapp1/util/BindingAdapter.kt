package com.example.weatherapp1.util

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weatherapp1.MyAdapter
import com.example.weatherapp1.R
import com.example.weatherapp1.domain.Model
import com.example.weatherapp1.overview.GenderConst
import com.example.weatherapp1.overview.ImageStatus

@BindingAdapter("setImageState")
fun ImageView.img(imageStatus: ImageStatus?) {
    when (imageStatus) {
        ImageStatus.LOADING -> {
            visibility = View.VISIBLE
            setImageResource(R.drawable.loading_animation)
        }
        ImageStatus.ERROR -> {
            visibility = View.VISIBLE
            setImageResource(R.drawable.connection_error)
        }
        else -> visibility = View.GONE
    }
}

@BindingAdapter("imageUrl")
fun ImageView.setImg(url: String?) {

    url?.let {
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context).load(imgUri).apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
        ).into(this)
    }
}

@BindingAdapter(value = ["app:listData", "app:someGender"], requireAll = true)
fun RecyclerView.setListData(list: List<Model>?, genderStatus: GenderConst?) {
    val myAdapter = adapter as MyAdapter

    list?.let {
        val filterMale = list.filter { it.gender == "Male" }
        val filterFemale = list.filter { it.gender == "Female" }

        when (genderStatus) {
            GenderConst.FEMALE -> {
                myAdapter.dataList = filterFemale
            }
            GenderConst.MALE -> {
                myAdapter.dataList = filterMale
            }
            else -> {
                myAdapter.dataList = list
            }
        }
    }
}
