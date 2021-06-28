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
        setContentView(R.layout.activity_main)

        tvCardFront = findViewById(R.id.tv_word)
        tvCardDefinition = findViewById(R.id.definition_layout)
        cv_vocabulary = findViewById(R.id.cv_vocabulary)
        tvDefinedWord = findViewById(R.id.tv_defined_word)
        tvDefinition = findViewById(R.id.tv_definition)
        btnGotIt = findViewById(R.id.btn_got_it)


        mVocabViewModel = ViewModelProvider(this).get(VocabViewModel::class.java)
        mVocabViewModel.countVocabs.observe(this) {count ->
            if (count < 260) {
                mVocabViewModel.deleteAllVocabs()
                addVocabs()
            } else {
                Toast.makeText(this, "There are already $count vocabs. What more do you want?", Toast.LENGTH_LONG).show()
            }
        }
        mVocabViewModel.readAllData.observe(this, { vocabs ->
            DataClass.vocabList = vocabs
            Toast.makeText(this, "Data Changed", Toast.LENGTH_LONG).show()
        })


        tvCardFront.setOnClickListener { rotate(1) }
        tvCardDefinition.setOnClickListener { rotate(2) }
        btnGotIt.setOnClickListener{
            currentIndex++
            if (currentIndex >= DataClass.vocabList.size) currentIndex = 0
            rotate(2)
            refreshCard()
        }

        refreshCard()
    }

    private fun addVocabs() {
        val jsonReader: JsonReader
        val inputStream = assets.open("words.json")
//      val json = inputStream.bufferedReader().use { it.readText() }
        var count = 0
        jsonReader = JsonReader(inputStream.reader())
        jsonReader.beginArray()
        while (jsonReader.hasNext()) {
            mVocabViewModel.addVocab(readVocab(jsonReader))
            count++
        }
        jsonReader.close()

        Log.i("DISPLAYED_VOCAB", "$count vocabularies have been added to the database.")
        Toast.makeText(this, "$count vocabularies have been added to the database.", Toast.LENGTH_LONG).show()
    }

    private fun readVocab(reader: JsonReader) : Vocab {
        var word = ""
        var definition = ""
        var example = ""

        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            when(name) {
                "word" -> word = reader.nextString()
                "definition" -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        definition += reader.nextString() + "\n"
                    }
                    reader.endArray()
                    definition = definition.trim()
                }
                "example" -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        example += reader.nextString() + "\n"
                    }
                    reader.endArray()
                    example = example.trim()
                }
            }
        }
        reader.endObject()
        return Vocab(0, word, definition, example)
    }

    private fun insertDataToDatabase() {
        val vocab = Vocab(0, "Abate", "to Abate", "He abated")
        mVocabViewModel.addVocab(vocab)
        Toast.makeText(this, "Successfully Added", Toast.LENGTH_LONG).show()
    }

    /**
     * @param to
     * 1 - to Definition
     * 2 - to Word
     */
    private fun rotate(to: Int) {
        val anime1 = ObjectAnimator.ofFloat(cv_vocabulary, "scaleX", 1f, 0f)
        val anime2 = ObjectAnimator.ofFloat(cv_vocabulary, "scaleX", 0f, 1f)

        anime1.interpolator = DecelerateInterpolator()
        anime2.interpolator = AccelerateInterpolator()

        anime1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                tvCardFront.translationZ = if (to == 1) 0f else 10f ;
                tvCardFront.alpha = if (to == 1) 0f else 1f;
                tvCardDefinition.translationZ = if (to == 1) 10f else 0f;
                tvCardDefinition.alpha = if (to == 1) 1f else 0f;
                anime2.start();
            }
        })
        anime1.start()
    }

    private fun refreshCard() {
        if (currentIndex >= DataClass.vocabList.size) return

        val currentVocab = DataClass.vocabList.random()
        tvCardFront.text = currentVocab.word
        val definitionList = currentVocab.definition.split("\n")
        val exampleList = currentVocab.example.split("\n")
        var definition = ""
        for (i in definitionList.indices) {
            definition += "${definitionList[i]}\n\t${exampleList[i]}\n\n"
        }
        tvDefinedWord.text = currentVocab.word
        tvDefinition.text = definition
    }
}