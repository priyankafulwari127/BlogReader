package org.example.blog.reader

import BlogDetails
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.example.blog.reader.ui.blogList.BlogList

@Composable
@Preview
fun App() {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Route.List
    ) {
        composable<Route.List> {
            BlogList(
                onBlogClick = {
                    navController.navigate(Route.BlogDetail(it.link))
                }
            )
        }

        composable<Route.BlogDetail>{
            val url = it.toRoute<Route.BlogDetail>().blogLink
            BlogDetails(url)
        }
    }
}

@Serializable
sealed class Route {
    @Serializable
    data object List : Route()
    @Serializable
    data class BlogDetail(val blogLink:String):Route()
}