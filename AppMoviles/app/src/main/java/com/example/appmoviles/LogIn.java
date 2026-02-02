package com.example.appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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


        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new
                ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                Intent intent=result.getData();

                if (intent != null){
                    if (intent.hasExtra("usuarioNuevo")){
                        Usuario u = (Usuario) intent.getSerializableExtra("usuarioNuevo");
                           ApiRest.subirUsuario(u);
                    }

                }



            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               String nombre = etNombre.getText().toString();
               String contra = etContra.getText().toString();

               Handler mainHandler = new Handler(Looper.getMainLooper());

               new Thread(() -> {
                   Usuario u = ApiRest.obtenerUsuario(nombre, contra);

                   mainHandler.post(() -> {
                       if (u != null) {

                           Intent principal = new Intent(getApplicationContext(), Principal.class);
                           principal.putExtra("idUsuario", u.getId());
                           startActivity(principal);


                       } else {
                           Toast.makeText(LogIn.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                       }


                   });

               }).start();
           }
       });


        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CrearCuenta.class);
                launcher.launch(i);
            }
        });

    }
}