package presentacion.factura;

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

import negocio.factura.TFactura;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaAbrirVenta extends JFrame implements IGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField tVendedor;
	JTextField tCliente;
	JButton ok;
	
	public VistaAbrirVenta() {
		super("Abrir Venta");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setContentPane(mainPanel);
		
		JPanel fila1 = new JPanel();
		fila1.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila1);
		JPanel fila2 = new JPanel();
		fila2.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila2);
		JPanel fila3 = new JPanel();
		fila3.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila3);
		
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
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idVendedor;
					int idCliente;
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
					Controlador.getInstancia().accion(Eventos.ABRIR_VENTA, new TFactura(idCliente,idVendedor));
				}
				catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaAbrirVenta.this), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
		case(Eventos.RES_ABRIR_VENTA_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Factura abierta con id " + datos, "Factura abierta", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_ABRIR_VENTA_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "El cliente o el vendedor no existen", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
		
	}
	

}
