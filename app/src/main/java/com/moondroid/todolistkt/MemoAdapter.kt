package com.moondroid.todolistkt

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*

class MemoAdapter constructor(val context: Context, val items:ArrayList<MemoVO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VH(LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //val vh:VH = holder as VH //코트린에서 클래스들의 형변환 연산자 as
        holder.itemView.tvTitle.text = items[position].time
        holder.itemView.tvMsg.text = items[position].txt
    }

    inner class VH(itemView : View) : RecyclerView.ViewHolder(itemView){

        //이렇게 find 하는 작업 필요없음. why? xml 의 id 가 바로 변수명으로 사용
        //var iv:ImageView = itemView.findViewById(R.id.iv)
        init {
            itemView.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    //클릭한 itemView의 위치
                    //val position = layoutPosition
                    val item = items[layoutPosition]

                    val intent = Intent(context, MemoActivity::class.java)
                    intent.putExtra("txt", item.txt)
                    intent.putExtra("time", item.time)

                    context.startActivity(intent)
                }

            })
        }

    }
}