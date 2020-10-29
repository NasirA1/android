package com.example.myquiz.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myquiz.R
import com.example.myquiz.model.QuizResult
import com.example.myquiz.util.Constants
import com.example.myquiz.vm.QuizResultViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuizResultFragment : Fragment() {

    companion object { const val TAG = Constants.TAG }

    private lateinit var navController: NavController
    private val viewModel: QuizResultViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        //button_start_quiz.setOnClickListener { startQuiz() }

        subscribeObservers()
    }


    private fun subscribeObservers() {
        viewModel.quizResultState.observe(viewLifecycleOwner, Observer {
            onQuizResult(it)
        })
    }

    private fun onQuizResult(quizResult: QuizResult) {
        Log.d(TAG, "onQuizResult: $quizResult")
    }

}