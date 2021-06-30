package com.urinapa.satvocabulary

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlin.math.roundToInt

class GameOverFragment : Fragment() {

    private val args: GameFragmentArgs by navArgs()
    private val adjectives = bundleOf(
        "4" to listOf("Excellent", "Superb", "Outstanding", "Magnificent", "Exceptional", "Perfect"),
        "3" to listOf("Marvellous", "Wonderful", "Sublime", "Fantastic"),
        "2" to listOf("Good", "Amazing", "Satisfactory", "Awesome"),
        "1" to listOf("Next Time"),
        "0" to listOf("Study Hard")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_over, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val score = args.questions!!.vocabularies.filter { it.correct }.count()
        val total = args.questions!!.vocabularies.count()
        val percentile = ((score/total.toFloat())*4).roundToInt()
        val text = "You have got $score out of $total"
        view.findViewById<TextView>(R.id.tv_score_text).text = text
        view.findViewById<TextView>(R.id.tv_score_adjective).text = (adjectives[percentile.toString()] as List<*>).random() as String

        Handler(Looper.myLooper()!!).postDelayed({
            val action = GameOverFragmentDirections.actionGameOverFragmentToHomeFragment()
            findNavController().navigate(action)
        }, 3000)
    }

}