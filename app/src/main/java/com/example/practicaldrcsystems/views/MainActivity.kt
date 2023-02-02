package com.example.practicaldrcsystems.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.practicaldrcsystems.R
import com.example.practicaldrcsystems.adapters.DenominationNoOfNotesAdapter
import com.example.practicaldrcsystems.consts.AppConsts
import com.example.practicaldrcsystems.databinding.ActivityMainBinding
import com.example.practicaldrcsystems.models.NotesAmountModel
import com.example.practicaldrcsystems.utils.ExtFuncs.notifyUser
import com.example.practicaldrcsystems.viewmodels.MainViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MainActivity : AppCompatActivity() {

    //for binding views
    private lateinit var binding: ActivityMainBinding

    //for notes adapter and list
    private lateinit var denominationNoOfNotesAdapter: DenominationNoOfNotesAdapter
    private var listOfNotes: ArrayList<NotesAmountModel> = ArrayList()

    //for view model
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initializeViews()
        initListeners()
        initData()
    }

    //to initialize views
    private fun initializeViews(){
        binding.apply {
            mtxtTotalAmount.text = AppConsts.TOTAL_AMOUNT.toString()
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

    //to initialize data
    private fun initData(){
        mainViewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)

        fetchNotesInMachineData()
    }

    //to fetch data of notes in ATM Machine
    private fun fetchNotesInMachineData() {
        mainViewModel.getNotesInATMMachine()?.observe(this) {
            if (it.success) {
                inflateListOfNotes(it.listOfNotes)
            } else {
                notifyUser(it.message)
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

    //to inflate total no. of notes in atm machine
    private fun inflateListOfNotes(tempListOfNotes: ArrayList<NotesAmountModel>){
        if (!tempListOfNotes.isNullOrEmpty()){
            denominationNoOfNotesAdapter = DenominationNoOfNotesAdapter(tempListOfNotes)
            binding.rcycDenominationNoOfNotesList.adapter = denominationNoOfNotesAdapter

            val layoutManager = FlexboxLayoutManager(this)
            layoutManager.flexDirection = FlexDirection.ROW
            layoutManager.justifyContent = JustifyContent.FLEX_START
            binding.rcycDenominationNoOfNotesList.layoutManager = layoutManager
        }
    }
}