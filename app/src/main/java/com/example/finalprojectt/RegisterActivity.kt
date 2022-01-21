package com.example.finalprojectt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPassword2: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.singup_activity_main)

        init()
        registerListeners()
    }

    private fun init() {
        editTextEmail = findViewById(R.id.editTextEmail2)
        editTextPassword = findViewById(R.id.editTextPassword3)
        editTextPassword2 = findViewById(R.id.editTextPassword2)
        buttonLogin = findViewById(R.id.buttonLogin)

    }

    private fun registerListeners() {
        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val password2 = editTextPassword2.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "შეიყვანეთ თქვენი მეილი ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "შეიყვანეთ პაროლი ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length < 9) {
                Toast.makeText(this, "პაროლი უნდა შეიცავდეს 9 სიმბოლოს", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password2.isEmpty()) {
                Toast.makeText(this, "გთხოვთ, გაიმეოროთ პაროლი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password2 != password) {
                Toast.makeText(this, "პაროლები არ ემთხვევა ერთამენთს!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, HomeActivity::class.java))
                        Toast.makeText(this, "თქვენ წარმატებით დარეგისტრირდით", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(
                            this,
                            "აღნიშნული ელ. ფოსტა არ არსებობს ან უკვე რეგისტრირებულია",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
        }

    }
}
















