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
import negocio.factura.TFactura;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaBuscarCliente extends JFrame implements IGUI {

	private JLabel lId;
	private JTextField tId;
	private JButton ok;

	public VistaBuscarCliente() {
		super("BUSCAR CLIENTE");
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
					Controlador.getInstancia().accion(Eventos.BUSCAR_CLIENTE, Iid);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaBuscarCliente.this), ex.getMessage(), "Error",
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
		case (Eventos.RES_BUSCAR_CLIENTE_OK):
			TCliente cliente = (TCliente) datos;
			setVisible(false);
			StringBuilder str = new StringBuilder();
			str.append("Cliente encontrado con datos: ").append(System.lineSeparator());
			str.append("Id: " + cliente.getId()).append(System.lineSeparator());
			str.append("Nombre: " + cliente.getNombre()).append(System.lineSeparator());
			if(cliente.getActivo()) {
				str.append("Cliente activo").append(System.lineSeparator());
			}
			else {
				str.append("Cliente inactivo").append(System.lineSeparator());
			}
			JOptionPane.showMessageDialog(Utils.getWindow(this), str, "Cliente encontrado",
					JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case (Eventos.RES_BUSCAR_CLIENTE_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo encontrar el cliente", "Error",
					JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}
}