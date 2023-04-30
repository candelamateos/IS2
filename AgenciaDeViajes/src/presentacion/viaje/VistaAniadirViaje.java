package presentacion.viaje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class VistaAniadirViaje extends JFrame implements IGUI{
	private JLabel lPrecio;
	private JTextField tPrecio;
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
		JPanel panel = new JPanel();
		
		lPrecio= new JLabel("PRECIO:");
		tPrecio = new JTextField(5);
		panel.add(lPrecio);
		panel.add(tPrecio);
		
		lNumPlazas= new JLabel("NUMERO DE PLAZAS:");
		tNumPlazas = new JTextField(5);
		panel.add(lNumPlazas);
		panel.add(tNumPlazas);
		
		lIdActividad= new JLabel("ID DE LA ACTIVIDAD:");
		tIdActividad = new JTextField(5);
		panel.add(lIdActividad);
		panel.add(tIdActividad);
		
		lIdAlojamiento= new JLabel("ID DEL ALOJAMIENTO:");
		tIdAlojamiento = new JTextField(5);
		panel.add(lIdAlojamiento);
		panel.add(tIdAlojamiento);
		
		lIdTransporte= new JLabel("ID DEL TRANSPORTE:");
		tIdTransporte = new JTextField(5);
		panel.add(lIdTransporte);
		panel.add(tIdTransporte);
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				String precio = tPrecio.getText();
				int Iprecio = Integer.parseInt(precio);
				String numPlazas = tNumPlazas.getText();
				int InumPlazas = Integer.parseInt(numPlazas);
				Controlador.getInstancia().accion(Eventos.ALTA_VIAJE, new TViaje(Iprecio, InumPlazas));
			}
		});
		panel.add(ok);
		setContentPane(panel);
		
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