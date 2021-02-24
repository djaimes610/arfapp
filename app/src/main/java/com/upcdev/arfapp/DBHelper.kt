package com.upcdev.arfapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DBNAME, null, 1) {
    override fun onCreate(MyDB: SQLiteDatabase) {
        val create_users = "create Table users(username TEXT primary key, email TEXT, password TEXT);"
        val create_animals = "create Table animals(ID INTEGER PRIMARY KEY AUTOINCREMENT,description TEXT, address TEXT, category_img Text);"
        MyDB!!.execSQL(create_users)
        MyDB!!.execSQL(create_animals)
    }

    override fun onUpgrade(MyDB: SQLiteDatabase, i: Int, i1: Int) {
        val drop_users = "drop Table if exists users"
        val drop_animals = "drop Table if exists animals"
        MyDB!!.execSQL(drop_users)
        MyDB!!.execSQL(drop_animals)
        onCreate(MyDB)
    }

    fun insertData(username: String?, email: String?, password: String?): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("email", email)
        contentValues.put("password", password)
        val result = MyDB.insert("users", null, contentValues)
        return if (result == -1L) false else true
    }

    fun checkusername(email: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery("Select * from users where email = ?", arrayOf(email))
        return if (cursor.count > 0) true else false
    }

    fun checkusernamepassword(email: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery(
            "Select * from users where email = ? and password = ?",
            arrayOf(email, password)
        )
        return if (cursor.count > 0) true else false
    }

    fun insertLostAnimal(description: String, address: String, ctgImage: String): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("description", description)
        contentValues.put("address", address)
        contentValues.put("category_img", ctgImage)
        val result = MyDB.insert("animals", null, contentValues)
        return result != -1L
    }

    fun queryAll(): Cursor {
        val MyDB = this.writableDatabase
        return MyDB!!.rawQuery("select * from animals", null)
    }

    companion object {
        const val DBNAME = "Arfapp.db"
    }
}