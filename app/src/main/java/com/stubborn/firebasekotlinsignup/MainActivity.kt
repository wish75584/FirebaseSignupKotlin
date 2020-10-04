package com.stubborn.firebasekotlinsignup

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
      signup.setOnClickListener() {
            signupUser()
        }
    }

    private fun signupUser() {
        if (email.text.toString().isEmpty()) {
            email.error = "Please enter email"
            email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Please enter valid email"
            email.requestFocus()
            return
        }
        if (password.text.toString().isEmpty()) {
            password.error = "Please enter password"
            password.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
// Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this, "User Regiser Successfully", Toast.LENGTH_LONG).show()
                    } else {
// If sign in fails, display a message to the user.

                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}