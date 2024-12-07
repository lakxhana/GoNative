package com.example.gonative.eventdetailspage

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
fun carbootDetails(
    navigateBackToEventPromotions: () -> Unit
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
            //modifier = Modifier.clickable { navigateBackToEventPromotions() }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp).clickable { navigateBackToEventPromotions() }
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Events/Promotion",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Title and Ratings
        Text(
            text = "Carboot Sale",
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
            painter = painterResource(id = R.drawable.carboot), // Replace with your drawable resource name
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
                text = "Commercial Park, Block C Outdoor Parking, Jalan Atmosphere 5, The Atmosphere, 43400 Seri Kembangan, Selangor",
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
            text = "A car boot sale offers a vibrant marketplace where individuals sell pre-loved items directly from their car trunks.\n\n" +
                    "It's a great way to find unique bargains, vintage treasures, and household essentials at affordable prices.\n\n" +
                    "The event fosters a friendly community spirit, making it enjoyable for both sellers and shoppers.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Pricing and Button
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth(),
        ) {
            //Contact Information
            Text(
                text = "Contact Information",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                //color = Color.Black
            )
            Text(
                text = "Email: enquiry@eksons.com.my\n"+
                        "Phone Number: 012-916 5676\n"+
                        "Website: https://theatmosphere.com.my/car-boot-sale/",
                fontSize = 14.sp,
                color = Color.Gray
            )

        }
    }
}
