package com.example.myquiz.ui.fragments

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.fragment_quiz_result.*


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
        button_start_another_session.setOnClickListener { startAnotherQuiz() }

        subscribeObservers()
    }

    private fun startAnotherQuiz() {
        Log.d(TAG, "startAnotherQuiz: start another..")
    }


    private fun subscribeObservers() {
        viewModel.quizResultState.observe(viewLifecycleOwner, Observer {
            onQuizResult(it)
        })
    }

    @SuppressLint("SetTextI18n")
    private fun onQuizResult(quizResult: QuizResult) {
        Log.d(TAG, "onQuizResult: $quizResult")
        textview_quiz_result.text = "${quizResult.correctAnswers}/${quizResult.totalQuestions}"
        textview_quiz_result_percentage.text = "${quizResult.percentage()}%"
    }

}