package com.example.mycal

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var currentNumber = ""
    private var previousNumber = ""
    private var operation = ""

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Buttons and TextViews
        val ac = findViewById<Button>(R.id.btn_ac)
        val div = findViewById<Button>(R.id.btn_div)
        val num7 = findViewById<Button>(R.id.num7)
        val num8 = findViewById<Button>(R.id.num8)
        val num9 = findViewById<Button>(R.id.num9)
        val mul = findViewById<Button>(R.id.btn_mul)
        val num4 = findViewById<Button>(R.id.num4)
        val num5 = findViewById<Button>(R.id.num5)
        val num6 = findViewById<Button>(R.id.num6)
        val sub = findViewById<Button>(R.id.btn_sub)
        val num1 = findViewById<Button>(R.id.num1)
        val num2 = findViewById<Button>(R.id.num2)
        val num3 = findViewById<Button>(R.id.num3)
        val add = findViewById<Button>(R.id.btn_add)
        val dot = findViewById<Button>(R.id.btn_dot)
        val num0 = findViewById<Button>(R.id.num0)
        val equal = findViewById<Button>(R.id.btn_equal)
        val userInput  = findViewById<TextView>(R.id.userInput)
        val result  = findViewById<TextView>(R.id.result)
        val backspace = findViewById<Button>(R.id.btn_del)

        fun appendNumber(number: String) {

            //concatinate
            currentNumber += number
            userInput.text = "$previousNumber $operation $currentNumber"

        }

        fun setOperation(op: String) {

            if (currentNumber.isNotEmpty()) {
                previousNumber = currentNumber
                currentNumber = ""
                operation = op
                userInput.text = "$previousNumber $operation"

            }

        }

        fun calculate() {

            if (currentNumber.isNotEmpty() && previousNumber.isNotEmpty() && operation.isNotEmpty()) {

                val resultValue = when (operation) {
                    "+" -> previousNumber.toDouble() + currentNumber.toDouble()
                    "-" -> previousNumber.toDouble() - currentNumber.toDouble()
                    "X" -> previousNumber.toDouble() * currentNumber.toDouble()
                    "/" -> {
                        if (currentNumber == "0") {
                            result.text = "Error"
                            return
                        } else {
                            previousNumber.toDouble() / currentNumber.toDouble()
                        }

                    }

                    else -> 0.0
                }
                result.text = resultValue.toString()
                currentNumber = resultValue.toString()
                previousNumber = ""
                operation = ""

            }
        }

        // Button click listeners
        ac.setOnClickListener {
            currentNumber = ""
            previousNumber = ""
            operation = ""
            userInput.text = ""
            result.text = ""
        }

        //Backspace
        backspace.setOnClickListener {
            if (currentNumber.isNotEmpty()) {
                currentNumber = currentNumber.dropLast(1) // Remove the last character
                userInput.text = currentNumber // Update the display
            } else if (operation.isNotEmpty()) {
                operation = ""
                userInput.text = previousNumber // Show the previous number
            }
        }


        num0.setOnClickListener { appendNumber("0") }
        num1.setOnClickListener { appendNumber("1") }
        num2.setOnClickListener { appendNumber("2") }
        num3.setOnClickListener { appendNumber("3") }
        num4.setOnClickListener { appendNumber("4") }
        num5.setOnClickListener { appendNumber("5") }
        num6.setOnClickListener { appendNumber("6") }
        num7.setOnClickListener { appendNumber("7") }
        num8.setOnClickListener { appendNumber("8") }
        num9.setOnClickListener { appendNumber("9") }
        dot.setOnClickListener { appendNumber(".") }

        add.setOnClickListener { setOperation("+") }
        sub.setOnClickListener { setOperation("-") }
        mul.setOnClickListener { setOperation("X") }
        div.setOnClickListener { setOperation("/") }

        equal.setOnClickListener {
            calculate()

        }

    }
}