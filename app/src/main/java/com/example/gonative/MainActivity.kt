package com.example.gonative


import android.os.Bundle
import android.provider.Telephony.Mms.Addr
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gonative.eventdetailspage.carbootDetails
import com.example.gonative.eventdetailspage.halloweenDetails
import com.example.gonative.eventdetailspage.midvalleyDetails
import com.example.gonative.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Create instance of AuthViewModel
        val authViewModel: AuthViewModel by viewModels()


        setContent {
            AppTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                // Define the screens that require a bottom bar
                val screensWithBottomBar = listOf("home", "review", "add review", "viewReviewRatings/{placeName}", "events", "profile", "halloweenDetails", "carbootDetails", "midvalleyDetails")

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (navBackStackEntry?.destination?.route in screensWithBottomBar) {
                            BottomAppBar(navController = navController)
                        }
                    }
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Navigation destinations
                        composable("login") {
                            LoginPage(Modifier, navController, authViewModel)
                        }
                        composable("signup") {
                            SignupPage(Modifier, navController, authViewModel)
                        }
                        composable("home") {
                            Home()
                        }
                        composable("review") {
                            ReviewRatings(navController)
                        }
                        composable("viewReviewRatings/{placeName}") { backStackEntry ->
                            val placeName = backStackEntry.arguments?.getString("placeName") ?: "Unknown Place"
                            ViewReviewRatings(navController, placeName)
                        }
                        composable("events"){
                            //add evets screen  func here
                            // Add events screen function here
                            EventsPromotions(navController)
                        }
                        composable("profile") {
                            // Add profile screen function here

                            Profile(Modifier, navController, authViewModel)
                        }
                        composable("halloweenDetails") {
                            // Add halloweenDetails screen function here
                            halloweenDetails(navigateBackToEventPromotions  = {
                                navController.popBackStack("events", inclusive = false)
                            },
                            )

                        }
                        composable("carbootDetails") {
                            // Add carbootDetails screen function here
                            carbootDetails(
                                navigateBackToEventPromotions  = {
                                    navController.popBackStack("events", inclusive = false)
                                },
                            )
                        }
                        composable("midvalleyDetails") {
                            // Add midvalleyDetails screen function here
                            midvalleyDetails(
                                navigateBackToEventPromotions  = {
                                    navController.popBackStack("events", inclusive = false)
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            IconButton(
                onClick = {
                    navController.navigate("home")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home Icon",
                    tint = Color.White
                )
            }

            IconButton(
                onClick = {
                    navController.navigate("events")
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Info ,
                    contentDescription = "Event Icon",
                    tint = Color.White
                )
            }



            IconButton(
                onClick = {
                    navController.navigate("review")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star Icon",
                    tint = Color.White
                )
            }


            IconButton(
                onClick = {
                    navController.navigate("profile")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Icon",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomAppBar() {
    AppTheme {
        val mockNavController = rememberNavController()
        BottomAppBar(navController = mockNavController)
    }
}