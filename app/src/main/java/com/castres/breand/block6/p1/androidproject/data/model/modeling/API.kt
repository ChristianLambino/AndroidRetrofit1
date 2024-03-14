package com.castres.breand.block6.p1.androidproject.data.model.modeling

import com.castres.breand.block6.p1.androidproject.Components.ComponentsItems
import com.castres.breand.block6.p1.androidproject.dataclass.EmailCheckRequest
import com.castres.breand.block6.p1.androidproject.dataclass.ItemRequest
import com.castres.breand.block6.p1.androidproject.dataclass.ItemResponse
import com.castres.breand.block6.p1.androidproject.dataclass.LoginRequest
import com.castres.breand.block6.p1.androidproject.dataclass.LoginResponse
import com.castres.breand.block6.p1.androidproject.dataclass.RegistrationRequest
import com.castres.breand.block6.p1.androidproject.dataclass.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface API {

    @Headers("Accept: application/json") // Specifies the Accept header
    @POST("/registration") // Adjust this endpoint to match your API.
    suspend fun register(@Body registrationRequest: RegistrationRequest): Response<UserResponse>

    @Headers("Accept: application/json")
    @POST("/login")
    suspend fun login(@Body credentials: LoginRequest): Response<LoginResponse>

    @Headers("Accept: application/json")
    @POST("/registration")
    suspend fun checkEmail(@Query("email") emailCheckRequest: EmailCheckRequest): Response<UserResponse>

    @Headers("Accept: application/json")
    @GET("components")
    suspend fun getComponentDetails(): Response<List<ComponentsItems>>


    @Headers("Accept: application/json")
    @GET("/components")
    suspend fun getComponent(): Response<ComponentsItems>
    @Headers("Accept: application/json")
    @POST("/cart")
    suspend fun addToCart(@Body items: ItemRequest): Response<ItemResponse>

}
