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
import com.example.myquiz.model.Question
import com.example.myquiz.util.Constants
import com.example.myquiz.util.DataState
import com.example.myquiz.vm.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuizFragment : Fragment() {

    companion object { const val TAG = Constants.TAG }

    private lateinit var navController: NavController
    private val viewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        subscribeObservers()

        viewModel.fetchNextQuestion()
    }

    private fun subscribeObservers() {
        viewModel.fetchQuestionState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Loading -> { Log.d(StartQuizFragment.TAG, "QuizFragment quizStartState Loading..") }
                is DataState.Success -> { onSuccessState(it) }
                is DataState.Error -> { Log.e(StartQuizFragment.TAG, "QuizFragment quizStartState ERROR: ${it.ex}") }
            }
        })
    }

    private fun onSuccessState(it: DataState.Success<Question?>) {
        if (it.data != null) {
            Log.d(TAG, "QuizFragment: TODO display question and options")
            Log.d(TAG, "QuizFragment: ${it.data}")
        } else {
            Log.w(TAG, "QuizFragment: quiz ended!")
            navController.navigate(R.id.action_quizFragment_to_quizResultFragment)
        }
    }

}