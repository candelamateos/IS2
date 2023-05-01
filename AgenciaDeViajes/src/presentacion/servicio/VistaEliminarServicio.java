package presentacion.servicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaEliminarServicio extends JFrame implements IGUI {
	JLabel lId;
	JTextField tId;
	JButton ok;
	public VistaEliminarServicio() {
		super("ELIMINAR SERVICIO");
		initGUI();
	}
	
	void initGUI() {
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
				Controlador.getInstancia().accion(Eventos.BAJA_SERVICIO, Iid);
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
		case(Eventos.RES_BAJA_SERVICIO_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "servicio eliminado ", "Servicio Eliminado", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_BAJA_SERVICIO_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo eliminar el servicio", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}
}
