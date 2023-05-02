package presentacion.servicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.cliente.TCliente;
import negocio.servicio.TActividad;
import negocio.servicio.TAlojamiento;
import negocio.servicio.TServicio;
import negocio.servicio.TTransporte;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.cliente.VistaBuscarCliente;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaBuscarServicio extends JFrame implements IGUI {

	private JLabel lId;
	private JTextField tId;
	private JButton ok;

	public VistaBuscarServicio() {
		super("BUSCAR SERVICIO");
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
					Controlador.getInstancia().accion(Eventos.BUSCAR_SERVICIO, Iid);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaBuscarServicio.this), ex.getMessage(), "Error",
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
		HashMap<Boolean, String> convertir = new HashMap<Boolean, String>();
		convertir.put(true, "SI");
		convertir.put(false, "NO");
		switch (evento) {
		case (Eventos.RES_BUSCAR_SERVICIO_OK):
			TServicio servicio = (TServicio) datos;
			setVisible(false);
			StringBuilder str = new StringBuilder();
			str.append("Servicio encontrado con datos: ").append(System.lineSeparator());
			str.append("Id: " + servicio.getId()).append(System.lineSeparator());
			str.append("Nombre: " + servicio.getNombre()).append(System.lineSeparator());
			str.append(servicio.isActivo() ? "Servicio activo" :"Servicio inactivo");
			str.append(System.lineSeparator());
			str.append("Precio: " + servicio.getPrecio()).append(System.lineSeparator());
			str.append("nº de plazas: " + servicio.getNumPlazas()).append(System.lineSeparator());
			str.append("Tipo: " + servicio.getTipo()).append(System.lineSeparator());
			
			if(servicio instanceof TTransporte) {
				str.append("Tipo transporte: " + ((TTransporte) servicio).getTipoTransporte());
				str.append(System.lineSeparator());
				str.append("Comida incluida: " + convertir.get(((TTransporte) servicio).isComida()));
				str.append(System.lineSeparator());
			}
			else if(servicio instanceof TActividad) {
				str.append("Tipo actividad: " + ((TActividad) servicio).getTipoActividad());
				str.append(System.lineSeparator());
				str.append("Colectivo: " + convertir.get(((TActividad) servicio).isColectivo()));
				str.append(System.lineSeparator());
			}
			else if(servicio instanceof TAlojamiento) {
				str.append("Estrellas: " + ((TAlojamiento) servicio).getEstrellas());
				str.append(System.lineSeparator());
				str.append("Regimen: " + ((TAlojamiento) servicio).getRegimen());
				str.append(System.lineSeparator());
			}
			JOptionPane.showMessageDialog(Utils.getWindow(this), str, "Servicio encontrado",
					JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case (Eventos.RES_BUSCAR_SERVICIO_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo encontrar el servicio", "Error",
					JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}
}
