package com.example.appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class LogIn extends AppCompatActivity {

    EditText etNombre;
    EditText etContra;
    Button btCrear;
    Button btLogin;
    ArrayList<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNombre = findViewById(R.id.etNombre);
        etContra = findViewById(R.id.etContra);
        btCrear = findViewById(R.id.btCrear);
        btLogin = findViewById(R.id.btLogin);
        usuarios = Usuario.generarUsuarios();

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new
                ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                Intent intent=result.getData();

                if (intent != null){
                    if (intent.hasExtra("usuarioNuevo")){
                        Usuario u = (Usuario) intent.getSerializableExtra("usuarioNuevo");
                           usuarios.add(u);
                    }

                }



            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < usuarios.size(); i++) {
                    Log.i("Info", usuarios.get(i).getNombre());
                    Log.i("Info", usuarios.get(i).getContra());
                    if (etNombre.getText().toString().trim().equals(usuarios.get(i).getNombre()) && etContra.getText().toString().trim().equals(usuarios.get(i).getContra())){
                        Intent principal = new Intent(getApplicationContext(), Principal.class);
                        principal.putExtra("idUsuario", usuarios.get(i).getId());
                        startActivity(principal);

                    }
                }

            }
        });

        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CrearCuenta.class);
                i.putExtra("usuarios", usuarios);
                launcher.launch(i);
            }
        });

    }
}