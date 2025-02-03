package com.practicamoviles_gestorequipos.practicagestionequiposgonzaloeduardo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnGestEquipos, btnGestJugadores, btnSimulacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGestEquipos = findViewById(R.id.btnGestEquipos);
        btnGestJugadores = findViewById(R.id.btnGestJugadores);
        btnSimulacion = findViewById(R.id.btnSimulacion);

        btnLogin.setOnClickListener(v -> {
            String user = etUsername.getText().toString();
            String pass = etPassword.getText().toString();

            if (user.equals("admin") && pass.equals("1234")) {
                Toast.makeText(MainActivity.this, "Login correcto", Toast.LENGTH_SHORT).show();
                mostrarBotones();
            } else {
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        btnGestEquipos.setOnClickListener(v -> startActivity(new Intent(this, GestionEquiposActivity.class)));
        btnGestJugadores.setOnClickListener(v -> startActivity(new Intent(this, GestionJugadoresActivity.class)));
        btnSimulacion.setOnClickListener(v -> startActivity(new Intent(this, SimulacionActivity.class)));
    }

    private void mostrarBotones() {
        btnGestEquipos.setVisibility(View.VISIBLE);
        btnGestJugadores.setVisibility(View.VISIBLE);
        btnSimulacion.setVisibility(View.VISIBLE);
    }
}