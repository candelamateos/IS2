package presentacion.trabajador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import negocio.trabajador.TTrabajador;
import negocio.trabajador.TVendedor;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaBuscarTrabajador extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	private JLabel lId;
	private JTextField tId;
	private JButton ok;

	public VistaBuscarTrabajador() {
		super("BUSCAR TRABAJADOR");
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
				try {
					int Iid = 0;
					try {
						Iid = Integer.parseInt(tId.getText());
					} catch (NumberFormatException ex) {
						tId.setText("");
					}
					Controlador.getInstancia().accion(Eventos.BUSCAR_TRABAJADOR, Iid);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaBuscarTrabajador.this), ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}

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
		case (Eventos.RES_BUSCAR_TRABAJADOR_OK):
			TTrabajador trabajador = (TTrabajador) datos;
			setVisible(false);
			StringBuilder str = new StringBuilder();
			str.append("Trabajador encontrado con datos: ").append(System.lineSeparator());
			str.append("Id: " + trabajador.getId()).append(System.lineSeparator());
			str.append("Nombre: " + trabajador.getNombre()).append(System.lineSeparator());
			str.append(trabajador.isActivo() ? "Servicio activo" :"Servicio inactivo");
			str.append(System.lineSeparator());
			str.append("Sueldo: " + trabajador.getSueldo()).append(System.lineSeparator());
			str.append("Id del departamento: " + trabajador.getIdDepart()).append(System.lineSeparator());
			str.append("Tipo: " + trabajador.getTipo()).append(System.lineSeparator());
			
			if(trabajador instanceof TVendedor) {
				str.append("Id del jefe: " + ((TVendedor) trabajador).getIdJefe());
				str.append(System.lineSeparator());
			}
			
			JOptionPane.showMessageDialog(Utils.getWindow(this), str, "Trabajador encontrado",
					JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case (Eventos.RES_BUSCAR_TRABAJADOR_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo encontrar al trabajador", "Error",
					JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
		
	}


	
}
