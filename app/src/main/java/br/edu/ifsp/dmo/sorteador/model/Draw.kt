package br.edu.ifsp.dmo.sorteador.model

import kotlin.random.Random

class Draw(private var limit: Int = 1000) {
    private val history = mutableListOf<Int>()

    fun setLimit(limit: Int) {
        this.limit = limit
        // Limpar histórico ao definir um novo limite
        history.clear()
    }

    fun getNumber(): Int {
        if (history.size >= limit) {
            throw IllegalStateException("Já foram sorteados todos os valores possíveis dentro do intervalo.")
        }
        var number: Int
        do {
            number = Random.nextInt(1, limit + 1)
        } while (history.contains(number))
        history.add(number)
        return number
    }

    fun getHighBorder(): Int {
        return limit
    }

    fun getHistory(): List<Int> {
        return history.toList()
    }
}
