package com.pnam.watchingsocceronline.data.database.network.impl

import android.content.res.Resources
import com.pnam.watchingsocceronline.data.database.network.UserNetwork
import com.pnam.watchingsocceronline.data.utils.getFakeUser
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Inject

class FakeUserNetworkImpl @Inject constructor() : UserNetwork {
    @Throws(Resources.NotFoundException::class)
    override suspend fun login(email: String, password: String): User {
        val fakeUser: User = getFakeUser() ?: throw Resources.NotFoundException()
        if (fakeUser.email == email && fakeUser.password == password) {
            return fakeUser
        }
        throw Resources.NotFoundException()
    }

    override suspend fun editUser(user: User) {

    }
}