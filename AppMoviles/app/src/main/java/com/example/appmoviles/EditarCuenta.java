package com.example.appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
public class EditarCuenta extends AppCompatActivity {

    Toolbar tb;
    ActionBar ab;
    EditText etNombre;
    EditText etCorreo;
    EditText etContra;
    Button btGuardar;
    Usuario usuario;
    int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.editar_cuenta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent pre = getIntent();
        idUsuario = pre.getIntExtra("idUsuario", -1);
        usuario = (Usuario) pre.getSerializableExtra("usuario");

        tb = findViewById(R.id.tbEditarCuenta);
        setSupportActionBar(tb);
        ab = getSupportActionBar();
        ab.setTitle("Editar cuenta");
        ab.setDisplayHomeAsUpEnabled(true);


        etNombre = findViewById(R.id.etNombre);

        etCorreo = findViewById(R.id.etCorreo);

        etContra = findViewById(R.id.etContra);

        btGuardar = findViewById(R.id.btGuardar);



        etNombre.setText(usuario.getNombre());

        etCorreo.setText(usuario.getCorreo());

        etContra.setText(usuario.getContra());


        btGuardar.setOnClickListener(view -> {

            String nuevoNombre = etNombre.getText().toString().trim();

            String nuevoCorreo = etCorreo.getText().toString().trim();

            String nuevaContra = etContra.getText().toString().trim();


            if (nuevoNombre.isEmpty() || nuevoCorreo.isEmpty() || nuevaContra.isEmpty()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();

            }else {
                ApiRest.editarUsuario(usuario.getId(), nuevoNombre, nuevoCorreo, nuevaContra, new VoidCallback() {
                    @Override
                    public void onLoginSuccess() {

                        Toast.makeText(EditarCuenta.this, "Cuenta actualizada", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), Principal.class);
                        i.putExtra("idUsuario", idUsuario);
                        usuario.setNombre(nuevoNombre);
                        usuario.setContra(nuevaContra);
                        usuario.setCorreo(nuevoCorreo);

                        i.putExtra("usuario",usuario);
                        startActivity(i);
                        finish();

                    }

                    @Override
                    public void onLoginFailure(String errorMessage) {
                        Toast.makeText(EditarCuenta.this, "Error al actualizar", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}