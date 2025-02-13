package com.example.animedatabase

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val profileImageUri = remember { mutableStateOf<Uri?>(null) }
    val username = remember { mutableStateOf("John Doe") }
    val email = remember { mutableStateOf("johndoe@example.com") }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.04).sp

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Top Bar with Title and Icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        Modifier.fillMaxSize()
                    )
                }

                Text(
                    "Profile",
                    fontSize = dynamicFontSize * 1.5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(4.dp)
                )

                Row(horizontalArrangement = Arrangement.End) {
                    IconButton(
                        onClick = { navController.navigate("home") },
                        modifier = Modifier
                            .size(50.dp)
                            .padding(4.dp)
                    ) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home",
                            Modifier.fillMaxSize()
                        )
                    }
                    IconButton(
                        onClick = { navController.navigate("search") },
                        modifier = Modifier
                            .size(50.dp)
                            .padding(4.dp)
                    ) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    IconButton(
                        onClick = { navController.navigate("profile") },
                        modifier = Modifier
                            .size(50.dp)
                            .padding(4.dp)
                    ) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "Account",
                            Modifier.fillMaxSize()
                        )
                    }
                }
            }

            // Spacer to push content below
            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Profile Image and User Info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Profile Image
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .background(Color.Gray)
                    ) {
                        // Default placeholder image
                        Image(
                            painter = painterResource(id = R.drawable.profile_default),
                            contentDescription = "Profile Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // User Info
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = username.value,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = email.value,
                            fontSize = 16.sp,
                            color = Color.Black.copy(alpha = 0.7f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Change Profile Image Button
                OutlinedButton(
                    onClick = {
                        // Replace with an image picker functionality (e.g., file picker or camera)
                        // For demonstration, we'll just show a toast
                        Toast.makeText(context, "Change Profile Image", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Change Profile Image")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Additional Info (like bio, preferences, etc.)
                Text(
                    text = "Bio: Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = "Joined: January 1, 2025",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}