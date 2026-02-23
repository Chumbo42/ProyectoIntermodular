package com.example.appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class confirmarAddGrupo extends AppCompatActivity {

    int idUsuario;
    Usuario usuario;
    ArrayList<Chat> integrantes;

    EditText et;
    TextView tv;
    Button bt;

    Toolbar tb;
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmar_add_grupo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent pre = getIntent();
        idUsuario = pre.getIntExtra("idUsuario", -1);
        usuario = (Usuario) pre.getSerializableExtra("usuario");
        integrantes = (ArrayList<Chat>) pre.getSerializableExtra("integrantes");


        tb = findViewById(R.id.tbConfirmarCrearGrupo);
        setSupportActionBar(tb);
        ab = getSupportActionBar();
        ab.setTitle("");
        ab.setDisplayHomeAsUpEnabled(true);

        et = findViewById(R.id.etNombreGrupo);
        tv = findViewById(R.id.tvNombresParticipantes);
        bt = findViewById(R.id.btCrearGrupo);

        for ( Chat c: integrantes ) {
            tv.setText(tv.getText() + c.getNombre() + ", ");
        }
        tv.setText(tv.getText().subSequence(0, tv.getText().length()-2));

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiRest.crearGrupo(et.getText().toString(), integrantes, idUsuario, new VoidCallback() {
                    @Override
                    public void onLoginSuccess() {
                        Intent i = new Intent(getApplicationContext(), Principal.class);
                        i.putExtra("idUsuario", idUsuario);
                        i.putExtra("usuario",usuario);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onLoginFailure(String errorMessage) {

                    }
                });
            }
        });

    }
}