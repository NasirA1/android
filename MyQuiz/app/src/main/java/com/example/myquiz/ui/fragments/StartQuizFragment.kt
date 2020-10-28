package com.example.myquiz.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myquiz.R
import com.example.myquiz.ui.MainActivity
import com.example.myquiz.util.Constants
import com.example.myquiz.util.DataState
import com.example.myquiz.vm.MainViewModel
import com.example.myquiz.vm.StartQuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_start_quiz.*


@AndroidEntryPoint
class StartQuizFragment : Fragment() {

    companion object { const val TAG = Constants.TAG }

    private lateinit var navController: NavController
    private val viewModel: StartQuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        button_start_quiz.setOnClickListener { startQuiz() }

        subscribeObservers()
    }


    private fun subscribeObservers() {
        viewModel.quizStartState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Loading -> { Log.d(TAG, "StartQuizFragment quizStartState Loading..") }
                is DataState.Success -> { navController.navigate(R.id.action_startQuizFragment_to_quizFragment) }
                is DataState.Error -> { Log.e(TAG, "StartQuizFragment quizStartState ERROR: ${it.ex}") }
            }
        })
    }

    private fun startQuiz() =
        viewModel.startQuiz(edittext_player_name.text.toString())

}