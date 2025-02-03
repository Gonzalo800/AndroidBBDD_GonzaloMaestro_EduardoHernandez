package com.practicamoviles_gestorequipos.practicagestionequiposgonzaloeduardo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import java.util.List;

public class GestionEquiposActivity extends AppCompatActivity {
    private EditText etNombreEquipo;
    private Button btnAgregarEquipo, btnEliminarEquipo;
    private ListView lvEquipos;
    private DBManager dbManager;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_equipos);

        etNombreEquipo = findViewById(R.id.etNombreEquipo);
        btnAgregarEquipo = findViewById(R.id.btnAgregarEquipo);
        btnEliminarEquipo = findViewById(R.id.btnEliminarEquipo);
        lvEquipos = findViewById(R.id.lvEquipos);

        dbManager = new DBManager(this);
        cargarListaEquipos();

        btnAgregarEquipo.setOnClickListener(v -> {
            String nombre = etNombreEquipo.getText().toString().trim();
            if (!nombre.isEmpty()) {
                dbManager.insertarEquipo(nombre);
                Toast.makeText(this, "Equipo agregado", Toast.LENGTH_SHORT).show();
                etNombreEquipo.setText("");
                cargarListaEquipos();
            } else {
                Toast.makeText(this, "Introduce un nombre de un equipo", Toast.LENGTH_SHORT).show();
            }
        });

        btnEliminarEquipo.setOnClickListener(v -> {
            String nombre=etNombreEquipo.getText().toString().trim();
            if(!nombre.isEmpty()){
                dbManager.eliminarEquipo(nombre);
                Toast.makeText(this, "Equipo eliminado", Toast.LENGTH_SHORT).show();
                etNombreEquipo.setText("");
                cargarListaEquipos();
            } else {
                Toast.makeText(this, "Introduce un nombre de un equipo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void cargarListaEquipos() {
        List<Equipo> equipos = dbManager.obtenerEquipos();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (Equipo equipo : equipos) {
            adapter.add(equipo.getNombre() + " - Puntos: " + equipo.getPuntos());
        }
        adapter.notifyDataSetChanged();
        lvEquipos.setAdapter(adapter);

    }
}
