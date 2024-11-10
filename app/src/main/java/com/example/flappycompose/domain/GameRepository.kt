package com.example.flappycompose.domain

import com.example.flappycompose.common.Constants
import com.example.flappycompose.data.models.Bird
import com.example.flappycompose.data.models.Frame
import com.example.flappycompose.data.models.Tube
import com.example.flappycompose.data.structures.TubeQueue
import kotlinx.coroutines.delay
import javax.inject.Inject

class GameRepository @Inject constructor() {
    var lose = false

    val tubeSpace get() = width / 4
    val birdX get() = width / 5

    private var width = 0
    private var height = 0

    private var queue = TubeQueue()

    fun setSize(width: Float, height: Float) {
        this.width = width.toInt()
        this.height = height.toInt()
    }

    suspend fun loop(refresh: (Frame?) -> Unit) {
        queue = TubeQueue()
        val tubeStart = 2 * birdX + Constants.TUBE_WIDTH / 2
        queue.add(Tube(tubeStart, height / 2 - Constants.SPACE_HEIGHT / 2))
        queue.add(
            Tube(
                tubeStart + Constants.TUBE_WIDTH + tubeSpace,
                height / 2 - Constants.SPACE_HEIGHT / 2
            )
        )
        queue.add(
            Tube(
                (tubeStart + 2 * (Constants.TUBE_WIDTH + tubeSpace)),
                height / 2 - Constants.SPACE_HEIGHT / 2
            )
        )
        refresh(Frame(Bird(birdX, height / 2), queue.getList()))
        while (!lose) {
            queue.moveTubes(1)
            if (queue.at(0).x < -Constants.TUBE_WIDTH / 2) queue.dropFirst()
            if (width - queue.last().x >= tubeSpace) queue.add(
                Tube(
                    width + Constants.TUBE_WIDTH / 2,
                    height / 2 - Constants.SPACE_HEIGHT / 2
                )
            )
            println(queue.getList().size)
            refresh(Frame(Bird(birdX, height / 2), queue.getList()))
            delay(10)
        }
    }
}