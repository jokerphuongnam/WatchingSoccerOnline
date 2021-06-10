package com.pnam.watchingsocceronline.data.database.network.impl

import com.pnam.watchingsocceronline.data.database.network.UserNetwork
import com.pnam.watchingsocceronline.data.database.network.dto.*
import com.pnam.watchingsocceronline.data.throwable.EmailInvalid
import com.pnam.watchingsocceronline.data.throwable.NotFoundException
import com.pnam.watchingsocceronline.data.utils.RetrofitUtils.AVATAR
import com.pnam.watchingsocceronline.data.utils.RetrofitUtils.BAD_REQUEST
import com.pnam.watchingsocceronline.data.utils.RetrofitUtils.CREATE
import com.pnam.watchingsocceronline.data.utils.RetrofitUtils.SUCCESS
import com.pnam.watchingsocceronline.data.utils.toMultipartBody
import com.pnam.watchingsocceronline.domain.model.User
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Inject

class RetrofitUserNetworkImpl @Inject constructor(
    private val service: UserService
) : UserNetwork {

    @Throws(NotFoundException::class)
    override suspend fun login(email: String, password: String): User {
        val response = service.login(LoginDto(email, password))
        return if (response.code().equals(SUCCESS)) {
            response.body()!!.toUser()
        } else {
            throw NotFoundException()
        }
    }

    @Throws(NotFoundException::class)
    override suspend fun editUser(user: User): User {
        val response = service.editUser(EditUserDto.fromUser(user), user.uid)
        return if (response.code().equals(SUCCESS)) {
            response.body()!!.toUser()
        } else {
            throw NotFoundException()
        }
    }

    @Throws(NotFoundException::class)
    override suspend fun changePassword(user: User): User {
        val response = service.changePassword(ChangePasswordDto.fromUser(user), user.uid)
        return if (response.code().equals(SUCCESS)) {
            response.body()!!.toUser()
        } else {
            throw NotFoundException()
        }
    }

    @Throws(NotFoundException::class)
    override suspend fun fetchUser(uid: String): User {
        val response = service.fetchUser(uid)
        return if (response.code().equals(SUCCESS)) {
            response.body()!!.toUser()
        } else {
            throw NotFoundException()
        }
    }

    @Throws(NotFoundException::class, EmailInvalid::class)
    override suspend fun register(user: User): User {
        val response = service.register(RegisterDto.fromUser(user))
        when {
            response.code().equals(CREATE) -> {
                return response.body()!!.toUser()
            }
            response.code().equals(BAD_REQUEST) -> {
                throw EmailInvalid()
            }
            else -> {
                throw NotFoundException()
            }
        }
    }

    override suspend fun uploadAvatar(uid: String, avatar: ByteArray): User {
        return service.uploadAvatar(uid, avatar.toMultipartBody(AVATAR)).body()!!.toUser()
    }

    override suspend fun removeAvatar(uid: String): User {
        return service.removeAvatar(uid).body()!!.toUser()
    }

    interface UserService {
        @POST("api/user/signin")
        suspend fun login(
            @Body login: LoginDto
        ): Response<UserResponse>

        @POST("api/user/{id}/edit")
        suspend fun editUser(
            @Body user: EditUserDto,
            @Path("id") uid: String
        ): Response<UserResponse>

        @POST("api/user/{id}/edit")
        suspend fun changePassword(
            @Body user: ChangePasswordDto,
            @Path("id") uid: String
        ): Response<UserResponse>

        @GET("api/user/{id}")
        suspend fun fetchUser(
            @Path("id") uid: String
        ): Response<UserResponse>

        @POST("api/user/signup")
        suspend fun register(
            @Body user: RegisterDto
        ): Response<UserResponse>

        @Multipart
        @POST("api/user/{id}/upload/avatar")
        suspend fun uploadAvatar(
            @Path("id") uid: String,
            @Part avatar: MultipartBody.Part
        ): Response<UserResponse>

        @DELETE("api/user/{id}/delete/avatar")
        suspend fun removeAvatar(
            @Path("id") uid: String
        ): Response<UserResponse>
    }
}