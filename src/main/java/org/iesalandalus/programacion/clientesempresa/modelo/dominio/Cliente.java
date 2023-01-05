package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
	private String ER_CORREO = "[A-Za-z0-9_-]+@[A-Za-z0-9]+.[A-Za-z0-9]+";
	private String ER_DNI = "(([0-9]{8})([A-Z|a-z]))";
	private String ER_TELEFONO = "([0-9]{9})";
	private String FORMATO_FECHA = "dd/MM/yyyy";
	private String nombre;
	private String dni;
	private String correo;
	private String telefono;
	private LocalDate fechaNacimiento;


	public Cliente(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
		this.setNombre(nombre);
		this.setDni(dni);
		this.setCorreo(correo);
		this.setTelefono(telefono);
		this.setFechaNacimiento(fechaNacimiento);
	}
	
	public Cliente(Cliente cliente) {
		if (cliente == null) 
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		
		this.setNombre(cliente.getNombre());
		this.setDni(cliente.getDni());
		this.setCorreo(cliente.getCorreo());
		this.setTelefono(cliente.getTelefono());
		this.setFechaNacimiento(cliente.getFechaNacimiento());
	}
	
	private String formateaNombre(String nombre) {
	
		char primeraLetra;
		String aux, result="";
		nombre = nombre.trim();
		String [] pts = nombre.split("\\s+");
		
		for (int i=0; i<pts.length; i++) {
			primeraLetra = pts[i].charAt(0);
			aux = primeraLetra+"";
			aux = aux.toUpperCase();
			result += aux+pts[i].substring(1,pts[i].length()).toLowerCase()+" ";	
		}
		result = result.substring(0,result.length()-1);
		
		return result;
	}
	
	private boolean comprobarLetraDni(String dni) {
		
		int num=0;
		char letra = '\0';
		char [] letras = {  'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E' };
		
		Pattern pattern = Pattern.compile(ER_DNI);
		Matcher matcher = pattern.matcher(dni); 
	
		if (matcher.matches()) {
		
			num = Integer.parseInt( matcher.group(2) );
			letra =  Character.toUpperCase( matcher.group(3).charAt(0) );

		} else
			throw new IllegalArgumentException("ERROR: El dni del cliente no tiene un formato válido.");
		
		num = (num % 23);
		
		if (letras[num] == letra) 
			return true;		
		 else 
			return false;	
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) 
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
		if (nombre.trim().isEmpty())
			throw new IllegalArgumentException("ERROR: El nombre de un cliente no puede estar vacío.");
		
		this.nombre = formateaNombre(nombre);
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		if (dni == null) 
			throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");
		
		if (this.comprobarLetraDni(dni))
			this.dni = dni;
		else
			throw new IllegalArgumentException("ERROR: La letra del dni del cliente no es correcta.");
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		if (correo == null) 
			throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
	
		Pattern pattern = Pattern.compile(ER_CORREO);
		Matcher matcher = pattern.matcher(correo);
		
		if (matcher.find())//.matches()) 
			this.correo = correo;
		else 
			throw new IllegalArgumentException("ERROR: El correo del cliente no tiene un formato válido.");
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		
		if (telefono == null) 
			throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");
		Pattern pattern = Pattern.compile(ER_TELEFONO);
		Matcher matcher = pattern.matcher(telefono);
		
		if (matcher.find()) 
			this.telefono = telefono;
		else
			throw new IllegalArgumentException("ERROR: El teléfono del cliente no tiene un formato válido.");
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento == null) 
			throw new NullPointerException("ERROR: La fecha de nacimiento de un cliente no puede ser nula.");
		this.fechaNacimiento = fechaNacimiento;
	}
	private String getIniciales() {
		String [] pts = getNombre().split("\\s");
		String resultado = "";
		for (int i=0; i<pts.length; i++) {
			resultado += pts[i].charAt(0);
		}
		return resultado;
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + " ("+this.getIniciales()+"), DNI=" + dni + ", correo=" + correo + ", teléfono=" + telefono
				+ ", fecha nacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA));
	}
	
	public static void main(String args[]) {
		Pattern pattern = Pattern.compile("[A-Za-z0-9+_.-]+@(.+)$");
		Matcher matcher = pattern.matcher("arr@.com");
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}
}
