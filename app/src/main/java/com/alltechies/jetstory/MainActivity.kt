package com.alltechies.jetstory

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.alltechies.jetstory.ui.theme.JetStoryTheme
import com.alltechies.storyui.data.StoryIndicator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetStoryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val localContext = LocalContext.current

                    val imageList = listOf(
                        "https://picsum.photos/2400",
                        "https://picsum.photos/2200",
                        "https://picsum.photos/1200",
                        "https://picsum.photos/seed/picsum/1800/1300",
                    )

                    SingleIndicatorDemo(localContext, imageList)
                }
            }
        }
    }
}

@Composable
fun SingleIndicatorDemo(localContext: Context, imageList: List<String>) {
    Story(
        urlList = imageList,
        swipeTime = 15_000,
        indicator = StoryIndicator.singleIndicator(
            indicatorColor = Color.White,
            indicatorTrackColor = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ),
    ) {
        Toast.makeText(localContext, "All stories shown", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun MultiIndicatorDemo(localContext: Context, imageList: List<String>) {
    Story(
        urlList = imageList,
        indicator = StoryIndicator.multiIndicator(
            indicatorColor = Color.White,
            indicatorTrackColor = Color.Gray,
            indicatorPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier.clip(RoundedCornerShape(16.dp))
        ),
    ) {
        Toast.makeText(localContext, "All stories shown", Toast.LENGTH_SHORT).show()
    }
}
