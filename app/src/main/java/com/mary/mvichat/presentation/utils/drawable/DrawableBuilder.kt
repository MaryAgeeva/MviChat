package com.mary.mvichat.presentation.utils.drawable

import android.content.Context
import android.graphics.drawable.GradientDrawable
import com.mary.mvichat.R
import com.mary.mvichat.presentation.utils.color

class DrawableBuilder private constructor(upperLeft: Float,
                                          upperRight: Float,
                                          lowerRight: Float,
                                          lowerLeft: Float,
                                          color: Int,
                                          shape: Shape) {

    val drawable : GradientDrawable

    init {
        drawable = GradientDrawable().apply {
            this.shape = when (shape) {
                Shape.RECT -> GradientDrawable.RECTANGLE
                Shape.CIRCLE -> GradientDrawable.OVAL
            }
            setColor(color)
            cornerRadii = floatArrayOf(upperLeft, upperLeft, upperRight, upperRight, lowerRight, lowerRight, lowerLeft, lowerLeft)
        }
    }

    class Builder(context: Context) {
        private var upperRightCorner: Float = 0.0f
        private var lowerRightCorner: Float = 0.0f
        private var upperLeftCorner: Float = 0.0f
        private var lowerLeftCorner: Float = 0.0f

        private var color: Int = context.color(R.color.white)

        private var shape: Shape = Shape.RECT

        fun corners(upperLeft: Float, upperRight: Float, lowerRight: Float, lowerLeft: Float) = apply {
            this.upperLeftCorner = upperLeft
            this.lowerRightCorner = lowerRight
            this.upperRightCorner = upperRight
            this.lowerLeftCorner = lowerLeft
        }

        fun corners(corners: Float) = apply {
            this.upperRightCorner = corners
            this.upperLeftCorner = corners
            this.lowerLeftCorner = corners
            this.lowerRightCorner = corners
        }

        fun upperLeft(corner: Float) = apply {
            this.upperLeftCorner = corner
        }

        fun upperRight(corner: Float) = apply {
            this.upperRightCorner = corner
        }

        fun lowerLeft(corner: Float) = apply {
            this.lowerLeftCorner = corner
        }

        fun lowerRight(corner: Float) = apply {
            this.lowerRightCorner = corner
        }

        fun color(color: Int) = apply {
            this.color = color
        }

        fun shape(shape: Shape) = apply {
            this.shape = shape
        }

        fun build() = DrawableBuilder(upperLeftCorner, upperRightCorner, lowerRightCorner, lowerLeftCorner, color, shape)
    }
}