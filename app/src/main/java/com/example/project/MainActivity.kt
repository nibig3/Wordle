package com.example.project

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var wordToGuess = FourLetterWordList.getRandomFourLetterWordList().toString()
    private var chancesLeft = 3

    private lateinit var guessEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var submitButton: Button
    private lateinit var targetWordTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        guessEditText = findViewById(R.id.guessEditText)
        resultTextView = findViewById(R.id.resultTextView)
        submitButton = findViewById(R.id.submitButton)
        targetWordTextView = findViewById(R.id.targetWordTextView)

        submitButton.setOnClickListener {
            val guess = guessEditText.text.toString().toUpperCase()
            if (chancesLeft > 0) {
                val result = checkGuess(guess)
                resultTextView.text = result
                chancesLeft--
                if (result == "0000") {
                    submitButton.isEnabled = false
                    targetWordTextView.text = wordToGuess.toString()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

}

private fun FourLetterWordList.Companion.getRandomFourLetterWordList() {

}

private operator fun Unit.contains(c: Char): Boolean {
    TODO("Not yet implemented")
}

