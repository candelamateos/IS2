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

public class VistaModificarViaje extends JFrame implements IGUI{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lId;
	private JTextField tId;
	private JLabel lNumPlazas;
	private JTextField tNumPlazas;
	private JLabel lIdActividad;
	private JTextField tIdActividad;
	private JLabel lIdAlojamiento;
	private JTextField tIdAlojamiento;
	private JLabel lIdTransporte;
	private JTextField tIdTransporte;
	private JButton ok;
	
	public VistaModificarViaje() {
		super("Modificar Viaje");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setContentPane(mainPanel);
		
		JPanel fila0 = new JPanel();
		fila0.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila0);
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
		
		lId= new JLabel("Id del viaje:");
		lId.setPreferredSize(new Dimension(100, 25));
		tId = new JTextField(10);
		fila0.add(lId);
		fila0.add(tId);
		
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
		mainPanel.add(ok);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int Iid;
					int InumPlazas;
					int IidActividad;
					int IidAlojamiento;
					int IidTransporte;	
					try{
						Iid = Integer.parseInt(tId.getText());
					}catch(NumberFormatException ex) {
						throw new IllegalArgumentException("El id del viaje debe ser un numero", ex);
					}
					
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
					TViaje viaje = new TViaje(InumPlazas, IidActividad, IidAlojamiento, IidTransporte);
					viaje.setId(Iid);
					Controlador.getInstancia().accion(Eventos.MODIFICAR_VIAJE, viaje);
				}catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaModificarViaje.this), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
		case(Eventos.RES_MODIFICAR_VIAJE_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Viaje modificado con id " + datos, "Viaje modificado", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_MODIFICAR_VIAJE_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo modificar el viaje", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}

}
