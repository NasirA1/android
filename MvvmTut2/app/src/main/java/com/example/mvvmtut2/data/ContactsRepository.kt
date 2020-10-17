package com.example.mvvmtut2.data

import com.example.mvvmtut2.util.SingletonHolder

class ContactsRepository private constructor(private val contactDao: ContactDao) {

    companion object : SingletonHolder<ContactsRepository, ContactDao>(::ContactsRepository)

    suspend fun getContacts() = contactDao.getAll()

    suspend fun addContact(contact: Contact): Long = contactDao.insert(contact)

    suspend fun deleteContact(contact: Contact) = contactDao.delete(contact)

    suspend fun updateContact(contact: Contact) = contactDao.update(contact)

    suspend fun purge() = contactDao.purge()
}