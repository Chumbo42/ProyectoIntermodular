package com.example.appmoviles;

import java.util.Date;

public class Mensaje {
    private int id;
    private int autor;
    private String contenido;
    private Date fecha;

    public Mensaje(int id, int autor, String contenido, Date fecha) {
        this.id = id;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAutor() { return autor; }
    public void setAutor(int autor) { this.autor = autor; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}