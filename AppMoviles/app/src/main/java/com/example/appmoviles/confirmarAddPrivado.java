package com.example.appmoviles;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class confirmarAddPrivado extends AppCompatActivity {

    Toolbar tb;
    ActionBar ab;
    TextView tv;
    Button btAcc;
    Button btCanc;
    int idUsuario;
    Chat chat;
    ImageView iv;
    Usuario usuario;

    ArrayList<Chat> chats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmar_add_privado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent pre = getIntent();
        idUsuario = pre.getIntExtra("idUsuario", -1);
        usuario = (Usuario) pre.getSerializableExtra("usuario");
        chat = (Chat) pre.getSerializableExtra("chat");
        Log.i("IDU", idUsuario + "");
        Log.i("CID", chat.getId() + "");

        tb = findViewById(R.id.tbConfirmarAddPrivado);
        setSupportActionBar(tb);
        ab = getSupportActionBar();
        ab.setTitle("");
        ab.setDisplayHomeAsUpEnabled(true);

        tv = findViewById(R.id.tvConfirmarAddPrivado);
        tv.setText(tv.getText() + chat.getNombre() + "?");

        iv = findViewById(R.id.ivConfirmarPriv);


        if(chat.getFoto() != null && !chat.getFoto().toString().trim().equals("") ){

            String stringImagen = new String(chat.getFoto());
            byte[] byteImagen = android.util.Base64.decode(stringImagen, android.util.Base64.DEFAULT);
            iv.setImageBitmap(BitmapFactory.decodeByteArray(byteImagen, 0, byteImagen.length));
        }else {
            iv.setImageResource(R.drawable.img_usuario);
        }


        btAcc = findViewById(R.id.btAceptarPriv);
        btCanc = findViewById(R.id.btCancelarPriv);


        btAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiRest.crearPrivado(idUsuario, chat.getId(), new CrearMdCallback() {
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

        btCanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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