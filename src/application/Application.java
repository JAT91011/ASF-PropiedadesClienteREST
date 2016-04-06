package application;

import java.awt.EventQueue;
import java.net.URI;

import javax.swing.UIManager;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import entities.Cliente;
import view.PanelClientes;
import view.Window;

public class Application {

	public static void main(final String[] args) {

		ClientConfig config = new DefaultClientConfig();

		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		System.out.println("Cliente: " + service.path("rest").path("client").path("one").get(Cliente.class).toString());

		Cliente[] array = service.path("rest").path("clients").get(Cliente[].class);
		for (Cliente c : array) {
			System.out.println(c.toString());
		}

		Cliente cliente = new Cliente("Endika", "Salgueiro", "email@mail.com", "Calle2...", 48900, 676909011);

		ClientResponse response = service.path("rest").path("clients").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, cliente);
		if (response.getStatus() == 201) { // Return code should be 201
			System.out.println("OK");
			System.out.println("Cliente actualizado: " + response.getEntity(Cliente.class).toString());
		} else { // Or code 409 == resource already exists
			System.out.println("Ya existe");
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					Window.getInstance().setContainer(new PanelClientes());
					Window.getInstance().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/PropiedadesServidorREST/").build();
	}
}