package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

	private static final long	serialVersionUID	= -8641413596663241575L;
	private static Window		instance;
	private JPanel				container;

	private Window() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(null);
		setSize(400, 700);
		setIconImage((new ImageIcon("icons/app-icon.png")).getImage());
		setMinimumSize(new Dimension(700, 500));
		setTitle("Listado clientes");
		setLocationRelativeTo(null);
	}

	public void setContainer(JPanel panel) {
		if (container != null) {
			instance.getContentPane().remove(container);
		}
		container = panel;
		getContentPane().add(container, BorderLayout.CENTER);
	}

	public static Window getInstance() {
		if (instance == null) {
			instance = new Window();
		}
		return instance;
	}
}