package com.example.tip_calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tip_calculator.databinding.ActivityMainBinding
import java.text.NumberFormat.getCurrencyInstance

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        Log.d("CREATION", "onCreate called")
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip()
    {
        Log.d("CREATION", "calculateTip called")
        val stringInTextField = binding.costOfService
        val cost = stringInTextField.text.toString().toDoubleOrNull() // convert to double
        val selectedId = binding.tipOptions.checkedRadioButtonId // get selected radio button

        if(cost == null)
        {
            binding.tipResult.text = "" // clear result if cost is null
            return
        }

        var tipPercentage = when (selectedId) // get percentage
        {
            R.id.option_twenty -> 0.20
            R.id.option_fifteen -> 0.15
            else -> 0.10
        }

        var tip = tipPercentage * cost // calculate tip

        val roundUp = binding.roundTip.isChecked
        if (roundUp)
        {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = getCurrencyInstance().format(tip) // format tip
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip) // display tip
        Log.d("SUCCESS", "calculateTip finished")
    }
}