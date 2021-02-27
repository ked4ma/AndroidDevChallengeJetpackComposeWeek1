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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.Animal
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.pink
import com.example.androiddevchallenge.util.quantityStringResource
import com.example.androiddevchallenge.vm.AnimalFilter
import com.example.androiddevchallenge.vm.AnimalsViewModel
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun Animals(selectAnimal: (Long) -> Unit, animalsViewModel: AnimalsViewModel = viewModel()) {
    val animals by animalsViewModel.animalList.observeAsState(initial = emptyList())
    Animals(
        animals = animals,
        filter = animalsViewModel.animalFilter,
        selectAnimal = selectAnimal,
        selectFilter = {
            animalsViewModel.selectFilter(it)
        }
    )
}

@Composable
fun Animals(
    animals: List<Animal>,
    filter: LiveData<AnimalFilter>,
    selectAnimal: (Long) -> Unit,
    selectFilter: (AnimalFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Box {
        LazyColumn(
            modifier = modifier.statusBarsPadding()
        ) {
            item { AppBar(filter, selectFilter) }
            animals.forEach {
                item {
                    AnimalItem(
                        animal = it,
                        selectAnimal = selectAnimal,
                        modifier = Modifier.height(80.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun FilterMenu(
    filter: LiveData<AnimalFilter>,
    selectFilter: (AnimalFilter) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val filters = AnimalFilter.values()
    val selected by filter.observeAsState(initial = AnimalFilter.ALL)
    Box(modifier = modifier) {
        IconButton(
            modifier = Modifier
                .size(40.dp),
            onClick = { expanded = true }
        ) {
            Icon(imageVector = Icons.Default.Filter, null)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            filters.forEach { filter ->
                DropdownMenuItem(
                    onClick = {
                        selectFilter(filter)
                        expanded = false
                    }
                ) {
                    if (filter == selected) {
                        Text(text = filter.name)
                    } else {
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            Text(text = filter.name)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AppBar(
    filter: LiveData<AnimalFilter>,
    selectFilter: (AnimalFilter) -> Unit,
) {
    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier.height(80.dp)
    ) {
        Text(
            text = stringResource(id = R.string.puppy),
            fontSize = 40.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 28.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1F)
        )
        FilterMenu(
            filter, selectFilter,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun AnimalItem(
    animal: Animal,
    selectAnimal: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
    ) {
        ConstraintLayout(
            modifier = Modifier.clickable {
                selectAnimal(animal.id)
            },
        ) {
            val (image, name, age, gender) = createRefs()
            Box(
                modifier = Modifier
                    .constrainAs(image) {
                        centerVerticallyTo(parent)
                        start.linkTo(parent.start)
                    },
            ) {
                Image(
                    painterResource(id = animal.imageRes), null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(5f / 3f)
                )
                if (animal.favorite) {
                    Spacer(
                        modifier = Modifier
                            .requiredSize(60.dp)
                            .clip(
                                GenericShape { size, _ ->
                                    lineTo(size.width / 3, 0F)
                                    lineTo(0F, size.height / 3)
                                    lineTo(0F, 0F)
                                }
                            )
                            .background(color = pink)
                            .align(Alignment.TopStart)
                    )
                }
            }
            Text(
                text = animal.name,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .constrainAs(name) {
                        start.linkTo(image.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(age.top)
                    }
            )
            Text(
                text = quantityStringResource(
                    id = R.plurals.age,
                    quantity = animal.ageInMonth,
                    animal.ageInMonth
                ),
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .constrainAs(age) {
                        start.linkTo(image.end)
                        top.linkTo(name.bottom)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Text(
                text = animal.gender.name,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .constrainAs(gender) {
                        start.linkTo(age.end)
                        baseline.linkTo(age.baseline)
                    }
            )
        }
    }
}

@Preview
@Composable
fun AnimalItemPreview() {
    MyTheme {
        AnimalItem(
            animal = Animal(
                0,
                Animal.Type.DOG,
                "dog",
                R.mipmap.jagdterrier,
                3,
                "Jagdterrier",
                Animal.Gender.MALE,
                "info"
            ),
            selectAnimal = { /*TODO*/ },
            modifier = Modifier
                .height(72.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun AnimalItemDarkPreview() {
    MyTheme(darkTheme = true) {
        AnimalItem(
            animal = Animal(
                0,
                Animal.Type.DOG,
                "dog",
                R.mipmap.jagdterrier,
                3,
                "Jagdterrier",
                Animal.Gender.MALE,
                "info"
            ),
            selectAnimal = { /*TODO*/ },
            modifier = Modifier.height(72.dp)
        )
    }
}
