package presentacion.viaje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaListarViaje extends JFrame implements IGUI{

	private JButton ok;
	
	public VistaListarViaje() {
		setTitle("LISTAR VIAJE");
		
	}
	
	private void initGUI() {
		JPanel panel = new JPanel();
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(Eventos.LISTAR_VIAJE, null);
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
		case(Eventos.RES_LISTAR_VIAJE_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Lista de Viajes", "Lista de Viajes mostrada", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_LISTAR_VIAJE_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudieron listar los viajes", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}

}
