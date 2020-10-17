package com.example.mvvmtut2.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtut2.R
import com.example.mvvmtut2.data.Contact

class ContactsAdapter(
    private var onItemClick: (Int) -> Unit
) : ListAdapter<Contact, ContactsAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean =
                oldItem.name == newItem.name &&
                        oldItem.email == newItem.email &&
                        oldItem.phone == newItem.phone
        }
    }

    fun getContact(index: Int): Contact = getItem(index)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.tv_name)
        val itemPhone: TextView = itemView.findViewById(R.id.tv_phone)
        val itemEmail: TextView = itemView.findViewById(R.id.tv_email)
        val itemPicture: ImageView = itemView.findViewById(R.id.iv_image)
        var contact: Contact? = null

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                onItemClick.invoke(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.contact_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = getItem(position)
        holder.contact = contact
        holder.itemName.text = contact.name
        holder.itemPhone.text = contact.phone
        holder.itemEmail.text = contact.email
        holder.itemPicture.setImageResource(R.mipmap.ic_launcher_round)
    }
}