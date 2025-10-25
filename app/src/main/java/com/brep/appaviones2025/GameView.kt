package com.brep.appaviones2025

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.graphics.Color
import android.view.MotionEvent
import kotlin.random.Random
class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private val thread: GameThread
    private val player = Player(500f, 1500f, 80f)
    private val bulllets = mutableListOf<Bullet>()
    private val enemies = mutableListOf<Enemy>()
    private val enemyBullets = mutableListOf<Bullet>()
    private val paint = Paint()
    private var score = 0
    private var gameOver = false

    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.setRunning(true)
        thread.start()
        spawnEnemies()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        thread.setRunning(false)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        canvas.drawColor(Color.BLACK)

        if(gameOver) {
            drawGameOver(canvas)
            return
        }

        player.draw(canvas)

        bullets.forEach { it.move(); it.draw(canvas)}

        enemyBullets.forEach { it.move(); it.draw(canvas) }

        enemies.forEach { enemy ->
            enemy.move()
            enemy.draw(canvas)

            if (Random.nextFloat() < 0.02) {
                enemyBullets.add(Bullet(enemy.x + 25, enemy.y + 50, 10f, Color.RED))
            }
        }

        checkCollsions()
        drawScore(canvas)
    }
}