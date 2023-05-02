package presentacion.cliente;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocio.cliente.TCliente;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Eventos;

public class VistaListarCliente extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel dataTableModel;

	private static final String[] HEADERS = { "Id", "Nombre" };

	public VistaListarCliente() {
		super("Listar Clientes");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(mainPanel);

		dataTableModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		};
		dataTableModel.setColumnIdentifiers(HEADERS);

		JTable tabla = new JTable(dataTableModel);
		JScrollPane scroll = new JScrollPane(tabla);
		mainPanel.add(scroll);

		setLocationRelativeTo(null);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case (Eventos.RES_LISTAR_CLIENTE_OK):
			setVisible(true);
			List<TCliente> lista = (List<TCliente>) datos;

			dataTableModel.setNumRows(lista.size());
			for (int i = 0; i < lista.size(); i++) {
				TCliente cliente = lista.get(i);
				dataTableModel.setValueAt(cliente.getId(), i, 0);
				dataTableModel.setValueAt(cliente.getNombre(), i, 1);
			}

			setSize(new Dimension(480, 270));
			setVisible(true);
			break;
		case (Eventos.RES_LISTAR_CLIENTE_ERROR):
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se han encontrado clientes", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		}

	}
}
