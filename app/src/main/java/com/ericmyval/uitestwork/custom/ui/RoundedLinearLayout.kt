package com.ericmyval.uitestwork.custom.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.LinearLayout

class RoundedLinearLayout : LinearLayout {
    var path: Path? = null
    var cornerRadius = 0f

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    override fun draw(canvas: Canvas) {
        canvas.save()
        canvas.clipPath(path!!)
        super.draw(canvas)
        canvas.restore()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val r = RectF(0F, 0F, w.toFloat(), h.toFloat())
        path = Path()
        path!!.addRoundRect(r, cornerRadius, cornerRadius, Path.Direction.CW)
        path!!.close()
    }
}