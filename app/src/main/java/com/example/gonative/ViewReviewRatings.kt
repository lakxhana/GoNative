package com.example.gonative

import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewReviewRatings(navController: NavController, placeName: String) {
    val imageResource = when (placeName) {
        "KLCC" -> R.drawable.klcc
        "Botanical Garden" -> R.drawable.botanicalgarden
        "Pasar Seni" -> R.drawable.pasarseni
        else -> null
    }

    val isDialogOpen = remember { mutableStateOf(false) }
    val sortOption = remember { mutableStateOf("") }
    val reviews = remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }
    val viewModel: AuthViewModel = viewModel()

    LaunchedEffect(placeName) {
        viewModel.fetchReviewsByPlace(placeName) { fetchedReviews ->

            val filteredReviews = fetchedReviews.filter { review ->
                review["placeName"] == placeName
            }
            reviews.value = filteredReviews
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = placeName,
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
                actions = {

                    IconButton(onClick = { isDialogOpen.value = true }) {
                        Icon(
                            imageVector = Icons.Filled.FilterList,
                            contentDescription = "Filter Reviews",
                            tint = Color.White
                        )
                    }

                    IconButton(onClick = {
                        navController.navigate("addReview/${placeName}")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Edit ,
                            contentDescription = "Pencil Icon",
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
                horizontalAlignment = Alignment.Start
            ) {
                imageResource?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = placeName,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .width(360.dp)
                    )
                }

                Text(
                    text = "Reviews for $placeName",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                if (reviews.value.isEmpty()) {
                    Text(
                        text = "No reviews yet.",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(reviews.value) { review ->
                            ReviewCard(review)
                        }
                    }
                }
            }

            if (isDialogOpen.value) {
                AlertDialog(
                    onDismissRequest = { isDialogOpen.value = false },
                    title = { Text("Sort Reviews") },
                    text = {
                        Column {
                            listOf("Most Recent", "Highest Rating", "Lowest Rating").forEach { option ->
                                TextButton(onClick = {
                                    sortOption.value = option
                                    isDialogOpen.value = false
                                }) {
                                    Text(option)
                                }
                            }
                        }
                    },
                    confirmButton = {},
                    dismissButton = {
                        TextButton(onClick = { isDialogOpen.value = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}@Composable
fun ReviewCard(review: Map<String, Any>) {
    val rating = review["rating"] as? Float ?: 0f
    val text = review["reviewText"] as? String ?: "No review provided"
    val date = review["date"] as? String ?: "Unknown date"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(5) { index ->
                    val starIcon = if (index < rating) {
                        Icons.Filled.Star
                    } else {
                        Icons.Filled.StarBorder
                    }
                    Icon(
                        imageVector = starIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Text(
                    text = "${"%.1f".format(rating)}/5",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(text = text, fontSize = 14.sp)
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(text = "Date: $date", fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewViewReviewRatings() {
    val mockNavController = rememberNavController()
    val mockPlaceName = "KLCC"
    ViewReviewRatings(navController = mockNavController, placeName = mockPlaceName)
}