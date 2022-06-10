package com.example.loginrestapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAction()
    }

    fun initAction(){
        btn_login.setOnClickListener{
            login()
        }
    }

    fun  login(){
        val request = UserRequest()
        request.email = et_email.text.toString().trim()
        request.password = et_password.text.toString().trim()

        val retro = Retro().getRetrofitClientInstance().create(UserApi::class.java)
        retro.login(request).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.message?.let { Log.e("Error", it) }
            }
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val user = response.body()
                user!!.data?.token?.let { Log.e("token", it) }
                user!!.data?.email?.let { Log.e("email", it) }
            }

        })
    }
}