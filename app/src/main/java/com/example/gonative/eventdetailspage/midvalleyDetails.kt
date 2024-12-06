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
fun midvalleyDetails(
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
            text = "Midvalley Exhibition",
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
            painter = painterResource(id = R.drawable.midvalley), // Replace with your drawable resource name
            contentDescription = "Midvalley Event Image",
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
                text = "Third Floor (3F) @ Mid Valley Megamall, Centre Court, Lingkaran Syed Putra, Mid Valley City, 59200 Kuala Lumpur, Federal Territory of Kuala Lumpur",
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
            text = "Midvalley Exhibitions feature a wide range of events, from trade shows to cultural and lifestyle exhibitions, attracting diverse crowds. \n\n" +
                    "Visitors can explore the latest products, innovations, and trends across various industries in a dynamic environment.\n\n" +
                    "The exhibitions provide exciting opportunities for networking, entertainment, and discovering new brands and ideas.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // contact information
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth(),
            //horizontalAlignment = Arrangement.SpaceBetween
        ) {
            //Contact Information
            Text(
                text = "Contact Information",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                //color = Color.Black
            )
            Text(
                text = "Email: mvec@midvalleycity.com\n"+
                        "Phone Number: +603 2289 8688\n"+
                        "Website: http://www.mvec.com.my",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
