package presentacion.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.cliente.TCliente;
import negocio.departamento.TDepartamento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaAniadirCliente extends JFrame implements IGUI{
	private JLabel lId;
	private JTextField tId;
	private JLabel lNombre;
	private JTextField tNombre;
	private JButton ok;
	
	public VistaAniadirCliente() {
		setTitle("ANIADIR CLIENTE");
		JPanel panel = new JPanel();
		lId= new JLabel("ID:");
		tId = new JTextField(5);
		panel.add(lId);
		panel.add(tId);
		lNombre= new JLabel("NOMBRE:");
		tNombre = new JTextField(5);
		panel.add(lNombre);
		panel.add(tNombre);
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				String id = tId.getText();
				int Iid = Integer.parseInt(id);
				String nombre = tNombre.getText();
				Controlador.getInstancia().accion(Eventos.ALTA_CLIENTE, new TCliente(Iid, nombre));
			}
		});
	}

	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		
	}
	
}
