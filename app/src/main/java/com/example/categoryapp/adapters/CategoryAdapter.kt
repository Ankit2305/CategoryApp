package com.example.categoryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.categoryapp.R
import com.example.categoryapp.models.Category
import kotlinx.android.synthetic.main.category_row.view.*
import kotlinx.android.synthetic.main.category_row.view.textView
import kotlinx.android.synthetic.main.subcategory_row.view.*

class CategoryAdapter(val categories: Array<Category>): RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.category_row, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.itemView.textView.text = category.name
        holder.itemView.subcategory_recyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        if(category.subCategory != null) {
            holder.itemView.subcategory_recyclerView.adapter =
                SubCategoryAdapter(category.subCategory, holder.itemView)
        }
        if(category.subCategory == null || category.subCategory.isEmpty())
            holder?.itemView.view.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}

class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view){
    init{
        view.checkBox.setOnClickListener {
            val b = view.checkBox.isChecked
            if (b) {
                for(i in 0 until view.subcategory_recyclerView.childCount) {
                    val currentSubCategory = view.subcategory_recyclerView.findViewHolderForAdapterPosition(i)
                    currentSubCategory?.itemView?.checkBox2?.isChecked = true
                }
            } else {
                for(i in 0 until view.subcategory_recyclerView.childCount) {
                    val currentSubCategory = view.subcategory_recyclerView.findViewHolderForAdapterPosition(i)
                    currentSubCategory?.itemView?.checkBox2?.isChecked = false
                }
            }
        }
    }
}