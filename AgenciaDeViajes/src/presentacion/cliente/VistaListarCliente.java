package presentacion.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.IGUI;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaListarCliente extends JFrame implements IGUI {

	JLabel lId;
	JTextField tId;
	JButton ok;
	public VistaListarCliente() {
		setTitle("LISTAR CLIENTE");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(Eventos.LISTAR_CLIENTE, null);
			}
		});
	}

	
	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		
	} 

	
	
}
