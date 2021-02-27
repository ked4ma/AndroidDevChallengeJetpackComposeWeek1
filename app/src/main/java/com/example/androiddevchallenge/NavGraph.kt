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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.Destinations.ANIMAL_DETAIL_ID
import com.example.androiddevchallenge.ui.page.list.AnimalDetail
import com.example.androiddevchallenge.ui.page.list.Animals
import com.example.androiddevchallenge.vm.AnimalsViewModel

object Destinations {
    const val ANIMALS_ROUTE = "animals"
    const val ANIMAL_DETAIL_ROUTE = "animal"
    const val ANIMAL_DETAIL_ID = "animalId"
}

@Composable
fun NavGraph(
    startDestination: String = Destinations.ANIMALS_ROUTE,
    animalsViewModel: AnimalsViewModel = viewModel()
) {
    val navController = rememberNavController()

    val actions = remember(navController) { Actions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.ANIMALS_ROUTE) {
            Animals(selectAnimal = actions.selectAnimal, animalsViewModel = animalsViewModel)
        }
        composable(
            "${Destinations.ANIMAL_DETAIL_ROUTE}/{$ANIMAL_DETAIL_ID}",
            arguments = listOf(navArgument(ANIMAL_DETAIL_ID) { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            animalsViewModel.getAnimalById(arguments.getLong(ANIMAL_DETAIL_ID))
            AnimalDetail(
                upPress = actions.upPress,
                animalsViewModel = animalsViewModel
            )
        }
    }
}

private class Actions(navController: NavHostController) {
    val selectAnimal: (Long) -> Unit = { animalId ->
        navController.navigate("${Destinations.ANIMAL_DETAIL_ROUTE}/$animalId")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
