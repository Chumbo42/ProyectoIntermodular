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
                           ApiRest.subirUsuario(u, new LoginCallback() {
                               @Override
                               public void onLoginSuccess(Usuario usuario) {
                                   // Mostrar mensaje
                                   Toast.makeText(getApplicationContext(),
                                           "Usuario Creado" ,
                                           Toast.LENGTH_SHORT).show();

                               }

                               @Override
                               public void onLoginFailure(String errorMessage) {
                                   Toast.makeText(getApplicationContext(),
                                           "Error: " + errorMessage,
                                           Toast.LENGTH_LONG).show();
                               }
                           });
                    }

                }



            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               String username = etNombre.getText().toString().trim();
               String password = etContra.getText().toString().trim();

               if (username.isEmpty() || password.isEmpty()) {
                   Toast.makeText(getApplicationContext(), "Introduce usuario y contraseña", Toast.LENGTH_SHORT).show();
                   return;
               }



               ApiRest.obtenerUsuario(username, password, new LoginCallback() {
                   @Override
                   public void onLoginSuccess(Usuario usuario) {

                       Intent intent = new Intent(getApplicationContext(), Principal.class);
                       intent.putExtra("idUsuario", usuario.getId());
                       startActivity(intent);
                       finish();
                   }

                   @Override
                   public void onLoginFailure(String errorMessage) {

                       Toast.makeText(getApplicationContext(),
                               "Error: " + errorMessage,
                               Toast.LENGTH_LONG).show();
                   }
               });


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