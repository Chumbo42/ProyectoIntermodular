package ejem1;

import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
public class Usuario {
	private int id;
	private String nombre;
	private String correo;
	private String contraseña;
	private byte[] foto;
	
	public Usuario() {
		
	}
	
	public Usuario(int id, String nombre, String correo, String contraseña, byte[] imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.contraseña = contraseña;
		this.foto = imagen;
	}
	
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    @XmlElement
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    @XmlElement
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
    @XmlElement
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
    @XmlElement
	public byte[] getFoto() {
		return foto;
	}
	public void setGenero(byte[] imagen) {
		this.foto = imagen;
    }
}
