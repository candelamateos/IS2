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
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.servicio.TAlojamiento;
import presentacion.IGUI;
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
		
		
		//panel actividad
		
		
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
		// TODO Auto-generated method stub
		
	}
	
	class AccionGuardar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String panelSeleccionado = (String) comboBox.getSelectedItem();
			
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
			}
			
		}
		
	}
	
	

}
