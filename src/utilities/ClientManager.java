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

	private static String			URL	= "http://localhost:8080/PropiedadesServidorREST/";

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
		ClientResponse response = service.path("rest").path("clientes").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, id);
		return response.getStatus() == 201 ? response.getEntity(Cliente.class) : null;
	}

	public ArrayList<Cliente> getAllClientes() {
		Cliente[] array = service.path("rest").path("clientes").get(Cliente[].class);
		return new ArrayList<Cliente>(Arrays.asList(array));
	}

	public boolean deleteCliente(final Cliente client) {
		try {
			service.path("rest").path("clientes").path(Integer.toString(client.getDni())).delete();
			return true;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("todos.{id}.DEL.status: " + r.getStatus());
			System.out.println("todos.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	public boolean saveCliente(final Cliente client) {
		try {
			ClientResponse response = service.path("rest").path("clientes").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, client);
			return response.getStatus() == 201 ? true : false;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("todos.{id}.DEL.status: " + r.getStatus());
			System.out.println("todos.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	// ALQUILERES
	public boolean saveAlquiler(final Alquiler alquiler) {
		try {
			ClientResponse response = service.path("rest").path("alquileres").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, alquiler);
			return response.getStatus() == 201 ? true : false;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("todos.{id}.DEL.status: " + r.getStatus());
			System.out.println("todos.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	public boolean deleteAlquiler(final Alquiler alquiler) {
		try {
			service.path("rest").path("alquileres").path(Integer.toString(alquiler.getId())).delete();
			return true;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("todos.{id}.DEL.status: " + r.getStatus());
			System.out.println("todos.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	// PROPIEDADES
	public ArrayList<Propiedad> getAllPropiedades() {
		Propiedad[] array = service.path("rest").path("propiedades").path("todas").get(Propiedad[].class);
		return new ArrayList<Propiedad>(Arrays.asList(array));
	}

	// ACTIVIDADES
	public ArrayList<Actividad> getActividadesByPropiedad(final Propiedad propiedad) {
		Actividad[] array = service.path("rest").path("actividades").path("propiedades").path(Integer.toString(propiedad.getId()))
				.get(Actividad[].class);
		return new ArrayList<Actividad>(Arrays.asList(array));
	}

	public ArrayList<Actividad> getAllActividades() {
		Actividad[] array = service.path("rest").path("actividades").path("todas").get(Actividad[].class);
		return new ArrayList<Actividad>(Arrays.asList(array));
	}

	public static ClientManager getInstance() {
		if (instance == null) {
			instance = new ClientManager();
		}
		return instance;
	}

	public void main(String[] args) {
		// TEST
	}
}