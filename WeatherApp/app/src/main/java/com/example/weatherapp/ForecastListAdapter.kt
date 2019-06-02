package com.example.weatherapp

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find



class ForecastListAdapter(private val weekForecast: ForecastVmList,
                          private val itemClick: (ForecastVm) -> Unit)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>()
{
    interface OnItemClickListener {
        operator fun invoke(forecastVm: ForecastVm)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = weekForecast.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(weekForecast[position]) {
            holder.bindForecastVm(weekForecast[position])
        }
    }

    class ViewHolder(val view: View, private val itemClick: (ForecastVm) -> Unit)
        : RecyclerView.ViewHolder(view)
    {
        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.dateText)
        private val descriptionView = view.find<TextView>(R.id.descriptionText)
        private val maxTemperatureView = view.find<TextView>(R.id.maxTemperature)
        private val minTemperatureView = view.find<TextView>(R.id.minTemperature)

        fun bindForecastVm(forecastVm: ForecastVm) {
            with(forecastVm) {
                Picasso.with(itemView.ctx).load(iconUrl).into(iconView)
                dateView.text = date.toDateString()
                descriptionView.text = description
                maxTemperatureView.text = "$high"
                minTemperatureView.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}