package com.example.categoryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.categoryapp.R
import com.example.categoryapp.models.SubCategory
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.category_row.view.*
import kotlinx.android.synthetic.main.category_row.view.textView
import kotlinx.android.synthetic.main.subcategory_row.view.*

class SubCategoryAdapter(val subcategories: Array<SubCategory>, val container: View): RecyclerView.Adapter<SubCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.subcategory_row, parent, false)
        return SubCategoryViewHolder(view, container)
    }

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        val category = subcategories[position]
        holder?.itemView.textView.text = category.name
    }

    override fun getItemCount(): Int {
        return subcategories.size
    }

}

class SubCategoryViewHolder(view: View, container: View): RecyclerView.ViewHolder(view){
    init {
        val parentRecyclerView = container.subcategory_recyclerView

        view.checkBox2.setOnClickListener {
            val b = view.checkBox2.isChecked
            if (b) {
                container?.checkBox?.isChecked = true
            } else {
                var checkParent = false
                for (i in 0 until parentRecyclerView.childCount) {
                    val currentSubCategaory = parentRecyclerView.findViewHolderForAdapterPosition(i)
                    if (currentSubCategaory?.itemView?.checkBox2?.isChecked!!) {
                        checkParent = true
                        break
                    }
                }
                container?.checkBox?.isChecked = checkParent
            }
        }
    }
}