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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String contra;
    public String getContra(){
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    private String correo;

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo(){
        return correo;
    }
    private byte[] foto;

    public byte[] getFoto() {
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
    public Usuario(int id, String nombre, String contra, String correo, byte[] foto){
        this.id = id;
        this.nombre = nombre;
        this.contra = contra;
        this.correo = correo;
        this.foto = foto;
    }


}
