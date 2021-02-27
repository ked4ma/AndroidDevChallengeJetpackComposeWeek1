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
package com.example.androiddevchallenge.ui.page.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.Animal
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.pink
import com.example.androiddevchallenge.util.quantityStringResource
import com.example.androiddevchallenge.vm.AnimalsViewModel
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun AnimalDetail(
    upPress: () -> Unit,
    animalsViewModel: AnimalsViewModel = viewModel()
) {
    val animal: Animal? by animalsViewModel.detailAnimal.observeAsState()

    if (animal == null) {
        Box {
            Text(
                text = stringResource(R.string.not_found),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        AnimalDetail(animal!!, upPress) {
            animalsViewModel.favorite(it)
        }
    }
}

@Composable
private fun AnimalDetail(
    animal: Animal,
    upPress: () -> Unit,
    favorite: (Long) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn {
            item { AnimalDetailHeader(animal, upPress) }
            item { AnimalDetailBody(animal, favorite) }
        }
    }
}

@Composable
private fun AnimalDetailHeader(
    animal: Animal,
    upPress: () -> Unit
) {
    Box {
        Image(
            painter = painterResource(id = animal.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(5f / 3f),
            contentScale = ContentScale.Crop,
        )
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = Color.White, // always white as image has dark scrim
            modifier = Modifier.statusBarsPadding()
        ) {
            IconButton(onClick = upPress) {
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.7F)),
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.label_back), tint = Color.Black,
                )
            }
            // Image(
            //     painter = painterResource(id = R.drawable.ic_logo),
            //     contentDescription = null,
            //     modifier = Modifier
            //         .padding(bottom = 4.dp)
            //         .size(24.dp)
            //         .align(Alignment.CenterVertically)
            // )
        }
    }
}

@Composable
private fun AnimalDetailBody(
    animal: Animal,
    favorite: (Long) -> Unit
) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = animal.name,
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = quantityStringResource(
                    id = R.plurals.age,
                    quantity = animal.ageInMonth,
                    animal.ageInMonth
                ),
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = animal.gender.name,
                style = MaterialTheme.typography.h6,
            )
        }
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        IconButton(
            modifier = Modifier.align(Alignment.Center),
            onClick = { favorite(animal.id) }
        ) {
            if (animal.favorite) {
                Icon(
                    Icons.Rounded.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    tint = pink
                )
            } else {
                Icon(
                    Icons.Rounded.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
    Divider(modifier = Modifier.padding(16.dp))
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = animal.breed,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Text(
            text = animal.info,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
    // Text(
    //     text = stringResource(id = R.string.what_you_ll_need),
    //     style = MaterialTheme.typography.h6,
    //     textAlign = TextAlign.Center,
    //     modifier = Modifier
    //         .fillMaxWidth()
    //         .padding(16.dp)
    // )
    // CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
    //     Text(
    //         text = stringResource(id = R.string.needs),
    //         style = MaterialTheme.typography.body1,
    //         textAlign = TextAlign.Center,
    //         modifier = Modifier
    //             .fillMaxWidth()
    //             .padding(
    //                 start = 16.dp,
    //                 top = 16.dp,
    //                 end = 16.dp,
    //                 bottom = 32.dp
    //             )
    //     )
    // }
}

@Preview
@Composable
fun AnimalDetailBodyPreview() {
    MyTheme {
        LazyColumn {
            item {
                AnimalDetailBody(
                    animal = Animal(
                        0,
                        Animal.Type.DOG,
                        "dog",
                        R.mipmap.jagdterrier,
                        3,
                        "Jagdterrier",
                        Animal.Gender.MALE,
                        "info"
                    )
                ) { }
            }
        }
    }
}
