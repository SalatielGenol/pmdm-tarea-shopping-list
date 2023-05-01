package com.example.shoppinglist

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlVanilla(context: Context?, dbName: String, dbVersion: Int) :
    SQLiteOpenHelper(context, dbName, null, dbVersion) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(
            "CREATE TABLE shoppingdata(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, checked BOOLEAN)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(
            "DROP TABLE IF EXISTS shoppingdata"
        )
        onCreate(db)
    }

    fun addItem(name: String, checked: Boolean) {
        val value = ContentValues().apply {
            put("name", name)
            put("checked", checked)
        }

        val db = this.writableDatabase

        db.insert("shoppingdata", null, value)
        db.close()
    }

    fun getData(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM shoppingdata", null)
    }

/*    fun getLastId(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM shoppingdata", null)
    }*/

    fun removeItem(id: Int){
        val args = arrayOf(id.toString())
        val db = this.writableDatabase

        db.delete("shoppingdata", "id = ?", args)
        db.close()
    }

/*    fun modifyItem(id: Int, checked: Boolean){
        val args = arrayOf(id.toString())

        val values = ContentValues().apply {
            put("checked", checked)
        }

        val db = this.writableDatabase
        db.update("shoppingdata", values, "id = ?", args)
        db.close()
    }*/
}