package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var uiState: UiState = UiState.Base("0")
    private val count = Count.Base(2, 4)
    private lateinit var binding: ActivityMainBinding
    private lateinit var textView: TextView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        button = binding.incrementButton
        textView = binding.countTextView

        binding.incrementButton.setOnClickListener {
            uiState = count.increment(binding.countTextView.text.toString())
            uiState.apply(textView, button)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(UISTATEKEY, uiState)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        uiState = savedInstanceState.getSerializable(UISTATEKEY) as UiState
        uiState.apply(textView, button)

    }

    companion object {
        private const val UISTATEKEY = "KEY"
    }
}