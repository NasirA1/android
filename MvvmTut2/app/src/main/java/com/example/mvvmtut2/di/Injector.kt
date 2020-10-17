package com.example.mvvmtut2.di

import android.content.Context
import com.example.mvvmtut2.data.ContactDatabase
import com.example.mvvmtut2.data.ContactsRepository
import com.example.mvvmtut2.vm.AddorEditContactViewModelFactory
import com.example.mvvmtut2.vm.ContactsViewModelFactory

object Injector {

    fun provideContactsViewModelFactory(context: Context): ContactsViewModelFactory {
        val contactsRepository = ContactsRepository.getInstance(
            ContactDatabase.getInstance(context).contactDao()
        )
        return ContactsViewModelFactory(contactsRepository)
    }

    fun provideAddorEditContactViewModelFactory(context: Context): AddorEditContactViewModelFactory {
        val contactsRepository = ContactsRepository.getInstance(
            ContactDatabase.getInstance(context).contactDao()
        )
        return AddorEditContactViewModelFactory(contactsRepository)
    }

}