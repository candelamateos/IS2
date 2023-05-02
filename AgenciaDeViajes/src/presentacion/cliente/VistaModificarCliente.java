package presentacion.cliente;

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

import negocio.cliente.TCliente;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaModificarCliente extends JFrame implements IGUI{
	
	private JButton ok;
	private JTextField tId;
	private JTextField tNombre;
	
	public VistaModificarCliente() {
		super("Modificar Cliente");
		initGUI();
	}
	
	void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setContentPane(mainPanel);
		
		JPanel fila0 = new JPanel();
		fila0.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila0);
		JPanel fila1 = new JPanel();
		fila1.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila1);
		JPanel fila2 = new JPanel();
		fila2.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila2);
		
		JLabel lId = new JLabel("Id del cliente: ");
		lId.setPreferredSize(new Dimension(120,25));
		tId = new JTextField(10);
		tId.setPreferredSize(new Dimension(100,25));
		fila0.add(lId);
		fila0.add(tId);
		
		JLabel lNombre = new JLabel("Nombre del cliente: ");
		lNombre.setPreferredSize(new Dimension(120,25));
		tNombre = new JTextField(10);
		tNombre.setPreferredSize(new Dimension(100, 25));
		fila1.add(lNombre);
		fila1.add(tNombre);
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idCliente;
					String nCliente;
					try{
						idCliente = Integer.parseInt(tId.getText());
					}catch(NumberFormatException ex) {
						tId.setText("");
						throw new IllegalArgumentException("El id del clilente debe ser un número", ex);
					}
					TCliente cliente =  new TCliente();
					cliente.setId(idCliente);
					nCliente = tNombre.getText();
					cliente.setNombre(nCliente);
					cliente.setActivo(true);
					Controlador.getInstancia().accion(Eventos.MODIFICAR_CLIENTE, cliente);
				}
				catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaModificarCliente.this), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
	case(Eventos.RES_MODIFICAR_CLIENTE_OK):
		setVisible(false);
		JOptionPane.showMessageDialog(Utils.getWindow(this), "Cliente modificado correctamente", "Cliente modificado", JOptionPane.INFORMATION_MESSAGE);
		setVisible(true);
		break;
	case(Eventos.RES_MODIFICAR_FACTURA_ERROR):
		setVisible(false);
		JOptionPane.showMessageDialog(Utils.getWindow(this), "El cliente no existe", "Error", JOptionPane.ERROR_MESSAGE);
		setVisible(true);
		break;
	}
}
	
}
