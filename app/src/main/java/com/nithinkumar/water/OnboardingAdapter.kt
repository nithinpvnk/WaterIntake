package com.nithinkumar.water

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.onboarding_item.view.*

class OnboardingAdapter : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {
    var pageNames: List<String> = listOf("First", "Second")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder =
        OnboardingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.onboarding_item, parent, false))

    override fun getItemCount() = pageNames.size
    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) = holder.bind(pageNames[position])

    class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(text: String) {
            itemView.onboardingItemText.text = text
        }
    }
}
