package com.example.appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CrearCuenta extends AppCompatActivity {


    Button btCancel;
    Button btSubmit;
    EditText etNewName;
    EditText etNewPass;
    EditText etRepPass;
    ArrayList<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_cuenta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btCancel = findViewById(R.id.btCancel);
        btSubmit = findViewById(R.id.btSubmit);
        etNewName = findViewById(R.id.etNewName);
        etNewPass = findViewById(R.id.etNewPass);
        etRepPass = findViewById(R.id.etRepPass);

        Intent i = getIntent();
       usuarios = (ArrayList<Usuario>) i.getSerializableExtra("usuarios");

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;

                while (i < usuarios.size() && !usuarios.get(i).getNombre().equals(etNewName.getText().toString())){
                    i++;
                }
                if (i == usuarios.size() && etNewPass.getText().toString().equals(etRepPass.getText().toString()) ){
                    Usuario n = new Usuario(etNewName.getText().toString(), etNewPass.getText().toString(), usuarios.size());
                    Intent result = new Intent();
                    result.putExtra("usuarioNuevo", n);
                    setResult(RESULT_OK,result);
                    finish();
                }else {
                    if (i != usuarios.size()){
                        Toast.makeText(CrearCuenta.this, "Ya existe un usuario con ese nombre", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(CrearCuenta.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}