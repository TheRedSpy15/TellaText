package com.example.tellaText

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class TextedActivity : AppCompatActivity() {

    private var smsSender = "Unknown"
    private var smsMessage = "No text found"
    private var textToSpeechSystem: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_texted)

        smsSender = intent.getStringExtra("sms_sender")
        smsMessage = intent.getStringExtra("sms_body")

        val senderTxtView: TextView = findViewById(R.id.txt_sender)
        senderTxtView.text = smsSender
    }

    fun callSender(view: View) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$smsSender")
        startActivity(intent)
    }

    fun ignoreMSG(view: View) {
        finish()
    }

    fun speakMSG(view: View) {
        textToSpeechSystem = TextToSpeech(this) { ttsInitResult ->
            if (TextToSpeech.SUCCESS == ttsInitResult) textToSpeechSystem!!.speak(smsMessage, TextToSpeech.QUEUE_ADD, null)
        }
    }
}
