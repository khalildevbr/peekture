package dev.khalil.peekture.model

import com.google.gson.annotations.SerializedName


data class PhotosResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("color")
    val color: String,
    @SerializedName("alt_description")
    val altDescription: String?,
    @SerializedName("urls")
    val urls: Urls,
    @SerializedName("links")
    val links: Links,
    @SerializedName("categories")
    val categories: List<Any>,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    @SerializedName("current_user_collections")
    val currentUserCollections: List<Any>,
    @SerializedName("user")
    val user: User,
    @SerializedName("sponsorship")
    val sponsorship: Sponsorship,
    @SerializedName("promoted_at")
    val promotedAt: String?,
    @SerializedName("description")
    val description: String?
)

data class Urls(
    @SerializedName("raw")
    val raw: String,
    @SerializedName("full")
    val full: String,
    @SerializedName("regular")
    val regular: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("thumb")
    val thumb: String
)

data class Links(
    @SerializedName("self")
    val self: String,
    @SerializedName("html")
    val html: String,
    @SerializedName("download")
    val download: String,
    @SerializedName("download_location")
    val downloadLocation: String
)

data class User(
    @SerializedName("id")
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("twitter_username")
    val twitterUsername: String,
    @SerializedName("portfolio_url")
    val portfolioUrl: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("links")
    val links: UserLinks,
    @SerializedName("profile_image")
    val profileImage: ProfileImage,
    @SerializedName("instagram_username")
    val instagramUsername: String,
    @SerializedName("total_collections")
    val totalCollections: Int,
    @SerializedName("total_likes")
    val totalLikes: Int,
    @SerializedName("total_photos")
    val totalPhotos: Int,
    @SerializedName("accepted_tos")
    val acceptedTos: Boolean
)

data class UserLinks(
    @SerializedName("self")
    val self: String,
    @SerializedName("html")
    val html: String,
    @SerializedName("photos")
    val photos: String,
    @SerializedName("likes")
    val likes: String,
    @SerializedName("portfolio")
    val portfolio: String,
    @SerializedName("following")
    val following: String,
    @SerializedName("followers")
    val followers: String
)

data class ProfileImage(
    @SerializedName("small")
    val small: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("large")
    val large: String
)

data class Sponsorship(
    @SerializedName("impression_urls")
    val impressionUrls: List<String>,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("sponsor")
    val sponsor: Sponsor
)

data class Sponsor(
    @SerializedName("id")
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: Any?,
    @SerializedName("twitter_username")
    val twitterUsername: String,
    @SerializedName("portfolio_url")
    val portfolioUrl: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("location")
    val location: Any?,
    @SerializedName("links")
    val links: Links,
    @SerializedName("profile_image")
    val profileImage: ProfileImage,
    @SerializedName("instagram_username")
    val instagramUsername: String,
    @SerializedName("total_collections")
    val totalCollections: Int,
    @SerializedName("total_likes")
    val totalLikes: Int,
    @SerializedName("total_photos")
    val totalPhotos: Int,
    @SerializedName("accepted_tos")
    val acceptedTos: Boolean
)