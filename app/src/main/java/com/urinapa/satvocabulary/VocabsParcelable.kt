package com.urinapa.satvocabulary

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VocabsParcelable(
    val vocabularies: List<VocabHelper>,
): Parcelable
