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

public class VistaBuscarCliente extends JFrame implements IGUI {

	private JLabel lId;
	private JTextField tId;
	private JButton ok;
	
	public VistaBuscarCliente() {
		setTitle("BUSCAR CLIENTE");
		JPanel panel = new JPanel();
		lId = new JLabel("ID:");
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
				Controlador.getInstancia().accion(Eventos.BUSCAR_CLIENTE, Iid);
			}
		});
	}
	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		
	}

}
