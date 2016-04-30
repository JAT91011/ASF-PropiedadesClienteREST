package entities;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomCliente {

	private static final String			NIF_STRING_ASOCIATION	= "TRWAGMYFPDXBNJZSQVHLCKE";
	private static final String			PATTERN_EMAIL			= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private int							dni;
	private String						nombre;
	private String						apellido;
	private String						email;
	private String						direccion;
	private int							codigoPostal;
	private int							telefono;
	private ArrayList<CustomAlquiler>	alquileres;

	public CustomCliente() {

	}

	public CustomCliente(String nombre, String apellido, String email, String direccion, int codigoPostal, int telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.alquileres = new ArrayList<CustomAlquiler>();
	}

	public CustomCliente(int dni, String nombre, String apellido, String email, String direccion, int codigoPostal, int telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.alquileres = new ArrayList<CustomAlquiler>();
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getDNIChar() {
		return String.valueOf(this.dni) + "-" + NIF_STRING_ASOCIATION.charAt(dni % 23);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public ArrayList<CustomAlquiler> getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(ArrayList<CustomAlquiler> alquileres) {
		this.alquileres = alquileres;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", direccion=" + direccion
				+ ", codigoPostal=" + codigoPostal + ", telefono=" + telefono + ", alquileres=" + alquileres + "]";
	}
}