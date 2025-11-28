package com.example.basketballscore;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.example.basketballscore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // Variables para almacenar los puntos de cada equipo
    private int puntosLocal = 0;
    private int puntosVisitante = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activar Edge-to-Edge para que el layout ocupe toda la pantalla

        EdgeToEdge.enable(this);

        // Inflar el layout usando Data Binding

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Ajustar padding automático para evitar que el contenido quede debajo de barras del sistema

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Botones para los puntos del equipo local

        // Botón +1 puntos para equipo local

        binding.sumarUnoLocal.setOnClickListener(v -> {
            puntosLocal += 1;
            binding.puntosLocal.setText(String.valueOf(puntosLocal));
        });

        // Botón +2 puntos para equipo local

        binding.getRoot().findViewById(R.id.sumarDosLocal).setOnClickListener(v -> {
            puntosLocal += 2;
            binding.puntosLocal.setText(String.valueOf(puntosLocal));
        });

        // Botón -1 puntos para equipo local

        binding.buttonRestarLocal.setOnClickListener(v -> {
            puntosLocal -= 1;

            // Sentencia if para evitar valores negativos

            if (puntosLocal < 0) puntosLocal = 0;
            binding.puntosLocal.setText(String.valueOf(puntosLocal));
        });

        // Botones para los puntos del equipo visitante

        // Botón +1 puntos visitante

        binding.sumarUnoVisitante.setOnClickListener(v -> {
            puntosVisitante += 1;
            binding.puntosVisitante.setText(String.valueOf(puntosVisitante));
        });

        // Botón +2 puntos visitante

        binding.sumarDosVisitante.setOnClickListener(v -> {
            puntosVisitante += 2;
            binding.puntosVisitante.setText(String.valueOf(puntosVisitante));
        });

        // Botón -1 puntos visitante

        binding.buttonRestarVisitante.setOnClickListener(v -> {
            puntosVisitante -= 1;

            // Sentencia if para evitar valores negativos

            if (puntosVisitante < 0) puntosVisitante = 0;
            binding.puntosVisitante.setText(String.valueOf(puntosVisitante));
        });

        // Botón para reiniciar los puntos

        binding.BotonReiniciar.setOnClickListener(v -> {

            // Reiniciar puntos a 0 para ambos equipos

            puntosLocal = 0;
            puntosVisitante = 0;

            // Actualizar TextViews para reflejar los cambios

            binding.puntosLocal.setText(String.valueOf(puntosLocal));
            binding.puntosVisitante.setText(String.valueOf(puntosVisitante));
        });

        // Botón para pasar a la siguiente página

        binding.BotonSiguiente.setOnClickListener(v -> {

            // Crear Intent explícito para abrir ScoreActivity

            Intent intent = new Intent(MainActivity.this, ScoreActivity.class);

            // Pasar datos a ScoreActivity mediante extras

            intent.putExtra("NOMBRE_LOCAL", binding.textViewLocal.getText().toString());
            intent.putExtra("NOMBRE_VISITANTE", binding.textViewVisitante.getText().toString());
            intent.putExtra("PUNTOS_LOCAL", puntosLocal);
            intent.putExtra("PUNTOS_VISITANTE", puntosVisitante);

            // Iniciar la nueva Activity

            startActivity(intent);
        });
    }
}