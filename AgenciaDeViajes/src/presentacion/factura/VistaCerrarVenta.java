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

import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaCerrarVenta extends JFrame implements IGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField tFactura;
	JButton ok;
	
	public VistaCerrarVenta() {
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
		JPanel fila3 = new JPanel();
		fila3.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila3);
		
		JLabel lFactura = new JLabel("Id de la factura:");
		lFactura.setPreferredSize(new Dimension(100,25));
		tFactura = new JTextField(10);
		tFactura.setPreferredSize(new Dimension(100,25));
		fila1.add(lFactura);
		fila1.add(tFactura);
		
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
						throw new IllegalArgumentException("El id de la factura debe ser un número", ex);
					}
					Controlador.getInstancia().accion(Eventos.CERRAR_VENTA, idFactura);
				}
				catch(IllegalArgumentException ex) {
					tFactura.setText("");
					JOptionPane.showMessageDialog(Utils.getWindow(VistaCerrarVenta.this), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
		case(Eventos.RES_CERRAR_VENTA_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Venta cerrada con éxito", "Venta cerrada", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_CERRAR_VENTA_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "La factura indicada no existe, ya está cerrada o no tiene viajes añadidos", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}
}
