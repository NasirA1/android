package com.example.mvvmtut2.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtut2.R
import com.example.mvvmtut2.TAG
import com.example.mvvmtut2.di.Injector
import com.example.mvvmtut2.vm.ContactsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val ADD_CONTACT_REQUEST = 1
        const val EDIT_CONTACT_REQUEST = 2
        const val EXTRA_EDITABLE_CONTACT = "EXTRA_EDITABLE_CONTACT"
    }

    private lateinit var vm: ContactsViewModel

    private val recyclerAdapter = ContactsAdapter(::onItemClick)
    private val recyclerSwipeDeleteHandler =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Log.d(TAG, "Swiped: index=${viewHolder.adapterPosition}, direction=$direction")
                val contact = recyclerAdapter.getContact(viewHolder.adapterPosition)
                vm.deleteContact(contact)
            }
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Contacts"
        fab_addContact.setOnClickListener { addNewContract() }

        rv_Contacts.setHasFixedSize(true)
        rv_Contacts.layoutManager = LinearLayoutManager(this)
        rv_Contacts.adapter = recyclerAdapter
        recyclerSwipeDeleteHandler.attachToRecyclerView(rv_Contacts)

        hookupData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult requestCode=$requestCode  resultCode=$resultCode")
    }

    private fun onItemClick(position: Int) {
        Log.d(TAG, "onItemClick position $position")
        val contact = recyclerAdapter.getContact(position)
        Log.d(TAG, "edit Contact: $contact")

        val intent = Intent(this, AddorEditContactActivity::class.java)
        intent.putExtra(EXTRA_EDITABLE_CONTACT, contact)
        startActivityForResult(intent, EDIT_CONTACT_REQUEST)
    }

    private fun addNewContract() {
        val intent = Intent(this, AddorEditContactActivity::class.java)
        startActivityForResult(intent, ADD_CONTACT_REQUEST)
    }

    private fun hookupData() {
        val factory = Injector.provideContactsViewModelFactory(this)
        vm = ViewModelProviders.of(this, factory).get(ContactsViewModel::class.java)

        vm.isLoading().observe(this, Observer {
            Log.d(TAG, "isLoading: $it")
            pb_loading.visibility = if (it) View.VISIBLE else View.GONE
        })

        vm.getContacts().observe(this, Observer { contacts ->
            recyclerAdapter.submitList(contacts)
        })
    }

    private fun areYouSure(message: String, onYes: () -> Unit, onNo: (() -> Unit)? = null) =
        AlertDialog.Builder(this)
            .setTitle("Confirm")
            .setMessage(message)
            .setPositiveButton("Yes") { _, _ -> onYes.invoke() }
            .setNegativeButton("No") { _, _ -> onNo?.invoke() }
            .show()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_purge -> {
                areYouSure("All data will be erased! Are you sure?", { vm.purge() })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}