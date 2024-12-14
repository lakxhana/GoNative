package com.example.gonative.placedetailspage

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gonative.R
import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.platform.LocalContext
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

@Composable
fun klccDetails(
    navigateBackToHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Back Arrow
        Row(
            verticalAlignment = Alignment.CenterVertically,
            //modifier = Modifier.clickable { navigateBackToHome() }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp).clickable { navigateBackToHome() }
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Home",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Title and Ratings
        Text(
            text = "KLCC",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Row(
            modifier = Modifier.padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "4.7", fontSize = 14.sp, color = Color.Gray)
            Text(text = " (12k+ reviews)", fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            //Text(text = "60k+ booked", fontSize = 14.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Image/Video Placeholder
        Image(
            painter = painterResource(id = R.drawable.klcc), // Replace with your drawable resource name
            contentDescription = "carboot Event Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .width(310.dp),
            //contentScale = ContentScale.Crop // Adjust the scale to fit the image properly
        )


        Spacer(modifier = Modifier.height(8.dp))

        // Location
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "241, Petronas Twin Tower, Kuala Lumpur City Centre, 50088 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Highlights
        Text(
            text = "Highlights",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Text(
            text = "Kuala Lumpur City Centre (KLCC) is a multipurpose development area in Kuala Lumpur, Malaysia. KLCC refers to the area within and surrounding the KLCC Park but the term has also been widely used by buildings nearby to the vicinity.\n\n" +
                    "Designed to be a city within a city, the 100-acre site hosts the tallest twin buildings in the world, the fourth tallest hotel in the world, a shopping mall, office buildings and several hotels.\n\n" +
                    "Areas within KLCC is cooled via district cooling located on the property.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Pricing and Button
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth(),
        ) {



        }
    }
}