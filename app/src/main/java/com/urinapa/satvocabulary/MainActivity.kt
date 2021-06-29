package com.urinapa.satvocabulary

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.JsonReader
import android.util.Log
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.urinapa.satvocabulary.data.Vocab
import com.urinapa.satvocabulary.data.VocabViewModel
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    lateinit var tvCardFront: TextView
    lateinit var tvCardDefinition: ConstraintLayout
    lateinit var cv_vocabulary: CardView
    lateinit var tvDefinedWord: TextView
    lateinit var tvDefinition: TextView
    lateinit var btnGotIt: Button

    var currentIndex = 0

    lateinit var mVocabViewModel: VocabViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SATVocabulary)

        mVocabViewModel = ViewModelProvider(this).get(VocabViewModel::class.java)
        setContentView(R.layout.activity_main)

//        tvCardFront = findViewById(R.id.tv_word)
//        tvCardDefinition = findViewById(R.id.definition_layout)
//        cv_vocabulary = findViewById(R.id.cv_vocabulary)
//        tvDefinedWord = findViewById(R.id.tv_defined_word)
//        tvDefinition = findViewById(R.id.tv_definition)
//        btnGotIt = findViewById(R.id.btn_got_it)
//
//

//
//
//        tvCardFront.setOnClickListener { rotate(1) }
//        tvCardDefinition.setOnClickListener { rotate(2) }
//        btnGotIt.setOnClickListener{
//            currentIndex++
//            if (currentIndex >= DataClass.vocabList.size) currentIndex = 0
//            rotate(2)
//            refreshCard()
//        }
//
//        refreshCard()
    }

}