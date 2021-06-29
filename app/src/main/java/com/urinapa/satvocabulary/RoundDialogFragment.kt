package com.urinapa.satvocabulary

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import java.lang.ClassCastException

class RoundDialogFragment : DialogFragment(), View.OnClickListener {

    interface OnRoundSelected {
        fun sendRound(round: Int)
    }

    lateinit var onRoundSelected: OnRoundSelected



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_round_dialog, container, false)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_round_10).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_round_25).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_round_50).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_round_100).setOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_round_10 -> {
                parentFragmentManager.setFragmentResult(
                    "rounds", bundleOf("rounds" to 10)
                )
            }
            R.id.btn_round_25 -> {
                parentFragmentManager.setFragmentResult(
                    "rounds", bundleOf("rounds" to 25)
                )
            }
            R.id.btn_round_50 -> {
                parentFragmentManager.setFragmentResult(
                    "rounds", bundleOf("rounds" to 50)
                )
            }
            R.id.btn_round_100 -> {
                parentFragmentManager.setFragmentResult(
                    "rounds", bundleOf("rounds" to 100)
                )
            }
        }
        dialog?.dismiss()
    }
}