package com.example.mvvmtut2.data

import android.content.Context
import androidx.room.*
import com.example.mvvmtut2.util.SingletonHolder
import java.io.Serializable

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var phone: String,
    var email: String
) : Serializable

@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact")
    suspend fun getAll(): List<Contact>

    @Insert
    suspend fun insert(contact: Contact): Long

    @Delete
    suspend fun delete(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Query("DELETE FROM Contact")
    suspend fun purge()
}

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object : SingletonHolder<ContactDatabase, Context>({
        Room.databaseBuilder(
            it.applicationContext,
            ContactDatabase::class.java, "Contacts.db"
        )
            .build()
    })
}
