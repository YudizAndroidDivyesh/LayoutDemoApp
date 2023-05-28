package com.example.demooflayouts.roomDBTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.demooflayouts.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var etUserEmail: EditText
    private lateinit var etUserName: EditText
    private lateinit var etUserPhone: EditText
    private lateinit var etUserPassword: EditText
    private lateinit var etUserConfirmPassword: EditText
    private lateinit var appDatabase: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        etUserName = findViewById(R.id.et_sign_name)
        etUserEmail = findViewById(R.id.et_sign_email)
        etUserPhone = findViewById(R.id.et_phoneNo)
        etUserPassword = findViewById(R.id.et_sign_password)
        etUserConfirmPassword = findViewById(R.id.et_confirm_pass)

        appDatabase = AppDatabase.getDatabase(this)

        findViewById<TextView>(R.id.tv_login_btn).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


        findViewById<Button>(R.id.btn_sign_up).setOnClickListener {
            if (isValidation()) {
                GlobalScope.launch {

                    appDatabase.userDetailDao().insertUserData(
                        UserDetails(
                            0,
                            etUserName.text.toString(),
                            etUserEmail.text.toString(),
                            etUserPhone.text.toString(),
                            etUserPassword.text.toString()
                        )
                    )

                }
                Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


    }

    private fun isValidation(): Boolean {
        if (etUserName.text.isEmpty() && etUserEmail.text.isEmpty() && etUserPhone.text.isEmpty() && etUserPassword.text.isEmpty() && etUserConfirmPassword.text.isEmpty()) {
            Toast.makeText(this, "Empty fields not allowed", Toast.LENGTH_SHORT).show()
            return false
        } else {
            if (etUserName.text.isEmpty()) {
                Toast.makeText(this, "Name is Empty", Toast.LENGTH_SHORT).show()
                return false
            } else if (etUserName.text.length < 3) {
                Toast.makeText(this, "Name length minimum 3", Toast.LENGTH_SHORT).show()
                return false
            } else if (etUserEmail.text.isEmpty()) {
                Toast.makeText(this, "Email Address is Empty", Toast.LENGTH_SHORT).show()
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(etUserEmail.text).matches()) {
                Toast.makeText(this, "invalid Email Address", Toast.LENGTH_SHORT).show()
                return false
            } else if (etUserPhone.text.isEmpty()) {
                Toast.makeText(this, "phone number is Empty", Toast.LENGTH_SHORT).show()
                return false
            } else if (etUserPhone.text.toString().length != 10) {
                Toast.makeText(this, "phone number must 10 digit", Toast.LENGTH_SHORT).show()
                return false
            } else if (etUserPassword.text.isEmpty()) {
                Toast.makeText(this, "password is Empty", Toast.LENGTH_SHORT).show()
                return false
            } else if (etUserPassword.text.toString().length < 8) {
                Toast.makeText(this, "password length must 8 or more", Toast.LENGTH_SHORT).show()
                return false
            } else if (etUserConfirmPassword.text.isEmpty()) {
                Toast.makeText(this, "Confirm password is Empty", Toast.LENGTH_SHORT).show()
                return false
            } else if (etUserConfirmPassword.text.toString() != etUserPassword.text.toString()) {
                Toast.makeText(this, "Confirm password and password not match", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
        }
        return true
    }
}