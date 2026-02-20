package com.example.appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;


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

public class add_privado extends AppCompatActivity {

    Toolbar tb;
    ActionBar ab;
    EditText et;
    int idUsuario;

    RecyclerView rv;
    AdaptadorAddPrivado miAdaptador;
    RecyclerView.LayoutManager miLayoutManager;

    ArrayList<Chat> chats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_privado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent pre = getIntent();
        idUsuario = pre.getIntExtra("idUsuario", -1);

        et = findViewById(R.id.etAddPrivado);

        tb = findViewById(R.id.tbAddPrivado);
        setSupportActionBar(tb);
        ab = getSupportActionBar();
        ab.setTitle("");
        ab.setDisplayHomeAsUpEnabled(true);

        rv = findViewById(R.id.rvAddPrivado);

        miAdaptador = new AdaptadorAddPrivado(chats, getApplicationContext(), idUsuario);
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
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_chat,menu);
        return true;
    }

}