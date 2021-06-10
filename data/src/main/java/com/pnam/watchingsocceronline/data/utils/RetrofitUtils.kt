package com.pnam.watchingsocceronline.data.utils

object RetrofitUtils {
    internal const val BASE_URL: String = "https://lamwebapp.somee.com/"
    internal const val TIMEOUT: Long = 30
    internal const val SUCCESS: Int = 200
    internal const val CREATE: Int = 201
    internal const val BAD_REQUEST: Int = 400
    internal const val NOT_FOUND: Int = 404
    internal const val LIKE: String = "like"
    internal const val DISLIKE: String = "dislike"
    internal const val NONE: String = "none"
    internal const val AVATAR: String = "avatar"
    internal fun convertLocalAvatar(avatar: String): String {
        return if (avatar.equals("N/A", false)) {
            avatar
        } else {
            "https://${avatar.substring(avatar.indexOf("www") until avatar.length)}"
        }
    }
}