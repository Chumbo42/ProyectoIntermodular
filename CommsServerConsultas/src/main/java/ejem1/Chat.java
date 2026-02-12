package ejem1;

public class Chat {
    private int id;
    String nombre;
    private byte[] foto;
    private boolean isPrivado;

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    public byte[] getFoto() {
        return foto;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public void setPrivado(boolean isPrivado) {
        this.isPrivado = isPrivado;
    }
    public boolean getPrivado(){
        return isPrivado;
    }

    public Chat(int id, String nombre, byte[] foto, boolean privado){
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.isPrivado = privado;
    }
}
