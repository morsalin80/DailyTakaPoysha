package com.example.dailytakapoysha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.example.dailytakapoysha.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var addItems: Array<String>
    private lateinit var selectedAddItems: String
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addItems = resources.getStringArray(R.array.add_items)
        selectedAddItems = addItems[0]
        binding.addSpinner.onItemSelectedListener = this
        initSpinners()
    }

    private fun initSpinners() {
        initAddSpinner()
        updateCategorySpinner(R.array.add_category)
        updateSpendSpinner(R.array.expense_category)
    }

    private fun updateSpendSpinner(expenseCategory: Int) {
        ArrayAdapter.createFromResource(
            this,
            expenseCategory,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spendSpinner.adapter = adapter
        }
    }

    private fun updateCategorySpinner(arrayId: Int) {
        ArrayAdapter.createFromResource(
            this,
            arrayId,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.categorySpinner.adapter = adapter
        }
    }

    private fun initAddSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.add_items,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.addSpinner.adapter = adapter
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p0 == binding.addSpinner) {
            if (selectedAddItems != addItems[p2]) {
                Log.i(TAG, "P2: $p2, p0: $p0, p1: $p1")
                if (p2 == 0) {
                    binding.spendText.text = getString(R.string.spend_to)
                    updateCategorySpinner(R.array.add_category)
                    updateSpendSpinner(R.array.expense_category)
                } else {
                    binding.spendText.text = getString(R.string.spend_from)
                    updateCategorySpinner(R.array.expense_category)
                    updateSpendSpinner(R.array.add_category)
                }
                selectedAddItems = addItems[p2]
            }

        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}