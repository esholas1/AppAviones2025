package com.brep.appaviones2025

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.Size
import kotlin.random.Random

class Enemy(var x: Float, var y: Float, val size: Float) {
    private val paint = Paint().apply { color = Color.RED }
    private val speed = 5f

    fun move(){
        y += speed
        if(y > 1800) {
            y = 0f
            x = Random.nextFloat() * 1000
        }
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(x, y, x + size, y + size, paint)
    }

    fun collidesWith(bullet: Bullet): Boolean {
        return bullet.x < x + size &&
                bullet.x + bullet.size > x &&
                bullet.y < y + size &&
                bullet.y + bullet.size > y
    }
}