package presentacion.factura;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import negocio.factura.TFactura;
import negocio.factura.TFacturaConLineas;
import negocio.factura.TLineaFactura;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaBuscarFactura extends JFrame implements IGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField tFactura;
	
	private JButton ok;
	
	private static final String[] HEADERS = {"Id", "Coste", "Plazas Vendidas", "IdFactura", "IdViaje"};
	
	public VistaBuscarFactura() {
		super("Buscar factura");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setContentPane(mainPanel);
		
		JPanel fila0 = new JPanel();
		fila0.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila0);
		JPanel fila3 = new JPanel();
		fila3.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila3);
		
		JLabel lFactura = new JLabel("Id de la factura:");
		lFactura.setPreferredSize(new Dimension(100,25));
		tFactura = new JTextField(10);
		tFactura.setPreferredSize(new Dimension(100,25));
		fila0.add(lFactura);
		fila0.add(tFactura);
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idFactura;
					try{
						idFactura = Integer.parseInt(tFactura.getText());
					}catch(NumberFormatException ex) {
						tFactura.setText("");
						throw new IllegalArgumentException("El id de la factura debe ser un número", ex);
					}
					Controlador.getInstancia().accion(Eventos.BUSCAR_FACTURA, idFactura);
				}
				catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaBuscarFactura.this), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
		fila3.add(ok);
		
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Eventos.RES_BUSCAR_FACTURA_OK):
			TFacturaConLineas facturaConLineas = (TFacturaConLineas) datos;
			TFactura factura = facturaConLineas.getFactura();
			List<TLineaFactura> lista = facturaConLineas.getLineas();
			setVisible(false);
			
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			
			JLabel label1 = new JLabel("Factura encontrada con datos: ");
			JLabel label2 = new JLabel("Id: " + factura.getId());
			JLabel label3 = new JLabel("Coste: " + factura.getCoste());
			JLabel label4 = new JLabel("IdCliente: " + factura.getIdCliente());
			JLabel label5 = new JLabel("IdVendedor: " + factura.getIdVendedor());
			JLabel label6 = new JLabel("Abierta: " + factura.isAbierta());
			mainPanel.add(label1);
			mainPanel.add(label2);
			mainPanel.add(label3);
			mainPanel.add(label4);
			mainPanel.add(label5);
			mainPanel.add(label6);
			mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
			
			DefaultTableModel dataTableModel = new DefaultTableModel() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				@Override
				public boolean isCellEditable(int row, int col) {
					return false;
				}
				
			};
			dataTableModel.setColumnIdentifiers(HEADERS);
			dataTableModel.setNumRows(lista.size());
			for(int i = 0; i < lista.size(); i++) {
				TLineaFactura linea = lista.get(i);
				dataTableModel.setValueAt(linea.getId(), i, 0);
				dataTableModel.setValueAt(linea.getCoste(), i, 1);
				dataTableModel.setValueAt(linea.getPlazasVendidas(), i, 2);
				dataTableModel.setValueAt(linea.getIdFactura(), i, 3);
				dataTableModel.setValueAt(linea.getIdViaje(), i, 4);
			}
			
			JTable table = new JTable(dataTableModel);
			JScrollPane scroll = new JScrollPane(table);
			mainPanel.add(scroll);
			
			JOptionPane.showMessageDialog(Utils.getWindow(this), mainPanel , "Factura encontrada", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_BUSCAR_FACTURA_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo encontrar la factura", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}

}
