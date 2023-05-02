package presentacion.trabajador;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
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
import negocio.trabajador.TJefe;
import negocio.trabajador.TTrabajador;
import negocio.trabajador.TVendedor;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaAniadirTrabajador extends JFrame implements IGUI{

	JPanel cards;
	JPanel panelVendedor;
	JPanel panelJefe;
	JPanel mainPanel;
	JPanel comboBoxPanel;
	JComboBox<String> comboBox;
	
	JTextField nombreVendedor;
	JTextField sueldoVendedor;
	JTextField idDepartVendedor;
	JTextField idJefeVendedor;
	
	JTextField nombreJefe;
	JTextField sueldoJefe;
	JTextField idDepartJefe;
	
	JButton buttonGuardar;
	
	JTextField textFieldTipoActividad;
	
	
	private final String PANEL_VENDEDOR = "vendedor";
	private final String PANEL_JEFE = "jefe";


	public VistaAniadirTrabajador() {
		super("AÑADIR TRABAJADOR");
		initGUI();
	}
	
	private void initGUI() {
		
		cards = new JPanel(new CardLayout());
		comboBoxPanel = new JPanel();
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(comboBoxPanel, BorderLayout.NORTH);
		mainPanel.add(cards, BorderLayout.CENTER);
		
		panelVendedor = new JPanel();
		panelJefe = new JPanel();
		
		cards.add(panelVendedor, PANEL_VENDEDOR);
		cards.add(panelVendedor, PANEL_JEFE);
		
		String opciones[] = {PANEL_VENDEDOR, PANEL_JEFE};
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
		
		//panel vendedor
		panelVendedor.setLayout(new GridLayout(6, 2));
		nombreVendedor = new JTextField(20);
		sueldoVendedor = new JTextField(20);
		idDepartVendedor = new JTextField(20);
		idJefeVendedor = new JTextField(20);
		
		panelVendedor.add(new JLabel("nombre", JLabel.CENTER));
		panelVendedor.add(nombreVendedor);
		panelVendedor.add(new JLabel("sueldo", JLabel.CENTER));
		panelVendedor.add(sueldoVendedor);
		panelVendedor.add(new JLabel("idDepart", JLabel.CENTER));
		panelVendedor.add(idDepartVendedor);
		panelVendedor.add(new JLabel("idJefe", JLabel.CENTER));
		panelVendedor.add(idJefeVendedor);
		
		//panel jefe
		panelJefe.setLayout(new GridLayout(6, 2));
		nombreJefe = new JTextField(20);
		sueldoJefe = new JTextField(20);
		idDepartJefe = new JTextField(20);
		panelJefe.add(new JLabel("nombre", JLabel.CENTER));
		panelJefe.add(nombreJefe);
		panelJefe.add(new JLabel("sueldo", JLabel.CENTER));
		panelJefe.add(sueldoJefe);
		panelJefe.add(new JLabel("idDepart", JLabel.CENTER));
		panelJefe.add(idDepartJefe);

		
		buttonGuardar = new JButton("guardar");
		mainPanel.add(buttonGuardar, BorderLayout.PAGE_END);
		buttonGuardar.addActionListener(new AccionGuardar());

		setContentPane(mainPanel);
		setBackground(VistaTrabajadores.COLOR_FONDO);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	class AccionGuardar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String panelSeleccionado = (String) comboBox.getSelectedItem();
			
			
			try {
			switch(panelSeleccionado) {
			case PANEL_VENDEDOR:{
				String nombre = nombreVendedor.getText();
				int sueldo = Integer.parseInt(sueldoVendedor.getText());
				int idDepart = Integer.parseInt(idDepartVendedor.getText());
				int idJefe = Integer.parseInt(idJefeVendedor.getText());
				
				setVisible(false);
				TVendedor transfer = new TVendedor(nombre, sueldo, idDepart, idJefe);
				Controlador.getInstancia().accion(Eventos.ALTA_TRABAJADOR, transfer);
				break;
			}
			
			case PANEL_JEFE:{
				String nombre = nombreJefe.getText();
				int sueldo = Integer.parseInt(sueldoJefe.getText());
				int idDepart = Integer.parseInt(idDepartJefe.getText());

				setVisible(false);
				TJefe transfer = new TJefe(nombre, sueldo, idDepart);
				Controlador.getInstancia().accion(Eventos.ALTA_TRABAJADOR, transfer);
				break;
			}
			
			}
			}
			catch(Exception ex) {
				setVisible(false);
				JOptionPane.showMessageDialog(Utils.getWindow(VistaAniadirTrabajador.this), "algun parametro es invalido", "Error", JOptionPane.ERROR_MESSAGE);
				setVisible(true);
			}
		}
		
	}
	

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Eventos.RES_ALTA_TRABAJADOR_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "trabajador anyadido con id " + datos, "trabajador Anyadido", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_ALTA_TRABAJADOR_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo anyadir el trabajador", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
		
		
	}
	
}
