package com.example.myquiz.ui.fragments

import android.app.ActionBar
import android.os.Bundle
import android.text.TextDirectionHeuristic
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
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
import kotlinx.android.synthetic.main.fragment_quiz_question.*


@AndroidEntryPoint
class QuizQuestionFragment : Fragment() {

    companion object { const val TAG = Constants.TAG }

    private lateinit var navController: NavController
    private val viewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        subscribeObservers()

        button_next_question.setOnClickListener {
            navController.navigate(R.id.action_quizQuestionFragment_self)
        }

        viewModel.fetchNextQuestion()
    }

    private fun subscribeObservers() {
        viewModel.fetchQuestionState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Loading -> { Log.d(StartQuizFragment.TAG, "QuizQuestionFragment quizStartState Loading..") }
                is DataState.Success -> { onSuccessState(it) }
                is DataState.Error -> { Log.e(StartQuizFragment.TAG, "QuizQuestionFragment quizStartState ERROR: ${it.ex}") }
            }
        })
    }

    private fun onSuccessState(it: DataState.Success<Question?>) {
        if (it.data != null) {
            Log.d(TAG, "QuizQuestionFragment: TODO display question and options")
            Log.d(TAG, "QuizQuestionFragment: ${it.data}")
            textview_question.text = it.data.question
            displayOptions(it.data)
        } else {
            Log.w(TAG, "QuizQuestionFragment: quiz ended!")
            navController.navigate(R.id.action_quizQuestionFragment_to_quizResultFragment)
        }
    }

    private fun displayOptions(question: Question) {
        question.apply {
            if(options.multiChoice) {
                options.questionOptions.forEach {
                    layout_options.addView(
                        CheckBox(this@QuizQuestionFragment.context).apply {
                            text = it.description
                            textSize = 24.0F
                            textDirection = View.TEXT_DIRECTION_RTL
                            setOnCheckedChangeListener { compoundButton, b ->
                                Log.d(TAG, "displayOptions: ${compoundButton.text}, $b")
                            }
                        }
                    )
                }
            } else {
                RadioGroup(this@QuizQuestionFragment.context).apply {
                    textDirection = View.TEXT_DIRECTION_RTL
                    layoutParams = LinearLayout.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT
                    )
                    options.questionOptions.forEach {
                        addView(
                            RadioButton(this@QuizQuestionFragment.context).apply {
                                text = it.description
                                textDirection = View.TEXT_DIRECTION_RTL
                                textSize = 22.0F
                                layoutParams = LinearLayout.LayoutParams(
                                    ActionBar.LayoutParams.MATCH_PARENT,
                                    ActionBar.LayoutParams.WRAP_CONTENT
                                )
                                setOnCheckedChangeListener { compoundButton, b ->
                                    Log.d(TAG, "displayOptions: ${compoundButton.text}, $b")
                                }
                            }
                        )
                    }
                    layout_options.addView(this)
                }
            }
        }

    }

}