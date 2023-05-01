package presentacion.viaje;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import negocio.viaje.TViaje;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaListarViaje extends JFrame implements IGUI{

	private JButton ok;
	
	public VistaListarViaje() {
		super("LISTAR VIAJE");
		initGUI();
	}
	
	private void initGUI() {
		JPanel panel = new JPanel();
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.getInstancia().accion(Eventos.LISTAR_VIAJE, null);
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
		switch(evento) {
		case(Eventos.RES_LISTAR_VIAJE_OK):
			setVisible(false);
		
			String[] columnas = {"Id", "Precio", "Numero de plazas", "IdActividad", "IdAlojamiento", "IdTransporte"};
		 	JTable tabla = new JTable();
	        DefaultTableModel modelo = new DefaultTableModel();
	        JScrollPane desplazamiento = new JScrollPane(tabla);   
	        setTitle("Lista de Viajes");
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setLayout(new BorderLayout());
	        
	        modelo.setColumnIdentifiers(columnas);
	        
	        desplazamiento.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        desplazamiento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        
	        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	        tabla.setFillsViewportHeight(true);
	        
	        tabla.setModel(modelo);
	        
	        modelo.setRowCount(0);
	        
	        // Creamos los datos de una fila de la tabla
	        Object[] datosFila = {"Id", "Precio", "NumPlazas", "IdActividad", "IdAlojamiento", "IdTransporte"};
	        modelo.addRow(datosFila);
	        for(TViaje viaje : (ArrayList<TViaje>) datos) {
	        	datosFila[0] = viaje.getId();
	        	datosFila[1] = viaje.getPrecio();
	        	datosFila[2] = viaje.getNumPlazas();
	        	datosFila[3] = viaje.getIdActividad();
	        	datosFila[4] = viaje.getIdAlojamiento();
	        	datosFila[5] = viaje.getIdTransporte();
	        	
	        	modelo.addRow(datosFila);
	        }
	        
	        this.getContentPane().add(desplazamiento, BorderLayout.NORTH);
			
//	        StringBuilder str = new StringBuilder();
//			str.append("Lista de Viajes").append(System.lineSeparator());
//			List<TViaje> lista = (ArrayList<TViaje>) datos;
//			for(TViaje viaje : lista) {
//				str.append("Viaje con id: " + viaje.getId() + ", con precio: " + viaje.getPrecio() + " y numero de plazas: " + viaje.getNumPlazas()).append(System.lineSeparator());
//			}
			JOptionPane.showMessageDialog(Utils.getWindow(this), tabla, "Lista de Viajes mostrada", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_LISTAR_VIAJE_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudieron listar los viajes", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}

}
