package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import entities.Cliente;

public class PanelAlquileres extends JPanel {

	private static final long	serialVersionUID	= 3716515640526061428L;

	private Cliente				cliente;

	private JButton				btnBorrar;
	private JButton				btnNuevo;
	private JButton				btnGuardar;
	private JTextField			txtPrecio;
	private JTable				table;

	public PanelAlquileres(Cliente cliente) {

		this.cliente = cliente;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0, 0, 0, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panCliente = new JPanel();
		panCliente.setBorder(new TitledBorder(null, "Cliente", TitledBorder.ABOVE_TOP, TitledBorder.ABOVE_TOP, null, null));
		GridBagConstraints gbc_panCliente = new GridBagConstraints();
		gbc_panCliente.insets = new Insets(0, 0, 10, 5);
		gbc_panCliente.fill = GridBagConstraints.BOTH;
		gbc_panCliente.gridx = 1;
		gbc_panCliente.gridy = 1;
		add(panCliente, gbc_panCliente);
		GridBagLayout gbl_panCliente = new GridBagLayout();
		gbl_panCliente.columnWidths = new int[] { 20, 0, 0, 0, 0 };
		gbl_panCliente.rowHeights = new int[] { 0, 0, 0 };
		gbl_panCliente.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panCliente.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panCliente.setLayout(gbl_panCliente);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.anchor = GridBagConstraints.WEST;
		gbc_lblDni.insets = new Insets(10, 0, 5, 5);
		gbc_lblDni.gridx = 1;
		gbc_lblDni.gridy = 0;
		panCliente.add(lblDni, gbc_lblDni);

		JLabel lblDniValue = new JLabel(Integer.toString(cliente.getDni()));
		lblDniValue.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDniValue = new GridBagConstraints();
		gbc_lblDniValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDniValue.insets = new Insets(10, 0, 5, 5);
		gbc_lblDniValue.gridx = 2;
		gbc_lblDniValue.gridy = 0;
		panCliente.add(lblDniValue, gbc_lblDniValue);

		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 10, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		panCliente.add(lblNombre, gbc_lblNombre);

		JLabel lblNombreValue = new JLabel(cliente.getNombre());
		lblNombreValue.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombreValue = new GridBagConstraints();
		gbc_lblNombreValue.anchor = GridBagConstraints.EAST;
		gbc_lblNombreValue.insets = new Insets(0, 0, 10, 5);
		gbc_lblNombreValue.gridx = 2;
		gbc_lblNombreValue.gridy = 1;
		panCliente.add(lblNombreValue, gbc_lblNombreValue);

		JPanel panTabla = new JPanel();
		panTabla.setBorder(new TitledBorder(null, "Alquileres", TitledBorder.ABOVE_TOP, TitledBorder.ABOVE_TOP, null, null));
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

		table = new JTable();
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
		btnBorrar.setIcon(new ImageIcon("icons/remove-icon.png"));
		btnBorrar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_btnBorrar = new GridBagConstraints();
		gbc_btnBorrar.anchor = GridBagConstraints.WEST;
		gbc_btnBorrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnBorrar.gridx = 0;
		gbc_btnBorrar.gridy = 0;
		panBotoneraTabla.add(btnBorrar, gbc_btnBorrar);

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
		gbl_panEdicion.rowWeights = new double[] { 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		panEdicion.setLayout(gbl_panEdicion);

		JLabel lblPropiedadEditor = new JLabel("PROPIEDAD:");
		lblPropiedadEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblPropiedadEditor = new GridBagConstraints();
		gbc_lblPropiedadEditor.insets = new Insets(10, 0, 5, 5);
		gbc_lblPropiedadEditor.anchor = GridBagConstraints.WEST;
		gbc_lblPropiedadEditor.gridx = 1;
		gbc_lblPropiedadEditor.gridy = 0;
		panEdicion.add(lblPropiedadEditor, gbc_lblPropiedadEditor);

		JComboBox cboPropiedades = new JComboBox();
		cboPropiedades.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_cboPropiedades = new GridBagConstraints();
		gbc_cboPropiedades.insets = new Insets(10, 0, 5, 5);
		gbc_cboPropiedades.fill = GridBagConstraints.BOTH;
		gbc_cboPropiedades.gridx = 2;
		gbc_cboPropiedades.gridy = 0;
		panEdicion.add(cboPropiedades, gbc_cboPropiedades);

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

		JLabel lblActividadEditor = new JLabel("ACTIVIDAD:");
		lblActividadEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblActividadEditor = new GridBagConstraints();
		gbc_lblActividadEditor.anchor = GridBagConstraints.WEST;
		gbc_lblActividadEditor.insets = new Insets(0, 0, 5, 5);
		gbc_lblActividadEditor.gridx = 1;
		gbc_lblActividadEditor.gridy = 1;
		panEdicion.add(lblActividadEditor, gbc_lblActividadEditor);

		JComboBox cboActividad = new JComboBox();
		cboActividad.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_cboActividad = new GridBagConstraints();
		gbc_cboActividad.gridwidth = 3;
		gbc_cboActividad.insets = new Insets(0, 0, 5, 5);
		gbc_cboActividad.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboActividad.gridx = 2;
		gbc_cboActividad.gridy = 1;
		panEdicion.add(cboActividad, gbc_cboActividad);

		JLabel lblFechaInicioEditor = new JLabel("FECHA INICIO:");
		lblFechaInicioEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblFechaInicioEditor = new GridBagConstraints();
		gbc_lblFechaInicioEditor.anchor = GridBagConstraints.WEST;
		gbc_lblFechaInicioEditor.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaInicioEditor.gridx = 1;
		gbc_lblFechaInicioEditor.gridy = 2;
		panEdicion.add(lblFechaInicioEditor, gbc_lblFechaInicioEditor);

		JPanel panFechaInicio = new JPanel();
		GridBagConstraints gbc_panFechaInicio = new GridBagConstraints();
		gbc_panFechaInicio.gridwidth = 3;
		gbc_panFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_panFechaInicio.fill = GridBagConstraints.BOTH;
		gbc_panFechaInicio.gridx = 2;
		gbc_panFechaInicio.gridy = 2;
		panEdicion.add(panFechaInicio, gbc_panFechaInicio);
		GridBagLayout gbl_panFechaInicio = new GridBagLayout();
		gbl_panFechaInicio.columnWidths = new int[] { 0, 0, 0 };
		gbl_panFechaInicio.rowHeights = new int[] { 0, 0 };
		gbl_panFechaInicio.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panFechaInicio.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panFechaInicio.setLayout(gbl_panFechaInicio);

		JLabel lblFechaInicioValor = new JLabel(" 08/04/2016");
		lblFechaInicioValor.setOpaque(true);
		lblFechaInicioValor.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblFechaInicioValor = new GridBagConstraints();
		gbc_lblFechaInicioValor.fill = GridBagConstraints.BOTH;
		gbc_lblFechaInicioValor.insets = new Insets(0, 4, 0, 5);
		gbc_lblFechaInicioValor.gridx = 0;
		gbc_lblFechaInicioValor.gridy = 0;
		panFechaInicio.add(lblFechaInicioValor, gbc_lblFechaInicioValor);

		JButton btnSeleccionarInicio = new JButton("Seleccionar");
		GridBagConstraints gbc_btnSeleccionarInicio = new GridBagConstraints();
		gbc_btnSeleccionarInicio.gridx = 1;
		gbc_btnSeleccionarInicio.gridy = 0;
		panFechaInicio.add(btnSeleccionarInicio, gbc_btnSeleccionarInicio);

		JLabel lblFechaFinEditor = new JLabel("FECHA FIN:");
		lblFechaFinEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblFechaFinEditor = new GridBagConstraints();
		gbc_lblFechaFinEditor.anchor = GridBagConstraints.WEST;
		gbc_lblFechaFinEditor.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaFinEditor.gridx = 1;
		gbc_lblFechaFinEditor.gridy = 3;
		panEdicion.add(lblFechaFinEditor, gbc_lblFechaFinEditor);

		JPanel panFechaFin = new JPanel();
		GridBagConstraints gbc_panFechaFin = new GridBagConstraints();
		gbc_panFechaFin.gridwidth = 3;
		gbc_panFechaFin.insets = new Insets(0, 0, 5, 5);
		gbc_panFechaFin.fill = GridBagConstraints.BOTH;
		gbc_panFechaFin.gridx = 2;
		gbc_panFechaFin.gridy = 3;
		panEdicion.add(panFechaFin, gbc_panFechaFin);
		GridBagLayout gbl_panFechaFin = new GridBagLayout();
		gbl_panFechaFin.columnWidths = new int[] { 0, 0, 0 };
		gbl_panFechaFin.rowHeights = new int[] { 0, 0 };
		gbl_panFechaFin.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panFechaFin.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panFechaFin.setLayout(gbl_panFechaFin);

		JLabel lblFechaFinValor = new JLabel(" 08/04/2016");
		lblFechaFinValor.setOpaque(true);
		lblFechaFinValor.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblFechaFinValor = new GridBagConstraints();
		gbc_lblFechaFinValor.fill = GridBagConstraints.BOTH;
		gbc_lblFechaFinValor.insets = new Insets(0, 4, 0, 5);
		gbc_lblFechaFinValor.gridx = 0;
		gbc_lblFechaFinValor.gridy = 0;
		panFechaFin.add(lblFechaFinValor, gbc_lblFechaFinValor);

		JButton btnSeleccionarFechaFin = new JButton("Seleccionar");
		GridBagConstraints gbc_btnSeleccionarFechaFin = new GridBagConstraints();
		gbc_btnSeleccionarFechaFin.gridx = 1;
		gbc_btnSeleccionarFechaFin.gridy = 0;
		panFechaFin.add(btnSeleccionarFechaFin, gbc_btnSeleccionarFechaFin);

		JLabel lblTelefonoEditor = new JLabel("PRECIO:");
		lblTelefonoEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_lblTelefonoEditor = new GridBagConstraints();
		gbc_lblTelefonoEditor.anchor = GridBagConstraints.WEST;
		gbc_lblTelefonoEditor.insets = new Insets(0, 0, 10, 5);
		gbc_lblTelefonoEditor.gridx = 1;
		gbc_lblTelefonoEditor.gridy = 4;
		panEdicion.add(lblTelefonoEditor, gbc_lblTelefonoEditor);

		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_txtPrecio = new GridBagConstraints();
		gbc_txtPrecio.insets = new Insets(0, 0, 10, 5);
		gbc_txtPrecio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrecio.gridx = 2;
		gbc_txtPrecio.gridy = 4;
		panEdicion.add(txtPrecio, gbc_txtPrecio);
		txtPrecio.setColumns(10);
	}
}