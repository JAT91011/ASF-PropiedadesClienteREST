package utilities;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import entities.Actividad;
import entities.Alquiler;
import entities.Cliente;
import entities.Propiedad;

public class ClientManager {

	private static ClientManager	instance;

	public static int				SUCCESS_CODE		= 201;
	public static int				EDIT_SUCCESS_CODE	= 204;
	public static int				ERROR_CODE			= 205;
	public static int				ALREADY_EXIST_CODE	= 206;

	private static String			URL					= "http://localhost:8080/PropiedadesServidorREST/";

	private DefaultClientConfig		config;
	private Client					client;
	private WebResource				service;

	private ClientManager() {
		config = new DefaultClientConfig();
		client = Client.create(config);
		service = client.resource(UriBuilder.fromUri(URL).build());
	}

	// CLIENTES
	public Cliente getClienteById(final int id) {
		Cliente client = service.path("rest").path("clientes").path(Integer.toString(id)).get(Cliente.class);
		return client;
	}

	public ArrayList<Cliente> getAllClientes() {
		Cliente[] array = service.path("rest").path("clientes").get(Cliente[].class);
		return new ArrayList<Cliente>(Arrays.asList(array));
	}

	public boolean saveCliente(final Cliente client) {
		try {
			ClientResponse response = service.path("rest").path("clientes").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, client);
			return response.getStatus() == SUCCESS_CODE ? true : false;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("clients.{id}.DEL.status: " + r.getStatus());
			System.out.println("clients.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	public boolean editCliente(final Cliente client) {
		ClientResponse response = service.path("rest").path("clientes").path(Integer.toString(client.getDni())).type(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, client);
		return response.getStatus() == EDIT_SUCCESS_CODE ? true : false;
	}

	public boolean deleteCliente(final int id) {
		try {
			service.path("rest").path("clientes").path(Integer.toString(id)).delete();
			return true;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("clients.{id}.DEL.status: " + r.getStatus());
			System.out.println("clients.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	// ALQUILERES
	public ArrayList<Alquiler> getAlquileresByDniCliente(final int dni) {
		Alquiler[] array = service.path("rest").path("alquileres").path("cliente").path(Integer.toString(dni)).get(Alquiler[].class);
		return new ArrayList<Alquiler>(Arrays.asList(array));
	}

	public boolean saveAlquiler(final Alquiler alquiler) {
		try {
			ClientResponse response = service.path("rest").path("alquileres").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, alquiler);
			return response.getStatus() == SUCCESS_CODE ? true : false;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("alquileres.{id}.DEL.status: " + r.getStatus());
			System.out.println("alquileres.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	public boolean editAlquiler(final Alquiler alquiler) {
		ClientResponse response = service.path("rest").path("alquileres").path("alquiler").path(Integer.toString(alquiler.getIdAlquiler()))
				.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, alquiler);
		return response.getStatus() == EDIT_SUCCESS_CODE ? true : false;
	}

	public boolean deleteAlquiler(final int id) {
		try {
			service.path("rest").path("alquileres").path("alquiler").path(Integer.toString(id)).delete();
			return true;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("alquileres.{id}.DEL.status: " + r.getStatus());
			System.out.println("alquileres.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	// ACTIVIDADES
	public ArrayList<Actividad> getAllActividades() {
		Actividad[] array = service.path("rest").path("actividades").get(Actividad[].class);
		return new ArrayList<Actividad>(Arrays.asList(array));
	}

	public ArrayList<Actividad> getActividadesByIdPropiedad(final int idPropiedad) {
		Actividad[] array = service.path("rest").path("actividades").path(Integer.toString(idPropiedad)).get(Actividad[].class);
		return new ArrayList<Actividad>(Arrays.asList(array));
	}

	// PROPIEDADES
	public ArrayList<Propiedad> getAllPropiedades() {
		Propiedad[] array = service.path("rest").path("propiedades").get(Propiedad[].class);
		return new ArrayList<Propiedad>(Arrays.asList(array));
	}

	public Propiedad getPropiedadById(final int idPropiedad) {
		Propiedad propiedad = service.path("rest").path("propiedades").path(Integer.toString(idPropiedad)).get(Propiedad.class);
		return propiedad;
	}

	public static ClientManager getInstance() {
		if (instance == null) {
			instance = new ClientManager();
		}
		return instance;
	}
}