package practica.patron.dominio;

import practica.patron.utiles.RolUsuario;
import practica.patron.utiles.Utiles;




public class Empleado extends UsuarioA {
	
	protected double salarioBruto;
	protected String iban;
	
	
	public Empleado(String nombre, String contrasena, String nombreCompleto, String dni, RolUsuario rol, double salarioBruto, String iban) {
		super(nombre, contrasena, nombreCompleto, dni, rol);
		this.salarioBruto = salarioBruto;
		this.iban = iban;
	}
	
	public double getSalarioBruto() {
		return salarioBruto;
	}
	
	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}
	
	public String getIban() {
		return iban;
	}
	
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	@Override
	public String toString() {
		return Utiles.textoATipoOracion(getRol().toString()) + ": " + nombre + ", " + nombreCompleto + ", " + dni + ", " + salarioBruto + " EUR, " + iban;
	}
	
}
