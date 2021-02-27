package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

object Destinations {
    const val ANIMALS_ROUTE = "animals"
    const val ANIMAL_DETAIL_ROUTE = "animal"
    const val ANIMAL_DETAIL_ID = "animalId"
}

@Composable
fun NavGraph(startDestination: String = Destinations.ANIMALS_ROUTE) {
    val navController = rememberNavController()

    val actions = remember(navController) { Actions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.ANIMALS_ROUTE) {
            Animals(selectAnimal = actions.selectAnimal)
        }
        composable(
            "${Destinations.ANIMAL_DETAIL_ROUTE}/{$ANIMAL_DETAIL_ID}",
            arguments = listOf(navArgument(ANIMAL_DETAIL_ID) { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            AnimalDetail(
                animalId = arguments.getLong(ANIMAL_DETAIL_ID),
                upPress = actions.upPress
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