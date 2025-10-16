package github.io.hugobezerra.cronometrogigante

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvTimer: TextView
    private var timer = 0L
    private var isRunning = false
    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 1000L // 1 segundo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Liga os componentes do layout
        tvTimer = findViewById(R.id.tvTimer)
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnStop = findViewById<Button>(R.id.btnStop)
        val btnReset = findViewById<Button>(R.id.btnReset)

        // Botão iniciar
        btnStart.setOnClickListener {
            if (!isRunning) {
                isRunning = true
                startTimer()
            }
        }

        // Botão parar
        btnStop.setOnClickListener {
            isRunning = false
        }

        // Botão zerar
        btnReset.setOnClickListener {
            isRunning = false
            timer = 0L
            updateTimerText()
        }

        // Mostra o tempo inicial (00:00:00)
        updateTimerText()
    }

    private fun startTimer() {
        handler.post(object : Runnable {
            override fun run() {
                if (isRunning) {
                    timer++
                    updateTimerText()
                    handler.postDelayed(this, updateInterval)
                }
            }
        })
    }

    private fun updateTimerText() {
        val hours = timer / 3600
        val minutes = (timer % 3600) / 60
        val seconds = timer % 60
        tvTimer.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}