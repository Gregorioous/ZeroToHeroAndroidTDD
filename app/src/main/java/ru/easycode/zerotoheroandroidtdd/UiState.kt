package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    fun apply(
        buttonDecrement: Button,
        buttonIncrement: Button,
        textView: TextView
    )

    data class Min(private val text: String) : UiState {
        override fun apply(buttonDecrement: Button, buttonIncrement: Button, textView: TextView) {
            textView.text = text
            buttonDecrement.isEnabled = false
            buttonIncrement.isEnabled = true
        }

    }

    data class Base(private val text: String) : UiState {

        override fun apply(buttonDecrement: Button, buttonIncrement: Button, textView: TextView) {
            textView.text = text
            buttonDecrement.isEnabled = true
            buttonIncrement.isEnabled = true
        }

    }

    data class Max(private val text: String) : UiState {
        override fun apply(buttonDecrement: Button, buttonIncrement: Button, textView: TextView) {
            textView.text = text
            buttonIncrement.isEnabled = false
            buttonDecrement.isEnabled = true
        }

    }
}