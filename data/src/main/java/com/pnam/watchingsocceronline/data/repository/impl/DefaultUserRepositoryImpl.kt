package com.pnam.watchingsocceronline.data.repository.impl

import android.content.res.Resources
import com.pnam.watchingsocceronline.data.database.local.CurrentUser
import com.pnam.watchingsocceronline.data.database.local.UserLocal
import com.pnam.watchingsocceronline.data.database.network.UserNetwork
import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Inject

class DefaultUserRepositoryImpl @Inject constructor(
    override val currentUser: CurrentUser,
    override val userLocal: UserLocal,
    override val userNetwork: UserNetwork
) : UserRepository {

    override suspend fun getUid(): Long? {
        return currentUser.findUid()
    }

    override suspend fun getUser(): User {
        val uid: Long = currentUser.findUid() ?: throw Resources.NotFoundException()
        return userLocal.findUser(uid)
    }

    override suspend fun login(email: String, password: String): User {
        return userNetwork.login(email, password).also {
            userLocal.insertUser(it)
            currentUser.changeCurrentUser(it.uid)
        }
    }

    override suspend fun edit(user: User) {
        userNetwork.editUser(user)
        userLocal.editUser(user)
    }

    override suspend fun logOut() {
        currentUser.signOut()
    }
}