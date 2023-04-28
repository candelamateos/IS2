package presentacion.viaje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.IGUI;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaListarViaje extends JFrame implements IGUI{

	private JButton ok;
	
	public VistaListarViaje() {
		setTitle("LISTAR VIAJE");
		JPanel panel = new JPanel();
		
		ok = new JButton("OK");
		panel.add(ok);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(Eventos.LISTAR_VIAJE, null);
			}
		});
	}
	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		
	}

}
