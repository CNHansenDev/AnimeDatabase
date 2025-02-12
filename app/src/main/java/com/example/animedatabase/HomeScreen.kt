import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.copy
import com.example.animedatabase.R
import kotlin.math.roundToInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animedatabase.LoginDialogue

@Composable
fun HomeScreen(navController: NavController) {
    var showDialogue by remember { mutableStateOf(true) } // Show login dialog initially

    if (showDialogue) {
        LoginDialogue(
            onDismiss = { showDialogue = false }, // Close dialog on guest mode
            onLogin = { email, password ->
                // Handle login logic (e.g., call a login API)
                println("Logging in with: $email")
                showDialogue = false
            }
        )
    }
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
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp)
                )

                Text(
                    "AnimeDatabase",
                    fontSize = dynamicFontSize * 1.5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(4.dp)
                )

                Row (
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = { navController.navigate("search") },
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(4.dp)
                    ) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search",
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = { navController.navigate("profile") },
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.White)
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

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                items(dummyAnimeList) { anime -> AnimeItem(anime)}
            }
        }

        // **Buttons Positioned at the Bottom**
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // Aligns to bottom of screen
                .padding(bottom = 16.dp) // Add some space from the bottom
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(
                    onClick = { navController.navigate("watched") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    border = BorderStroke(3.dp, color = Color.Black),
                    modifier = Modifier
                        .width(165.dp)
                        .height(50.dp)
                ) {
                    Text("Watched List", color = Color.Black)
                }
                OutlinedButton(
                    onClick = { navController.navigate("to_watch") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    border = BorderStroke(3.dp, color = Color.Black),
                    modifier = Modifier
                        .width(165.dp)
                        .height(50.dp)
                ) {
                    Text("To-Watch List", color = Color.Black)
                }
            }
        }
    }
}

//Placeholder for future anime api microservice
data class Anime(val title: String, @DrawableRes val imageRes: Int, val rating: Int)

val dummyAnimeList = listOf(
    Anime("Naruto", R.drawable.naruto, 75),
    Anime("One Piece", R.drawable.one_piece, 88),
    Anime("Attack on Titan", R.drawable.attack_on_titan, 84),
    Anime("Demon Slayer", R.drawable.demon_slayer, 56),
    Anime("Full Metal Alchemist: Brotherhood", R.drawable.full_metal_alchemist_brotherhood, 92),
    Anime("Hunter x Hunter", R.drawable.hunter_x_hunter, 88),
    Anime("My Hero Academia", R.drawable.my_hero_academia, 75),
    Anime("Dragon Ball Z", R.drawable.dragon_ball_z, 45),
    Anime("Dragon Ball", R.drawable.dragon_ball, 72),
    Anime("Cowboy Bebop", R.drawable.cowboy_bebop, 87),
    Anime("Baccano!", R.drawable.baccano, 34),
    Anime("Neon Genesis Evangelion", R.drawable.neon_genesis_evangelion, 63),
    Anime("Mob Psycho 100", R.drawable.mob_psycho_100, 90),
    Anime("Hajime No Ippo", R.drawable.hajime_no_ippo, 80),
    Anime("Monster", R.drawable.monster, 68),
    Anime("Samurai Champloo", R.drawable.samurai_champloo, 72),
)

@Composable
fun AnimeItem(anime: Anime) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.04).sp
    val (fullStars, hasHalfStar, emptyStars) = convertPercentageToStars(anime.rating)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp) // Ensures all boxes have the same width
            .height(220.dp), // Ensures all boxes have the same height
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, Color.Gray), // Optional: Border around the box
        elevation = CardDefaults.elevatedCardElevation(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(fullStars) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Full Star",
                        modifier = Modifier.size(14.dp)
                    )
                }

                // Half star
                if (hasHalfStar) {
                    Image(
                        painter = painterResource(id = R.drawable.half_star),
                        contentDescription = "Half Star",
                        modifier = Modifier.size(14.dp)
                    )
                }

                // Empty stars
                repeat(emptyStars) {
                    Image(
                        painter = painterResource(id = R.drawable.star_outline),
                        contentDescription = "Empty Star",
                        modifier = Modifier.size(14.dp)
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(2.dp, 1.dp),
                    text = "${anime.rating.toString()}%",
                    color = Color.Black,
                    fontSize = dynamicFontSize / 1.3,
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f), // Maintains aspect ratio
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.elevatedCardElevation(4.dp)
            ) {
                Image(
                    painter = painterResource(id = anime.imageRes),
                    contentDescription = anime.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )

            }

            Spacer(modifier = Modifier.height(4.dp)) // Space between image and text

            Text(
                text = anime.title,
                color = Color.Black,
                fontSize = dynamicFontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
    }
}

fun convertPercentageToStars(percentage: Int): Triple<Int, Boolean, Int> {
    val fullStars = percentage / 20 // Full stars (each representing 20%)
    val hasHalfStar = (percentage % 20) >= 10 // If the remainder is 10 or more, we consider a half star
    val emptyStars = 5 - fullStars - (if (hasHalfStar) 1 else 0) // The rest are empty stars

    return Triple(fullStars, hasHalfStar, emptyStars)
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}