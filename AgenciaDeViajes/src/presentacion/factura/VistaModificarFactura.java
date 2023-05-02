package presentacion.factura;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.factura.TFactura;
import negocio.factura.TFacturaConLineas;
import negocio.servicio.TServicio;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaModificarFactura extends JFrame implements IGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel error;
	private JTextField tFactura;
	private JTextField tVendedor;
	private JTextField tCliente;
	private JButton Guardar;
	
	public VistaModificarFactura() {
		super("Modificar factura");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setContentPane(mainPanel);
		
		error = new JPanel();
		error.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(error);
		JLabel lError = new JLabel("Factura no encontrada");
		lError.setForeground(Color.RED);
		error.add(lError);
		error.setVisible(false);
		
		JPanel fila0 = new JPanel();
		fila0.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila0);
		JPanel fila1 = new JPanel();
		fila1.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila1);
		JPanel fila2 = new JPanel();
		fila2.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila2);
		JPanel fila3 = new JPanel();
		fila3.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila3);
		
		JLabel lFactura = new JLabel("Id de la factura:");
		lFactura.setPreferredSize(new Dimension(100,25));
		tFactura = new JTextField(10);
		tFactura.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
			}

			private void update() {
				try{
					int id = Integer.parseInt(tFactura.getText());
					TFacturaConLineas t = FactoriaAbstractaNegocio.getInstancia().crearSAFactura().readFactura(id);
					if(t == null) actualizar(Eventos.RES_BUSCAR_FACTURA_ERROR, null);
					else actualizar(Eventos.RES_BUSCAR_FACTURA_OK, t);
				}
				catch(NumberFormatException ex){
					actualizar(Eventos.RES_BUSCAR_FACTURA_ERROR, null);
				}	
			}

			@Override
			public void changedUpdate(DocumentEvent e) {}
		});

		tFactura.setPreferredSize(new Dimension(100,25));
		fila0.add(lFactura);
		fila0.add(tFactura);
		
		JLabel lVendedor = new JLabel("Id del vendedor:");
		lVendedor.setPreferredSize(new Dimension(100,25));
		tVendedor = new JTextField(10);
		tVendedor.setPreferredSize(new Dimension(100,25));
		fila1.add(lVendedor);
		fila1.add(tVendedor);
		
		JLabel lCliente = new JLabel("Id del cliente:");
		lCliente.setPreferredSize(new Dimension(100,25));
		tCliente = new JTextField(10);
		tCliente.setPreferredSize(new Dimension(100,25));
		fila2.add(lCliente);
		fila2.add(tCliente);
		
		Guardar = new JButton("Guardar");
		Guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idFactura;
					int idVendedor;
					int idCliente;
					try{
						idFactura = Integer.parseInt(tFactura.getText());
					}catch(NumberFormatException ex) {
						tFactura.setText("");
						throw new IllegalArgumentException("El id de la factura debe ser un número", ex);
					}
					try{
						idVendedor = Integer.parseInt(tVendedor.getText());
					}catch(NumberFormatException ex) {
						tVendedor.setText("");
						throw new IllegalArgumentException("El id del vendedor debe ser un número", ex);
					}
					try{
						idCliente = Integer.parseInt(tCliente.getText());
					}catch(NumberFormatException ex) {
						tCliente.setText("");
						throw new IllegalArgumentException("El id del cliente debe ser un número", ex);
					}
					TFactura factura =  new TFactura(idCliente,idVendedor);
					factura.setId(idFactura);
					Controlador.getInstancia().accion(Eventos.MODIFICAR_FACTURA, factura);
				}
				catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaModificarFactura.this), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
		fila3.add(Guardar);
		
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Eventos.RES_MODIFICAR_FACTURA_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Factura modificada correctamente ", "Factura abierta", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_MODIFICAR_FACTURA_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "El cliente o el vendedor no existen", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;	
		case(Eventos.RES_BUSCAR_FACTURA_OK):
			TFacturaConLineas facturaConLineas = (TFacturaConLineas) datos;
			TFactura factura = facturaConLineas.getFactura();
			tFactura.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
			error.setVisible(false);
			tVendedor.setText(Integer.toString(factura.getIdVendedor()));
			tCliente.setText(Integer.toString(factura.getIdCliente()));
			break;
		case(Eventos.RES_BUSCAR_FACTURA_ERROR):
			tFactura.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
			error.setVisible(true);
			break;
		}
	}

}
