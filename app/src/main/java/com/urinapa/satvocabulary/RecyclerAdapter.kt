package com.urinapa.satvocabulary

import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.urinapa.satvocabulary.data.Vocab

class RecyclerAdapter(val parent: Fragment, val etSearch: EditText): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var vocabularies: List<Vocab>

    init {
        vocabularies = filteredData()
    }

    private fun filteredData(): List<Vocab> {
        if (TextUtils.isEmpty(etSearch.text)) {
            return DataClass.vocabList
        }
        val res = DataClass.vocabList.filter { it.word.lowercase().contains(etSearch.text.toString().lowercase()) }
        Log.i("SEARCHVALUE", "Size: ${res.size}")
        return res
    }

    fun filterData() {
        vocabularies = filteredData()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.wordlist_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.tvWordlistItem.text = vocabularies[position].word
        holder.vocab = vocabularies[position]
    }

    override fun getItemCount(): Int {
        return vocabularies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvWordlistItem: TextView
        lateinit var vocab: Vocab

        init {
            tvWordlistItem = itemView.findViewById(R.id.tv_wordlist_item)
            itemView.setOnClickListener {
                val wordDefinitionDialogFragment = WordDefinitionDialogFragment(vocab)
                wordDefinitionDialogFragment.show(parent.parentFragmentManager, "WordDefinitionDialogFragment")
            }
        }
    }
}