package com.brep.appaviones2025

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Bullet(var x: Float, var y: Float, private val speed: Float, private val color: Int) {
    val size = 10f

    fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = color
        canvas.drawCircle(x, y, size, paint)
    }

    fun move() {
        y += speed
    }

    fun collidesWith(enemy: Enemy): Boolean {
        return x < enemy.x + enemy.size &&
                x + size > enemy.x &&
                y < enemy.y + enemy.size &&
                y + size > enemy.y
    }
}