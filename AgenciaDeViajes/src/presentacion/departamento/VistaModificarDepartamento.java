package presentacion.departamento;

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

public class VistaModificarDepartamento extends JFrame implements IGUI {

	private JLabel lId;
	private JTextField tId;
	private JButton ok;

	public VistaModificarDepartamento() {
		super("MODIFICAR DEPARTAMENTO");
		initGUI();
	}

	private void initGUI() {
		JPanel panel = new JPanel();

		lId = new JLabel("ID:");
		tId = new JTextField(5);
		panel.add(lId);
		panel.add(tId);

		ok = new JButton("OK");
		panel.add(ok);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				String id = tId.getText();
				int Iid = Integer.parseInt(id);
				Controlador.getInstancia().accion(Eventos.MODIFICAR_DEPARTAMENTO, Iid);
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
		switch (evento) {
		case (Eventos.RES_MODIFICAR_DEPARTAMENTO_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Departamento modificado con id " + datos,
					"Departamento modificado", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case (Eventos.RES_MODIFICAR_DEPARTAMENTO_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo modificar el departamento", "Error",
					JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}

}
