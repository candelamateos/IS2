package presentacion.servicio;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
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
import negocio.servicio.TActividad;
import negocio.servicio.TAlojamiento;
import negocio.servicio.TServicio;
import negocio.servicio.TTransporte;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;
import presentacion.factoria.FactoriaAbstractaPresentacion;
import presentacion.servicio.VistaAnyadirServicio.AccionGuardar;

public class VistaModificarServicio extends JFrame implements IGUI{
	JPanel cards;
	JPanel panelActividad;
	JPanel panelTransporte;
	JPanel panelAlojamiento;
	JPanel mainPanel;
	JTextField tfNombreAlo;
	JTextField tfPrecioAlo;
	JTextField tfPlazasAlo;
	JTextField tfNombreAct;
	JTextField tfPrecioAct;
	JTextField tfPlazasAct;
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
	JLabel lServicioNoEncontrado;
	
	
	private final String PANEL_ACTIVIDAD = "actividad";
	private final String PANEL_TRANSPORTE = "transporte";
	private final String PANEL_ALOJAMIENTO = "alojamiento";


	public VistaModificarServicio() {
		super("Modificar servicio");
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
		lServicioNoEncontrado = new JLabel("Servicio no encontrado");
		lServicioNoEncontrado.setForeground(Color.red);
		lServicioNoEncontrado.setVisible(false);
		idPanel.add(lServicioNoEncontrado);

		
		panelActividad = new JPanel();
		panelTransporte = new JPanel();
		panelAlojamiento = new JPanel();
		cards.add(panelActividad, PANEL_ACTIVIDAD);
		cards.add(panelTransporte, PANEL_TRANSPORTE);
		cards.add(panelAlojamiento, PANEL_ALOJAMIENTO);
		
		String opciones[] = {PANEL_ACTIVIDAD, PANEL_TRANSPORTE, PANEL_ALOJAMIENTO};
		
		
		//panel alojamiento
		panelAlojamiento.setLayout(new GridLayout(6, 2));
		tfNombreAlo = new JTextField(20);
		tfPlazasAlo = new JTextField(20);
		tfPrecioAlo = new JTextField(20);
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
		panelAlojamiento.add(tfNombreAlo);
		panelAlojamiento.add(new JLabel("nº plazas", JLabel.CENTER));
		panelAlojamiento.add(tfPlazasAlo);
		panelAlojamiento.add(new JLabel("precio", JLabel.CENTER));
		panelAlojamiento.add(tfPrecioAlo);
		panelAlojamiento.add(new JLabel("Régimen", JLabel.CENTER));
		panelAlojamiento.add(textFieldRegimen);
		panelAlojamiento.add(new JLabel("nº estrellas", JLabel.CENTER));
		panelAlojamiento.add(comboBoxEstrellas);
		
		//panel transporte
		panelTransporte.setLayout(new GridLayout(6, 2));
		tfNombreTrans = new JTextField(20);
		tfPlazasTrans = new JTextField(20);
		tfPrecioTrans = new JTextField(20);
		textFieldTipoTransporte = new JTextField(20);
		convertirComida = new HashMap<String, Boolean>();
		convertirComida.put("SI", true);
		convertirComida.put("NO", false);
		String opcionesComboBoxComida[] = {"SI", "NO"};
		comboBoxComida = new JComboBox<String>(opcionesComboBoxComida);
		panelTransporte.add(new JLabel("nombre", JLabel.CENTER));
		panelTransporte.add(tfNombreTrans);
		panelTransporte.add(new JLabel("nº plazas", JLabel.CENTER));
		panelTransporte.add(tfPlazasTrans);
		panelTransporte.add(new JLabel("precio", JLabel.CENTER));
		panelTransporte.add(tfPrecioTrans);
		panelTransporte.add(new JLabel("tipo de transporte", JLabel.CENTER));
		panelTransporte.add(textFieldTipoTransporte);
		panelTransporte.add(new JLabel("comida incluida", JLabel.CENTER));
		panelTransporte.add(comboBoxComida);
		
		
		//panel actividad
		panelActividad.setLayout(new GridLayout(6, 2));
		tfNombreAct = new JTextField(20);
		tfPlazasAct = new JTextField(20);
		tfPrecioAct = new JTextField(20);
		textFieldTipoActividad = new JTextField(20);
		convertirColectivo = new HashMap<String, Boolean>();
		convertirColectivo.put("SI", true);
		convertirColectivo.put("NO", false);
		String opcionesComboBoxColectivo[] = {"SI", "NO"};
		comboBoxColectivo = new JComboBox<String>(opcionesComboBoxColectivo);
		panelActividad.add(new JLabel("nombre", JLabel.CENTER));
		panelActividad.add(tfNombreAct);
		panelActividad.add(new JLabel("nº plazas", JLabel.CENTER));
		panelActividad.add(tfPlazasAct);
		panelActividad.add(new JLabel("precio", JLabel.CENTER));
		panelActividad.add(tfPrecioAct);
		panelActividad.add(new JLabel("tipo de actividad", JLabel.CENTER));
		panelActividad.add(textFieldTipoActividad);
		panelActividad.add(new JLabel("colectivo", JLabel.CENTER));
		panelActividad.add(comboBoxColectivo);
		
		
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
		case(Eventos.RES_MODIFICAR_SERVICIO_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Servicio modificado correctamente", "Servicio modificado", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_MODIFICAR_SERVICIO_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "El Servicio no existe", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}
	
	
	class AccionGuardar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
			switch(panelActual) {
			case PANEL_ALOJAMIENTO:{
				String nombre = tfNombreAlo.getText();
				int numPlazas = Integer.parseInt(tfPlazasAlo.getText());
				int precio = Integer.parseInt(tfPrecioAlo.getText());
				String regimen = textFieldRegimen.getText();
				int estrellas = convertirEstrellas.get((String) comboBoxEstrellas.getSelectedItem());
				setVisible(false);
				TAlojamiento transfer = new TAlojamiento(nombre, numPlazas, precio, regimen, estrellas);
				transfer.setId(Integer.parseInt(tfId.getText()));
				Controlador.getInstancia().accion(Eventos.MODIFICAR_SERVICIO, transfer);
				break;
			}
			
			case PANEL_TRANSPORTE:{
				String nombre = tfNombreTrans.getText();
				int numPlazas = Integer.parseInt(tfPlazasTrans.getText());
				int precio = Integer.parseInt(tfPrecioTrans.getText());
				String tipoTransporte = textFieldTipoTransporte.getText();
				boolean comida = convertirComida.get(comboBoxComida.getSelectedItem());
				setVisible(false);
				TTransporte transfer = new TTransporte(nombre, numPlazas, precio, tipoTransporte, comida);
				transfer.setId(Integer.parseInt(tfId.getText()));
				Controlador.getInstancia().accion(Eventos.MODIFICAR_SERVICIO, transfer);
				break;
			}
			
			case PANEL_ACTIVIDAD:{
				String nombre = tfNombreAct.getText();
				int numPlazas = Integer.parseInt(tfPlazasAct.getText());
				int precio = Integer.parseInt(tfPrecioAct.getText());
				String tipoActividad = textFieldTipoActividad.getText();
				boolean colectivo = convertirColectivo.get(comboBoxColectivo.getSelectedItem());
				setVisible(false);
				TActividad transfer = new TActividad(nombre, numPlazas, precio, tipoActividad, colectivo);
				transfer.setId(Integer.parseInt(tfId.getText()));
				Controlador.getInstancia().accion(Eventos.MODIFICAR_SERVICIO, transfer);
				break;
			}
			}
			}
			catch(Exception ex) {
				setVisible(false);
				JOptionPane.showMessageDialog(Utils.getWindow(VistaModificarServicio.this), "algun parametro es invalido", "Error", JOptionPane.ERROR_MESSAGE);
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
					 TServicio t = FactoriaAbstractaNegocio.getInstancia().crearSAServicio().readServicio(id);
				     
					 if (t == null){
				       setBorder(BorderFactory.createLineBorder(Color.red));
				       lServicioNoEncontrado.setVisible(true);
					 }
				     else {
				    	 setBorder(BorderFactory.createLineBorder(Color.green));
				    	 lServicioNoEncontrado.setVisible(false);
				    	 if(t instanceof TTransporte) {
							 CardLayout cl = (CardLayout)(cards.getLayout());
							 cl.show(cards, PANEL_TRANSPORTE);
							 panelActual = PANEL_TRANSPORTE;
							 tfNombreTrans.setText(t.getNombre());
							 tfPlazasTrans.setText(Integer.toString(t.getNumPlazas()));
							 tfPrecioTrans.setText(Integer.toString(t.getPrecio()));
							 textFieldTipoTransporte.setText(((TTransporte) t).getTipoTransporte());
							 boolean bComida = ((TTransporte) t).isComida();
							 comboBoxComida.setSelectedIndex(bComida? 0 : 1);	 
							 
				    	 }
				    	 else if(t instanceof TActividad) {
							 CardLayout cl = (CardLayout)(cards.getLayout());
							 cl.show(cards, PANEL_ACTIVIDAD);
							 panelActual = PANEL_ACTIVIDAD;
							 tfNombreAct.setText(t.getNombre());
							 tfPlazasAct.setText(Integer.toString(t.getNumPlazas()));
							 tfPrecioAct.setText(Integer.toString(t.getPrecio()));
							 textFieldTipoActividad.setText(((TActividad) t).getTipoActividad());
							 boolean bColectivo = ((TActividad) t).isColectivo();
							 comboBoxColectivo.setSelectedIndex(bColectivo? 0 : 1);	 
				    	 }
				    	 else if(t instanceof TAlojamiento) {
				    		 CardLayout cl = (CardLayout)(cards.getLayout());
							 cl.show(cards, PANEL_ALOJAMIENTO);
							 panelActual = PANEL_ALOJAMIENTO;
							 tfNombreAlo.setText(t.getNombre());
							 tfPlazasAlo.setText(Integer.toString(t.getNumPlazas()));
							 tfPrecioAlo.setText(Integer.toString(t.getPrecio()));
							 comboBoxEstrellas.setSelectedIndex(((TAlojamiento) t).getEstrellas() - 1);
							 textFieldRegimen.setText(((TAlojamiento) t).getRegimen());
				    	 }
				     }
					 }
					 catch(Exception ex) {
						 
					 }
				  }
				});
			
		}
		
	}

}
