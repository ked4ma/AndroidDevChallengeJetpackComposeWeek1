package com.example.androiddevchallenge.ui.page.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.Animal
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.util.quantityStringResource
import com.example.androiddevchallenge.vm.AnimalsViewModel
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun Animals(selectAnimal: (Long) -> Unit, animalsViewModel: AnimalsViewModel = viewModel()) {
    val animals by animalsViewModel.animalList.observeAsState(initial = emptyList())
    Animals(animals = animals, selectAnimal = selectAnimal)
}

@Composable
fun Animals(
    animals: List<Animal>,
    selectAnimal: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.statusBarsPadding()
    ) {
        item { AppBar() }
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

@Composable
private fun AppBar() {
    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier.height(80.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = stringResource(id = R.string.puppy),
                fontSize = 40.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 28.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
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
            modifier = Modifier.clickable { selectAnimal(animal.id) },
        ) {
            val (image, name, age, gender) = createRefs()
            Image(
                painterResource(id = animal.imageRes), null,
                modifier = Modifier
                    .aspectRatio(5f / 3f)
                    .constrainAs(image) {
                        centerVerticallyTo(parent)
                        start.linkTo(parent.start)
                    },
                contentScale = ContentScale.Crop,
            )
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