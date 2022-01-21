package com.example.finalprojectt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class loginactivity : AppCompatActivity() {
    private lateinit var editTextPassword4: EditText
    private lateinit var EditTextEmail: EditText
    private lateinit var button2: Button
    private lateinit var button5: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)
        init()
        registerListeners()
    }

    private fun init() {
        editTextPassword4 = findViewById(R.id.editTextPassword4)
        EditTextEmail = findViewById(R.id.EmailEditText)
        button2 = findViewById(R.id.button2)
        button5 = findViewById(R.id.button5)
    }

    private fun registerListeners() {
        button2.setOnClickListener {
            val email = EditTextEmail.text.toString()
            val password = editTextPassword4.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "ჩაწერეთ თქვენი მეილი", Toast.LENGTH_SHORT).show()
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
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, HomeActivity::class.java))
                        Toast.makeText(this, "თქვენ წარმატებით შეხვედით თქვენს პროფილზე", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(
                            this,
                            "აღნიშნული ელ. ფოსტა არ არსებობს",
                            Toast.LENGTH_SHORT
                        ).show()


                    }
                }
        }
        button5.setOnClickListener(){
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}


