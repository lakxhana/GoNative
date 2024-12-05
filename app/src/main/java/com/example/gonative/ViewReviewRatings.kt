package com.example.gonative

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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    val sortOptions = listOf(
        stringResource(R.string.mostRecentRatings),
        stringResource(R.string.highestRatings),
        stringResource(R.string.lowestRatings)
    )

    fun sortReviews(option: String) {
        sortOption.value = option
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 18.dp, start = 365.dp, top = 6.dp)
                    .clickable { isDialogOpen.value = true }
            ) {
                Icon(
                    imageVector = Icons.Filled.FilterList,
                    contentDescription = "Filter Icon",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp, start = 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 18.dp)
                ) {
                    Text(
                        text = placeName,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

                if (imageResource != null) {
                    Image(
                        painter = painterResource(id = imageResource),
                        contentDescription = placeName,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .width(360.dp)
                            .clickable {
                                navController.navigate("viewReviewRatings/$placeName")
                            }
                    )
                }

                Text(
                    text = "Written Review",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular))
                )
            }

            if (isDialogOpen.value) {
                AlertDialog(
                    onDismissRequest = { isDialogOpen.value = false },
                    title = { Text("Sort Reviews") },
                    text = {
                        Column {
                            sortOptions.forEach { option ->
                                TextButton(onClick = {
                                    sortReviews(option)
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
}


@Preview(showBackground = true)
@Composable
fun PreviewViewReviewRatings() {
    val mockNavController = rememberNavController()
    val mockPlaceName = "KLCC"
    ViewReviewRatings(navController = mockNavController, placeName = mockPlaceName)
}