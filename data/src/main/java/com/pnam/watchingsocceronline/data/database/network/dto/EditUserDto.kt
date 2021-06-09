package com.pnam.watchingsocceronline.data.database.network.dto

import com.pnam.watchingsocceronline.domain.model.User

data class EditUserDto(
    var email: String,
    var avatar: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var birthDay: Long,
    var gender: Boolean
) {
    companion object {
        fun fromUser(user: User): EditUserDto {
            return EditUserDto(
                user.email,
                user.avatar,
                user.password,
                user.firstName,
                user.lastName,
                user.birthDay,
                user.gender.toBoolean()
            )
        }
    }
}