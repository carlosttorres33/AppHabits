package com.carlostorres.apphabits.onboarding.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.carlostorres.apphabits.core.presentation.HabitButton
import com.carlostorres.apphabits.core.presentation.HabitTitle
import com.carlostorres.apphabits.onboarding.presentation.OnboardingPagerInfo
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingPager(
    pages: List<OnboardingPagerInfo>,
    modifier: Modifier = Modifier,
    onFinish: () -> Unit
) {

    val pagerState = rememberPagerState()

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .background(Color.White)
    ) {

        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) { index ->

            val information = pages[index]

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(32.dp))

                HabitTitle(
                    title = information.title
                )

                Spacer(modifier = Modifier.height(32.dp))

                Image(
                    modifier = Modifier
                        .aspectRatio(1f),
                    painter = painterResource(information.image),
                    contentDescription = "OB1",
                    contentScale = ContentScale.FillHeight
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = information.subtitle.uppercase(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.tertiary
                )

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            if (pagerState.currentPage == pages.lastIndex) {

                HabitButton(
                    text = "Get Started",
                    modifier = Modifier.fillMaxWidth()
                ){
                    onFinish()
                }

            } else {

                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pages.lastIndex)
                        }
                    }
                ) {

                    Text(
                        text = "Skip",
                        color = MaterialTheme.colorScheme.tertiary
                    )

                }

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = MaterialTheme.colorScheme.primary,
                    inactiveColor = MaterialTheme.colorScheme.tertiary
                )

                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                ) {

                    Text(
                        text = "Next",
                        color = MaterialTheme.colorScheme.tertiary
                    )

                }

            }
        }
    }
}
