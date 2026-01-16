package io.github.kasim1011.readbird.home.domain.models

import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class Tweet(
    val id: String,
    val text: String,
    val author: Author,
    val createdAt: Instant,
    val mediaUrls: List<String> = emptyList(),
    val isRetweet: Boolean = false,
    val retweetedBy: Author? = null
)

@Serializable
data class Author(
    val id: String,
    val username: String,
    val displayName: String,
    val avatarUrl: String?
)

@Serializable
data class Subscription(
    val id: String,
    val author: Author,
    val addedDate: Instant,
    val category: String? = null,
    var lastFetched: Instant? = null
)

enum class AppScreen {
    FEEDS, SUBSCRIPTIONS, SETTINGS
}

data class AppState(
    val currentScreen: AppScreen = AppScreen.FEEDS,
    val tweets: List<Tweet> = emptyList(),
    val subscriptions: List<Subscription> = emptyList(),
    val isLoading: Boolean = false,
    val lastUpdated: Instant? = null
)
