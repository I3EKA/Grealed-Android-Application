package com.example.grailed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.grailed.ui.theme.GrailedTheme

sealed class Screen(val route: String, val label: String, val icon: @Composable () -> ImageVector) {
    object Home : Screen("home", "My Feed", { ImageVector.vectorResource(R.drawable.wdqvw) })
    object Search : Screen("search", "Discover", { Icons.Default.Search })
    object Favorites :
        Screen("favorites", "Sell", { ImageVector.vectorResource(R.drawable.gbd) })

    object Messages : Screen("messages", "Messages", { ImageVector.vectorResource(R.drawable.mess) })
    object Profile : Screen("profile", "Profile", { Icons.Default.Person })

    // Дополнительные маршруты без нижней навигации
    object Settings : Screen("settings", "Settings", { Icons.Default.Settings })
    object EditProfile : Screen("edit_profile", "Edit Profile", { Icons.Default.Person })
    object Search1: Screen("search1","Search",{ Icons.Default.Search })
    object Detail: Screen("detail","Detail",{ Icons.Default.Search })
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrailedTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Favorites,
        Screen.Messages,
        Screen.Profile
    )
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, items = bottomNavItems)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { MyFeedScreen(navController) }
            composable(Screen.Search.route) { DiscoverScreen(navController) }
            composable(Screen.Favorites.route) { SellScreen(navController) }
            composable(Screen.Messages.route) { MessagesScreen(navController) }
            composable(
                route = "profile?tab={tab}",
                arguments = listOf(navArgument("tab") {
                    type = NavType.StringType
                    defaultValue = "selling"
                })
            ) {
                val tabArg = it.arguments?.getString("tab") ?: "selling"
                ProfileScreen(navController, initialTab = tabArg)
            }

            // Новые экраны
            composable(Screen.Settings.route) { SettingsScreen(navController) }
            composable(Screen.EditProfile.route) { EditProfileScreen(navController) }
            composable(Screen.Search1.route){ SearchScreen(navController) }
            composable(Screen.Detail.route){DetailScreen(navController)}
        }

    }
}


@Composable
fun BottomNavigationBar(navController: NavHostController, items: List<Screen>) {
    NavigationBar {
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val fullRoute = currentBackStackEntry?.destination?.route
        val currentRoute = fullRoute?.substringBefore('?') // Отрезаем параметры

        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon(), contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route, // теперь будет работать и с параметрами
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}



