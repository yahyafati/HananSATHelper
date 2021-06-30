package com.urinapa.satvocabulary

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.urinapa.satvocabulary.data.Vocab

class WordDefinitionDialogFragment(val vocab: Vocab) : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_word_definition_dialog, container, false)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvWordlistDefinition = view.findViewById<TextView>(R.id.tv_wordlist_definition)
        tvWordlistDefinition.setTextIsSelectable(true)

        view.findViewById<TextView>(R.id.tv_wordlist_word).text = vocab.word
        tvWordlistDefinition.text = vocab.formatDefinition()
        tvWordlistDefinition.movementMethod = ScrollingMovementMethod()
    }
}