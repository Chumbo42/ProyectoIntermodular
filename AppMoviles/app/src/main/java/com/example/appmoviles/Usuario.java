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

    public Usuario(){
        id = 0;
        nombre = "root";
        contra = "";
    }

    public Usuario(String nombre, String contra, int id){
        this.id=id;
        this.nombre = nombre;
        this.contra = contra;
    }

    public static ArrayList<Usuario> generarUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("lugonpa","fernando33",1));
        usuarios.add(new Usuario("skyhill", "bandetinder",2));
        usuarios.add(new Usuario("iagodoval", "ourensecampeon",3));
        return usuarios;
    }
}
