package com.castres.breand.block6.p1.androidproject.LogandResAct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.castres.breand.block6.p1.androidproject.R

class ForgotPasswordActivity : AppCompatActivity() {

    // Declare class variables for Email, Name, Password, and Confirm Password EditTexts
    private lateinit var fpEmail: EditText
    private lateinit var fpPassword: EditText
    private lateinit var fpConfirmFP: EditText
    private lateinit var fpButton: Button
    private lateinit var fpLogIn: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        fpEmail = findViewById(R.id.fpEmail)
        fpPassword = findViewById(R.id.fpPassword)
        fpConfirmFP = findViewById(R.id.fpConfirmPW)
        fpButton = findViewById(R.id.fpButton)
        fpLogIn = findViewById(R.id.fpLogIn)

        fpButton.setOnClickListener {
            handleResetPassword()
        }

        fpLogIn.setOnClickListener {
            // Redirect the user to the LogInActivity
            startActivity(Intent(this, LogInActivity::class.java))
            finish() // Finish the current activity to prevent going back to it
        }

    }

    private fun handleResetPassword() {
        val email = fpEmail.text.toString().trim()
        val password = fpPassword.text.toString().trim()
        val confirmPassword = fpConfirmFP.text.toString().trim()

        if (isValidInput(email, password, confirmPassword)) {
            // Perform the password reset logic here
            // For example, you might want to update the password in your database
            // Show a success message or redirect to another activity
            Toast.makeText(this, "Password reset successful!", Toast.LENGTH_SHORT).show()
        } else {
            // Show an error message if validation fails
            Toast.makeText(this, "Invalid input. Please check your details.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidInput(email: String, password: String, confirmPassword: String): Boolean {
        // Add your validation logic here
        // For example, check if the email is valid, if password and confirmPassword match, etc.
        return true // Replace with your actual validation conditions
    }
}


