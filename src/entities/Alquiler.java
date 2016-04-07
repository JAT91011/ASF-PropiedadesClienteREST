package entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Alquiler {

	private int			id;
	private Cliente		cliente;
	private Actividad	actividad;
	private Propiedad	propiedad;
	private Date		fechaInicio;
	private Date		fechaFin;
	private double		precio;

	public Alquiler() {

	}

	public Alquiler(Actividad actividad, Propiedad propiedad, Date fechaInicio, Date fechaFin, double precio) {
		this.actividad = actividad;
		this.propiedad = propiedad;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
	}

	public Alquiler(int id, Cliente cliente, Actividad actividad, Propiedad propiedad, Date fechaInicio, Date fechaFin, double precio) {
		this.id = id;
		this.cliente = cliente;
		this.actividad = actividad;
		this.propiedad = propiedad;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Propiedad getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Alquiler [id=" + id + ", cliente=" + cliente + ", actividad=" + actividad + ", propiedad=" + propiedad + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", precio=" + precio + "]";
	}
}