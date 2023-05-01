package presentacion.viaje;

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

import negocio.viaje.TViaje;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaListarViaje extends JFrame implements IGUI{
	
	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel dataTableModel;
	
	private static final String[] HEADERS = {"Id", "Precio", "Numero de plazas", "IdActividad", "IdAlojamiento", "IdTransporte"};
	
	public VistaListarViaje() {
		super("LISTAR VIAJE");
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
		case(Eventos.RES_LISTAR_VIAJE_OK):
			List<TViaje> lista = (List<TViaje>) datos;
		
		dataTableModel.setNumRows(lista.size());
		for(int i = 0; i < lista.size(); i++) {
			TViaje viaje = lista.get(i);
			dataTableModel.setValueAt(viaje.getId(), i, 0);
			dataTableModel.setValueAt(viaje.getPrecio(), i, 1);
			dataTableModel.setValueAt(viaje.getNumPlazas(), i, 2);
			dataTableModel.setValueAt(viaje.getIdActividad(), i, 3);
			dataTableModel.setValueAt(viaje.getIdAlojamiento(), i, 4);
			dataTableModel.setValueAt(viaje.getIdTransporte(), i, 5);
			dataTableModel.setValueAt(viaje.getActivo(), i, 6);
		}
		dataTableModel.fireTableDataChanged();
		setSize(new Dimension(480,270));
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
		break;
		case(Eventos.RES_LISTAR_VIAJE_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se han encontrado viajes", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}

}
