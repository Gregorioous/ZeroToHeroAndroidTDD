package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val count = Count.Base(2, 4, 0)
    private lateinit var uiState: UiState
    private lateinit var textView: TextView
    private lateinit var buttonIncrement: Button
    private lateinit var buttonDecrement: Button
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        textView = binding.countTextView
        buttonIncrement = binding.incrementButton
        buttonDecrement = binding.decrementButton

        buttonIncrement.setOnClickListener {
            uiState = count.increment(textView.text.toString())
            uiState.apply(buttonDecrement, buttonIncrement, textView)
        }

        buttonDecrement.setOnClickListener {
            uiState = count.decrement(textView.text.toString())
            uiState.apply(buttonDecrement, buttonIncrement, textView)
        }

        if (savedInstanceState == null) {
            uiState = count.initial((textView.text.toString()))
            uiState.apply(buttonDecrement, buttonIncrement, textView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEYFIRST, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            uiState = savedInstanceState.getSerializable(KEYFIRST, uiState::class.java) as UiState
        else
            uiState = savedInstanceState.getSerializable(KEYFIRST) as UiState

        uiState.apply(buttonDecrement, buttonIncrement, textView)
    }

    companion object {
        private const val KEYFIRST = "KEYFIRST"
    }
}