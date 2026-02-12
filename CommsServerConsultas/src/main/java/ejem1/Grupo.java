package ejem1;

public class Grupo extends Chat{
    private String desc;
    
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Grupo(int id, String nombre, byte[] foto, String desc){
        super(id,nombre,foto,false);
        this.desc = desc;
    }


}
