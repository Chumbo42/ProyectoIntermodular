package com.example.appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmoviles.databinding.ActivityPrincipalBinding;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {


   Toolbar tb;
   ActionBar ab;

    private ActivityPrincipalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_chat, R.id.navigation_actividades, R.id.navigation_user).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_principal);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        RecyclerView rv = findViewById(R.id.rvChats);

        Intent i = getIntent();
        int id = i.getIntExtra("idUsuario", -1);
        DatosUsuarioLogueado viewModel = new ViewModelProvider(this).get(DatosUsuarioLogueado.class);
        viewModel.setUserId(id);
        viewModel.setRvChats(rv);


        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ab = getSupportActionBar();
        ab.setTitle("Comms");




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_main,menu);
        return true;
    }

}