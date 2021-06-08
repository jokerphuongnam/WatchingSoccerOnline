package com.pnam.watchingsocceronline.data.database.local.impl

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.pnam.watchingsocceronline.data.database.local.UserLocal
import com.pnam.watchingsocceronline.data.database.local.dto.UserDto
import com.pnam.watchingsocceronline.data.utils.toDto
import com.pnam.watchingsocceronline.domain.model.User

@Dao
interface UserDao : UserLocal {
    @Query("SELECT * FROM USERS")
    suspend fun findUsersDto(): List<UserDto>

    override suspend fun findUsers(): List<User> {
        return findUsersDto().map { it.toUser() }
    }

    @Query("SELECT * FROM USERS WHERE user_id = :uid")
    suspend fun findUserDto(uid: String): UserDto

    override suspend fun findUser(uid: String): User {
        return findUserDto(uid).toUser()
    }

    @Insert(onConflict = REPLACE)
    suspend fun insertUserDto(user: UserDto)

    override suspend fun insertUser(user: User) {
        insertUserDto(user.toDto())
    }

    @Update
    suspend fun editUserDto(user: UserDto)

    override suspend fun editUser(user: User) {
        editUserDto(user.toDto())
    }

    @Delete
    suspend fun deleteUserDto(user: UserDto)

    override suspend fun deleteUser(user: User) {
        deleteUserDto(user.toDto())
    }
}