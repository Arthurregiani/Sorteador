package br.edu.ifsp.dmo.sorteador.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.sorteador.R
import br.edu.ifsp.dmo.sorteador.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.sorteador.model.Draw

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var draw = Draw()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListener()
    }

    override fun onClick(v: View) {
        when (v) {
            binding.buttonUseLimit -> {
                val limit = try {
                    binding.editLimit.text.toString().toInt()
                } catch (e: NumberFormatException) {
                    -1
                }
                if (limit > 0) {
                    draw.setLimit(limit)
                    updateUI()
                }
            }
            binding.buttonDraw -> {
                try {
                    val number = draw.getNumber()
                    binding.textviewExit.text = number.toString()
                    updateListview()
                } catch (e: IllegalStateException) {
                    // Lidar com a exceção quando o limite de sorteio é atingido
                    binding.textviewExit.text = "Limite de sorteio atingido."
                }
            }
        }
    }

    private fun setClickListener() {
        binding.buttonDraw.setOnClickListener(this)
        binding.buttonUseLimit.setOnClickListener(this)
    }

    private fun updateUI() {
        val str = String.format("Intervalo de 1 a %d.", draw.getHighBorder())
        binding.textviewInterval.text = str
        binding.editLimit.text.clear()
        binding.textviewExit.text = getString(R.string.inicie_o_sorteio)
        updateListview()
    }

    private fun updateListview() {
        val history = draw.getHistory()
        val formattedHistory = mutableListOf<String>()
        for (i in history.indices) {
            val order = i + 1
            val number = history[i]
            formattedHistory.add("$order° sorteado = $number")
        }
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            formattedHistory
        )
        binding.listviewDraw.adapter = adapter
    }

}
