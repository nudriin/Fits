package com.nudriin.fits.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nudriin.fits.data.domain.HealthAnalysis
import com.nudriin.fits.data.dto.product.UserHistoryItem
import com.nudriin.fits.databinding.ScanHistoryCardBinding

class ScanHistoryAdapter(private val scanHistoryList: List<UserHistoryItem>) :
    RecyclerView.Adapter<ScanHistoryAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(
            label: String,
            name: String,
            overall: String,
            sugar: HealthAnalysis,
            fat: HealthAnalysis,
            protein: HealthAnalysis,
            calories: HealthAnalysis,
            assessment: String,
        )
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(private val binding: ScanHistoryCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(scanHistory: UserHistoryItem) {
            binding.tvScanHistoryTitle.text = scanHistory.product.name
            binding.tvScanHistoryDescription.text = scanHistory.product.overall
            binding.tvScanHistoryLabel.text = scanHistory.product.grade.gradeName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ScanHistoryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = scanHistoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val scanHistory = scanHistoryList[position]
        holder.bind(scanHistory)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(
                label = scanHistory.product.grade.gradeName,
                name = scanHistory.product.name,
                overall = scanHistory.product.overall,
                sugar = HealthAnalysis(
                    "Sugar",
                    scanHistory.product.sugar,
                    scanHistory.product.sugarIng
                ),
                fat = HealthAnalysis("Fat", scanHistory.product.fat, scanHistory.product.fatIng),
                protein = HealthAnalysis(
                    "Protein",
                    scanHistory.product.protein,
                    scanHistory.product.proteinIng
                ),
                calories = HealthAnalysis(
                    "Calories",
                    scanHistory.product.calories,
                    scanHistory.product.caloriesIng
                ),
                assessment = scanHistory.product.healthAssessment
            )
        }
    }
}