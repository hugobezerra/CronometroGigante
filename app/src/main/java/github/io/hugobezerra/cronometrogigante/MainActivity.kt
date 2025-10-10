package github.io.hugobezerra.cronometrogigante

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvTimer: TextView
    private var timer = 0L
    private var isRunning = false
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvTimer = findViewById(R.id.tvTimer)
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnStop = findViewById<Button>(R.id.btnStop)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val btnSobre = findViewById<Button>(R.id.btnSobre) // <- botão Sobre

        handler = Handler(Looper.getMainLooper())

        btnStart.setOnClickListener {
            if (!isRunning) {
                isRunning = true
                startTimer()
            }
        }

        btnStop.setOnClickListener {
            isRunning = false
        }

        btnReset.setOnClickListener {
            isRunning = false
            timer = 0
            updateTimerText()
        }

        // Botão Sobre
        btnSobre.setOnClickListener {
            val intent = Intent(this, SobreActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startTimer() {
        val runnable = object : Runnable {
            override fun run() {
                if (isRunning) {
                    timer++
                    updateTimerText()
                    handler.postDelayed(this, 1000)
                }
            }
        }
        handler.post(runnable)
    }

    private fun updateTimerText() {
        val hours = (timer / 3600)
        val minutes = (timer % 3600) / 60
        val seconds = (timer % 60)
        tvTimer.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}