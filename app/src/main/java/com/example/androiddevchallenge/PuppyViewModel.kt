package com.example.androiddevchallenge

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.model.Puppy

class PuppyViewModel : ViewModel() {
    private val _puppys = MutableLiveData<List<Puppy>>()
    val puppys: LiveData<List<Puppy>> = _puppys

    init {
        val list = mutableListOf<Puppy>()
        repeat(10) {
            list.add(Puppy().apply {
                name = "Bailey";
                imgId = R.mipmap.a
                age = "8 months"
                description =
                    "Meet Bailey! This playful dog is ready for her forever home. she is only 2 years old and has not quite figured out that she is not a puppy anymore. Bailey is going to do well in a home where she can get a ton of consistency and help becoming the best girl she be. If Bailey sounds like your speed we hope you will adopt her today!"
            })
            list.add(Puppy().apply {
                name = "Jax";
                imgId = R.mipmap.b
                age = "7 months"
                description =
                    "Meet Jax! This playful pup is looking for his forever home. He is still very much a puppy and will need help in his new home to stay on track to becoming the best boy he can be. With some consistency and training he will do well in a wide variety of homes. Jax is going to do best in a home without small kids. If Jax sounds like he would fit into your life we hope you will adopt him today!"
            })
            list.add(Puppy().apply {
                name = "Theodosia";
                imgId = R.mipmap.c
                age = "10 months"
                description = "Say hello tot he one and only Theodosia! This spunky lady is looking for an active family willing to give her mental enrichment and physical activity! Theodosia does need to go into a home with no young kids, and no other dogs! She is a very friendly dog, but she can be a lot of dog if you are not dog savvy. She is about 5 years old, but she would still love to learn new things! She may benefit from basic training to learn new commands and manners. If you think Theodosia is the dog of your dreams, come on by and make it a reality!"
            })
            list.add(Puppy().apply {
                name = "Stranger";
                imgId = R.mipmap.d
                age = "10 months"
                description = "Looking for a young, sweet and active dog? Stranger might be the girl for you! She needs to go to a home without children under 5 years old, just to make sure no one gets knocked down when she happily romps through the house! Stranger is a very affectionate dog, but needs some training to help boost her confidence and to learn proper manners. Once Stranger is fully comfortable with her new family and home, she will make a wonderful companion. If you're looking forward to helping Stranger become more of a superstar every day, come in and meet her!"
            })
        }
        _puppys.value = list
    }
}