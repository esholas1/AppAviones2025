package com.brep.appaviones2025

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Player(var x: Float, var y: Float, val size: Float) {
    private val paint = Paint().apply { color = Color.BLUE }

    fun move(newX: Float) {
        x = newX - size / 2
        if (x < 0) x = 0f
        if (x + size > 1080) x = 1080 - size
    }

    fun draw(canvas: Canvas){
        canvas.drawRect(x, y, x + size, y + size, paint)
    }

    fun collidesWith(bullet: Bullet): Boolean {
        return bullet.x < x + size &&
                bullet.x + bullet.size > x &&
                bullet.y < y + size &&
                bullet.y + bullet.size > y
    }
}