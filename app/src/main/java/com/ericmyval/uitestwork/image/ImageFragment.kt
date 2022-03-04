package com.ericmyval.uitestwork.image

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.ericmyval.uitestwork.R
import com.ericmyval.uitestwork.databinding.FragmentImageBinding


class ImageFragment: Fragment(R.layout.fragment_image) {
    private lateinit var binding: FragmentImageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageBinding.bind(view)
        binding.button.setOnClickListener {
            binding.image1.layoutParams.height = binding.image2.measuredHeight
            val listAnim = ArrayList<Float>()
            listAnim.add(0.5F)
            listAnim.add(0.7F)
            listAnim.add(0.3F)
            listAnim.add(0.9F)
            listAnim.add(0.0F)
            expandListener(binding.rllImage, binding.rlWideLine, binding.iLine, listAnim, binding.image2.measuredHeight, 500L)
        }
    }

    private fun expandListener(it: View, itLineWideBox: View, itLineWide: View, percents: ArrayList<Float>, heightBox: Int, duration: Long) {
        if (percents.isEmpty())
            return
        val currentLine = (itLineWide.measuredHeight / 2)
        val current = it.measuredHeight
        val subtract = current - (heightBox * percents[0]).toInt()
        percents.removeAt(0)
        val anim: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                val height = current - (subtract * interpolatedTime).toInt() + 1
                it.layoutParams = LinearLayout.LayoutParams(it.measuredWidth, height, 0F)
                itLineWideBox.layoutParams = LinearLayout.LayoutParams(it.measuredWidth, height + currentLine, 0F)
                it.requestLayout()
                itLineWide.requestLayout()
                if (interpolatedTime == 1.0F)
                    expandListener(binding.rllImage, itLineWideBox, itLineWide, percents, heightBox, duration)
            }
            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        anim.duration = duration
        it.startAnimation(anim)
    }
}