package com.example.flappycompose.data.structures

import com.example.flappycompose.data.models.Tube

class TubeQueue {
    private var list = mutableListOf<Tube>()

    fun last(): Tube {
        return list[list.size - 1]
    }

    fun at(index: Int): Tube {
        return list[index]
    }

    fun moveTubes(distance: Int) {
        list = list.map { it.copy(x = it.x - distance) }.toMutableList()
    }

    fun add(tube: Tube) {
        list.add(tube)
    }

    fun dropFirst() {
        list.removeAt(0)
    }

    fun getList(): List<Tube> {
        return list.toList()
    }
}