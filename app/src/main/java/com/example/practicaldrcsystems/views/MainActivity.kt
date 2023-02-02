package com.example.practicaldrcsystems.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicaldrcsystems.R
import com.example.practicaldrcsystems.databinding.ActivityMainBinding
import com.example.practicaldrcsystems.utils.ExtFuncs.notifyUser

class MainActivity : AppCompatActivity() {

    //for binding views
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initializeViews()
        initListeners()
    }

    //to initialize views
    private fun initializeViews(){
        binding.apply {

        }
    }

    //to initialize click listeners
    private fun initListeners(){
        binding.apply {
            mcrdSubmitInput.setOnClickListener{
                if (validateInputAmount(tietAmountToWithdrawInput.text.toString().trim())){
                    notifyUser("Accepted")
                }
            }
        }
    }

    //to validate the input amount
    private fun validateInputAmount(enteredAmount: String): Boolean{
        var isValid: Boolean = true

        if (enteredAmount.isNullOrEmpty()){
            isValid = false
            notifyUser("Please enter an amount")
        }
        else if (enteredAmount.toInt() % 10 != 0){
            isValid = false
            notifyUser("Amount must be a multiple of 10")
        }

        return isValid
    }
}