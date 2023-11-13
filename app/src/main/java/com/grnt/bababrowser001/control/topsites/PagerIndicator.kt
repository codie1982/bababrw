package com.grnt.bababrowser001.control.topsites

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class PagerIndicator : LinearLayout{
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var selectedIndex = 0
    fun setSize(size: Int){
        println("Size$size")
    }
    /**
     * Set the current selected pager dot.
     */
    fun setSelection(index: Int) {
        if (selectedIndex == index) {
            return
        }

        getChildAt(selectedIndex)?.run {
            isSelected = false
        }
        getChildAt(index)?.run {
            isSelected = true
        }
        selectedIndex = index
    }
}