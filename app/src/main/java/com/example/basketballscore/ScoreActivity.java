package com.example.basketballscore;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.example.basketballscore.databinding.ActivityScoreBinding;

public class ScoreActivity extends AppCompatActivity {

    private ActivityScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activar Edge-to-Edge para que el layout ocupe toda la pantalla

        EdgeToEdge.enable(this);

        // Inflar el layout usando Data Binding

        binding = DataBindingUtil.setContentView(this, R.layout.activity_score);

        // Ajustar padding automático para evitar que el contenido quede debajo de barras del sistema

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener el Intent que abrió esta Activity

        Intent intent = getIntent();

        // Recuperar nombres de equipos enviados desde MainActivity

        String nombreLocal = intent.getStringExtra("NOMBRE_LOCAL");
        String nombreVisitante = intent.getStringExtra("NOMBRE_VISITANTE");

        // Recuperar puntos de ambos equipos (si no vienen, por defecto 0)

        int puntosLocal = intent.getIntExtra("PUNTOS_LOCAL", 0);
        int puntosVisitante = intent.getIntExtra("PUNTOS_VISITANTE", 0);

        //Ponemos el nombre del equipo local

        binding.TextScoreLocal.setText(nombreLocal);

        // Ponemos los puntos del equipo local

        binding.TextScoreResultadoLocal.setText(String.valueOf(puntosLocal));

        // Ponemos el nombre del equipo visitante

        binding.TextScoreVisitante.setText(nombreVisitante);

        // Ponemos los puntos del equipo visitante

        binding.TextScoreResultadoVisitante.setText(String.valueOf(puntosVisitante));

        // La línea de guion es parte del layout, no necesita asignación de valor
        binding.getRoot().findViewById(R.id.guionResultado);

        // Sentencia if para decir si hay ganador o hay empate

        if (puntosLocal > puntosVisitante) {
            binding.ResultadoGanadorOEmpate.setText("\uD83E\uDD47¡GANADOR!: " + nombreLocal + "\uD83E\uDD47");
        } else if (puntosVisitante > puntosLocal) {
            binding.ResultadoGanadorOEmpate.setText("\uD83E\uDD47¡GANADOR!: " + nombreVisitante + "\uD83E\uDD47");
        } else {
            binding.ResultadoGanadorOEmpate.setText("⚔\uFE0F¡EMPATE!⚔\uFE0F");
        }
    }
}