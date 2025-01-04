package com.example.f1biography

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class ListDriverAdapter(private val listDriver: ArrayList<Driver>) : RecyclerView.Adapter<ListDriverAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: CircleImageView = itemView.findViewById(R.id.iv_driver)
        val tvDriverName: TextView = itemView.findViewById(R.id.tv_driver_name)
        val tvDriverTeam: TextView = itemView.findViewById(R.id.tv_team_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description_name)
        val cvItem: CardView = itemView.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_driver, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listDriver.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, born, photo, nationality, team, carNumber, winCount, podiumCount, description) = listDriver[position]

        holder.imgPhoto.setImageResource(photo)
        holder.tvDriverTeam.text = team
        holder.tvDriverName.text = name
        holder.tvDescription.text = description

        holder.cvItem.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_driver", listDriver[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }
}