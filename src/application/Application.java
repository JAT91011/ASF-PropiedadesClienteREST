package application;

import java.awt.EventQueue;

import javax.swing.UIManager;

import view.PanelClientes;
import view.Window;

public class Application {

	public static void main(final String[] args) {
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
}