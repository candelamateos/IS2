package presentacion.viaje;

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

public class VistaEliminarViaje extends JFrame implements IGUI{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lId;
	private JTextField tId;
	private JButton ok;
	
	public VistaEliminarViaje() {
		super("Eliminar Viaje");
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
		
		lId= new JLabel("Id del viaje:");
		lId.setPreferredSize(new Dimension(100, 25));
		tId = new JTextField(10);
		fila1.add(lId);
		fila1.add(tId);
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int Iid;
					try {
						Iid = Integer.parseInt(tId.getText());
					}catch(NumberFormatException ex) {
						throw new IllegalArgumentException("El id del viaje debe ser un numero", ex);
					}
					Controlador.getInstancia().accion(Eventos.BAJA_VIAJE, Iid);
				}catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaEliminarViaje.this), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}	
			}
		});
		fila2.add(ok);
		
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Eventos.RES_BAJA_VIAJE_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Viaje eliminado", "Viaje Eliminado", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_BAJA_VIAJE_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo eliminar el viaje", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}

}
