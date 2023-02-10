package com.kinofilms.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kinofilms.R

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.kinofilms_logo1)
        .into(this)
}