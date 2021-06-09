package com.pnam.watchingsocceronline.data.database.network.dto

import com.pnam.watchingsocceronline.domain.model.User

data class RegisterDto(
    var email: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var birthDay: Long,
    var gender: Boolean
) {
    companion object {
        fun fromUser(user: User): RegisterDto {
            return RegisterDto(
                user.email,
                user.password,
                user.firstName,
                user.lastName,
                user.birthDay,
                user.gender.toBoolean()
            )
        }
    }
}