package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import entities.CustomCliente;
import utilities.ClientManager;
import view.components.TableModel;

public class PanelClientes extends JPanel implements ActionListener {

	private static final long			serialVersionUID	= -4665388463973898411L;

	private static int					MODE_NEW			= 0;
	private static int					MODE_EDIT			= 1;

	private JTextField					txtDni;
	private JTextField					txtNombre;

	private JButton						btnVerAlquileres;
	private JButton						btnBorrar;

	private JButton						btnBuscar;
	private JButton						btnLimpiar;

	private JTextField					txtDniEditor;
	private JTextField					txtNombreEditor;
	private JTextField					txtApellidosEditor;
	private JTextField					txtEmailEditor;
	private JTextField					txtDireccionEditor;
	private JTextField					txtTelefonoEditor;
	private JTextField					txtCodPostalEditor;

	private JButton						btnNuevo;
	private JButton						btnGuardar;

	private String[]					header;
	private JTable						table;
	private TableModel					modelTable;

	private ArrayList<CustomCliente>	clientes;
	private ArrayList<CustomCliente>	clientesVisualizados;
	private CustomCliente				currentCliente;

	int									mode				= 0;

	public PanelClientes() {

		currentCliente = null;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0, 0, 0, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panFiltro = new JPanel();
		panFiltro.setBorder(new TitledBorder(null, "B\u00fasqueda", TitledBorder.ABOVE_TOP, TitledBorder.ABOVE_TOP, null, null));
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
		txtDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if ((ch < '0' || ch > '9') || txtDni.getText().length() == 8) {
					e.consume();
				}
			}
		});
		txtDni.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_txtDni = new GridBagConstraints();
		gbc_txtDni.anchor = GridBagConstraints.WEST;
		gbc_txtDni.insets = new Insets(10, 0, 5, 5);
		gbc_txtDni.gridx = 2;
		gbc_txtDni.gridy = 0;
		panFiltro.add(txtDni, gbc_txtDni);
		txtDni.setColumns(10);

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

		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(this);
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
		btnLimpiar.addActionListener(this);
		btnLimpiar.setIcon(new ImageIcon("icons/clear-icon.png"));
		btnLimpiar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
		gbc_btnLimpiar.fill = GridBagConstraints.BOTH;
		gbc_btnLimpiar.gridheight = 2;
		gbc_btnLimpiar.insets = new Insets(10, 0, 10, 5);
		gbc_btnLimpiar.gridx = 4;
		gbc_btnLimpiar.gridy = 0;
		panFiltro.add(btnLimpiar, gbc_btnLimpiar);

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

		header = new String[7];
		header[0] = "DNI";
		header[1] = "Nombre";
		header[2] = "Apellido";
		header[3] = "Email";
		header[4] = "Direcci\u00f3n";
		header[5] = "C.P.";
		header[6] = "Tel\u00e9fono";

		modelTable = new TableModel();
		modelTable.setDataVector(new String[0][0], header);

		table = new JTable(modelTable);
		table.getTableHeader().setReorderingAllowed(false);
		table.setDragEnabled(false);
		table.setSelectionForeground(Color.WHITE);
		table.setSelectionBackground(Color.BLUE);
		table.setForeground(Color.BLACK);
		table.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		table.setRowHeight(20);
		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				int row = table.getSelectedRow();
				if (row >= 0) {
					mode = MODE_EDIT;
					txtDniEditor.setText(Integer.toString(clientesVisualizados.get(row).getDni()));
					txtNombreEditor.setText(clientesVisualizados.get(row).getNombre());
					txtApellidosEditor.setText(clientesVisualizados.get(row).getApellido());
					txtEmailEditor.setText(clientesVisualizados.get(row).getEmail());
					txtDireccionEditor.setText(clientesVisualizados.get(row).getDireccion());
					txtTelefonoEditor.setText(Integer.toString(clientesVisualizados.get(row).getTelefono()));
					txtCodPostalEditor.setText(Integer.toString(clientesVisualizados.get(row).getCodigoPostal()));
				}
			}
		});

		scrollPane.setViewportView(table);

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
		btnBorrar.addActionListener(this);
		btnBorrar.setIcon(new ImageIcon("icons/remove-icon.png"));
		btnBorrar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_btnBorrar = new GridBagConstraints();
		gbc_btnBorrar.anchor = GridBagConstraints.WEST;
		gbc_btnBorrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnBorrar.gridx = 0;
		gbc_btnBorrar.gridy = 0;
		panBotoneraTabla.add(btnBorrar, gbc_btnBorrar);

		btnVerAlquileres = new JButton("VER ALQUILERES");
		btnVerAlquileres.addActionListener(this);
		btnVerAlquileres.setIcon(new ImageIcon("icons/see-icon.png"));
		btnVerAlquileres.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_btnVerAlquileres = new GridBagConstraints();
		gbc_btnVerAlquileres.anchor = GridBagConstraints.EAST;
		gbc_btnVerAlquileres.gridx = 1;
		gbc_btnVerAlquileres.gridy = 0;
		panBotoneraTabla.add(btnVerAlquileres, gbc_btnVerAlquileres);

		JPanel panEdicion = new JPanel();
		panEdicion.setBorder(new TitledBorder(null, "Edici\u00f3n", TitledBorder.ABOVE_TOP, TitledBorder.ABOVE_TOP, null, null));
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
		txtDniEditor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if ((ch < '0' || ch > '9') || txtDniEditor.getText().length() == 8) {
					e.consume();
				}
			}
		});
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
		btnNuevo.addActionListener(this);
		btnNuevo.setIcon(new ImageIcon("icons/add-icon.png"));
		btnNuevo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_btnNuevo = new GridBagConstraints();
		gbc_btnNuevo.insets = new Insets(0, 0, 0, 5);
		gbc_btnNuevo.gridx = 1;
		gbc_btnNuevo.gridy = 0;
		panel.add(btnNuevo, gbc_btnNuevo);

		btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(this);
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

		JLabel lblApellido = new JLabel("APELLIDO:");
		lblApellido.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.WEST;
		gbc_lblApellido.insets = new Insets(0, 10, 5, 5);
		gbc_lblApellido.gridx = 3;
		gbc_lblApellido.gridy = 1;
		panEdicion.add(lblApellido, gbc_lblApellido);

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

		JLabel lblDireccionEditor = new JLabel("DIRECCI\u00d3N:");
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

		JLabel lblTelefonoEditor = new JLabel("TEL\u00c9FONO:");
		lblTelefonoEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblTelefonoEditor = new GridBagConstraints();
		gbc_lblTelefonoEditor.anchor = GridBagConstraints.WEST;
		gbc_lblTelefonoEditor.insets = new Insets(0, 0, 10, 5);
		gbc_lblTelefonoEditor.gridx = 1;
		gbc_lblTelefonoEditor.gridy = 4;
		panEdicion.add(lblTelefonoEditor, gbc_lblTelefonoEditor);

		txtTelefonoEditor = new JTextField();
		txtTelefonoEditor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if ((ch < '0' || ch > '9') || txtTelefonoEditor.getText().length() == 9) {
					e.consume();
				}
			}
		});
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
		txtCodPostalEditor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if ((ch < '0' || ch > '9') || txtCodPostalEditor.getText().length() == 5) {
					e.consume();
				}
			}
		});
		txtCodPostalEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtCodPostalEditor.setColumns(20);
		GridBagConstraints gbc_txtCodPostalEditor = new GridBagConstraints();
		gbc_txtCodPostalEditor.insets = new Insets(0, 0, 10, 5);
		gbc_txtCodPostalEditor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodPostalEditor.gridx = 4;
		gbc_txtCodPostalEditor.gridy = 4;
		panEdicion.add(txtCodPostalEditor, gbc_txtCodPostalEditor);

		// Se cargan todos los clientes del servidor
		loadData();
	}

	@SuppressWarnings("unchecked")
	public void loadData() {
		// TODO Obtener los clientes del servidor y cargar la tabla
		clientes = ClientManager.getInstance().getAllClientes();
		if (clientes != null && clientes.size() > 0) {
			clientesVisualizados = (ArrayList<CustomCliente>) clientes.clone();
			updateData();
		}
	}

	public void filterData() {
		// TODO visualizar los clientes con el dni y nombre introducidos
		if (clientes != null && clientes.size() > 0) {
			clientesVisualizados = new ArrayList<CustomCliente>();
			for (CustomCliente c : clientes) {
				if (!txtDni.getText().isEmpty() && !txtNombre.getText().isEmpty()) {
					// Se filtra por dni y por nombre
					if (Integer.parseInt(txtDni.getText()) == c.getDni()
							&& txtNombre.getText().trim().toLowerCase().contains(c.getNombre().toLowerCase())) {
						clientesVisualizados.add(c);
					}
				} else if (!txtDni.getText().isEmpty()) {
					// Se filtra por dni
					if (Integer.parseInt(txtDni.getText()) == c.getDni()) {
						clientesVisualizados.add(c);
					}
				} else if (!txtNombre.getText().isEmpty()) {
					// Se filtra por nombre
					if (txtNombre.getText().trim().equals(c.getNombre())) {
						clientesVisualizados.add(c);
					}
				} else {
					clientesVisualizados.add(c);
				}
			}
			updateData();
		}
	}

	public void updateData() {
		modelTable.setDataVector(new String[clientesVisualizados.size()][header.length], header);
		for (int i = 0; i < clientesVisualizados.size(); i++) {
			// DNI
			table.getModel().setValueAt(clientesVisualizados.get(i).getDNIChar(), i, 0);
			// NOMBRE
			table.getModel().setValueAt(clientesVisualizados.get(i).getNombre(), i, 1);
			// APELLIDO
			table.getModel().setValueAt(clientesVisualizados.get(i).getApellido(), i, 2);
			// EMAIL
			table.getModel().setValueAt(clientesVisualizados.get(i).getEmail(), i, 3);
			// DIRECCION
			table.getModel().setValueAt(clientesVisualizados.get(i).getDireccion(), i, 4);
			// CP
			table.getModel().setValueAt(Integer.toString(clientesVisualizados.get(i).getCodigoPostal()), i, 5);
			// TELEFONO
			table.getModel().setValueAt(Integer.toString(clientesVisualizados.get(i).getTelefono()), i, 6);
		}
	}

	public void deleteCliente() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			CustomCliente c = clientesVisualizados.get(selectedRow);
			if (ClientManager.getInstance().deleteCliente(c.getDni())) {
				int aux = 0;
				for (aux = 0; aux < clientes.size(); aux++) {
					if (clientes.get(aux).getDni() == c.getDni()) {
						break;
					}
				}
				clientesVisualizados.remove(selectedRow);
				clientes.remove(aux);
				updateData();
				clearFormData();
				JOptionPane.showMessageDialog(Window.getInstance(), "El cliente con DNI: " + c.getDNIChar() + " se ha eliminado correctamente.",
						"Eliminado correctamente", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(Window.getInstance(), "Ha ocurrido un error al eliminar el cliente.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(Window.getInstance(), "Selecciona un cliente para poder eliminarlo", "Alerta", JOptionPane.WARNING_MESSAGE);
		}
	}

	public String validateCliente() {
		String errorMessage = "";
		// Se comprueba el DNI
		if (txtDniEditor.getText().length() != 8) {
			errorMessage += " - El DNI tiene que tener 8 d\u00edgitos.\n";
		}

		// Se comprueba el nombre 50
		if (txtNombreEditor.getText().isEmpty()) {
			errorMessage += " - El campo nombre del cliente es obligatorio.\n";
		}

		// Se comprueba el apellido 50
		if (txtApellidosEditor.getText().isEmpty()) {
			errorMessage += " - El campo apellido del cliente es obligatorio.\n";
		}

		// Se comprueba el email 100
		if (txtEmailEditor.getText().isEmpty()) {
			errorMessage += " - El campo email del cliente es obligatorio.\n";
		} else if (!CustomCliente.validateEmail(txtEmailEditor.getText())) {
			errorMessage += " - El email introducido es incorrecto.\n";
		}

		// Se comprueba la direccion 100
		if (txtDireccionEditor.getText().isEmpty()) {
			errorMessage += " - El campo direcci\u00f3n del cliente es obligatorio.\n";
		}

		// Se comprueba el telefono
		if (txtTelefonoEditor.getText().length() != 9) {
			errorMessage += " - El tel\u00e9fono tiene que tener 9 d\u00edgitos.\n";
		}

		// Se comprueba el telefono
		if (txtCodPostalEditor.getText().length() != 5) {
			errorMessage += " - El c\u00f3digo postal tiene que tener 5 d\u00edgitos.\n";
		}

		return errorMessage;
	}

	public void saveCliente() {
		String errorMessage = validateCliente();
		if (!errorMessage.isEmpty()) {
			// Error en algun campo
			JOptionPane.showMessageDialog(Window.getInstance(), "Error en los siguientes campos:\n" + errorMessage, "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			currentCliente = new CustomCliente();
			currentCliente.setDni(Integer.parseInt(txtDniEditor.getText()));
			currentCliente.setNombre(txtNombreEditor.getText().trim());
			currentCliente.setApellido(txtApellidosEditor.getText().trim());
			currentCliente.setEmail(txtEmailEditor.getText().trim());
			currentCliente.setDireccion(txtDireccionEditor.getText().trim());
			currentCliente.setTelefono(Integer.parseInt(txtTelefonoEditor.getText()));
			currentCliente.setCodigoPostal(Integer.parseInt(txtCodPostalEditor.getText()));

			if (mode == MODE_NEW) {
				if (ClientManager.getInstance().saveCliente(currentCliente)) {
					clientes.add(currentCliente);
					mode = MODE_EDIT;
					filterData();
					int i;
					for (i = 0; i < clientesVisualizados.size(); i++) {
						if (clientesVisualizados.get(i).getDni() == currentCliente.getDni()) {
							break;
						}
					}
					if (i < clientesVisualizados.size()) {
						table.setRowSelectionInterval(i, i);
					}
					JOptionPane.showMessageDialog(Window.getInstance(), "Cliente insertado correctamente.", "Informaci\u00f3n",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(Window.getInstance(),
							"El cliente no se ha podido a\u00f1adir porque ya existe uno con el mismo DNI.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else if (mode == MODE_EDIT) {
				if (ClientManager.getInstance().editCliente(currentCliente)) {
					int i;
					for (i = 0; i < clientes.size(); i++) {
						if (clientes.get(i).getDni() == currentCliente.getDni()) {
							break;
						}
					}
					clientes.set(i, currentCliente);
					filterData();
					for (i = 0; i < clientesVisualizados.size(); i++) {
						if (clientesVisualizados.get(i).getDni() == currentCliente.getDni()) {
							break;
						}
					}
					if (i < clientesVisualizados.size()) {
						table.setRowSelectionInterval(i, i);
					}
					JOptionPane.showMessageDialog(Window.getInstance(), "Cliente editado correctamente.", "Informaci\u00f3n",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(Window.getInstance(), "Ha ocurrido un error al editar el cliente.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public void clearFormData() {
		txtDniEditor.setText("");
		txtNombreEditor.setText("");
		txtApellidosEditor.setText("");
		txtEmailEditor.setText("");
		txtDireccionEditor.setText("");
		txtTelefonoEditor.setText("");
		txtCodPostalEditor.setText("");
		mode = MODE_NEW;
	}

	public void showAlquileres() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			CustomCliente c = clientesVisualizados.get(selectedRow);
			PanelAlquileres panAlquileres = new PanelAlquileres(c);
			Window.getInstance().setContainer(panAlquileres);
			Window.getInstance().setTitle("Alquileres de: " + c.getNombre().toUpperCase() + " " + c.getApellido().toUpperCase());
		} else {
			JOptionPane.showMessageDialog(Window.getInstance(), "Selecciona un cliente para poder ver sus alquileres", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (btnBuscar == e.getSource()) {
			filterData();
		} else if (btnLimpiar == e.getSource()) {
			txtDni.setText("");
			txtNombre.setText("");
		} else if (btnBorrar == e.getSource()) {
			deleteCliente();
		} else if (btnVerAlquileres == e.getSource()) {
			showAlquileres();
		} else if (btnNuevo == e.getSource()) {
			clearFormData();
		} else if (btnGuardar == e.getSource()) {
			saveCliente();
		}
	}
}