package com.asct94.securenote.presentation.utils

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.HorizontalScrollView
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.asct94.securenote.databinding.CustomColorSelectorBinding
import com.asct94.securenote.databinding.CustomColorSelectorItemBinding


class ColorSelector @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : HorizontalScrollView(context, attrs, defStyleAttr) {

    private var binding: CustomColorSelectorBinding
    private var colorList: List<Int> = emptyList()
    private var selectedPos: Int = 0
    private var nextSelectedPos: Int = 0
    private var onColorSelected: (Int) -> Unit = {}

    init {
        binding = CustomColorSelectorBinding.inflate(LayoutInflater.from(context), this, true)
//        setColorSelection(getSelectedColor())
    }

    fun getSelectedColor(): Int {
        return colorList.getOrNull(selectedPos) ?: 0
    }

    fun getNextSelectedColor(): Int {
        return colorList.getOrNull(nextSelectedPos) ?: 0
    }

    fun setOnColorSelectedListener(listener: (Int) -> Unit) {
        this.onColorSelected = listener
    }

    fun setColors(colorList: List<Int>) {
        if (this.colorList == colorList) return
        this.colorList = colorList
        binding.content.removeAllViews()
        val layoutInflater = LayoutInflater.from(context)
        colorList.forEach { color ->
            val view =
                CustomColorSelectorItemBinding.inflate(layoutInflater, binding.content, false).root
            view.backgroundTintList = ColorStateList.valueOf(color)
            view.setOnClickListener {
                this.nextSelectedPos = colorList.indexOf(color)
                onColorSelected(color)
            }
            binding.content.addView(view)
        }
    }

    fun setColorSelection(color: Int) {
        this.selectedPos = colorList.indexOf(color)
        binding.content.children.forEachIndexed { index, view ->
            view.isSelected = index == selectedPos
        }
    }
}


@BindingAdapter("app:colors")
fun ColorSelector.setColorsBinder(colorList: List<Int>) {
    this.setColors(colorList)
}

@BindingAdapter("app:colorsAttrChanged")
fun ColorSelector.setColorsListenerBinder(
    attrChange: InverseBindingListener
) {
    this.setOnColorSelectedListener {
        attrChange.onChange()
    }
}

@BindingAdapter("app:selectedColor")
fun ColorSelector.setColorSelectionBinder(color: Int) {
    if (color != getSelectedColor()) {
        this.setColorSelection(color)
    }
}

@InverseBindingAdapter(attribute = "app:selectedColor", event = "app:colorsAttrChanged")
fun ColorSelector.getColorsBinder(): Int {
    return this.getNextSelectedColor()
}
