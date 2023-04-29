package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class MainWindow extends JFrame implements IGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String TITULO = "Viajes Bel-Mundo";
	
	private JButton BCliente;
	private JButton BDepartamento;
	private JButton BFactura;
	private JButton BServicio;
	private JButton BTrabajador;
	private JButton BViaje;
	
	public MainWindow() {
		super("Agencia de Viajes");
		initGUI();
	}
	
	private void initGUI() {
		//mainPanel con margen de 10
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setContentPane(mainPanel);
		
		//Cabecera con el título en zona "NORTH"
		JPanel cabecera = new JPanel();
		cabecera.setLayout(new BoxLayout(cabecera, BoxLayout.Y_AXIS));
		mainPanel.add(cabecera, BorderLayout.NORTH);
		
		JLabel titulo = new JLabel(TITULO);
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		cabecera.add(titulo);
		cabecera.add(Box.createVerticalStrut(10)); //Espacio entre el título y los botones
		
		//ContentPanel con los botones en la zona "CENTER"
		//El contentPanel usa GridLayout
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(3, 2, 10, 10));
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		
		
		//Botones de acceso a los distintos subsistemas
		BCliente = new JButton("Clientes");
		BCliente.addActionListener(null);
		contentPanel.add(BCliente);
		
		BDepartamento = new JButton("Departamentos");
		BDepartamento.addActionListener(null);
		contentPanel.add(BDepartamento);
		
		BFactura = new JButton("Facturas");
		BFactura.addActionListener(null);
		contentPanel.add(BFactura);
		
		BServicio = new JButton("Servicios");
		BServicio.addActionListener(null);
		contentPanel.add(BServicio);
		
		BTrabajador = new JButton("Trabajadores");
		BTrabajador.addActionListener(null);
		contentPanel.add(BTrabajador);
		
		BViaje = new JButton("Viajes");
		BViaje.addActionListener(null);
		contentPanel.add(BViaje);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub

	}
	
	

}
