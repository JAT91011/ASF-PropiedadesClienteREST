package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelClientes extends JPanel implements ActionListener {

	private static final long	serialVersionUID	= -4665388463973898411L;

	private JTextField			txtDni;
	private JTextField			txtNombre;

	private JButton				btnVerAlquileres;
	private JButton				btnBorrar;

	private JButton				btnBuscar;
	private JButton				btnLimpiar;

	private JTextField			txtDniEditor;
	private JTextField			txtNombreEditor;
	private JTextField			txtApellidosEditor;
	private JTextField			txtEmailEditor;
	private JTextField			txtDireccionEditor;
	private JTextField			txtTelefonoEditor;
	private JTextField			txtCodPostalEditor;

	private JButton				btnNuevo;
	private JButton				btnGuardar;

	public PanelClientes() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0, 0, 0, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panFiltro = new JPanel();
		panFiltro.setBorder(new TitledBorder(null, "Búsqueda", TitledBorder.ABOVE_TOP, TitledBorder.ABOVE_TOP, null, null));
		GridBagConstraints gbc_panFiltro = new GridBagConstraints();
		gbc_panFiltro.insets = new Insets(0, 0, 10, 5);
		gbc_panFiltro.fill = GridBagConstraints.BOTH;
		gbc_panFiltro.gridx = 1;
		gbc_panFiltro.gridy = 1;
		add(panFiltro, gbc_panFiltro);
		GridBagLayout gbl_panFiltro = new GridBagLayout();
		gbl_panFiltro.columnWidths = new int[] { 20, 0, 0, 0, 0, 0, 0 };
		gbl_panFiltro.rowHeights = new int[] { 0, 0, 0 };
		gbl_panFiltro.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panFiltro.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panFiltro.setLayout(gbl_panFiltro);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.anchor = GridBagConstraints.WEST;
		gbc_lblDni.insets = new Insets(10, 0, 5, 5);
		gbc_lblDni.gridx = 1;
		gbc_lblDni.gridy = 0;
		panFiltro.add(lblDni, gbc_lblDni);

		txtDni = new JTextField();
		txtDni.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_txtDni = new GridBagConstraints();
		gbc_txtDni.anchor = GridBagConstraints.WEST;
		gbc_txtDni.insets = new Insets(10, 0, 5, 5);
		gbc_txtDni.gridx = 2;
		gbc_txtDni.gridy = 0;
		panFiltro.add(txtDni, gbc_txtDni);
		txtDni.setColumns(10);

		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setIcon(new ImageIcon("icons/search-icon.png"));
		btnBuscar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.BOTH;
		gbc_btnBuscar.gridheight = 2;
		gbc_btnBuscar.insets = new Insets(10, 0, 10, 5);
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 0;
		panFiltro.add(btnBuscar, gbc_btnBuscar);

		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setIcon(new ImageIcon("icons/clear-icon.png"));
		btnLimpiar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
		gbc_btnLimpiar.fill = GridBagConstraints.BOTH;
		gbc_btnLimpiar.gridheight = 2;
		gbc_btnLimpiar.insets = new Insets(10, 0, 10, 5);
		gbc_btnLimpiar.gridx = 4;
		gbc_btnLimpiar.gridy = 0;
		panFiltro.add(btnLimpiar, gbc_btnLimpiar);

		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 10, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		panFiltro.add(lblNombre, gbc_lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.anchor = GridBagConstraints.WEST;
		gbc_txtNombre.insets = new Insets(0, 0, 10, 5);
		gbc_txtNombre.fill = GridBagConstraints.VERTICAL;
		gbc_txtNombre.gridx = 2;
		gbc_txtNombre.gridy = 1;
		panFiltro.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(20);

		JPanel panTabla = new JPanel();
		panTabla.setBorder(new TitledBorder(null, "Clientes", TitledBorder.ABOVE_TOP, TitledBorder.ABOVE_TOP, null, null));
		GridBagConstraints gbc_panTabla = new GridBagConstraints();
		gbc_panTabla.insets = new Insets(0, 0, 10, 5);
		gbc_panTabla.fill = GridBagConstraints.BOTH;
		gbc_panTabla.gridx = 1;
		gbc_panTabla.gridy = 2;
		add(panTabla, gbc_panTabla);
		GridBagLayout gbl_panTabla = new GridBagLayout();
		gbl_panTabla.columnWidths = new int[] { 20, 0, 20, 0 };
		gbl_panTabla.rowHeights = new int[] { 0, 0, 0 };
		gbl_panTabla.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panTabla.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panTabla.setLayout(gbl_panTabla);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(10, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		panTabla.add(scrollPane, gbc_scrollPane);

		JPanel panBotoneraTabla = new JPanel();
		GridBagConstraints gbc_panBotoneraTabla = new GridBagConstraints();
		gbc_panBotoneraTabla.insets = new Insets(0, 0, 0, 5);
		gbc_panBotoneraTabla.fill = GridBagConstraints.BOTH;
		gbc_panBotoneraTabla.gridx = 1;
		gbc_panBotoneraTabla.gridy = 1;
		panTabla.add(panBotoneraTabla, gbc_panBotoneraTabla);

		GridBagLayout gbl_panBotoneraTabla = new GridBagLayout();
		gbl_panBotoneraTabla.columnWidths = new int[] { 0, 0, 0 };
		gbl_panBotoneraTabla.rowHeights = new int[] { 0, 0 };
		gbl_panBotoneraTabla.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panBotoneraTabla.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panBotoneraTabla.setLayout(gbl_panBotoneraTabla);

		btnBorrar = new JButton("ELIMINAR");
		btnBorrar.setIcon(new ImageIcon("icons/remove-icon.png"));
		btnBorrar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_btnBorrar = new GridBagConstraints();
		gbc_btnBorrar.anchor = GridBagConstraints.WEST;
		gbc_btnBorrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnBorrar.gridx = 0;
		gbc_btnBorrar.gridy = 0;
		panBotoneraTabla.add(btnBorrar, gbc_btnBorrar);

		btnVerAlquileres = new JButton("VER ALQUILERES");
		btnVerAlquileres.setIcon(new ImageIcon("icons/see-icon.png"));
		btnVerAlquileres.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_btnVerAlquileres = new GridBagConstraints();
		gbc_btnVerAlquileres.anchor = GridBagConstraints.EAST;
		gbc_btnVerAlquileres.gridx = 1;
		gbc_btnVerAlquileres.gridy = 0;
		panBotoneraTabla.add(btnVerAlquileres, gbc_btnVerAlquileres);

		JPanel panEdicion = new JPanel();
		panEdicion.setBorder(new TitledBorder(null, "Edición", TitledBorder.ABOVE_TOP, TitledBorder.ABOVE_TOP, null, null));
		GridBagConstraints gbc_panEdicion = new GridBagConstraints();
		gbc_panEdicion.insets = new Insets(0, 0, 5, 5);
		gbc_panEdicion.fill = GridBagConstraints.BOTH;
		gbc_panEdicion.gridx = 1;
		gbc_panEdicion.gridy = 3;
		add(panEdicion, gbc_panEdicion);
		GridBagLayout gbl_panEdicion = new GridBagLayout();
		gbl_panEdicion.columnWidths = new int[] { 20, 0, 0, 0, 0, 20, 0 };
		gbl_panEdicion.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panEdicion.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panEdicion.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panEdicion.setLayout(gbl_panEdicion);

		JLabel lblDniEditor = new JLabel("DNI:");
		lblDniEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDniEditor = new GridBagConstraints();
		gbc_lblDniEditor.insets = new Insets(10, 0, 5, 5);
		gbc_lblDniEditor.anchor = GridBagConstraints.WEST;
		gbc_lblDniEditor.gridx = 1;
		gbc_lblDniEditor.gridy = 0;
		panEdicion.add(lblDniEditor, gbc_lblDniEditor);

		txtDniEditor = new JTextField();
		txtDniEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtDniEditor.setColumns(10);
		GridBagConstraints gbc_txtDniEditor = new GridBagConstraints();
		gbc_txtDniEditor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDniEditor.insets = new Insets(10, 0, 5, 5);
		gbc_txtDniEditor.gridx = 2;
		gbc_txtDniEditor.gridy = 0;
		panEdicion.add(txtDniEditor, gbc_txtDniEditor);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(10, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 0;
		panEdicion.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		btnNuevo = new JButton("NUEVO");
		btnNuevo.setIcon(new ImageIcon("icons/add-icon.png"));
		btnNuevo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_btnNuevo = new GridBagConstraints();
		gbc_btnNuevo.insets = new Insets(0, 0, 0, 5);
		gbc_btnNuevo.gridx = 1;
		gbc_btnNuevo.gridy = 0;
		panel.add(btnNuevo, gbc_btnNuevo);

		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setIcon(new ImageIcon("icons/save-icon.png"));
		btnGuardar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.fill = GridBagConstraints.VERTICAL;
		gbc_btnGuardar.gridx = 2;
		gbc_btnGuardar.gridy = 0;
		panel.add(btnGuardar, gbc_btnGuardar);

		JLabel lblNombreEditor = new JLabel("NOMBRE:");
		lblNombreEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNombreEditor = new GridBagConstraints();
		gbc_lblNombreEditor.anchor = GridBagConstraints.WEST;
		gbc_lblNombreEditor.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreEditor.gridx = 1;
		gbc_lblNombreEditor.gridy = 1;
		panEdicion.add(lblNombreEditor, gbc_lblNombreEditor);

		txtNombreEditor = new JTextField();
		txtNombreEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtNombreEditor.setColumns(20);
		GridBagConstraints gbc_txtNombreEditor = new GridBagConstraints();
		gbc_txtNombreEditor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombreEditor.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombreEditor.gridx = 2;
		gbc_txtNombreEditor.gridy = 1;
		panEdicion.add(txtNombreEditor, gbc_txtNombreEditor);

		JLabel lblApellidos = new JLabel("APELLIDOS:");
		lblApellidos.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.WEST;
		gbc_lblApellidos.insets = new Insets(0, 10, 5, 5);
		gbc_lblApellidos.gridx = 3;
		gbc_lblApellidos.gridy = 1;
		panEdicion.add(lblApellidos, gbc_lblApellidos);

		txtApellidosEditor = new JTextField();
		txtApellidosEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtApellidosEditor.setColumns(20);
		GridBagConstraints gbc_txtApellidosEditor = new GridBagConstraints();
		gbc_txtApellidosEditor.insets = new Insets(0, 0, 5, 5);
		gbc_txtApellidosEditor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApellidosEditor.gridx = 4;
		gbc_txtApellidosEditor.gridy = 1;
		panEdicion.add(txtApellidosEditor, gbc_txtApellidosEditor);

		JLabel lblEmailEditor = new JLabel("EMAIL:");
		lblEmailEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblEmailEditor = new GridBagConstraints();
		gbc_lblEmailEditor.anchor = GridBagConstraints.WEST;
		gbc_lblEmailEditor.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailEditor.gridx = 1;
		gbc_lblEmailEditor.gridy = 2;
		panEdicion.add(lblEmailEditor, gbc_lblEmailEditor);

		txtEmailEditor = new JTextField();
		txtEmailEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtEmailEditor.setColumns(20);
		GridBagConstraints gbc_txtEmailEditor = new GridBagConstraints();
		gbc_txtEmailEditor.gridwidth = 3;
		gbc_txtEmailEditor.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmailEditor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmailEditor.gridx = 2;
		gbc_txtEmailEditor.gridy = 2;
		panEdicion.add(txtEmailEditor, gbc_txtEmailEditor);

		JLabel lblDireccionEditor = new JLabel("DIRECCIÓN:");
		lblDireccionEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDireccionEditor = new GridBagConstraints();
		gbc_lblDireccionEditor.anchor = GridBagConstraints.WEST;
		gbc_lblDireccionEditor.insets = new Insets(0, 0, 5, 5);
		gbc_lblDireccionEditor.gridx = 1;
		gbc_lblDireccionEditor.gridy = 3;
		panEdicion.add(lblDireccionEditor, gbc_lblDireccionEditor);

		txtDireccionEditor = new JTextField();
		txtDireccionEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtDireccionEditor.setColumns(20);
		GridBagConstraints gbc_txtDireccionEditor = new GridBagConstraints();
		gbc_txtDireccionEditor.gridwidth = 3;
		gbc_txtDireccionEditor.insets = new Insets(0, 0, 5, 5);
		gbc_txtDireccionEditor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDireccionEditor.gridx = 2;
		gbc_txtDireccionEditor.gridy = 3;
		panEdicion.add(txtDireccionEditor, gbc_txtDireccionEditor);

		JLabel lblTelefonoEditor = new JLabel("TELÉFONO:");
		lblTelefonoEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblTelefonoEditor = new GridBagConstraints();
		gbc_lblTelefonoEditor.anchor = GridBagConstraints.WEST;
		gbc_lblTelefonoEditor.insets = new Insets(0, 0, 10, 5);
		gbc_lblTelefonoEditor.gridx = 1;
		gbc_lblTelefonoEditor.gridy = 4;
		panEdicion.add(lblTelefonoEditor, gbc_lblTelefonoEditor);

		txtTelefonoEditor = new JTextField();
		txtTelefonoEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtTelefonoEditor.setColumns(20);
		GridBagConstraints gbc_txtTelefonoEditor = new GridBagConstraints();
		gbc_txtTelefonoEditor.insets = new Insets(0, 0, 10, 5);
		gbc_txtTelefonoEditor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefonoEditor.gridx = 2;
		gbc_txtTelefonoEditor.gridy = 4;
		panEdicion.add(txtTelefonoEditor, gbc_txtTelefonoEditor);

		JLabel lblCodPostal = new JLabel("COD. POSTAL:");
		lblCodPostal.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblCodPostal = new GridBagConstraints();
		gbc_lblCodPostal.anchor = GridBagConstraints.WEST;
		gbc_lblCodPostal.insets = new Insets(0, 10, 10, 5);
		gbc_lblCodPostal.gridx = 3;
		gbc_lblCodPostal.gridy = 4;
		panEdicion.add(lblCodPostal, gbc_lblCodPostal);

		txtCodPostalEditor = new JTextField();
		txtCodPostalEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtCodPostalEditor.setColumns(20);
		GridBagConstraints gbc_txtCodPostalEditor = new GridBagConstraints();
		gbc_txtCodPostalEditor.insets = new Insets(0, 0, 10, 5);
		gbc_txtCodPostalEditor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodPostalEditor.gridx = 4;
		gbc_txtCodPostalEditor.gridy = 4;
		panEdicion.add(txtCodPostalEditor, gbc_txtCodPostalEditor);
	}

	public void actionPerformed(ActionEvent e) {
		if (btnBuscar == e.getSource()) {

		} else if (btnLimpiar == e.getSource()) {

		} else if (btnBorrar == e.getSource()) {

		} else if (btnVerAlquileres == e.getSource()) {

		} else if (btnNuevo == e.getSource()) {

		} else if (btnGuardar == e.getSource()) {

		}
	}
}