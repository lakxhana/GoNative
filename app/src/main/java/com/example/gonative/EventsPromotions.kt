package com.example.gonative

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.TextButton
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gonative.eventmodels.Place
import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.IconButton
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun EventsPromotions(navController: NavHostController) {
    val places = listOf(
        Place(R.string.eventPromotion1, R.drawable.halloween, "halloweenDetails", "31-10-2024"),
        Place(R.string.eventPromotion2, R.drawable.carboot, "carbootDetails", "15-11-2024"),
        Place(R.string.eventPromotion3, R.drawable.midvalley, "midvalleyDetails", "01-12-2024")
    )
    val searchQuery = remember { mutableStateOf("") }
    val selectedDate = remember { mutableStateOf("") } // Store the selected date
    val context = LocalContext.current

    val filteredPlaces = places.filter { place ->
        (searchQuery.value.isEmpty() ||
                stringResource(id = place.nameResId).contains(searchQuery.value, ignoreCase = true)) &&
                (selectedDate.value.isEmpty() || place.date == selectedDate.value)
    }

    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Events/Promotions",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopStart)
                .padding(top = 30.dp, start = 43.dp)
        )


        SearchBarWithDatePicker(
            searchQuery = searchQuery,
            selectedDate = selectedDate,
            onDatePicked = { selectedDate.value = it },
            context = context,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 70.dp, start = 40.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopStart)
                .padding(top = 135.dp, start = 40.dp, end = 40.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            filteredPlaces.forEach { place ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(id = place.nameResId),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Image(
                    painter = painterResource(id = place.imageResId),
                    contentDescription = stringResource(id = place.nameResId),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                        .height(200.dp)
                        .width(310.dp)
                )
                TextButton(
                    onClick = { navController.navigate(place.detailsRoute) },
                    modifier = Modifier.align(Alignment.Start),

                    ) {
                    Icon(
                        imageVector = Icons.Default.Info, // icon info :<
                        contentDescription = "Date Picker Icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(text = "More Info", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@Composable
fun SearchBarWithDatePicker(
    searchQuery: MutableState<String>,
    selectedDate: MutableState<String>,
    onDatePicked: (String) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        TextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            placeholder = {
                Text(
                    text = stringResource(R.string.search),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .height(50.dp)
                .weight(1f),
            singleLine = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )
            }
        )
        IconButton(
            onClick = {
                showDatePicker(context) { selectedDate ->
                    onDatePicked(selectedDate)
                }
            },
            modifier = Modifier.size(50.dp)
        ) {
            Icon(
                imageVector = Icons.Default.DateRange, //xde icon calender :<
                contentDescription = "Date Picker Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@SuppressLint("DefaultLocale")
fun showDatePicker(context: Context, onDatePicked: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePicker = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val date = String.format("%02d-%02d-%04d", selectedDay, selectedMonth + 1, selectedYear)
            onDatePicked(date)
        },
        year,
        month,
        day
    )
    datePicker.show()
}