package com.example.gonative.eventmodels

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.util.Date

data class Place(
    @StringRes val nameResId: Int,
    @DrawableRes val imageResId: Int,
    val detailsRoute: String,
    val date: String
)
