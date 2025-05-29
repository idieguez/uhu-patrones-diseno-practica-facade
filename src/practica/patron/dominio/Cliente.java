package practica.patron.dominio;

import practica.patron.utiles.RolUsuario;
import practica.patron.utiles.TipoCliente;
import practica.patron.utiles.Utiles;




public class Cliente extends UsuarioA {
	
	protected TipoCliente tipoCliente;
	
	
	public Cliente(String nombre, String contrasena, String nombreCompleto, String dni, RolUsuario rol, TipoCliente tipoCliente) {
		super(nombre, contrasena, nombreCompleto, dni, rol);
		this.tipoCliente = tipoCliente;
	}
	
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}
	
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	@Override
	public String toString() {
		return Utiles.textoATipoOracion(getRol().toString()) + ": " + nombre + ", " + nombreCompleto + ", " + dni + ", " + Utiles.textoATipoOracion(tipoCliente.toString());
	}
	
}
