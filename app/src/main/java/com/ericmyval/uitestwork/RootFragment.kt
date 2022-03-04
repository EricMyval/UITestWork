package com.ericmyval.uitestwork

import android.annotation.SuppressLint
import android.content.Context
import android.os.*
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ericmyval.uitestwork.databinding.FragmentRootBinding
import kotlin.math.max

class RootFragment: Fragment(R.layout.fragment_root) {
    private lateinit var binding: FragmentRootBinding
    private var defSizeBoxes: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)
        defSizeBoxes = resources.getDimension(R.dimen.menu_size_back).toInt()

        binding.tlTable2.setOnLongClickListener(expandListener)
        binding.tlTable2.setOnClickListener(collapseListener)

        binding.btnMobile.setOnClickListener(btnMenuListener)
        binding.btnDrop.setOnClickListener(btnMenuListener)
        binding.btnRouter.setOnClickListener(btnMenuListener)
        binding.btnWifi.setOnClickListener(btnMenuListener)
        binding.btnBL.setOnClickListener(btnMenuListener)
        binding.btnBL.setOnLongClickListener {
            btnMenuListener.onClick(it)
            findNavController().navigate(R.id.action_rootFragment_to_scannerFragment)
            return@setOnLongClickListener true
        }
        binding.btnAirplane.setOnClickListener {
            findNavController().navigate(R.id.action_rootFragment_to_imageFragment)
        }
    }

    private val btnMenuListener = View.OnClickListener {
        val colorOff = ContextCompat.getColorStateList(requireActivity(), R.color.button_off)
        val colorOn = ContextCompat.getColorStateList(requireActivity(), R.color.button_on)
        it.backgroundTintList = if (it.backgroundTintList == colorOn) colorOff else colorOn
        vibrator(100L)
    }

    private fun vibrator(time: Long) {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            (requireActivity().getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
        else
            (context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(time, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(time)
        }
    }

    private val expandListener = View.OnLongClickListener {
        it.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val targetHeight: Int = it.measuredHeight + resources.getDimension(R.dimen.circle_background_margin).toInt()
        val currentHeight = it.layoutParams.height
        if (currentHeight != defSizeBoxes)
            return@OnLongClickListener false
        val anim: Animation = object : Animation(){
            @SuppressLint("ResourceType")
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                val param = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    max((targetHeight * interpolatedTime).toInt(), currentHeight),
                    1.0f - (1.0f * interpolatedTime)
                )
                param.setMargins(it.marginStart, it.marginTop, it.marginEnd, it.marginBottom)
                it.layoutParams = param
                it.requestLayout()
            }
            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        anim.duration = (targetHeight / it.context.resources.displayMetrics.density).toInt().toLong()
        vibrator(50L)
        it.startAnimation(anim)
        return@OnLongClickListener true
    }

    private val collapseListener = View.OnClickListener {
        val initialHeight: Int = it.measuredHeight
        if (initialHeight == defSizeBoxes)
            return@OnClickListener
        val anim: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                val param = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    max(defSizeBoxes, initialHeight - (initialHeight * interpolatedTime).toInt()),
                    (1.0f * interpolatedTime)
                )
                param.setMargins(it.marginStart, it.marginTop, it.marginEnd, it.marginBottom)
                it.layoutParams = param
                it.requestLayout()
            }
            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        anim.duration = (initialHeight / it.context.resources.displayMetrics.density).toInt().toLong()
        vibrator(50L)
        it.startAnimation(anim)
    }
}