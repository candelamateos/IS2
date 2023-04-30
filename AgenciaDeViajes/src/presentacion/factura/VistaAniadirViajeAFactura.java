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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import negocio.factura.TFactura;
import negocio.factura.TLineaFactura;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaAniadirViajeAFactura extends JFrame implements IGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTextField tFactura;
	JTextField tViaje;
	JSpinner sPlazas;
	JButton ok;
	
	public VistaAniadirViajeAFactura() {
		super("Añadir Viaje");
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
		JPanel fila4 = new JPanel();
		fila4.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila4);
		
		JLabel lFactura = new JLabel("Id de la factura:");
		lFactura.setPreferredSize(new Dimension(100,25));
		tFactura = new JTextField(10);
		tFactura.setPreferredSize(new Dimension(100,25));
		fila1.add(lFactura);
		fila1.add(tFactura);
		
		JLabel lViaje = new JLabel("Id del viaje:");
		lViaje.setPreferredSize(new Dimension(100,25));
		tViaje = new JTextField(10);
		tViaje.setPreferredSize(new Dimension(100,25));
		fila2.add(lViaje);
		fila2.add(tViaje);
		
		JLabel lPlazas = new JLabel("Número de plazas:");
		lPlazas.setPreferredSize(new Dimension(110,25));
		sPlazas = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
		sPlazas.setPreferredSize(new Dimension(100,25));
		fila3.add(lPlazas);
		fila3.add(sPlazas);
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idFactura;
					int idViaje;
					int plazas;
					try{
						idFactura = Integer.parseInt(tFactura.getText());
					}catch(NumberFormatException ex) {
						tFactura.setText("");
						throw new IllegalArgumentException("El id de la factura debe ser un número", ex);
					}
					try{
						idViaje = Integer.parseInt(tViaje.getText());
					}catch(NumberFormatException ex) {
						tViaje.setText("");
						throw new IllegalArgumentException("El id del viaje debe ser un número", ex);
					}
					plazas = (int) sPlazas.getValue();
					if(plazas <= 0) {
						sPlazas.setValue(0);
						throw new IllegalArgumentException("El número de plazas debe ser mayor que 0");
					}
					Controlador.getInstancia().accion(Eventos.ANIADIR_VIAJE_A_FACTURA, new TLineaFactura(plazas, idFactura, idViaje));
				}
				catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaAniadirViajeAFactura.this), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
		fila4.add(ok);
		
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
