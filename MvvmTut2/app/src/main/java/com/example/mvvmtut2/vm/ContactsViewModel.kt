package com.example.mvvmtut2.vm

import android.util.Log
import androidx.lifecycle.*
import com.example.mvvmtut2.TAG
import com.example.mvvmtut2.data.Contact
import com.example.mvvmtut2.data.ContactsRepository
import kotlinx.coroutines.launch

class ContactsViewModel(private val contactsRepository: ContactsRepository) : ViewModel() {

    private var contacts = MutableLiveData<List<Contact>>()
    private var loading = MutableLiveData<Boolean>()

    init {
        loadContacts()
    }

    private fun loadContacts() {
        viewModelScope.launch {
            loading.value = true
            contacts.value = contactsRepository.getContacts()
            loading.value = false
        }
    }

    fun getContacts(): LiveData<List<Contact>> = contacts

    fun isLoading(): LiveData<Boolean> = loading


    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            contactsRepository.deleteContact(contact)
            Log.d(TAG, "Deleted $contact")
            loadContacts()
        }
    }

    fun purge() {
        viewModelScope.launch {
            contactsRepository.purge()
            Log.d(TAG, "All contacts deleted.")
            loadContacts()
        }
    }

}


class ContactsViewModelFactory(private val contactsRepository: ContactsRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactsViewModel(contactsRepository) as T
    }
}
