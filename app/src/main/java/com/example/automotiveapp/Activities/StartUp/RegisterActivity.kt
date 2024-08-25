package com.example.automotiveapp.Activities.StartUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.automotiveapp.Activities.Main.AddVehicleActivity
import com.example.automotiveapp.R
import com.example.automotiveapp.Untills.Functions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private lateinit var etUserName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etUserEmail: EditText
    private lateinit var etUserPhoneNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvLogin: TextView
    private lateinit var checkBox: CheckBox
    private lateinit var spPhase: Spinner

    private  var userName = ""
    private  var userEmail = ""
    private  var phoneNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        Functions.setTransparentStatusBar(this)

        etUserName = findViewById(R.id.et_userName)
        etLastName = findViewById(R.id.editTextLastName)
        etUserEmail = findViewById(R.id.et_userEmail)
        etUserPhoneNumber = findViewById(R.id.et_userPhoneNumber)
        etPassword = findViewById(R.id.editTextPassword)
        etConfirmPassword = findViewById(R.id.editTextconfirmPwd)
        btnRegister = findViewById(R.id.btn_register)
        tvLogin = findViewById(R.id.textViewLogin)
        checkBox = findViewById(R.id.checkBox)
        spPhase = findViewById(R.id.sp_phase)

        btnRegister.setOnClickListener {
            val userName = etUserName.text.toString().trim()
            val lastName = etLastName.text.toString().trim()
            val userEmail = etUserEmail.text.toString().trim()
            val phoneNumber = etUserPhoneNumber.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            if (userName.isEmpty() || lastName.isEmpty() || userEmail.isEmpty() || phoneNumber.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty()) {
                val database = FirebaseDatabase.getInstance()
                val ref = database.getReference("Register")
                val userEmail = ref.push().key
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!checkBox.isChecked) {
                Toast.makeText(this, "Please agree to the terms", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            registerUser(userName, lastName, userEmail, phoneNumber, password)
        }

        tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun registerUser(userName: String, lastName: String, userEmail: String, phoneNumber: String, password: String) {
        auth.createUserWithEmailAndPassword(userEmail, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val uid = user?.uid

                    val ref = FirebaseDatabase.getInstance().getReference("users")
                    val userInfo = HashMap<String, Any>()
                    userInfo["firstName"] = userName
                    userInfo["lastName"] = lastName
                    userInfo["email"] = userEmail
                    userInfo["phone"] = phoneNumber
                    userInfo["uid"] = uid ?: ""

                    ref.child(uid ?: "").setValue(userInfo)
                        .addOnCompleteListener { registerTask ->
                            if (registerTask.isSuccessful) {

                                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                                navigateToAgenciesActivity(userName, userEmail, phoneNumber)
                                finish()

                            } else {
                                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                            }
                        }

                } else {

                    Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToAgenciesActivity(userName: String, userEmail: String, phoneNumber: String) {
        val intent = Intent(this, AddVehicleActivity::class.java).apply {
            putExtra("firstName", userName)
            putExtra("email", userEmail)
            putExtra("phone", phoneNumber)
        }
        startActivity(intent)
    }
}