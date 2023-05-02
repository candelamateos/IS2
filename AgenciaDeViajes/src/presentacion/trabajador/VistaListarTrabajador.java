package presentacion.trabajador;

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

import negocio.trabajador.TTrabajador;
import negocio.viaje.TViaje;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Eventos;

public class VistaListarTrabajador extends JFrame implements IGUI{
private static final long serialVersionUID = 1L;
	
	private DefaultTableModel dataTableModel;
	
	private static final String[] HEADERS = {"Id", "Nombre", "Sueldo", "IdDepartamento", "Tipo", "IdJefe", "Activo"};
	
	public VistaListarTrabajador() {
		super("Listar Trabajadores");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
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
		switch(evento) {
		case(Eventos.RES_LISTAR_TRABAJADOR_OK):
			List<TTrabajador> lista = (List<TTrabajador>) datos;
		
			dataTableModel.setNumRows(lista.size());
			for(int i = 0; i < lista.size(); i++) {
				TTrabajador trabajador = lista.get(i);
				dataTableModel.setValueAt(trabajador.getId(), i, 0);
				dataTableModel.setValueAt(trabajador.getNombre(), i, 1);
				dataTableModel.setValueAt(trabajador.getSueldo(), i, 2);
				dataTableModel.setValueAt(trabajador.getIdDepart(), i, 3);
				dataTableModel.setValueAt(trabajador.getTipo(), i, 4);
				dataTableModel.setValueAt(trabajador.getIdJefe(), i, 5);
				dataTableModel.setValueAt(trabajador.isActivo(), i, 6);
			}
			setSize(new Dimension(480,270));
			setVisible(true);
			break;
		case(Eventos.RES_LISTAR_TRABAJADOR_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se han encontrado trabajadores", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
		
	}

}
