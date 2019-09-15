package com.example.lapane_skm.ui.question.questionsatu

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lapane_skm.R
import kotlinx.android.synthetic.main.item_layout.view.*

class SatuanKerjaAdapter (var context: Context?, var list: List<ModelSatuanKerja>) :
    RecyclerView.Adapter<SatuanKerjaAdapter.ViewHolder>() {

    private var mOnItemClickListener: OnItemClickListener? = null
    private lateinit var uri: Uri

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    //    fun mActivity(activity: Activity) {
//        this.activity = activity
//    }
    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mItemClickListener
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = list.get(position).title
        holder.itemView.setOnClickListener {
            mOnItemClickListener?.onItemClick(position)
        }


    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.item_layout, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tv_title

    }
}