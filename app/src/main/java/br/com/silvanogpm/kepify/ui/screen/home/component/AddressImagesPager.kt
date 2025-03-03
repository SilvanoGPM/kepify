package br.com.silvanogpm.kepify.ui.screen.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AddressImagesPager(
    modifier: Modifier = Modifier,
    images: List<String>,
    onChangeCurrentPage: (Int) -> Unit
) {
    val context = LocalContext.current

    val pagerState =
        rememberPagerState(initialPage = 0)

    LaunchedEffect(pagerState.currentPage) {
        onChangeCurrentPage(pagerState.currentPage)
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        HorizontalPager(
            modifier = modifier,
            state = pagerState,
            count = images.size,
            itemSpacing = 10.dp,
        ) { page ->
            val image = images[page]

            AsyncImage(
                model = ImageRequest.Builder(context).data(image).crossfade(true).build(),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp)),
                placeholder = ColorPainter(Color.Gray),
                contentDescription = null,
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}