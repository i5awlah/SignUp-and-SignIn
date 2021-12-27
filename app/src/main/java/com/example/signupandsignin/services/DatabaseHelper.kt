package com.example.signupandsignin.services

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.signupandsignin.models.User

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "details.db", null, 1) {
    private val sqLiteDatabase: SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table users (Name text, Mobile text, Location text, Password text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun saveData(user: User) : Int {
        val contentValues = ContentValues()
        contentValues.put("Name", user.name)
        contentValues.put("Mobile", user.mobile)
        contentValues.put("Location", user.location)
        contentValues.put("Password", user.password)
        return sqLiteDatabase.insert("users", null, contentValues).toInt()
    }

    fun readData() : ArrayList<User> {
        val users = arrayListOf<User>()
        val cursor: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null)
        if (cursor.count == 0) {
            println("No Data")
        } else {
            while (cursor.moveToNext()) {
                val name = cursor.getString(0)
                val mobile = cursor.getString(1)
                val location = cursor.getString(2)
                val password = cursor.getString(3)
                users.add(User(name, mobile, location, password))
            }
        }
        return users
    }

    fun readOneUser(mobile: String, password: String) : User {
        var user = User("", "", "", "")
        val cursor: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM users where Mobile='$mobile' AND Password='$password'", null)
        if (cursor.count == 0) {
            println("No Data")
        } else {
            while (cursor.moveToNext()) {
                val name = cursor.getString(0)
                val mobile = cursor.getString(1)
                val location = cursor.getString(2)
                val password = cursor.getString(3)
                user = User(name, mobile, location, password)
            }
        }
        return user
    }
}