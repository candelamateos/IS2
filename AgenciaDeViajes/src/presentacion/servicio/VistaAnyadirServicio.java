package presentacion.servicio;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.servicio.TActividad;
import negocio.servicio.TAlojamiento;
import negocio.servicio.TTransporte;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaAnyadirServicio extends JFrame implements IGUI{
	
	JPanel cards;
	JPanel panelActividad;
	JPanel panelTransporte;
	JPanel panelAlojamiento;
	JPanel mainPanel;
	JPanel comboBoxPanel;
	JComboBox<String> comboBox;
	JTextField textFieldNombre;
	JTextField textFieldPrecio;
	JTextField textFieldNumPlazas;
	JTextField textFieldRegimen;
	JComboBox<String> comboBoxEstrellas;
	Map<String, Integer> convertirEstrellas;
	JButton buttonGuardar;
	JTextField textFieldTipoTransporte;
	JComboBox<String> comboBoxComida;
	Map<String, Boolean> convertirComida;
	JTextField textFieldTipoActividad;
	JComboBox<String> comboBoxColectivo;
	Map<String, Boolean> convertirColectivo;
	
	
	private final String PANEL_ACTIVIDAD = "actividad";
	private final String PANEL_TRANSPORTE = "transporte";
	private final String PANEL_ALOJAMIENTO = "alojamiento";


	public VistaAnyadirServicio() {
		super("añadir servicio");
		initGUI();
	}
	
	private void initGUI() {
		
		cards = new JPanel(new CardLayout());
		comboBoxPanel = new JPanel();
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(comboBoxPanel, BorderLayout.NORTH);
		mainPanel.add(cards, BorderLayout.CENTER);
		
		panelActividad = new JPanel();
		panelTransporte = new JPanel();
		panelAlojamiento = new JPanel();
		cards.add(panelActividad, PANEL_ACTIVIDAD);
		cards.add(panelTransporte, PANEL_TRANSPORTE);
		cards.add(panelAlojamiento, PANEL_ALOJAMIENTO);
		
		String opciones[] = {PANEL_ACTIVIDAD, PANEL_TRANSPORTE, PANEL_ALOJAMIENTO};
		comboBox = new JComboBox<>(opciones);
		comboBoxPanel.add(comboBox);
		comboBox.setEditable(false);
		comboBoxPanel.setOpaque(false);
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
			    CardLayout cl = (CardLayout)(cards.getLayout());
			    cl.show(cards, (String)e.getItem());
			}
			
		});
		
		//panel alojamiento
		panelAlojamiento.setLayout(new GridLayout(6, 2));
		textFieldNombre = new JTextField(20);
		textFieldNumPlazas = new JTextField(20);
		textFieldPrecio = new JTextField(20);
		textFieldRegimen = new JTextField(20);
		String opcionesEstrellas[] = {"*", "**", "***", "****", "*****"};
		convertirEstrellas = new HashMap<String, Integer>();
		convertirEstrellas.put("*", 1);
		convertirEstrellas.put("**", 2);
		convertirEstrellas.put("***", 3);
		convertirEstrellas.put("****", 4);
		convertirEstrellas.put("*****", 5);
		comboBoxEstrellas = new JComboBox<>(opcionesEstrellas);
		panelAlojamiento.add(new JLabel("nombre", JLabel.CENTER));
		panelAlojamiento.add(textFieldNombre);
		panelAlojamiento.add(new JLabel("nº plazas", JLabel.CENTER));
		panelAlojamiento.add(textFieldNumPlazas);
		panelAlojamiento.add(new JLabel("precio", JLabel.CENTER));
		panelAlojamiento.add(textFieldPrecio);
		panelAlojamiento.add(new JLabel("Régimen", JLabel.CENTER));
		panelAlojamiento.add(textFieldRegimen);
		panelAlojamiento.add(new JLabel("nº estrellas", JLabel.CENTER));
		panelAlojamiento.add(comboBoxEstrellas);
		
		//panel transporte
		panelTransporte.setLayout(new GridLayout(6, 2));
		textFieldNombre = new JTextField(20);
		textFieldNumPlazas = new JTextField(20);
		textFieldPrecio = new JTextField(20);
		textFieldTipoTransporte = new JTextField(20);
		convertirComida = new HashMap<String, Boolean>();
		convertirComida.put("SI", true);
		convertirComida.put("NO", false);
		String opcionesComboBoxComida[] = {"SI", "NO"};
		comboBoxComida = new JComboBox<String>(opcionesComboBoxComida);
		panelTransporte.add(new JLabel("nombre", JLabel.CENTER));
		panelTransporte.add(textFieldNombre);
		panelTransporte.add(new JLabel("nº plazas", JLabel.CENTER));
		panelTransporte.add(textFieldNumPlazas);
		panelTransporte.add(new JLabel("precio", JLabel.CENTER));
		panelTransporte.add(textFieldPrecio);
		panelTransporte.add(new JLabel("tipo de transporte", JLabel.CENTER));
		panelTransporte.add(textFieldTipoTransporte);
		panelTransporte.add(new JLabel("comida incluida", JLabel.CENTER));
		panelTransporte.add(comboBoxComida);
		
		
		//panel actividad
		panelActividad.setLayout(new GridLayout(6, 2));
		textFieldNombre = new JTextField(20);
		textFieldNumPlazas = new JTextField(20);
		textFieldPrecio = new JTextField(20);
		textFieldTipoActividad = new JTextField(20);
		convertirColectivo = new HashMap<String, Boolean>();
		convertirColectivo.put("SI", true);
		convertirColectivo.put("NO", false);
		String opcionesComboBoxColectivo[] = {"SI", "NO"};
		comboBoxColectivo = new JComboBox<String>(opcionesComboBoxColectivo);
		panelActividad.add(new JLabel("nombre", JLabel.CENTER));
		panelActividad.add(textFieldNombre);
		panelActividad.add(new JLabel("nº plazas", JLabel.CENTER));
		panelActividad.add(textFieldNumPlazas);
		panelActividad.add(new JLabel("precio", JLabel.CENTER));
		panelActividad.add(textFieldPrecio);
		panelActividad.add(new JLabel("tipo de actividad", JLabel.CENTER));
		panelActividad.add(textFieldTipoActividad);
		panelActividad.add(new JLabel("colectivo", JLabel.CENTER));
		panelActividad.add(comboBoxComida);
		
		
		buttonGuardar = new JButton("guardar");
		mainPanel.add(buttonGuardar, BorderLayout.PAGE_END);
		buttonGuardar.addActionListener(new AccionGuardar());

		setContentPane(mainPanel);
		setBackground(VistaServicios.COLOR_FONDO);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Eventos.RES_ALTA_SERVICIO_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "servicio anyadido con id " + datos, "servicio Anyadido", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_ALTA_SERVICIO_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo anyadir el cliente", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
		
	}
	
	class AccionGuardar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String panelSeleccionado = (String) comboBox.getSelectedItem();
			
			try {
			switch(panelSeleccionado) {
			case PANEL_ALOJAMIENTO:{
				String nombre = textFieldNombre.getText();
				int numPlazas = Integer.parseInt(textFieldNumPlazas.getText());
				int precio = Integer.parseInt(textFieldPrecio.getText());
				String regimen = textFieldRegimen.getText();
				int estrellas = convertirEstrellas.get((String) comboBoxEstrellas.getSelectedItem());
				setVisible(false);
				TAlojamiento transfer = new TAlojamiento(nombre, numPlazas, precio, regimen, estrellas);
				Controlador.getInstancia().accion(Eventos.ALTA_SERVICIO, transfer);
				break;
			}
			
			case PANEL_TRANSPORTE:{
				String nombre = textFieldNombre.getText();
				int numPlazas = Integer.parseInt(textFieldNumPlazas.getText());
				int precio = Integer.parseInt(textFieldPrecio.getText());
				String tipoTransporte = textFieldTipoTransporte.getText();
				boolean comida = convertirComida.get(comboBoxComida.getSelectedItem());
				setVisible(false);
				TTransporte transfer = new TTransporte(nombre, numPlazas, precio, tipoTransporte, comida);
				Controlador.getInstancia().accion(Eventos.ALTA_SERVICIO, transfer);
				break;
			}
			
			case PANEL_ACTIVIDAD:{
				String nombre = textFieldNombre.getText();
				int numPlazas = Integer.parseInt(textFieldNumPlazas.getText());
				int precio = Integer.parseInt(textFieldPrecio.getText());
				String tipoActividad = textFieldTipoActividad.getText();
				boolean colectivo = convertirColectivo.get(comboBoxColectivo.getSelectedItem());
				setVisible(false);
				TActividad transfer = new TActividad(nombre, numPlazas, precio, tipoActividad, colectivo);
				Controlador.getInstancia().accion(Eventos.ALTA_SERVICIO, transfer);
				break;
			}
			}
			}
			catch(Exception ex) {
				setVisible(false);
				JOptionPane.showMessageDialog(Utils.getWindow(VistaAnyadirServicio.this), "algun parametro es invalido", "Error", JOptionPane.ERROR_MESSAGE);
				setVisible(true);
			}
		}
		
	}
	
	

}
