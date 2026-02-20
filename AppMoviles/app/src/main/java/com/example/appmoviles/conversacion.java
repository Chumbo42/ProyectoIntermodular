package com.example.appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class conversacion extends AppCompatActivity {

    Toolbar tb;
    ActionBar ab;
    RecyclerView rv;
    AdaptadorMensajes miAdaptador;
    RecyclerView.LayoutManager miLayoutManager;
    ArrayList<Mensaje> msgs;
    ImageButton enviar;
    int idUsuario;
    EditText etMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conversacion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent pretexto = getIntent();



        idUsuario = pretexto.getIntExtra("usuario", -1);
        Chat chat = (Chat) pretexto.getSerializableExtra("chat");
        Boolean isPrivado = pretexto.getBooleanExtra("isPrivado", false);

        tb = findViewById(R.id.toolbar2);
        setSupportActionBar(tb);
        ab = getSupportActionBar();
        ab.setTitle(chat.getNombre().toString());
        ab.setDisplayHomeAsUpEnabled(true);

        etMensaje = findViewById(R.id.etMensaje);
        rv = findViewById(R.id.rvMensajes);
        msgs = new ArrayList<>();

        miAdaptador = new AdaptadorMensajes(msgs, this, idUsuario);
        miLayoutManager = new LinearLayoutManager(this);

        rv.setAdapter(miAdaptador);
        rv.setLayoutManager(miLayoutManager);

        enviar = findViewById(R.id.btEnviar);



       actualizarMensajes(chat,isPrivado,msgs);


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (!etMensaje.getText().toString().isEmpty())
                    ApiRest.enviarMensaje(chat.getId(), idUsuario, etMensaje.getText().toString(), chat.getPrivado(), new ApiRest.MensajeEnviadoCallback() {
                        @Override
                        public void onSuccess() {

                            etMensaje.setText("");
                            actualizarMensajes(chat, chat.getPrivado(), msgs);

                        }

                        @Override
                        public void onFailure(String error) {
                            Log.d("CLICK", "Botón pulsado, failure");
                        }
                });
            }
        });

    }

    public void actualizarMensajes(Chat chat, Boolean isPrivado, ArrayList<Mensaje> msgs){
        ApiRest.getMsgs(chat.getId(),idUsuario, isPrivado,new MsgsCallback() {
            @Override
            public void onLoginSuccess(ArrayList<Mensaje> mensajes) {


                msgs.clear();
                msgs.addAll(mensajes);



                miAdaptador.notifyDataSetChanged();
                if (!msgs.isEmpty()){
                    rv.smoothScrollToPosition(msgs.size() - 1);
                }
            }
            @Override
            public void onLoginFailure(String errorMessage) {
                Log.i("Mensajes","login failure");
                msgs.clear();
                miAdaptador.notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_chat,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}