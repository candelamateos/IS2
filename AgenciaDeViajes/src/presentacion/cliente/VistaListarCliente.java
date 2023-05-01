package presentacion.cliente;

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

import negocio.cliente.TCliente;
import negocio.viaje.TViaje;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaListarCliente extends JFrame implements IGUI {

	private JButton ok;
	
	public VistaListarCliente() {
		super("LISTAR CLIENTE");
		initGUI();
	}
	
	void initGUI() {
		JPanel panel = new JPanel();
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(Eventos.LISTAR_CLIENTE, null);
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
		case(Eventos.RES_LISTAR_CLIENTE_OK):
			setVisible(false);
			StringBuilder str = new StringBuilder();
			str.append("Lista de Clientes").append(System.lineSeparator());
			List<TCliente> lista = (ArrayList<TCliente>) datos;
			for(TCliente cliente : lista) {
				str.append("Cliente con nombre: " + cliente.getNombre() + ", con id: " + cliente.getId()).append(System.lineSeparator());
			}
			JOptionPane.showMessageDialog(Utils.getWindow(this), str, "Lista de Clientes mostrada", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_LISTAR_VIAJE_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudieron listar los clientes", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	} 

	
	
}
