package com.holat.login.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.holat.login.R

/**
Created by Mohamed Mohamed Taha on 12/9/2023
 */
fun View.showImage(url: String, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                // .error(R.drawable.loading_animation)
                .error(R.drawable.ic_error) // Error image in case of loading failure
        )
        //.centerCrop()
        .into(imageView)
}

fun View.showImage(url: Int, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.loading_animation)
            //  .error(R.drawable.ic_error) // Error image in case of loading failure
        )
        .into(imageView)
}

//fun View.showImagePicasso(url: String, imageView: ImageView) {
//    Picasso.get()
//        .load(url)
//        .resize(50, 50)
//        .centerCrop()
//        .placeholder(R.drawable.loading_animation)
//        .error(R.drawable.ic_error) // Error image in case of loading failure
//        .into(imageView)
//
//}