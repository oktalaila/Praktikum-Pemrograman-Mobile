package com.example.calculatortipxmlversion

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etBill: EditText = findViewById(R.id.et_bill_amount)
        val autocompleteTip: AutoCompleteTextView = findViewById(R.id.spinner_tip_options)
        val swRoundUp: SwitchMaterial = findViewById(R.id.switch_round_up)
        val btnCalc: Button = findViewById(R.id.btn_calculate)
        val tvResult: TextView = findViewById(R.id.tv_tip_amount)

        val items = resources.getStringArray(R.array.tip_options)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        autocompleteTip.setAdapter(adapter)

        btnCalc.setOnClickListener {
            val cost = etBill.text.toString().toDoubleOrNull() ?: 0.0
            val selectedText = autocompleteTip.text.toString().replace("%", "")
            val percentage = (selectedText.toDoubleOrNull() ?: 15.0) / 100.0

            var tip = percentage * cost
            if (swRoundUp.isChecked) {
                tip = kotlin.math.ceil(tip)
            }

            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            tvResult.text = formattedTip
        }

        etBill.requestFocus()
    }
}