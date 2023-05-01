package presentacion.departamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import negocio.departamento.TDepartamento;
import negocio.viaje.TViaje;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaListarDepartamento extends JFrame implements IGUI {

	private JButton ok;

	public VistaListarDepartamento() {
		setTitle("LISTAR DEPARTAMENTO");
		initGUI();
	}

	private void initGUI() {
		JPanel panel = new JPanel();

		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(Eventos.LISTAR_DEPARTAMENTO, null);
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
		case (Eventos.RES_LISTAR_DEPARTAMENTO_OK):
			setVisible(false);
			StringBuilder str = new StringBuilder();
			str.append("Lista de Departamentos").append(System.lineSeparator());
			List<TDepartamento> lista = (ArrayList<TDepartamento>) datos;
			for(TDepartamento departamento : lista) {
				str.append("Departamento con id: " + departamento.getId() + ", con nombre: " + departamento.getNombre() + " y numero de empleados: " + departamento.getNumEmpleados()).append(System.lineSeparator());
			}
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Lista de Departamentos",
					"Lista de Departamentos mostrada", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case (Eventos.RES_LISTAR_DEPARTAMENTO_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudieron listar los departamentos", "Error",
					JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}

}
