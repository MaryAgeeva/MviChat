package com.mary.mvichat.presentation.utils.drawable

import android.content.Context
import android.graphics.drawable.Drawable
import com.mary.mvichat.R
import com.mary.mvichat.presentation.utils.color
import javax.inject.Inject
import kotlin.random.Random

interface SimpleDrawableCache<T> {
    fun get(key: T) : Drawable
    fun getRandom() : Drawable
}

class CircleDrawableCache @Inject constructor(private val context: Context) : SimpleDrawableCache<Int> {

    private val drawableCache: LinkedHashMap<Int, Drawable> = linkedMapOf()
    private val size = 20

    override fun get(key: Int) : Drawable {
        synchronized(drawableCache) {
            val colorKey = context.color(key)
            if (drawableCache.containsKey(colorKey) && drawableCache[colorKey] != null)
                return drawableCache[colorKey] as Drawable
            else {
                val newDrawable = DrawableBuilder.Builder(context)
                    .color(colorKey)
                    .shape(Shape.CIRCLE)
                    .build()
                    .drawable
                drawableCache[colorKey] = newDrawable
                if(drawableCache.size > size) {
                    val iterator = drawableCache.iterator()
                    if(iterator.hasNext()) {
                        iterator.next()
                        iterator.remove()
                    }
                }
                return newDrawable
            }
        }
    }

    override fun getRandom() : Drawable {
        val colors = arrayOf(R.color.blue, R.color.red, R.color.green, R.color.pink, R.color.violet, R.color.yellow)
        return get(colors[Random.nextInt(6)])
    }

}