package com.example.mvvmtut2.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvmtut2.TAG
import com.example.mvvmtut2.data.Contact
import com.example.mvvmtut2.data.ContactsRepository
import kotlinx.coroutines.launch

class AddorEditContactViewModel(private val contactsRepository: ContactsRepository) : ViewModel() {

    fun addContact(contact: Contact) {
        viewModelScope.launch {
            val rc = contactsRepository.addContact(contact)
            if (rc > 0) {
                Log.d(TAG, "New contact inserted. id=$rc")
            } else {
                Log.e(TAG, "ERRO inserting new contact. rc=$rc")
            }

        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            contactsRepository.updateContact(contact)
            Log.d(TAG, "Contact updated: $contact")
        }
    }

}


class AddorEditContactViewModelFactory(private val contactsRepository: ContactsRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddorEditContactViewModel(contactsRepository) as T
    }
}
