package com.pnam.watchingsocceronline.data.database.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.util.toGender

@Entity(tableName = "users")
data class UserDto(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    var uid: String,
    @ColumnInfo(name = "avatar")
    var avatar: String,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "password")
    var password: String,
    @ColumnInfo(name = "first_name")
    var firstName: String,
    @ColumnInfo(name = "last_name")
    var lastName: String,
    @ColumnInfo(name = "birth_day")
    var birthDay: Long,
    @ColumnInfo(name = "gender")
    var gender: Boolean
) {
    fun toUser(): User = User(
        uid,
        avatar,
        email,
        password,
        firstName,
        lastName,
        birthDay,
        gender.toGender()
    )

}