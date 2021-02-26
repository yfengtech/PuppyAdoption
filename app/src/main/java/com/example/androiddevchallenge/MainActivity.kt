/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(puppyViewModel: PuppyViewModel = viewModel()) {
    var currentSelectedPuppy by remember { mutableStateOf<Puppy?>(null) }
    Surface(color = MaterialTheme.colors.background) {
        Column {
            TopAppBar(
                title = { Text(text = "Puppy Adoption") }
            )
            LazyColumn(Modifier.fillMaxWidth()) {
                items(puppyViewModel.puppys.value!!) { puppy ->
                    PuppyItem(puppy) {
                        currentSelectedPuppy = puppy
                    }
                }
            }
        }
    }
    if (currentSelectedPuppy != null) {
        val p = currentSelectedPuppy!!
        Surface(color = MaterialTheme.colors.background) {
            Column {
                TopAppBar(
                    title = { Text(text = p.name) }
                )
                PuppyDetail(p)
                BackHandler(
                    onBack = {
                        currentSelectedPuppy = null
                    }
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 720)
@Composable
fun DemoPuppy() {
    val demo = Puppy().apply {
        name = "Bailey"
        imgId = R.mipmap.a
        age = "8 months"
        description =
            "Meet Bailey! This playful dog is ready for her forever home. she is only 2 years old and has not quite figured out that she is not a puppy anymore. Bailey is going to do well in a home where she can get a ton of consistency and help becoming the best girl she be. If Bailey sounds like your speed we hope you will adopt her today!"
    }
    PuppyDetail(puppy = demo)
}

@Composable
fun PuppyDetail(puppy: Puppy) {
    val padding = 16.dp
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val image: Painter = painterResource(id = puppy.imgId)
            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier.fillMaxWidth().padding(top = padding)
            )
            Text(
                text = puppy.name,
                modifier = Modifier.padding(top = padding),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "Age:${puppy.age}",
                modifier = Modifier.padding(top = padding),
                style = MaterialTheme.typography.body1
            )
            Text(
                text = puppy.description,
                modifier = Modifier.padding(start = padding, end = padding, top = padding),
                style = MaterialTheme.typography.body1
            )

            Button(onClick = { }, modifier = Modifier.padding(top = padding)) {
                Text(text = "Bring it home！")
            }
        }
    }
}

@Composable
fun PuppyItem(puppy: Puppy, onClick: () -> Unit) {
    val padding = 16.dp
    Column(
        Modifier
            .clickable(onClick = {})
            .height(140.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Card(
                elevation = 4.dp,
                modifier = Modifier.padding(start = padding, top = 8.dp, bottom = 8.dp)
            ) {
                val image: Painter = painterResource(id = puppy.imgId)
                Image(
                    painter = image,
                    contentDescription = "",
                    modifier = Modifier.fillMaxHeight()
                )
            }
            Column {
                Text(
                    text = puppy.name,
                    modifier = Modifier.padding(start = padding),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Age:${puppy.age}",
                    modifier = Modifier.padding(start = padding, top = padding),
                    style = MaterialTheme.typography.body1
                )
            }
        }
        Divider(modifier = Modifier.height(1.dp), color = Color.Black)
    }
}

@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
