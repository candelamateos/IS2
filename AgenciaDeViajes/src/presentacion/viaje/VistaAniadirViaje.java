package presentacion.viaje;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.viaje.TViaje;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;
import presentacion.factura.VistaAbrirVenta;

public class VistaAniadirViaje extends JFrame implements IGUI{
	
	private JLabel lNumPlazas;
	private JTextField tNumPlazas;
	private JLabel lIdActividad;
	private JTextField tIdActividad;
	private JLabel lIdAlojamiento;
	private JTextField tIdAlojamiento;
	private JLabel lIdTransporte;
	private JTextField tIdTransporte;
	private JButton ok;

	public VistaAniadirViaje() {
		super("AÑADIR VIAJE");
		initGUI();	
	}
	
	void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setContentPane(mainPanel);
		
		JPanel fila1 = new JPanel();
		fila1.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila1);
		JPanel fila2 = new JPanel();
		fila2.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila2);
		JPanel fila3 = new JPanel();
		fila3.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila3);
		JPanel fila4 = new JPanel();
		fila4.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila4);
		JPanel fila5 = new JPanel();
		fila5.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila5);
		
		lNumPlazas= new JLabel("Numero de plazas:");
		lNumPlazas.setPreferredSize(new Dimension(100, 25));
		tNumPlazas = new JTextField(10);
		fila1.add(lNumPlazas);
		fila1.add(tNumPlazas);
		
		lIdActividad= new JLabel("Id de la actividad:");
		lIdActividad.setPreferredSize(new Dimension(100, 25));
		tIdActividad = new JTextField(10);
		fila2.add(lIdActividad);
		fila2.add(tIdActividad);
		
		lIdAlojamiento= new JLabel("Id del alojamiento:");
		lIdActividad.setPreferredSize(new Dimension(100, 25));
		tIdAlojamiento = new JTextField(10);
		fila3.add(lIdAlojamiento);
		fila3.add(tIdAlojamiento);
		
		lIdTransporte= new JLabel("Id del transporte:");
		lIdTransporte.setPreferredSize(new Dimension(100, 25));
		tIdTransporte = new JTextField(10);
		fila4.add(lIdTransporte);
		fila4.add(tIdTransporte);
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int InumPlazas;
					int IidActividad;
					int IidAlojamiento;
					int IidTransporte;	
					try{
						InumPlazas = Integer.parseInt(tNumPlazas.getText());
					}catch(NumberFormatException ex) {
						throw new IllegalArgumentException("El numero de plazas debe ser un entero", ex);
					}
					
					try {
						IidActividad = Integer.parseInt(tIdActividad.getText());
					}catch(NumberFormatException ex) {
						throw new IllegalArgumentException("El id de la actividad debe ser un numero", ex);
					}
					
					try {
						IidAlojamiento = Integer.parseInt(tIdAlojamiento.getText());
					}catch(NumberFormatException ex) {
						throw new IllegalArgumentException("El id del alojamiento debe ser un numero", ex);
					}
					
					try {
						IidTransporte = Integer.parseInt(tIdTransporte.getText());
					}catch(NumberFormatException ex) {
						throw new IllegalArgumentException("El id del transporte debe ser un numero", ex);
					}
					
					Controlador.getInstancia().accion(Eventos.ALTA_VIAJE, new TViaje(InumPlazas, IidActividad, IidAlojamiento, IidTransporte));
				}catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaAniadirViaje.this), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
				
			}
		});
		fila5.add(ok);
		
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Eventos.RES_ALTA_VIAJE_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Viaje a�adido con id " + datos, "Viaje A�adido", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_ALTA_VIAJE_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo a�adir el viaje", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}
	
}