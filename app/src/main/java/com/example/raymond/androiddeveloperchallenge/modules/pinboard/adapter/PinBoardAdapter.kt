package com.example.raymond.androiddeveloperchallenge.modules.pinboard.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.raymond.androiddeveloperchallenge.R
import com.example.raymond.androiddeveloperchallenge.core.utils.ImageLoader
import com.example.raymond.androiddeveloperchallenge.modules.pinboard.model.PinBoard
import kotlinx.android.synthetic.main.item_pin_board.view.*

class PinBoardAdapter(val context: Context?, val items: List<PinBoard>) : RecyclerView.Adapter<PinBoardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PinBoardAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pin_board, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PinBoardAdapter.ViewHolder, position: Int) {
        val item = items[position]

        ImageLoader().loadImage(item._urls.full, holder.image_background, context)

        holder.txt_name?.text = item._user.name
        if (item._likes != 0) {
            holder.ll_liked?.visibility = View.VISIBLE
            holder.txt_likes?.text = item._likes.toString()
        } else {
            holder.ll_liked?.visibility = View.GONE
            holder.img_like?.visibility = View.VISIBLE
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image_background = itemView.image_background
        val txt_name = itemView.txt_name
        val ll_liked = itemView.ll_liked
        val txt_likes = itemView.txt_likes
        val img_like = itemView.img_like
    }
}