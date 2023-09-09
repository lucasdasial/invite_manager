package com.dasial.invitemanager.repo

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.dasial.invitemanager.model.Guest
import java.lang.Exception

class GuestRepo private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    companion object {
        private lateinit var repo: GuestRepo

        fun getInstance(context: Context): GuestRepo {
            if (!::repo.isInitialized) {
                repo = GuestRepo(context)
            }
            return this.repo
        }
    }

    fun insert(guest: Guest): Boolean {
        return try {

            val presence = if (guest.presence) 1 else 0

            val db = guestDataBase.writableDatabase
            val values = ContentValues()

            values.put("name", guest.name)
            values.put("presence", presence)

            db.insert("Guest", null, values)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun update(guest: Guest): Boolean {
        return try {

            val presence = if (guest.presence) 1 else 0

            val db = guestDataBase.writableDatabase
            val values = ContentValues()

            values.put("name", guest.name)
            values.put("presence", presence)

            val selection = "id = ?"

            val args = arrayOf(guest.id.toString())

            db.update("Guest", values, selection, args)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun remove(id: Int): Boolean {
        return try {

            val db = guestDataBase.writableDatabase

            val selection = "id = ?"

            val args = arrayOf(id.toString())

            db.delete("Guest", selection, args)
            true
        } catch (e: Exception) {
            false
        }

    }

    @SuppressLint("Range")
    fun getAll(): List<Guest> {

        val list = mutableListOf<Guest>()

        try {
            val db = guestDataBase.readableDatabase

            val selection = arrayOf("id", "name", "presence")

            val cursor = db.query("Guest", selection, null, null, null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val presence = cursor.getInt(cursor.getColumnIndex("presence"))

                    val guest = Guest(id, name, presence == 1)

                    list.add(guest)
                }
                cursor.close()

            }
        } catch (e: Exception) {
            return list
        }
        return list
    }


}