package com.example.mvvmtut2.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmtut2.R
import com.example.mvvmtut2.data.Contact
import com.example.mvvmtut2.di.Injector
import com.example.mvvmtut2.vm.AddorEditContactViewModel
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddorEditContactActivity : AppCompatActivity() {

    private lateinit var vm: AddorEditContactViewModel
    private var editMode: Boolean = false
    private lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)

        hookupData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_contact -> {
                saveContact(); true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun copyContactToUi(contact: Contact) {
        contact.apply {
            et_Name.setText(name)
            et_Phone.setText(phone)
            et_Email.setText(email)
        }
    }

    private fun copyUiToContact(contact: Contact) {
        contact.apply {
            name = et_Name.text.toString()
            phone = et_Phone.text.toString()
            email = et_Email.text.toString()
        }
    }

    private fun saveContact() {
        copyUiToContact(contact)
        if (editMode) {
            vm.updateContact(contact)
        } else {
            vm.addContact(contact)
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun hookupData() {
        val factory = Injector.provideAddorEditContactViewModelFactory(this)
        vm = ViewModelProviders.of(this, factory).get(AddorEditContactViewModel::class.java)

        intent.getSerializableExtra(MainActivity.EXTRA_EDITABLE_CONTACT)?.let {
            title = "Edit Contact"
            editMode = true
            this.contact = it as Contact
            copyContactToUi(contact)
        } ?: run {
            this.contact = Contact(0, "", "", "")
            editMode = false
            title = "Add Contact"
        }
    }
}
