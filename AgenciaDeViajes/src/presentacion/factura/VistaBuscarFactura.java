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

public class VistaBuscarFactura extends JFrame implements IGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField tFactura;
	
	private JButton ok;
	
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
			TFactura factura = (TFactura) datos;
			setVisible(false);
			StringBuilder str = new StringBuilder();
			str.append("Factura encontrada con datos: ").append(System.lineSeparator());
			str.append("Id: " + factura.getId()).append(System.lineSeparator());
			str.append("Coste: " + factura.getCoste()).append(System.lineSeparator());
			str.append("IdCliente: " + factura.getIdCliente()).append(System.lineSeparator());
			str.append("IdVendedor: " + factura.getIdVendedor()).append(System.lineSeparator());
			str.append("Abierta: " + factura.isAbierta()).append(System.lineSeparator());
			JOptionPane.showMessageDialog(Utils.getWindow(this), str , "Factura encontrada", JOptionPane.INFORMATION_MESSAGE);
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
