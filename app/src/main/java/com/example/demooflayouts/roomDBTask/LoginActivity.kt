package com.example.demooflayouts.roomDBTask

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.demooflayouts.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    lateinit var etEmail: TextView
    lateinit var etPassword: TextView
    lateinit var appDatabase: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)

        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "userDetailsDB").build()


        findViewById<TextView>(R.id.tv_login_sign_up).setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.btn_login).setOnClickListener {
            if (isValidate()) {

                GlobalScope.launch {
                  val record = appDatabase.userDetailDao().getOneUserRecord(etEmail.text.toString(), etPassword.text.toString())
                    if (record.isNotEmpty()) {
                        storeLoginDetails(record)
                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        }

    }

    private fun storeLoginDetails(record: List<UserDetails>) {
            val shredPref = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        with(shredPref.edit()) {
            val userRecord = record[0]
            putLong("uid",userRecord.userId)
            putString("name",userRecord.user_name)
            putString("email",userRecord.user_email)
            putBoolean("isLogin",true)
            apply()
        }

    }

    private fun isValidate(): Boolean {
        if (etEmail.text.isEmpty() && etPassword.text.isEmpty()) {
            Toast.makeText(this, "Empty fields not allowed", Toast.LENGTH_SHORT).show()
            return false
        } else {
            if (etEmail.text.isEmpty()) {
                Toast.makeText(this, "empty Email Address not allowed", Toast.LENGTH_SHORT).show()
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
                Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show()
                return false
            } else if (etPassword.text.isEmpty()) {
                Toast.makeText(this, "Password empty not allowed", Toast.LENGTH_SHORT).show()
                return false
            } else if (etPassword.text.length < 8) {
                Toast.makeText(this, "Password length must be 8", Toast.LENGTH_SHORT).show()
                return false
            }
        }

        return true
    }
}