package com.urinapa.satvocabulary

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar

class GameFragment : Fragment(), View.OnClickListener {


    private val args: GameFragmentArgs by navArgs()

    lateinit var cv_vocabulary:CardView
    lateinit var tvCardWord: TextView
    lateinit var tvCardDefinition: TextView
    lateinit var clDidYouGetIt: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clDidYouGetIt = view.findViewById(R.id.cl_did_you_get_it)
        cv_vocabulary = view.findViewById(R.id.cv_vocabulary)
        tvCardWord = view.findViewById(R.id.tv_word)
        tvCardDefinition = view.findViewById(R.id.tv_definition)

        tvCardWord.setOnClickListener { rotate(1) }
        tvCardDefinition.setOnClickListener { rotate(2) }

        val currentVocabHelper: VocabHelper? = args.questions?.vocabularies?.get(args.currentIndex)
        val currentVocab = currentVocabHelper?.vocab
        Log.i("SOMESHIT", args.questions.toString())
        Log.i("SOMESHIT", currentVocabHelper.toString())
        Log.i("SOMESHIT", currentVocab.toString())
        tvCardWord.text = currentVocab?.word
        tvCardDefinition.text = currentVocab?.formatDefinition()

        view.findViewById<Button>(R.id.btn_got_it).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_forgot_it).setOnClickListener(this)
    }

    /**
     * @param to
     * 1 - to Definition
     * 2 - to Word
     */
    private fun rotate(to: Int) {
        clDidYouGetIt.visibility = LinearLayout.VISIBLE
        val anime1 = ObjectAnimator.ofFloat(cv_vocabulary, "scaleX", 1f, 0f)
        val anime2 = ObjectAnimator.ofFloat(cv_vocabulary, "scaleX", 0f, 1f)

        anime1.interpolator = DecelerateInterpolator()
        anime2.interpolator = AccelerateInterpolator()

        anime1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                tvCardWord.translationZ = if (to == 1) 0f else 10f ;
                tvCardWord.alpha = if (to == 1) 0f else 1f;
                tvCardDefinition.translationZ = if (to == 1) 10f else 0f;
                tvCardDefinition.alpha = if (to == 1) 1f else 0f;
                anime2.start();
            }
        })
        anime1.start()
    }

    override fun onClick(v: View?) {

        args.questions?.vocabularies?.get(args.currentIndex)?.correct = v?.id == R.id.btn_got_it

//        when (v?.id) {
//            R.id.btn_forgot_it -> {
//                args.questions?.vocabularies?.get(args.currentIndex)?.correct = false
//            }
//            R.id.btn_got_it -> {
//                args.questions?.vocabularies?.get(args.currentIndex)?.correct = true
//            }
//        }

        if (args.currentIndex+1 < args.questions?.vocabularies?.size!!) {
            val action = GameFragmentDirections.actionGameFragmentSelf(args.questions, args.currentIndex+1)
            findNavController().navigate(action)
        } else {
            val action = GameFragmentDirections.actionGameFragmentToGameOverFragment(args.questions!!)
            findNavController().navigate(action)
//            Snackbar.make(v!!, "Last Item", Snackbar.LENGTH_LONG).show()
        }
    }
}