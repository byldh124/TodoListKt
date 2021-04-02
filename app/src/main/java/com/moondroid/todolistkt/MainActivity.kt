package com.moondroid.todolistkt

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper : DBHelper
    lateinit var database :SQLiteDatabase
    lateinit var memos : ArrayList<MemoVO>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        dbHelper = DBHelper(this, "newdb.db", null, 1)
        database = dbHelper.writableDatabase
        memos = ArrayList()

        var query = "SELECT * FROM mytable;"
        var c = database.rawQuery(query,null)
        while(c.moveToNext()){
            val txt = c.getString(c.getColumnIndex("txt"))
            val time = c.getString(c.getColumnIndex("time"))
            memos.add(MemoVO(txt, time))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add){
            var intent = Intent(this, MemoActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}