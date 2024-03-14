package com.castres.breand.block6.p1.androidproject.LogandResAct

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.castres.breand.block6.p1.androidproject.MainActivity
import com.castres.breand.block6.p1.androidproject.R
import com.castres.breand.block6.p1.androidproject.RetrofitInstance
import com.castres.breand.block6.p1.androidproject.dataclass.EmailCheckRequest
import com.castres.breand.block6.p1.androidproject.dataclass.RegistrationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerEmail: EditText
    private lateinit var registerName: EditText
    private lateinit var registerPW: EditText
    private lateinit var registerConfirmPW: EditText
    private lateinit var Phone: EditText
    private lateinit var Address: EditText
    private lateinit var registerButton: Button
    private lateinit var loginTextView: TextView

    private lateinit var email: String
    private lateinit var name: String
    private lateinit var password: String
    private lateinit var confirmPassword: String
    private lateinit var phone: String
    private lateinit var address: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerEmail = findViewById(R.id.registerEmail)
        registerName = findViewById(R.id.registerName)
        registerPW = findViewById(R.id.registerPW)
        registerConfirmPW = findViewById(R.id.registerConfirmPW)
        Phone = findViewById(R.id.Phone)
        Address = findViewById(R.id.Address)
        registerButton = findViewById(R.id.register_Button)
        loginTextView = findViewById(R.id.loginTextView)

        loginTextView.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            email = registerEmail.text.toString()
            name = registerName.text.toString()
            password = registerPW.text.toString()
            confirmPassword = registerConfirmPW.text.toString()
            phone = Phone.text.toString()
            address = Address.text.toString()

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                Toast.makeText(this, "Email is Required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (name.isEmpty()) {
                Toast.makeText(this, "Name is Required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (phone.isEmpty()) {
                Toast.makeText(this, "Phone is Required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (address.isEmpty()) {
                Toast.makeText(this, "Address is Required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check email availability before registration
            checkEmailAvailability(EmailCheckRequest(email))
        }
    }

    private fun checkEmailAvailability(emailCheckRequest: EmailCheckRequest) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val API = RetrofitInstance.PostAPI(this@RegisterActivity)
                val response = API.checkEmail(emailCheckRequest)

                if (response.isSuccessful) {
                    // Email is already in use
                    Toast.makeText(this@RegisterActivity, "Email is already in use", Toast.LENGTH_SHORT).show()
                } else {
                    // Proceed with registration
                    val registrationRequest = RegistrationRequest(email, name, password, confirmPassword, phone, address)
                    registerUser(registrationRequest)
                }
            } catch (e: Exception) {
                // Handle network errors or other exceptions
                Toast.makeText(this@RegisterActivity, "Failed to check email availability: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun registerUser(registrationRequest: RegistrationRequest) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val API = RetrofitInstance.PostAPI(this@RegisterActivity)
                val response = API.register(registrationRequest)

                if (response.isSuccessful) {
                    // Handle successful registration
                    Toast.makeText(this@RegisterActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Handle unsuccessful registration
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    Toast.makeText(this@RegisterActivity, "Registration failed: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Handle other exceptions
                Toast.makeText(this@RegisterActivity, "Registration failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
