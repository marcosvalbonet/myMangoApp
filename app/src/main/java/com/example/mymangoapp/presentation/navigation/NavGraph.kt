package com.example.mymangoapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mymangoapp.presentation.favorites.FavoritesScreen
import com.example.mymangoapp.presentation.products.ProductsScreen
import com.example.mymangoapp.presentation.profile.ProfileScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Products.route,
        modifier = modifier
    ) {
        composable(Screen.Products.route) {
            ProductsScreen()
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}
