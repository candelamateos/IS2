package presentacion.servicio;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocio.cliente.TCliente;
import negocio.servicio.TActividad;
import negocio.servicio.TAlojamiento;
import negocio.servicio.TServicio;
import negocio.servicio.TTransporte;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Eventos;

public class VistaListarServicios extends JFrame implements IGUI {


	private DefaultTableModel modelTransportes;
	private DefaultTableModel modelActividades;
	private DefaultTableModel modelAlojamientos;
	
	JPanel cards;
	JPanel panelActividad;
	JPanel panelTransporte;
	JPanel panelAlojamiento;
	JPanel mainPanel;
	JPanel comboBoxPanel;
	JComboBox<String> comboBox;

	private static final String[] HEADERS_TRANSPORTES = { "Id", "Nombre", "nº plazas", "precio", "tipo transporte", "comida" };
	private static final String[] HEADERS_ALOJAMIENTOS = { "Id", "Nombre", "nº plazas", "precio", "regimen", "estrellas"};
	private static final String[] HEADERS_ACTIVIDADES = { "Id", "Nombre", "nº plazas", "precio", "tipo actividad", "colectivo"};

	private final String PANEL_ACTIVIDAD = "actividad";
	private final String PANEL_TRANSPORTE = "transporte";
	private final String PANEL_ALOJAMIENTO = "alojamiento";

	public VistaListarServicios() {
		super("Listar Servicios");
		initGUI();
	}

	private void initGUI() {
		
		cards = new JPanel(new CardLayout());
		comboBoxPanel = new JPanel();
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(comboBoxPanel, BorderLayout.NORTH);
		mainPanel.add(cards, BorderLayout.CENTER);
		
		panelActividad = new JPanel();
		panelTransporte = new JPanel();
		panelAlojamiento = new JPanel();
		cards.add(panelActividad, PANEL_ACTIVIDAD);
		cards.add(panelTransporte, PANEL_TRANSPORTE);
		cards.add(panelAlojamiento, PANEL_ALOJAMIENTO);
		
		String opciones[] = {PANEL_ACTIVIDAD, PANEL_TRANSPORTE, PANEL_ALOJAMIENTO};
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


		modelTransportes = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		};

		modelTransportes.setColumnIdentifiers(HEADERS_TRANSPORTES);
		
		modelAlojamientos = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		};
		modelAlojamientos.setColumnIdentifiers(HEADERS_ALOJAMIENTOS);
		
		modelActividades = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		};
		modelActividades.setColumnIdentifiers(HEADERS_ACTIVIDADES);

		
		panelActividad.add( new JScrollPane(new JTable(modelActividades)));
		panelTransporte.add( new JScrollPane(new JTable(modelTransportes)));
		panelAlojamiento.add( new JScrollPane(new JTable(modelAlojamientos)));
		
		setContentPane(mainPanel);
		setLocationRelativeTo(null);
		setSize(new Dimension(480, 270));
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case (Eventos.RES_LISTAR_SERVICIO_OK):
			setVisible(true);
			List<TServicio> lista = (List<TServicio>) datos;
			
			List<TAlojamiento> listaAlo = new ArrayList<>();
			List<TActividad> listaAct = new ArrayList<>();
			List<TTransporte> listaTrans = new ArrayList<>();
			
			for(TServicio elem : lista) {
				if(elem instanceof TAlojamiento) listaAlo.add((TAlojamiento) elem);
				else if (elem instanceof TActividad) listaAct.add((TActividad) elem);
				else if (elem instanceof TTransporte) listaTrans.add((TTransporte) elem);
			}
			
			modelTransportes.setNumRows(listaTrans.size());
			for(int i = 0; i < listaTrans.size(); ++i) {
				TTransporte servicio = listaTrans.get(i);
				modelTransportes.setValueAt(servicio.getId(), i, 0);
				modelTransportes.setValueAt(servicio.getNombre(), i, 1);
				modelTransportes.setValueAt(servicio.getNumPlazas(), i, 2);
				modelTransportes.setValueAt(servicio.getPrecio(), i, 3);
				modelTransportes.setValueAt(servicio.getTipoTransporte(), i, 4);
				modelTransportes.setValueAt(servicio.isComida(), i, 5);
			}
			
			modelAlojamientos.setNumRows(listaAlo.size());
			for(int i = 0; i < listaAlo.size(); ++i) {
				TAlojamiento servicio = listaAlo.get(i);
				modelAlojamientos.setValueAt(servicio.getId(), i, 0);
				modelAlojamientos.setValueAt(servicio.getNombre(), i, 1);
				modelAlojamientos.setValueAt(servicio.getNumPlazas(), i, 2);
				modelAlojamientos.setValueAt(servicio.getPrecio(), i, 3);
				modelAlojamientos.setValueAt(servicio.getRegimen(), i, 4);
				modelAlojamientos.setValueAt(servicio.getEstrellas(), i, 5);
			}
			
			modelActividades.setNumRows(listaAct.size());
			for(int i = 0; i < listaAct.size(); ++i) {
				TActividad servicio = listaAct.get(i);
				modelActividades.setValueAt(servicio.getId(), i, 0);
				modelActividades.setValueAt(servicio.getNombre(), i, 1);
				modelActividades.setValueAt(servicio.getNumPlazas(), i, 2);
				modelActividades.setValueAt(servicio.getPrecio(), i, 3);
				modelActividades.setValueAt(servicio.getTipoActividad(), i, 4);
				modelActividades.setValueAt(servicio.isColectivo(), i, 5);
			}




			break;
		case (Eventos.RES_LISTAR_SERVICIO_ERROR):
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se han encontrado servicios", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		}

	}
}
