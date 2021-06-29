package com.urinapa.satvocabulary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.urinapa.satvocabulary.data.Vocab
import com.urinapa.satvocabulary.data.VocabViewModel

class HomeFragment : Fragment(), View.OnClickListener {


    private lateinit var navController: NavController
    private lateinit var tvWordOfDay: TextView
    private lateinit var tvDefinitionOfDay: TextView
    lateinit var mVocabViewModel: VocabViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVocabViewModel = ViewModelProvider(this).get(VocabViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btn_start).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_wordlist).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_start).setOnClickListener(this)

        tvWordOfDay = view.findViewById(R.id.tv_word_of_day)
        tvDefinitionOfDay = view.findViewById(R.id.tv_definition_of_day)

        mVocabViewModel.getAllVocabularies().invokeOnCompletion {
            val wordOfDay = DataClass.vocabList.random()
            tvWordOfDay.text = wordOfDay.word
            tvDefinitionOfDay.text = wordOfDay.formatDefinition()
        }

    }

//    private fun formatDefinition(vocab: Vocab) : String {
//        val definitionList = vocab.definition.split("\n")
//        val exampleList = vocab.example.split("\n")
//        var definition = ""
//        for (i in definitionList.indices) {
//            definition += "${definitionList[i]}\n\t${exampleList[i]}\n\n"
//        }
//        return definition
//    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_start -> {
                val roundsDialog = RoundDialogFragment()
                parentFragmentManager.setFragmentResultListener(
                    "rounds",
                    roundsDialog,
                    {req:String, res:Bundle ->
                        val rounds = res.getInt("rounds")
                        val words = DataClass.vocabList.shuffled().subList(0, rounds).map { VocabHelper(it, false) }

                        val arg = VocabsParcelable(words)
                        Log.i("SOMESHIT", arg.toString())

                        val action = HomeFragmentDirections.actionHomeFragmentToGameFragment(arg, 0)
                        navController.navigate(action)

//                        val bar = Snackbar.make(v, "${res.getInt("rounds")} rounds have been selected", Snackbar.LENGTH_LONG)
//                        bar.animationMode = Snackbar.ANIMATION_MODE_FADE
//                        bar.show()
                    }
                )
                roundsDialog.show(parentFragmentManager, "RoundsDialogFragment")
            }
        }
    }
}