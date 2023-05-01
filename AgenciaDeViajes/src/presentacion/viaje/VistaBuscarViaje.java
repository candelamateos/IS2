package presentacion.viaje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

public class VistaBuscarViaje extends JFrame implements IGUI{
	
	private JLabel lId;
	private JTextField tId;
	private JButton ok;
	
	public VistaBuscarViaje() {
		super("BUSCAR VIAJE");
		initGUI();
	}
	
	private void initGUI() {
		JPanel panel = new JPanel();
		
		lId= new JLabel("ID:");
		tId = new JTextField(5);
		panel.add(lId);
		panel.add(tId);
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				String id = tId.getText();
				int Iid = Integer.parseInt(id);
				Controlador.getInstancia().accion(Eventos.BUSCAR_VIAJE, Iid);
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
		case(Eventos.RES_BUSCAR_VIAJE_OK):
			TViaje viaje = (TViaje) datos;
			setVisible(false);
			StringBuilder str = new StringBuilder();
			str.append("Viaje encontrado con datos: ").append(System.lineSeparator());
			str.append("Id: " + viaje.getId()).append(System.lineSeparator());
			str.append("Precio: " + viaje.getPrecio()).append(System.lineSeparator());
			str.append("Numero de plazas: " + viaje.getNumPlazas()).append(System.lineSeparator());
			str.append("IdActividad: " + viaje.getIdActividad()).append(System.lineSeparator());
			str.append("IdAlojamiento: " + viaje.getIdAlojamiento()).append(System.lineSeparator());
			str.append("IdTransporte: " + viaje.getIdTransporte()).append(System.lineSeparator());
			JOptionPane.showMessageDialog(Utils.getWindow(this), str , "Viaje encontrado", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_BUSCAR_VIAJE_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo encontrar el viaje", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}

}
