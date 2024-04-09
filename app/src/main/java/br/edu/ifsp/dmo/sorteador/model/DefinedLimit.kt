package br.edu.ifsp.dmo.sorteador.model

class DefinedLimit(private val border: Int) : SorteioStrategy() {

    override fun nextNumber(): Int {
        return random.nextInt(1, border + 1)
    }

    override fun getLowBorder(): Int {
        return 1
    }

    override fun getHighBorder(): Int {
        return border
    }
}