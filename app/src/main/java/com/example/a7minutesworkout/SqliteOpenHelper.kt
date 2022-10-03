package com.example.a7minutesworkout

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteOpenHelper(context: Context):  SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "SevenMinutesWorkout.db"
        private const val TABLE_HISTORY = "history"

        private const val COLUMN_ID = "_id"
        private const val COLUMN_COMPLETED_DATE = "completed_date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        /**actual sql instruction
         * CREATE TABLE EmployeeDatabase(_id INTEGER PRIMARY KEY, name TEXT, email TEXT)
         * here, the table title is EmployeeDatabase with column title: _id, name & email which has its own type
         **/
        val CREATE_EXERCISE_TABLE = ("CREATE TABLE "
                + TABLE_HISTORY + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_COMPLETED_DATE + " TEXT)")
        db?.execSQL(CREATE_EXERCISE_TABLE)
    }

    //when we upgrade table for example when we add new column so we need to upgrade to see change
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY")
        onCreate(db)
    }

    //method to insert data
    fun addDate(date: String): Long{
        val db = this.writableDatabase

        Log.i("DB: ", " XXXXXXXXXXXXX")

        //its like container
        val contentValues = ContentValues()
        contentValues.put(COLUMN_COMPLETED_DATE, date)

        //inserting row
        val success = db.insert(TABLE_HISTORY,null, contentValues)

        db.close()
        //insert returns long
        return success
    }

    @SuppressLint("Range")
    fun getAllCompletedDatesList(): ArrayList<String>{
        val list = ArrayList<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_HISTORY", null)

        while (cursor.moveToNext()){
            val dateValue = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED_DATE))
            list.add(dateValue)
        }
        cursor.close()
        return list
    }
}