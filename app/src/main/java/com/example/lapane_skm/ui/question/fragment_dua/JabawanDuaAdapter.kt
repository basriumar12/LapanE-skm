package com.example.lapane_skm.ui.question.fragment_dua

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lapane_skm.R
import com.example.lapane_skm.save.PrefSaveQuestionDua
import com.example.lapane_skm.ui.question.questionsatu.ModelSatuanKerja
import kotlinx.android.synthetic.main.item_layout_jawaban.view.*

class JabawanDuaAdapter (var context: Context?, var list: List<ModelSatuanKerja>) :
    RecyclerView.Adapter<JabawanDuaAdapter.ViewHolder>() {

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
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = list.get(position).title
        holder.itemView.setOnClickListener {
            mOnItemClickListener?.onItemClick(position)
        }

        val dataCheckImage = context?.let { PrefSaveQuestionDua(it).getData() }

        if (dataCheckImage != null){

            if (list.get(position).id == dataCheckImage){
                holder.imgCheck.visibility = View.VISIBLE
            }

            if(list.get(position).id != dataCheckImage){
                holder.imgCheck.visibility = View.GONE
            }
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.item_layout_jawaban, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tv_title_jawaban
        val imgCheck = itemView.check

    }
}