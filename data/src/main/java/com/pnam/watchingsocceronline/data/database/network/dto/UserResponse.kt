package com.pnam.watchingsocceronline.data.database.network.dto

import com.pnam.watchingsocceronline.data.utils.RetrofitUtils.convertLocalAvatar
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.util.toGender

class UserResponse(
    var id: String,
    var avatar: String,
    var email: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var birthDay: Long,
    var createDate: Long,
    var gender: Boolean
) {
    fun toUser(): User {
        return User(
            id,
            convertLocalAvatar(avatar),
            email,
            password,
            firstName,
            lastName,
            birthDay,
            gender.toGender()
        )
    }
}