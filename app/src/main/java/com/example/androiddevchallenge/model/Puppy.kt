package com.example.androiddevchallenge.model

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R
import java.io.Serializable

class Puppy : Serializable {
    var name = ""
    var age = ""
    @DrawableRes
    var imgId: Int = R.mipmap.a
    var description = ""
}