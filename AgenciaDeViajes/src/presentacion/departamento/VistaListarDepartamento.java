package presentacion.departamento;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocio.departamento.TDepartamento;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaListarDepartamento extends JFrame implements IGUI {

	private DefaultTableModel dataTableModel;

	private static final String[] HEADERS = { "Id", "Nombre", "Numero de empleados" };

	public VistaListarDepartamento() {
		setTitle("LISTAR DEPARTAMENTO");
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
		case (Eventos.RES_LISTAR_DEPARTAMENTO_OK):
			List<TDepartamento> lista = (ArrayList<TDepartamento>) datos;
			for (int i = 0; i < lista.size(); i++) {
				TDepartamento departamento = lista.get(i);
				dataTableModel.setValueAt(departamento.getId(), i, 0);
				dataTableModel.setValueAt(departamento.getNombre(), i, 1);
				dataTableModel.setValueAt(departamento.getNumEmpleados(), i, 2);
			}
			dataTableModel.fireTableDataChanged();
			setSize(new Dimension(480, 270));
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
