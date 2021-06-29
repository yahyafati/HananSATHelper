package com.urinapa.satvocabulary

import android.os.Parcelable
import com.urinapa.satvocabulary.data.Vocab
import kotlinx.parcelize.Parcelize

@Parcelize
class VocabHelper(
    val vocab: Vocab,
    var correct: Boolean
): Parcelable