package uz.gita.authcontactngrok.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.newauthcontactngrok.R
import uz.gita.newauthcontactngrok.data.models.LocalData

class ContactAdapter : ListAdapter<LocalData, ContactAdapter.ContactViewHolder>(CallBack) {

    private var editListener: ((LocalData) -> Unit)? = null
    private var deleteListener: ((LocalData) -> Unit)? = null

    fun setEditListener(block: (LocalData) -> Unit) {
        editListener = block
    }

    fun setDeleteListener(block: (LocalData) -> Unit) {
        deleteListener = block
    }

    object CallBack : DiffUtil.ItemCallback<LocalData>() {
        override fun areItemsTheSame(oldItem: LocalData, newItem: LocalData): Boolean {
            return oldItem.localId == newItem.localId
        }

        override fun areContentsTheSame(oldItem: LocalData, newItem: LocalData): Boolean {
            return oldItem.localId == newItem.localId &&
                    oldItem.id == newItem.id &&
                    oldItem.name == newItem.name &&
                    oldItem.phone == newItem.phone &&
                    oldItem.state == newItem.state
        }
    }

    inner class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.etRegisterName)
        private val phone: TextView = view.findViewById(R.id.tvNumber)
        private val edit: ImageView = view.findViewById(R.id.ivEdit)
        private val delete: ImageView = view.findViewById(R.id.ivDelete)
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            name.text = item.name
            phone.text = item.phone
        }

        init {
            edit.setOnClickListener {
                editListener?.invoke(getItem(absoluteAdapterPosition))
            }

            delete.setOnClickListener {
                deleteListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind()
    }
}