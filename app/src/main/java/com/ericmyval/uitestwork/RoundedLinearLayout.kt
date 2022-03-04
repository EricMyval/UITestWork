package com.ericmyval.uitestwork

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.LinearLayout

class RoundedLinearLayout : LinearLayout {
    var mPath: Path? = null
    var mCornerRadius = 0f

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    override fun draw(canvas: Canvas) {
        canvas.save()
        canvas.clipPath(mPath!!)
        super.draw(canvas)
        canvas.restore()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val r = RectF(0F, 0F, w.toFloat(), h.toFloat())
        mPath = Path()
        mPath!!.addRoundRect(r, mCornerRadius, mCornerRadius, Path.Direction.CW)
        mPath!!.close()
    }
}