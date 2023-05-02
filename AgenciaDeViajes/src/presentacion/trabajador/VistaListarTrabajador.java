package presentacion.trabajador;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocio.trabajador.TJefe;
import negocio.trabajador.TTrabajador;
import negocio.trabajador.TVendedor;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Eventos;

public class VistaListarTrabajador extends JFrame implements IGUI{
	private DefaultTableModel modelVendedores;
	private DefaultTableModel modelJefes;
	
	JPanel cards;
	JPanel panelVendedor;
	JPanel panelJefe;
	JPanel mainPanel;
	JPanel comboBoxPanel;
	JComboBox<String> comboBox;

	private static final String[] HEADERS_VENDEDORES = { "Id", "Nombre", "Sueldo", "Id Departamento", "Id jefe" };
	private static final String[] HEADERS_JEFES = { "Id", "Nombre", "Sueldo", "Id Departamento" };

	private final String PANEL_VENDEDORES = "vendedor";
	private final String PANEL_JEFES = "jefe";
	
	public VistaListarTrabajador() {
		super("LISTAR TRABAJADORES");
		initGUI();
	}

	private void initGUI() {
		
		cards = new JPanel(new CardLayout());
		comboBoxPanel = new JPanel();
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(comboBoxPanel, BorderLayout.NORTH);
		mainPanel.add(cards, BorderLayout.CENTER);
		
		panelVendedor = new JPanel();
		panelJefe = new JPanel();
		
		cards.add(panelVendedor, PANEL_VENDEDORES);
		cards.add(panelJefe, PANEL_JEFES);
		
		String opciones[] = {PANEL_VENDEDORES, PANEL_JEFES};
		comboBox = new JComboBox<>(opciones);
		comboBoxPanel.add(comboBox);
		comboBox.setEditable(false);
		comboBoxPanel.setOpaque(false);
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
			    CardLayout cl = (CardLayout)(cards.getLayout());
			    cl.show(cards, (String)e.getItem());
			}
			
		});


		modelVendedores = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		};

		modelVendedores.setColumnIdentifiers(HEADERS_VENDEDORES);
		
		modelJefes = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		};
		modelJefes.setColumnIdentifiers(HEADERS_JEFES);

		
		panelVendedor.add( new JScrollPane(new JTable(modelVendedores)));
		panelJefe.add( new JScrollPane(new JTable(modelJefes)));
		
		setContentPane(mainPanel);
		setLocationRelativeTo(null);
		setSize(new Dimension(480, 270));
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case (Eventos.RES_LISTAR_TRABAJADOR_OK):
			setVisible(true);
			List<TTrabajador> lista = (List<TTrabajador>) datos;
			
			List<TVendedor> listaVendedores = new ArrayList<>();
			List<TJefe> listaJefes = new ArrayList<>();
			
			for(TTrabajador elem : lista) {
				if(elem instanceof TVendedor) listaVendedores.add((TVendedor) elem);
				else if (elem instanceof TJefe) listaJefes.add((TJefe) elem);
			}
			
			modelVendedores.setNumRows(listaVendedores.size());
			for(int i = 0; i < listaVendedores.size(); ++i) {
				TVendedor trabajador = listaVendedores.get(i);
				modelVendedores.setValueAt(trabajador.getId(), i, 0);
				modelVendedores.setValueAt(trabajador.getNombre(), i, 1);
				modelVendedores.setValueAt(trabajador.getSueldo(), i, 2);
				modelVendedores.setValueAt(trabajador.getIdDepart(), i, 3);
				modelVendedores.setValueAt(trabajador.getIdJefe(), i, 4);
			}
			
			modelJefes.setNumRows(listaJefes.size());
			for(int i = 0; i < listaJefes.size(); ++i) {
				TJefe trabajador = listaJefes.get(i);
				modelJefes.setValueAt(trabajador.getId(), i, 0);
				modelJefes.setValueAt(trabajador.getNombre(), i, 1);
				modelJefes.setValueAt(trabajador.getSueldo(), i, 2);
				modelJefes.setValueAt(trabajador.getIdDepart(), i, 3);
			}
			
			break;
		case (Eventos.RES_LISTAR_TRABAJADOR_ERROR):
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se han encontrado trabajadores", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		}
		
	}


}
