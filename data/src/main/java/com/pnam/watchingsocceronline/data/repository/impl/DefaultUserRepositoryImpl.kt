package com.pnam.watchingsocceronline.data.repository.impl

import com.pnam.watchingsocceronline.data.database.local.CurrentUser
import com.pnam.watchingsocceronline.data.database.local.UserLocal
import com.pnam.watchingsocceronline.data.database.network.UserNetwork
import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.data.throwable.EmailInvalid
import com.pnam.watchingsocceronline.data.throwable.NotFoundException
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Inject

class DefaultUserRepositoryImpl @Inject constructor(
    override val currentUser: CurrentUser,
    override val userLocal: UserLocal,
    override val userNetwork: UserNetwork
) : UserRepository {

    override suspend fun getUid(): String? {
        return currentUser.findUid()
    }

    @Throws(NotFoundException::class)
    override suspend fun getUser(): User {
        val uid: String = currentUser.findUid() ?: throw NotFoundException()
        return userLocal.findUser(uid)
    }

    @Throws(NotFoundException::class)
    override suspend fun getUser(uid: String): User {
        return userNetwork.fetchUser(uid)
    }

    override suspend fun login(email: String, password: String): User {
        return userNetwork.login(email, password)
    }

    override suspend fun saveUser(user: User) {
        userLocal.insertUser(user)
        currentUser.changeCurrentUser(user.uid)
    }

    @Throws(NotFoundException::class, EmailInvalid::class)
    override suspend fun register(user: User) {
        userNetwork.register(user)
    }

    override suspend fun edit(user: User) {
        userLocal.editUser(userNetwork.editUser(user))
    }

    override suspend fun uploadAvatar(uid: String, avatar: ByteArray) {
        userLocal.editUser(userNetwork.uploadAvatar(uid, avatar))
    }

    override suspend fun removeAvatar(uid: String) {
        userLocal.editUser(userNetwork.removeAvatar(uid))
    }

    @Throws(NotFoundException::class)
    override suspend fun changePassword(user: User) {
        userLocal.editUser(userNetwork.changePassword(user))
    }

    override suspend fun signOut() {
        currentUser.signOut()
    }
}