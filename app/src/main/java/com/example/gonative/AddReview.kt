package com.example.gonative

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReview(navController: NavController, placeName: String) {
    var rating by remember { mutableStateOf(0f) }
    var reviewText by remember { mutableStateOf("") }
    val currentDate = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) }

    val authViewModel: AuthViewModel = viewModel()
    val context = LocalContext.current // Get context for Toast

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Rate $placeName",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Button",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Rating: ${rating.toInt()}", fontSize = 16.sp)
                Slider(
                    value = rating,
                    onValueChange = { rating = it },
                    valueRange = 0f..5f,
                    steps = 5,
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = reviewText,
                    onValueChange = { reviewText = it },
                    label = { Text("Write your review") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        val user = authViewModel.getCurrentUser()
                        if (user != null) {
                            Log.d("Auth", "User ID: ${user.uid}")
                            authViewModel.saveReviewToFirebase(user.uid, placeName, rating, reviewText, currentDate)

                            Toast.makeText(context, "Review submitted successfully!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Log.e("Auth", "User is not authenticated")
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Submit Review")
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddReview() {
    val mockNavController = rememberNavController()
    val mockPlaceName = "KLCC"
    AddReview(navController = mockNavController, placeName = mockPlaceName)
}
