package io.github.kasim1011.readbird.feed.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.kasim1011.readbird.home.domain.models.Tweet
import org.jetbrains.compose.resources.stringResource
import readbird.composeapp.generated.resources.Res
import readbird.composeapp.generated.resources.loading
import readbird.composeapp.generated.resources.menu_feeds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedsScreen(
    tweets: List<Tweet>,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    onTweetClick: (Tweet) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.menu_feeds)) },
                modifier = Modifier.statusBarsPadding()
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                // Loading indicator
                Text(stringResource(Res.string.loading), modifier = Modifier.padding(16.dp))
            } else if (tweets.isEmpty()) {
                EmptyFeedsState()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(tweets) { tweet ->
                        TweetCard(
                            tweet = tweet,
                            onClick = { onTweetClick(tweet) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyFeedsState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "No subscriptions yet",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Add accounts to start reading",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}
