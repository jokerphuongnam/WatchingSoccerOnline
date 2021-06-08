package com.pnam.watchingsocceronline.data.database.network.impl

import android.content.res.Resources
import com.pnam.watchingsocceronline.data.database.network.UserNetwork
import com.pnam.watchingsocceronline.data.throwable.NotFoundException
import com.pnam.watchingsocceronline.data.throwable.WrongException
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

    override suspend fun editUser(user: User) {

    }

    @Throws(WrongException::class)
    override suspend fun register(user: User) {
        val fakeUsers = getFakeUsers()
        var tempUser: User? = null
        fakeUsers.forEach {
            if(it.email == user.email){
                tempUser == it
            }
        }
        tempUser?: throw WrongException()
    }
}