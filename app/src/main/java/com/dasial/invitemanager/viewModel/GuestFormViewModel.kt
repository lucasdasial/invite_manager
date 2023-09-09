package com.dasial.invitemanager.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.dasial.invitemanager.model.Guest
import com.dasial.invitemanager.repo.GuestRepo

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepo.getInstance(application)

    fun save(guest: Guest){
        repository.insert(guest)
    }

}