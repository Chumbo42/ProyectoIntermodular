package com.example.appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class add_grupo extends AppCompatActivity {

    Toolbar tb;
    ActionBar ab;
    EditText et;
    int idUsuario;

    RecyclerView rv;
    AdaptadorAddGrupo miAdaptador;
    RecyclerView.LayoutManager miLayoutManager;
    Button bt;
    TextView tv;

    ArrayList<Chat> chats = new ArrayList<>();
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_grupo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent pre = getIntent();
        idUsuario = pre.getIntExtra("idUsuario", -1);
        usuario = (Usuario) pre.getSerializableExtra("usuario");

        tv = findViewById(R.id.tvTituloAddGrupo);

        et = findViewById(R.id.etAddGrupo);

        bt = findViewById(R.id.btAddGrupo);

        tb = findViewById(R.id.tbAddGrupo);
        setSupportActionBar(tb);
        ab = getSupportActionBar();
        ab.setTitle("");
        ab.setDisplayHomeAsUpEnabled(true);

        rv = findViewById(R.id.rvAddGrupo);

        miAdaptador = new AdaptadorAddGrupo(chats, getApplicationContext(), idUsuario, usuario, tv);
        miLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rv.setAdapter(miAdaptador);
        rv.setLayoutManager(miLayoutManager);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                ApiRest.getListaUsuarios(et.getText().toString(),idUsuario, getApplicationContext(), new ChatsCallback() {
                    @Override
                    public void onLoginSuccess(ArrayList<Chat> usuarios) {
                        chats.clear();
                        chats.addAll(usuarios);
                        miAdaptador.notifyDataSetChanged();
                    }

                    @Override
                    public void onLoginFailure(String errorMessage) {

                    }
                });
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Chat> integrantes = miAdaptador.getIntegrantes();

                if (integrantes.size() < 2){
                    Toast.makeText(getApplicationContext(), "Introduce por lo menos 2 usuarios", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(getApplicationContext(), confirmarAddGrupo.class);

                    for (Chat c:chats) {
                        c.setFoto(null);
                    }
                    i.putExtra("idUsuario", idUsuario);
                    i.putExtra("integrantes", integrantes);
                    i.putExtra("usuario",usuario);
                    startActivity(i);
                }

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