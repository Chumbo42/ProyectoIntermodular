package com.example.appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class add extends AppCompatActivity {
    int idUsuario;

    Toolbar tb;
    ActionBar ab;
    Button addPrivado;
    Button addGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent pre = getIntent();
        idUsuario = pre.getIntExtra("idUsuario", -1);
        Log.i("ID", "idUsuario: " + idUsuario);

        tb = findViewById(R.id.tbAdd);
        setSupportActionBar(tb);
        ab = getSupportActionBar();
        ab.setTitle("");
        ab.setDisplayHomeAsUpEnabled(true);


        addPrivado = findViewById(R.id.addPrivado);

        addPrivado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), add_privado.class);
                i.putExtra("idUsuario",idUsuario);
                startActivity(i);
                finish();
            }
        });

        addGrupo = findViewById(R.id.addGrupo);

        addGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), add_grupo.class);
                i.putExtra("idUsuario",idUsuario);
                startActivity(i);
                finish();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_chat,menu);
        return true;
    }

}