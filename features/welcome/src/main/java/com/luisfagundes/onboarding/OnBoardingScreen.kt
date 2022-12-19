package com.luisfagundes.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.*
import com.luisfagundes.extensions.getActivity
import com.luisfagundes.extensions.launchActivity
import com.luisfagundes.theme.FoodlabTheme
import com.luisfagundes.theme.spacing

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        HorizontalPager(
            count = pages.count(),
            modifier = Modifier.fillMaxWidth(),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = MaterialTheme.spacing.default),
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.tertiary
        )
        FinishButton(
            pagerState = pagerState
        ) {
            viewModel.saveOnBoardingState(completed = true)
            context.getActivity()?.run {
                launchActivity(
                    packageName = context.packageName,
                    className = "com.luisfagundes.navigation.NavigationActivity"
                ).also {
                    finish()
                }
            }
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .height(400.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = MaterialTheme.spacing.default,
                        bottomEnd = MaterialTheme.spacing.default
                    )
                ),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "Pager Image",
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.default),
            text = onBoardingPage.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.large)
                .padding(top = MaterialTheme.spacing.verySmall),
            text = onBoardingPage.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButton(
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.large)
            .padding(top = MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                onClick = onClick,
                shape = RectangleShape
            ) {
                Text(
                    text = "Finish",
                    modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true,
    device = Devices.PIXEL_4
)
@Composable
fun OnBoardingScreenPreview() {
    FoodlabTheme {
        OnBoardingScreen()
    }
}