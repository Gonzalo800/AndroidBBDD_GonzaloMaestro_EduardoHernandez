package com.practicamoviles_gestorequipos.practicagestionequiposgonzaloeduardo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulacionActivity extends AppCompatActivity {
    private Button btnSimularPartido;
    private ListView lvClasificacion;
    private DBManager dbManager;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulacion);

        btnSimularPartido = findViewById(R.id.btnSimularPartido);
        lvClasificacion = findViewById(R.id.lvClasificacion);

        dbManager = new DBManager(this);
        cargarClasificacion();

        btnSimularPartido.setOnClickListener(v -> {
            simularPartido();
            cargarClasificacion();
        });
    }

    private void simularPartido() {
        List<Equipo> equipos = dbManager.obtenerEquipos();
        if (equipos.size() < 2) {
            Toast.makeText(this, "Debe haber al menos dos equipos", Toast.LENGTH_SHORT).show();
            return;
        }
        Collections.shuffle(equipos);
        Equipo equipo1 = equipos.get(0);
        Equipo equipo2 = equipos.get(1);
        int resultado = new Random().nextInt(3); // 0 = empate, 1 = gana equipo1, 2 = gana equipo2

        if (resultado == 1) {
            equipo1.setPuntos(equipo1.getPuntos() + 3);
            Toast.makeText(this, equipo1.getNombre() + " gana contra " + equipo2.getNombre(), Toast.LENGTH_SHORT).show();
        } else if (resultado == 2) {
            equipo2.setPuntos(equipo2.getPuntos() + 3);
            Toast.makeText(this, equipo2.getNombre() + " gana contra " + equipo1.getNombre(), Toast.LENGTH_SHORT).show();
        } else {
            equipo1.setPuntos(equipo1.getPuntos() + 1);
            equipo2.setPuntos(equipo2.getPuntos() + 1);
            Toast.makeText(this, "Empate entre " + equipo1.getNombre() + " y " + equipo2.getNombre(), Toast.LENGTH_SHORT).show();
        }

    }

    private void cargarClasificacion() {
        List<Equipo> equipos = dbManager.obtenerEquipos();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (Equipo equipo : equipos) {
            adapter.add(equipo.getNombre() + " - Puntos: " + equipo.getPuntos());
        }
        lvClasificacion.setAdapter(adapter);
    }
}
