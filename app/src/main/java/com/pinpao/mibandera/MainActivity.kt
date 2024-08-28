package com.pinpao.mibandera

import android.media.MediaPlayer
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var descriptionTextView: TextView
    private lateinit var soundAmarillo: MediaPlayer
    private lateinit var soundAzul: MediaPlayer
    private lateinit var soundRojo: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        descriptionTextView = findViewById(R.id.descriptionTextView)

        val btnAmarillo: Button = findViewById(R.id.btnAmarillo)
        val btnAzul: Button = findViewById(R.id.btnAzul)
        val btnRojo: Button = findViewById(R.id.btnRojo)
        val btnRegresar: Button = findViewById(R.id.btnRegresar)

        // Inicializar sonidos
        soundAmarillo = MediaPlayer.create(this, R.raw.sound_amarillo)
        soundAzul = MediaPlayer.create(this, R.raw.sound_azul)
        soundRojo = MediaPlayer.create(this, R.raw.sound_rojo)

        // Configurar botones
        btnAmarillo.setOnClickListener {
            stopAllSounds()  // Detener todos los sonidos antes de reproducir uno nuevo
            imageView.setImageResource(R.drawable.amarillo)
            descriptionTextView.text = "El color amarillo de la bandera de Colombia simboliza la riqueza del país."
            descriptionTextView.setTypeface(null, android.graphics.Typeface.BOLD) // Aplicar negrita
            soundAmarillo.start()
            animateFlag()
        }

        btnAzul.setOnClickListener {
            stopAllSounds()  // Detener todos los sonidos antes de reproducir uno nuevo
            imageView.setImageResource(R.drawable.azul)
            descriptionTextView.text = "Azul: Representa los vastos océanos y ríos de Colombia."
            descriptionTextView.setTypeface(null, android.graphics.Typeface.BOLD) // Aplicar negrita
            soundAzul.start()
            animateFlag()
        }

        btnRojo.setOnClickListener {
            stopAllSounds()  // Detener todos los sonidos antes de reproducir uno nuevo
            imageView.setImageResource(R.drawable.rojo)
            descriptionTextView.text = "Rojo: Simboliza la sangre derramada por los patriotas de Colombia."
            descriptionTextView.setTypeface(null, android.graphics.Typeface.BOLD) // Aplicar negrita
            soundRojo.start()
            animateFlag()
        }

        btnRegresar.setOnClickListener {
            // Restablecer la vista inicial
            imageView.setImageResource(R.drawable.bandera)
            descriptionTextView.text = "Seleccione su color de interés"
            descriptionTextView.setTypeface(null, android.graphics.Typeface.NORMAL)

            // Detener cualquier sonido que esté sonando
            stopAllSounds()
        }
    }

    // Método para detener todos los sonidos antes de iniciar uno nuevo
    private fun stopAllSounds() {
        if (soundAmarillo.isPlaying) {
            soundAmarillo.stop()
            soundAmarillo.prepare() // Preparar de nuevo el MediaPlayer para futuras reproducciones
        }
        if (soundAzul.isPlaying) {
            soundAzul.stop()
            soundAzul.prepare()
        }
        if (soundRojo.isPlaying) {
            soundRojo.stop()
            soundRojo.prepare()
        }
    }

    // Método para animar la bandera
    private fun animateFlag() {
        val animation: Animation = AlphaAnimation(0.0f, 1.0f)
        animation.duration = 500
        imageView.startAnimation(animation)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Liberar recursos de MediaPlayer
        soundAmarillo.release()
        soundAzul.release()
        soundRojo.release()
    }
}
