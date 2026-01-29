package com.example.appmoviles;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private int id;
    public int getId(){
        return id;
    }
    private String nombre;
    public String getNombre(){
        return nombre;
    }
    private String contra;
    public String getContra(){
        return contra;
    }
    private int foto;

    public int getFoto() {
        return foto;
    }

    public Usuario(){
        id = 0;
        nombre = "root";
        contra = "";
    }

    public Usuario(String nombre, String contra){

        this.nombre = nombre;
        this.contra = contra;
    }


}
