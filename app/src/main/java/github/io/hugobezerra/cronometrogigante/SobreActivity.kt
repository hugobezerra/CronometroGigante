package github.io.hugobezerra.cronometrogigante

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SobreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sobre)

        // Ajusta Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TextView com informações do app
        val textInfo: TextView = findViewById(R.id.textInfo)
        textInfo.text = """
            App Nome: Cronômetro Gigante
            Versão: 1.0
            Autor: Hugo Bezerra
            Email: hugo@exemplo.com
            Chave PIX: 00011122233
        """.trimIndent()

        // Botão Voltar
        val btnVoltar: Button = findViewById(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish() // fecha a Activity
        }
    }
}