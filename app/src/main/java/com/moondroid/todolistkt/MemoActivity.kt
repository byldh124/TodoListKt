package com.moondroid.todolistkt

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_memo.*
import java.text.SimpleDateFormat
import java.util.*

class MemoActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper
    lateinit var database: SQLiteDatabase
    lateinit var txt: String
    lateinit var time: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)
        setSupportActionBar(toolbar_memo)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        dbHelper = DBHelper(this, "newdb.db", null, 1)
        database = dbHelper.writableDatabase
        if (intent.getStringExtra("txt") != null) {
            txt = intent.getStringExtra("txt")!!

        }
        if (intent.getStringExtra("time") != null) {
            time = intent.getStringExtra("time")!!
        }
        if (txt != null) {
            etMemo.setText(txt)
        }
    }

    @SuppressLint("ResourceAsColor")
    fun clickColor(view: View) {
        when (view.id) {
            R.id.color1 -> etMemo.setBackgroundColor(R.color.memo_001)
            R.id.color2 -> etMemo.setBackgroundColor(R.color.memo_002)
            R.id.color3 -> etMemo.setBackgroundColor(R.color.memo_003)
            R.id.color4 -> etMemo.setBackgroundColor(R.color.memo_004)
            R.id.color5 -> etMemo.setBackgroundColor(R.color.memo_005)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun clickSave(view: View) {
        if (txt == null || time == null) {
            var query =
                "INSERT INTO mytable('txt', 'time') values('" + etMemo.text + "','" + SimpleDateFormat(
                    "yyyy.MM.dd HH:mm:ss"
                ).format(Date()) + "')"
            database.execSQL(query)
            finish()

        }
    }
}