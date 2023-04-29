package presentacion.cliente;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.IGUI;

public class VistaClientes extends JFrame implements IGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton BAniadirCliente;
	private JButton BBuscarCliente;
	private JButton BEliminarCliente;
	private JButton BListarCliente;
	private JButton BModificarCliente;
	
	public VistaClientes() {
		super("Clientes");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
		
		BAniadirCliente = new JButton("Añadir Cliente");
		BAniadirCliente.addActionListener(null);
		BAniadirCliente.setPreferredSize(new Dimension(130,30));
		fila1.add(BAniadirCliente);
		
		BBuscarCliente = new JButton("Buscar Cliente");
		BBuscarCliente.addActionListener(null);
		BBuscarCliente.setPreferredSize(new Dimension(130,30));
		fila1.add(BBuscarCliente);
		
		BEliminarCliente = new JButton("Eliminar Cliente");
		BEliminarCliente.addActionListener(null);
		BEliminarCliente.setPreferredSize(new Dimension(130,30));
		fila2.add(BEliminarCliente);
		
		BListarCliente = new JButton("Listar Clientes");
		BListarCliente.addActionListener(null);
		BListarCliente.setPreferredSize(new Dimension(130,30));
		fila2.add(BListarCliente);
		
		BModificarCliente = new JButton("Modificar Cliente");
		BModificarCliente.addActionListener(null);
		BModificarCliente.setPreferredSize(new Dimension(130,30));
		fila3.add(BModificarCliente);
		
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub

	}

}
