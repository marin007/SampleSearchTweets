package com.example.demo_list_tweets.domain.entity

data class Tweet(
    val statuses: List<TweetContainer>
)

data class TweetContainer(
    val id: String?,
    val text: String?,
    val entities: Entities,
    val user: User
)

data class Entities(
    val urls: List<URLs>
)

data class URLs(
    val expanded_url: String?
)

data class User(
    val id: String?,
    val name: String?,
    val profile_background_color: String?,
    val profile_image_url_https: String?
)

