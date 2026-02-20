package ejem1;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Mensaje {
    private int id;
    private int autor;
    private String contenido;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date fecha;

    public Mensaje(int id, String contenido, Date fecha, int autor) {
        this.id = id;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public Mensaje(){}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAutor() { return autor; }
    public void setAutor(int autor) { this.autor = autor; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}
