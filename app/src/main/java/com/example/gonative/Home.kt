package com.example.gonative

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
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

data class Place(
    val nameResId: Int,
    val imageResId: Int,
    val detailsRoute: String
)

@Composable
fun Home(navController: NavController) {
    val places = listOf(
        Place(R.string.reviewPlace1, R.drawable.botanicalgarden, "botanicalDetails"),
        Place(R.string.reviewPlace2, R.drawable.pasarseni, "pasarSeniDetails"),
        Place(R.string.reviewPlace3, R.drawable.klcc, "klccDetails")
    )
    val searchQuery = remember { mutableStateOf("") }

    val filteredPlaces = if (searchQuery.value.isEmpty()) {
        places
    } else {
        places.filter {
            stringResource(it.nameResId).contains(searchQuery.value, ignoreCase = true)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.home_name),
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 30.dp, start = 43.dp)
        )

        HomeSearchBar(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 70.dp, start = 40.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 135.dp, start = 40.dp)
        ) {
            filteredPlaces.forEach { place ->
                Text(
                    text = stringResource(place.nameResId),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular))
                )
                Image(
                    painter = painterResource(place.imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .width(310.dp)
                        .padding(bottom = 0.dp)
                )
                // Use Column to stack the button below the image
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextButton(
                        onClick = { navController.navigate(place.detailsRoute) },

                        modifier = Modifier
                            .padding(start = 236.dp, bottom = 3.dp)
                            .height(35.dp)
                    ) {
                        Text("More Info", color = MaterialTheme.colorScheme.primary,   fontSize = 14.sp,)
                    }
                }
            }
        }
    }
}


@Composable
fun HomeSearchBar(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
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
fun PreviewHome() {
    val mockNavController = rememberNavController()
    Home(navController = mockNavController)
}

