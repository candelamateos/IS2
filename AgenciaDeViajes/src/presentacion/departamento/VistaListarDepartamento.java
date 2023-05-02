package presentacion.departamento;

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

import negocio.departamento.TDepartamento;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Eventos;

public class VistaListarDepartamento extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel dataTableModel;

	private static final String[] HEADERS = { "Id", "Nombre", "Numero de empleados", "Activo" };

	public VistaListarDepartamento() {
		super("LISTAR DEPARTAMENTO");
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
		
		setContentPane(mainPanel);
		
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case (Eventos.RES_LISTAR_DEPARTAMENTO_OK):
			setVisible(true);
			List<TDepartamento> lista = (List<TDepartamento>) datos;
			dataTableModel.setNumRows(lista.size());
			for (int i = 0; i < lista.size(); i++) {
				TDepartamento departamento = lista.get(i);
				dataTableModel.setValueAt(departamento.getId(), i, 0);
				dataTableModel.setValueAt(departamento.getNombre(), i, 1);
				dataTableModel.setValueAt(departamento.getNumEmpleados(), i, 2);
				dataTableModel.setValueAt(departamento.getActivo(), i, 3);
			}
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
