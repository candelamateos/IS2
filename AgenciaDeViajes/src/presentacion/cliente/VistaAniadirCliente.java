package presentacion.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.cliente.TCliente;
import negocio.departamento.TDepartamento;
import presentacion.IGUI;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;
import presentacion.Utils;

public class VistaAniadirCliente extends JFrame implements IGUI{
	private JLabel lId;
	private JTextField tId;
	private JLabel lNombre;
	private JTextField tNombre;
	private JButton ok;
	
	public VistaAniadirCliente() {
		super("Anyadir Cliente");
		initGUI();
	}
	
	void initGUI() {
		JPanel panel = new JPanel();
		lNombre= new JLabel("Nombre:");
		tNombre = new JTextField(5);
		panel.add(lNombre);
		panel.add(tNombre);
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				String nombre = tNombre.getText();
				Controlador.getInstancia().accion(Eventos.ALTA_CLIENTE, new TCliente(nombre));
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
		case(Eventos.RES_ALTA_CLIENTE_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Cliente anyadido con id " + datos, "Cliente Anyadido", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_ALTA_CLIENTE_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo anyadir el cliente", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
		
	}
	
}
