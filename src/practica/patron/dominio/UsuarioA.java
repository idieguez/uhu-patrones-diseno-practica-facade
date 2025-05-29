package practica.patron.dominio;

import java.util.UUID;

import practica.patron.utiles.RolUsuario;




public abstract class UsuarioA {
	
	protected String id;
	protected String nombre;
	protected String contrasena;
	protected String nombreCompleto;
	protected String dni;
	protected RolUsuario rol;
	
	
	public UsuarioA(String nombre, String contrasena, String nombreCompleto, String dni, RolUsuario rol) {
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.nombreCompleto = nombreCompleto;
		this.dni = dni;
		this.rol = rol;
	}
	
	public String getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public RolUsuario getRol() {
		return rol;
	}
	
	public void setRol(RolUsuario rol) {
		this.rol = rol;
	}
	
	public abstract String toString();
	
}
