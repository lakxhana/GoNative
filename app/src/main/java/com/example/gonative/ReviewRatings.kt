package com.example.gonative

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
@Composable
fun ReviewRatings(navController: NavController) {
    val places = listOf(
        Triple(R.string.reviewPlace1, R.drawable.botanicalgarden, 5),
        Triple(R.string.reviewPlace2, R.drawable.pasarseni, 4),
        Triple(R.string.reviewPlace3, R.drawable.klcc, 3)
    )
    val searchQuery = remember { mutableStateOf("") }
    val filteredPlaces = if (searchQuery.value.isEmpty()) {
        places
    } else {
        places.filter {
            // Use stringResource to get the name of the place and compare it to the search query
            stringResource(it.first).contains(searchQuery.value, ignoreCase = true)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Text(
            text = stringResource(R.string.reviewRatings_name),
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopStart)
                .padding(top = 30.dp, start = 43.dp)
        )

        // Search bar
        SearchBar(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 70.dp, start = 40.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopStart)
                .padding(top = 135.dp, start = 40.dp)
        ) {
            filteredPlaces.forEach { place ->
                val placeName = stringResource(place.first)

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
                    RowOfStars(starCount = place.third, starColor = MaterialTheme.colorScheme.surfaceTint)
                }

                Image(
                    painter = painterResource(id = place.second),
                    contentDescription = placeName,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .width(310.dp)
                        .clickable {
                            navController.navigate("viewReviewRatings/$placeName")
                        }
                )
            }
        }
    }
}


@Composable
fun RowOfStars(
    starCount: Int,
    starColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(starCount) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Star Icon",
                tint = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                fontSize = 14.sp,
                color = Color.Gray
            )
        },
        modifier = modifier
            .height(50.dp)
            .width(310.dp),
        singleLine = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewReviewRatings() {
    val navController = rememberNavController()
    ReviewRatings(navController)
}
