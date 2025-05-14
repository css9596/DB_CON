package com.example.db_con_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = intent.getStringExtra("title")
        val textView = findViewById<TextView>(R.id.textViewDetail)
        textView.text = "$title 상세 화면"

        val fab = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, SourceCodeActivity::class.java)
            intent.putExtra("title", title)
            startActivity(intent)
        }
    }
}
