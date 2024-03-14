package com.castres.breand.block6.p1.androidproject

import android.content.Context
import com.castres.breand.block6.p1.androidproject.Components.ComponentsActivity
import com.castres.breand.block6.p1.androidproject.Components.ComponentsDetailActivity
import com.castres.breand.block6.p1.androidproject.LogandResAct.LogInActivity
import com.castres.breand.block6.p1.androidproject.LogandResAct.RegisterActivity
import com.castres.breand.block6.p1.androidproject.data.model.modeling.API
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val URL = "https://cyberservice-96805b7c1a96.herokuapp.com/"
    private const val PREF_NAME = "mySharedPreferences"
    private const val TOKEN_KEY = "token"


    private fun getToken(context: Context):String?{
        val prefs=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
        return prefs.getString(TOKEN_KEY,null)
    }

    private class AuthInterceptor(val context: Context) : Interceptor{

        override fun intercept(chain: Interceptor.Chain):okhttp3.Response {
            val token = getToken(context)
            val request = chain.request().newBuilder()
            token?.let {
                request.addHeader("Authorization", "Bearer $token")
            }
           return chain.proceed(request.build())
        }
    }



    fun getAPI(context: LogInActivity): API{
        val loggingInterceptor= HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val authInterceptor = AuthInterceptor(context)

        val client=OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .build()

        return retrofit.create(API::class.java)
    }

    fun PostAPI(context: RegisterActivity): API{
        val loggingInterceptor= HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val authInterceptor = AuthInterceptor(context)

        val client=OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .build()

        return retrofit.create(API::class.java)
    }


    fun getComp(context: ComponentsDetailActivity): API{
        val loggingInterceptor= HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val authInterceptor = AuthInterceptor(context)

        val client=OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .build()

        return retrofit.create(API::class.java)
    }

    fun FetchComp(context: ComponentsActivity): API{
        val loggingInterceptor= HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val authInterceptor = AuthInterceptor(context)

        val client=OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .build()

        return retrofit.create(API::class.java)
    }


}
//private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
  //  level = HttpLoggingInterceptor.Level.BODY
//}

//private val client: OkHttpClient = OkHttpClient.Builder()
  //  .addInterceptor(interceptor)
    //.build()

//private val retrofit: Retrofit = Retrofit.Builder()
  //  .baseUrl(RetrofitInstance.URL)
    //.client(client)
    //.addConverterFactory(ScalarsConverterFactory.create())
    //.addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    //.build()

//val api: API = retrofit.create(API::class.java)
