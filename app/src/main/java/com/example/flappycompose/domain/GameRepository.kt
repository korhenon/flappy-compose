package com.example.flappycompose.domain

import com.example.flappycompose.common.Constants
import com.example.flappycompose.data.models.Bird
import com.example.flappycompose.data.models.Frame
import com.example.flappycompose.data.models.Tube
import com.example.flappycompose.data.structures.TubeQueue
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class GameRepository @Inject constructor() {
    private var lose = false

    private val birdX get() = width / 5
    private var birdY = 0f
    private var jumpHeight: Float? = null
    private var jumpStart = 0f
    private var width = 0f
    private var height = 0f
    private var score = 0
    private var speed = Constants.START_SPEED
    private var queue = TubeQueue()

    fun setSize(width: Float, height: Float) {
        this.width = width
        this.height = height
    }

    private fun updateTubes() {
        queue.moveTubes(speed)
        if (queue.at(0).x < -Constants.TUBE_WIDTH / 2) queue.dropFirst()
        if (width - queue.last().x >= Constants.SPACE_BETWEEN_TUBES) createTube(width + Constants.TUBE_WIDTH / 2)
    }

    private fun updateBird() {
        if (jumpHeight == null && birdY > jumpStart - Constants.JUMP_HEIGHT * 0.5) birdY += Constants.GRAVITY
        else if (jumpHeight == null && birdY <= jumpStart - Constants.JUMP_HEIGHT * 0.5) birdY += (Constants.GRAVITY * 0.7f)
        else if (birdY > jumpHeight!!) {
            val distance = birdY - jumpHeight!!
            birdY -= if (distance > Constants.JUMP_HEIGHT * 0.3) Constants.JUMP_ACCELERATION else Constants.JUMP_ACCELERATION / 3
        } else jumpHeight = null
    }

    private fun checkCollisions() {
        if (birdY > height) {
            lose = true
            return
        }
        val top = birdY - Constants.BIRD_HEIGHT / 2
        val bottom = birdY + Constants.BIRD_HEIGHT / 2
        var tube = queue.at(0)
        if (tube.x + Constants.TUBE_WIDTH / 2 < birdX - Constants.BIRD_WIDTH / 2) tube = queue.at(1)
        lose = (tube.x - (Constants.TUBE_WIDTH / 2 + Constants.BIRD_WIDTH / 2) < birdX &&
                birdX < tube.x + (Constants.TUBE_WIDTH / 2 + Constants.BIRD_WIDTH / 2))
                && (top <= tube.spaceStartY || bottom >= tube.spaceStartY + Constants.SPACE_HEIGHT)
    }

    private fun updateScore(updater: (Int) -> Unit) {
        for (tube in queue.getList()) {
            if (0 < birdX - tube.x && birdX - tube.x <= speed) {
                score += 1
                if (score.mod(20) == 0) speed += Constants.LEVEL_MULTIPLIER
                updater(score)
                if (score == 100) lose = true
                break
            }
        }
    }

    private fun createTube(x: Float) {
        queue.add(
            Tube(
                x,
                Random.nextInt(
                    Constants.TUBE_MIN_HEIGHT.toInt(),
                    (height - (Constants.TUBE_MIN_HEIGHT + Constants.SPACE_HEIGHT)).toInt()
                ).toFloat()
            )
        )
    }

    suspend fun loop(refresh: (Frame?) -> Unit, onScoreUpdated: (Int) -> Unit): Boolean {
        lose = false
        score = 0
        speed = Constants.START_SPEED
        jumpStart = 0f
        jumpHeight = null
        birdY = height / 2
        queue = TubeQueue()
        val tubeStart = 2 * birdX + Constants.SPACE_BETWEEN_TUBES
        createTube(tubeStart)
        createTube(tubeStart + Constants.TUBE_WIDTH + Constants.SPACE_BETWEEN_TUBES)
        refresh(Frame(Bird(birdX, height / 2), queue.getList()))
        while (!lose) {
            updateTubes()
            updateBird()
            checkCollisions()
            updateScore { onScoreUpdated(it) }
            refresh(Frame(Bird(birdX, birdY), queue.getList()))
            delay(10)
        }
        refresh(null)
        return score == 100
    }

    fun jump() {
        jumpHeight = birdY - Constants.JUMP_HEIGHT
        jumpStart = birdY
    }
}