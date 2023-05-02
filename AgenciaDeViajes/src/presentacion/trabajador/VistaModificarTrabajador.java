package presentacion.trabajador;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.trabajador.TJefe;
import negocio.trabajador.TTrabajador;
import negocio.trabajador.TVendedor;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;
import presentacion.servicio.VistaServicios;

public class VistaModificarTrabajador extends JFrame implements IGUI{
	JPanel cards;
	JPanel panelVendedor;
	JPanel panelJefe;
	JPanel mainPanel;
	JTextField nombreVendedor;
	JTextField sueldoVendedor;
	JTextField idDepartVendedor;
	JTextField idJefeVendedor;
	JTextField nombreJefe;
	JTextField sueldoJefe;
	JTextField idDepartJefe;
	JTextField tfNombreTrans;
	JTextField tfPrecioTrans;
	JTextField tfPlazasTrans;
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
	TfId tfId;
	JPanel idPanel;
	String panelActual;
	JLabel lTrabajadorNoEncontrado;
	
	
	private final String PANEL_VENDEDOR = "vendedor";
	private final String PANEL_JEFE = "jefe";
	
	public VistaModificarTrabajador() {
		super("MODIFICAR TRABAJADOR");
		initGUI();
	}
	
	private void initGUI() {
		
		cards = new JPanel(new CardLayout());
		idPanel = new JPanel();
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(idPanel, BorderLayout.NORTH);
		mainPanel.add(cards, BorderLayout.CENTER);
		idPanel.add(new JLabel("Introduce el ID: "));
		tfId = new TfId();
		idPanel.add(tfId);
		lTrabajadorNoEncontrado = new JLabel("Servicio no encontrado");
		lTrabajadorNoEncontrado.setForeground(Color.red);
		lTrabajadorNoEncontrado.setVisible(false);
		idPanel.add(lTrabajadorNoEncontrado);

		
		panelVendedor = new JPanel();
		panelJefe = new JPanel();
		
		cards.add(panelVendedor, PANEL_VENDEDOR);
		cards.add(panelJefe, PANEL_JEFE);
		
		String opciones[] = {PANEL_VENDEDOR, PANEL_JEFE};
		
		
		//panel vendedores
		panelVendedor.setLayout(new GridLayout(6, 2));
		nombreVendedor = new JTextField(20);
		sueldoVendedor = new JTextField(20);
		idDepartVendedor = new JTextField(20);
		idJefeVendedor = new JTextField(20);
		
		panelVendedor.add(new JLabel("nombre", JLabel.CENTER));
		panelVendedor.add(nombreVendedor);
		panelVendedor.add(new JLabel("sueldo", JLabel.CENTER));
		panelVendedor.add(sueldoVendedor);
		panelVendedor.add(new JLabel("id departamento", JLabel.CENTER));
		panelVendedor.add(idDepartVendedor);
		panelVendedor.add(new JLabel("id Jefe", JLabel.CENTER));
		panelVendedor.add(idJefeVendedor);
		
		//panel jefes
		panelJefe.setLayout(new GridLayout(6, 2));
		nombreJefe = new JTextField(20);
		sueldoJefe = new JTextField(20);
		idDepartJefe = new JTextField(20);
	
		panelJefe.add(new JLabel("nombre", JLabel.CENTER));
		panelJefe.add(nombreJefe);
		panelJefe.add(new JLabel("sueldo", JLabel.CENTER));
		panelJefe.add(sueldoJefe);
		panelJefe.add(new JLabel("id departamento", JLabel.CENTER));
		panelJefe.add(idDepartJefe);
		
		
		buttonGuardar = new JButton("guardar");
		mainPanel.add(buttonGuardar, BorderLayout.PAGE_END);
		buttonGuardar.addActionListener(new AccionGuardar());

		setContentPane(mainPanel);
		setBackground(VistaServicios.COLOR_FONDO);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	class AccionGuardar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
			switch(panelActual) {
			case PANEL_VENDEDOR:{
				String nombre = nombreVendedor.getText();
				int sueldo = Integer.parseInt(sueldoVendedor.getText());
				int idDepart = Integer.parseInt(idDepartVendedor.getText());
				int idJefe = Integer.parseInt(idJefeVendedor.getText());
				
				setVisible(false);
				TVendedor transfer = new TVendedor(nombre, sueldo, idDepart, idJefe);
				transfer.setId(Integer.parseInt(tfId.getText()));
				Controlador.getInstancia().accion(Eventos.MODIFICAR_TRABAJADOR, transfer);
				break;
			}
			
			case PANEL_JEFE:{
				String nombre = nombreJefe.getText();
				int sueldo = Integer.parseInt(sueldoJefe.getText());
				int idDepart = Integer.parseInt(idDepartJefe.getText());
				
				setVisible(false);
				TJefe transfer = new TJefe(nombre, sueldo, idDepart);
				transfer.setId(Integer.parseInt(tfId.getText()));
				Controlador.getInstancia().accion(Eventos.MODIFICAR_TRABAJADOR, transfer);
				break;
			}
			}
			}
			catch(Exception ex) {
				setVisible(false);
				JOptionPane.showMessageDialog(Utils.getWindow(VistaModificarTrabajador.this), "algun parametro es invalido", "Error", JOptionPane.ERROR_MESSAGE);
				setVisible(true);
			}
		}
		
	}
	
	
	class TfId extends JTextField{
		
		TfId(){
			super(10);
			getDocument().addDocumentListener(new DocumentListener() {
				  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
					 try {
					 int id = Integer.parseInt(getText());
					 TTrabajador t = FactoriaAbstractaNegocio.getInstancia().crearSATrabajador().readTrabajador(id);
				     
					 if (t == null){
				       setBorder(BorderFactory.createLineBorder(Color.red));
				       lTrabajadorNoEncontrado.setVisible(true);
					 }
				     else {
				    	 setBorder(BorderFactory.createLineBorder(Color.green));
				    	 lTrabajadorNoEncontrado.setVisible(false);
				    	 if(t instanceof TVendedor) {
							 CardLayout cl = (CardLayout)(cards.getLayout());
							 cl.show(cards, PANEL_VENDEDOR);
							 panelActual = PANEL_VENDEDOR;
							 nombreVendedor.setText(t.getNombre());
							 sueldoVendedor.setText(Integer.toString(t.getSueldo()));
							 idDepartVendedor.setText(Integer.toString(t.getIdDepart()));
							 idJefeVendedor.setText(Integer.toString(((TVendedor) t).getIdJefe()));
							 
				    	 }
				    	 else if(t instanceof TJefe) {
							 CardLayout cl = (CardLayout)(cards.getLayout());
							 cl.show(cards, PANEL_JEFE);
							 panelActual = PANEL_JEFE;
							 nombreJefe.setText(t.getNombre());
							 sueldoJefe.setText(Integer.toString(t.getSueldo()));
							 idDepartJefe.setText(Integer.toString(t.getIdDepart()));
				    	 }
				     }
					 }
					 catch(Exception ex) {
						 
					 }
				  }
				});
			
		}
		
	}
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Eventos.RES_MODIFICAR_TRABAJADOR_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Trabajador modificado correctamente", "Trabajador modificado", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_MODIFICAR_TRABAJADOR_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "El trabajador no existe", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}


}
