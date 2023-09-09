package com.dasial.invitemanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.ViewModelProvider
import com.dasial.invitemanager.R
import com.dasial.invitemanager.databinding.ActivityGuestFormBinding
import com.dasial.invitemanager.model.Guest
import com.dasial.invitemanager.viewModel.GuestFormViewModel


class GuestFormActivity : AppCompatActivity(), OnClickListener {

    private lateinit var b: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(b.root)

         viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        b.buttonSave.setOnClickListener(this)
        b.radioButtonPresent.isChecked = true
    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_save){
            val name = b.textInputName.editText?.text.toString()
            val presence = b.radioButtonPresent.isChecked

            viewModel.save(Guest(0,name, presence))
        }
    }
}