import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animedatabase.R

@Composable
fun WatchedListScreen(navController: NavController) {
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
                    "Watched List",
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
                        Icon(Icons.Default.Home, contentDescription = "Home", Modifier.fillMaxSize())
                    }
                    IconButton(
                        onClick = { navController.navigate("search") },
                        modifier = Modifier
                            .size(50.dp)
                            .padding(4.dp)
                    ) {
                        Icon(Icons.Filled.Search, contentDescription = "Search", modifier = Modifier.fillMaxSize())
                    }
                    IconButton(
                        onClick = { navController.navigate("profile") },
                        modifier = Modifier
                            .size(50.dp)
                            .padding(4.dp)
                    ) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Account", Modifier.fillMaxSize())
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
                items(watchedAnimeList) { anime -> AnimeItem(anime) }
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

// Example Watched List Data (Modify as needed)
val watchedAnimeList = listOf(
    Anime("Naruto", R.drawable.naruto, 75),
    Anime("Attack on Titan", R.drawable.attack_on_titan, 84),
    Anime("Demon Slayer", R.drawable.demon_slayer, 56),
)

@Preview(showBackground = true)
@Composable
fun WatchedListPreview() {
    val navController = rememberNavController()
    WatchedListScreen(navController = navController)
}