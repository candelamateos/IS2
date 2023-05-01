package presentacion.departamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.departamento.TDepartamento;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaBuscarDepartamento extends JFrame implements IGUI {
	private JLabel lId;
	private JTextField tId;
	private JButton ok;

	public VistaBuscarDepartamento() {
		super("BUSCAR VIAJE");
		initGUI();
	}

	private void initGUI() {
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
				Controlador.getInstancia().accion(Eventos.BUSCAR_DEPARTAMENTO, Iid);
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
		case (Eventos.RES_BUSCAR_DEPARTAMENTO_OK):
			setVisible(false);
			TDepartamento departamento = (TDepartamento) datos;
			StringBuilder str = new StringBuilder();
			str.append("Departamento encontrado con datos: ").append(System.lineSeparator());
			str.append("Id: " + departamento.getId()).append(System.lineSeparator());
			str.append("Nombre: " + departamento.getNombre()).append(System.lineSeparator());
			str.append("Numero de empleados: " + departamento.getNumEmpleados()).append(System.lineSeparator());
			JOptionPane.showMessageDialog(Utils.getWindow(this), str, "Departamento encontrado",
					JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case (Eventos.RES_BUSCAR_DEPARTAMENTO_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo encontrar el departamento", "Error",
					JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}

}
