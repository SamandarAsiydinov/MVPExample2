package com.samsdk.mvptask.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.samsdk.mvptask.R
import com.samsdk.mvptask.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }
    private fun initViews() {
        val title = intent.getStringExtra("title")
        val body = intent.getStringExtra("body")

        binding.editTitle.setText(title)
        binding.editBody.setText(body)
        binding.btnUpdate.setOnClickListener {
            if (isNotEmpty(
                    binding.editTitle.text.toString().trim(),
                    binding.editBody.text.toString().trim()
                )
            ) {
                finish()
                toast("Updated")
            } else {
                toast("Please enter data")
            }
        }
    }

    private fun isNotEmpty(s1: String, s2: String): Boolean {
        return !(TextUtils.isEmpty(s1) && TextUtils.isEmpty(s2))
    }
    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}