package com.nithinkumar.water

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.pow

class WaterIntakeRecyclerView(
        context: Context,
        attrs: AttributeSet
) : RecyclerView(context, attrs) {

    private val activeColor by lazy { ContextCompat.getColor(context, R.color.blue) }
    private val inactiveColor by lazy { ContextCompat.getColor(context, R.color.gray) }
    private var viewsToChangeColor: List<Int> = listOf()

    fun <T : ViewHolder> initialize(newAdapter: Adapter<T>) {
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        newAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                post {
                    val sidePadding = (width / 2.5) - (getChildAt(0).width / 2)
                    setPadding(sidePadding.toInt(), 0, sidePadding.toInt(), 0)
                    scrollToPosition(0)
                    addOnScrollListener(object : OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            onScrollChanged()
                        }
                    })
                }
            }
        })
        adapter = newAdapter
    }

    fun setViewsToChangeColor(viewIds: List<Int>) {
        viewsToChangeColor = viewIds
    }

    private fun onScrollChanged() {
        post {
            (0 until childCount).forEach { position ->
                val child = getChildAt(position)
                val childCenterX = (child.left + child.right) / 2
                val scaleValue = getGaussianScale(childCenterX)
                child.scaleX = scaleValue
                child.scaleY = scaleValue
                colorView(child, scaleValue)
            }
        }
    }

    private fun colorView(child: View, scaleValue: Float) {
        val saturationPercent = (scaleValue - 1) / 1f
        val alphaPercent = scaleValue / 2f
        val matrix = ColorMatrix()
        matrix.setSaturation(saturationPercent)

        viewsToChangeColor.forEach { viewId ->
            when (val viewToChangeColor = child.findViewById<View>(viewId)) {
                is ImageView -> {
                    viewToChangeColor.colorFilter = ColorMatrixColorFilter(matrix)
                    viewToChangeColor.imageAlpha = (255 * alphaPercent).toInt()
                }
                is TextView -> {
                    val textColor = ArgbEvaluator().evaluate(saturationPercent, inactiveColor, activeColor) as Int
                    viewToChangeColor.setTextColor(textColor)
                }
            }
        }
    }

    private fun getGaussianScale(
            childCenterX: Int
    ): Float {
        val recyclerCenterX = (left + right) / 2
        return (Math.E.pow(-(childCenterX - recyclerCenterX.toDouble()).pow(2.toDouble()) / (2 * SPREAD_FACTOR.pow(2.toDouble()))) * SCALE_FACTOR + MIN_SCALE_OFFSET).toFloat()
    }

    companion object {
        const val MIN_SCALE_OFFSET = 1.0f
        const val SCALE_FACTOR = 1.0f
        const val SPREAD_FACTOR = 200.0
    }
}