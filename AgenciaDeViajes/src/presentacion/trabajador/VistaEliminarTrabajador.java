package presentacion.trabajador;

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

public class VistaEliminarTrabajador extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	JLabel lId;
	JTextField tId;
	JButton ok;

	public VistaEliminarTrabajador() {
		super("ELIMINAR TRABAJADOR");
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
				Controlador.getInstancia().accion(Eventos.BAJA_TRABAJADOR, Iid);
			}
		});
		panel.add(ok);
		setContentPane(panel);

		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case (Eventos.RES_BAJA_TRABAJADOR_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "trabajador eliminado ", "Trabajador Eliminado",
					JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case (Eventos.RES_BAJA_TRABAJADOR_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo eliminar al trabajador", "Error",
					JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}

	}

}
