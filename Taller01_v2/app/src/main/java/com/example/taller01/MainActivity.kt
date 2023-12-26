package com.example.taller01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import com.example.taller01.menu.CuentaMesa
import com.example.taller01.menu.ItemMenu
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var switchPropina: Switch? = null
    private var qtyPastel: EditText? = null
    private var qtyCazuela: EditText? = null
    private var tvTotalPastel: EditText? = null
    private var tvTotalCazuela: EditText? = null
    private var tvValorComida: EditText? = null
    private var tvValorPropina: EditText? = null
    private var tvValorTotal: EditText? = null
    private lateinit var cuentaMesa: CuentaMesa



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        qtyPastel = findViewById<EditText>(R.id.edQtyPastel)
        qtyCazuela = findViewById<EditText>(R.id.edQtyCazuela)
        tvTotalPastel = findViewById<EditText>(R.id.tvTotalPastel)
        tvTotalCazuela = findViewById<EditText>(R.id.tvTotalCazuela)
        tvValorComida = findViewById<EditText>(R.id.tvValorComida)
        tvValorPropina = findViewById<EditText>(R.id.tvValorPropina)
        tvValorTotal = findViewById<EditText>(R.id.tvValorTotal)
        switchPropina = findViewById<Switch>(R.id.switchPropina)

        val cuentaMesa = CuentaMesa(1)

        qtyPastel?.addTextChangedListener(CustomTextWatcher())
        qtyCazuela?.addTextChangedListener(CustomTextWatcher())

        switchPropina?.setOnCheckedChangeListener { _, isChecked ->
            cuentaMesa.aceptaPropina = isChecked
            updateUI(cuentaMesa)
        }

        updateUI(cuentaMesa)
    }

    private inner class CustomTextWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            val cantidadPastel = qtyPastel?.text.toString().toIntOrNull() ?:0
            val cantidadCazuela = qtyCazuela?.text.toString().toIntOrNull()?:0
            cuentaMesa.agregarItem(ItemMenu("Pastel de Choclo", "12000"), cantidadPastel)
            cuentaMesa.agregarItem(ItemMenu("Cazuela", "10000"), cantidadCazuela)
            updateUI(cuentaMesa)
        }
    }


    private fun updateUI(cuentaMesa: CuentaMesa) {

        val formatoMoneda = NumberFormat.getCurrencyInstance(Locale("es", "CL"))

        tvTotalPastel?.text = Editable.Factory.getInstance().newEditable(formatoMoneda.format(cuentaMesa.calcularTotalSinPropina()))
        tvTotalCazuela?.text = Editable.Factory.getInstance().newEditable(formatoMoneda.format(cuentaMesa.calcularTotalSinPropina()))
        tvValorComida?.text = Editable.Factory.getInstance().newEditable(formatoMoneda.format(cuentaMesa.calcularTotalSinPropina()))
        tvValorPropina?.text = Editable.Factory.getInstance().newEditable(formatoMoneda.format(cuentaMesa.calcularPropina()))
        tvValorTotal?.text = Editable.Factory.getInstance().newEditable(formatoMoneda.format(cuentaMesa.calcularTotalConPropina()))
    }

}
