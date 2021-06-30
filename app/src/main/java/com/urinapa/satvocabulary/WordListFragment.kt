package com.urinapa.satvocabulary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WordListFragment : Fragment() {

    lateinit var etSearch : EditText
    lateinit var rvWordList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_word_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etSearch = view.findViewById(R.id.et_search)
        rvWordList = view.findViewById(R.id.rv_wordlist)

        val layoutManager = LinearLayoutManager(requireContext())
        rvWordList.layoutManager = layoutManager
        val adapter = RecyclerAdapter(this, etSearch)
        rvWordList.adapter = adapter
        etSearch.doAfterTextChanged {
            Log.i("SEARCHVALUE", etSearch.text.toString())
            adapter.filterData()
            adapter.notifyDataSetChanged()
        }

    }
}