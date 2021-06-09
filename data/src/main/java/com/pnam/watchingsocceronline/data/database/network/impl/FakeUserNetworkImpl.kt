package com.pnam.watchingsocceronline.data.database.network.impl

import android.content.res.Resources
import com.pnam.watchingsocceronline.data.database.network.UserNetwork
import com.pnam.watchingsocceronline.data.throwable.EmailInvalid
import com.pnam.watchingsocceronline.data.throwable.NotFoundException
import com.pnam.watchingsocceronline.data.utils.addUser
import com.pnam.watchingsocceronline.data.utils.getFakeUsers
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Inject

class FakeUserNetworkImpl @Inject constructor() : UserNetwork {
    @Throws(NotFoundException::class)
    override suspend fun login(email: String, password: String): User {
        val fakeUsers: List<User> = getFakeUsers()
        for (fakeUser in fakeUsers) {
            if (fakeUser.email == email && fakeUser.password == password) {
                return fakeUser
            }
        }
        throw Resources.NotFoundException()
    }

    @Throws(NotFoundException::class)
    override suspend fun editUser(user: User): User {
        val fakeUsers: List<User> = getFakeUsers()
        for (fakeUser in fakeUsers) {
            if (fakeUser.uid.equals(user.uid)) {
                fakeUser.firstName = user.firstName
                fakeUser.lastName = user.lastName
                fakeUser.gender = user.gender
                fakeUser.birthDay = user.birthDay
            }
            return fakeUser
        }
        throw NotFoundException()
    }

    @Throws(NotFoundException::class)
    override suspend fun changePassword(user: User): User {
        val fakeUsers: List<User> = getFakeUsers()
        for (fakeUser in fakeUsers) {
            if (fakeUser.uid.equals(user.uid)) {
                fakeUser.password = user.password
            }
            return fakeUser
        }
        throw NotFoundException()
    }

    @Throws(NotFoundException::class)
    override suspend fun fetchUser(uid: String): User {
        val fakeUsers: List<User> = getFakeUsers()
        for (fakeUser in fakeUsers) {
            if (fakeUser.uid.equals(uid)) {
                return fakeUser
            }
        }
        throw NotFoundException()
    }

    @Throws(NotFoundException::class, EmailInvalid::class)
    override suspend fun register(user: User): User {
        val fakeUsers = getFakeUsers()
        var tempUser: User? = null
        fakeUsers.forEach {
            if (it.email == user.email) {
                tempUser == it
            }
        }
        tempUser?.let {
            EmailInvalid()
        }
        return addUser(user)
    }
}