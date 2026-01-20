package com.example.appmoviles;



//Para esta página con una barra de navegación he intentado usar una toolbar con menú como se vió en clase,
//pero no me gustaba tener que abrir un intent cada vez que escogía una opción del menú, ni no poder centrar los elementos.

//Por ello, decidí busar en la web formas distintas para dejar esta funcionalidad a mi gusto, y he seguito el siguiente video:
//https://www.youtube.com/watch?v=rm9NGA9UBXs

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Principal extends AppCompatActivity {

    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment fithirdFragment = new ThirdFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_logged);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener(){
    @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.firstFragment:
                loadFragment(firstFragment);
                return true;
            case R.id.secondFragment:
                loadFragment(secondFragment);
                return true;
            case R.id.thirdFragment:
                loadFragment(thirdFragment);
                return true;
        }
        return false;
         }
    };


}