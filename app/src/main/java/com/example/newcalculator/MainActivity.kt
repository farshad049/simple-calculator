package com.example.newcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.newcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    var isDigit=false
    var isDot=false

    fun onDigit(view:View){
        binding.textView.append((view as Button).text)
        isDigit=true
    }

    fun clear(view: View){
        binding.textView.text=""
    }

    fun onDecimal(view: View){
        if (isDigit && !isDot){
            binding.textView.append(".")
        }
        isDigit=false
        isDot=true
    }

    fun onOperator(view: View){
        if (isDigit && !isOperator((binding.textView.text).toString())){
            binding.textView.append((view as Button).text)
        }
        isDot=false
        isDigit=false
    }

    fun isOperator(value:String):Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("+") || value.contains("-") ||
                    value.contains("/") || value.contains("*")
        }
    }


    fun onEqual(view: View){
        var input=binding.textView.text
        var prefix=""

        if (isDigit){

            if (input.startsWith("-")){
                input=input.substring(1)
                prefix="-"
            }

            if (input.contains("-")){
                var splited=input.split("-")
                var one=splited[0]
                var two=splited[1]
                if (!prefix.isEmpty()){
                    one=prefix+one
                }
                binding.textView.text=isDotZero((one.toDouble() - two.toDouble()).toString())
            }

            if (input.contains("+")){
                var splited=input.split("+")
                var one=splited[0]
                var two=splited[1]
                if (!prefix.isEmpty()){
                    one=prefix+one
                }
                binding.textView.text=isDotZero((one.toDouble() + two.toDouble()).toString())
            }

            if (input.contains("/")){
                var splited=input.split("/")
                var one=splited[0]
                var two=splited[1]
                if (!prefix.isEmpty()){
                    one=prefix+one
                }
                binding.textView.text=isDotZero((one.toDouble() / two.toDouble()).toString())
            }

            if (input.contains("*")){
                var splited=input.split("*")
                var one=splited[0]
                var two=splited[1]
                if (!prefix.isEmpty()){
                    one=prefix+one
                }
                binding.textView.text=isDotZero((one.toDouble() * two.toDouble()).toString())
            }
        }
    }

    fun isDotZero(value: String):String{
        var resault=value
       if (value.contains(".0")){
            resault=value.substring(0,value.length - 2)
        }
        return resault
    }
}

