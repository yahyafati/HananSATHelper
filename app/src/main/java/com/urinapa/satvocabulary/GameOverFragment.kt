package com.urinapa.satvocabulary

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class GameOverFragment : Fragment() {

    private val args: GameFragmentArgs by navArgs()

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
        val text = "You have got $score out of $total"
        view.findViewById<TextView>(R.id.tv_score_text).text = text

        Handler(Looper.myLooper()!!).postDelayed({
            val action = GameOverFragmentDirections.actionGameOverFragmentToHomeFragment()
            findNavController().navigate(action)
        }, 2000)
    }

}