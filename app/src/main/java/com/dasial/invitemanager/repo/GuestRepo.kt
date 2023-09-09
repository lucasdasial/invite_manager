package com.dasial.invitemanager.repo

import android.content.ContentValues
import android.content.Context
import com.dasial.invitemanager.model.Guest

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

    fun insert(guest: Guest) {
        val presence = if (guest.presence) 1 else 0

        val db = guestDataBase.writableDatabase
        val values = ContentValues()

        values.put("name", guest.name)
        values.put("presence", presence)

        db.insert("Guest", null, values)

    }

    fun update() {

    }


}