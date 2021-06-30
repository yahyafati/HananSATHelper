package com.urinapa.satvocabulary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.urinapa.satvocabulary.data.Vocab

class RecyclerAdapter(val parent: Fragment): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.wordlist_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.tvWordlistItem.text = DataClass.vocabList[position].word
        holder.vocab = DataClass.vocabList[position]
    }

    override fun getItemCount(): Int {
        return DataClass.vocabList.size
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