package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentacion.controlador.Eventos;
import presentacion.factoria.FactoriaAbstractaPresentacion;

public class MainWindow extends JFrame implements IGUI {

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
		mainPanel.setPreferredSize(new Dimension(700, 340));
		this.setContentPane(mainPanel);
		
		
		//Cabecera con el t�tulo en zona "NORTH"
		JPanel cabecera = new JPanel();
		
		cabecera.setLayout(new BoxLayout(cabecera, BoxLayout.Y_AXIS));
		mainPanel.add(cabecera, BorderLayout.NORTH);
		JLabel foto = new JLabel();
		foto.setPreferredSize(new Dimension(300, 130));
		foto.setAlignmentX(CENTER_ALIGNMENT);
		foto.setIcon(loadImage("lib/TITULO.JPG.png"));
		cabecera.add(foto);
		
		/*JLabel titulo = new JLabel(TITULO);
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		cabecera.add(titulo);
		cabecera.add(Box.createVerticalStrut(10)); //Espacio entre el t�tulo y los botones
		*/
		//ContentPanel con los botones en la zona "CENTER"
		//El contentPanel usa GridLayout
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(3, 2, 10, 10));
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		
		
		//Botones de acceso a los distintos subsistemas
		BCliente = new JButton("Clientes");
		BCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.CLIENTES);
			}
		});
		contentPanel.add(BCliente);
		
		BDepartamento = new JButton("Departamentos");
		BDepartamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.DEPARTAMENTO);
			}
		});
		contentPanel.add(BDepartamento);
		
		BFactura = new JButton("Facturas");
		BFactura.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.FACTURAS);
			}
		});
		contentPanel.add(BFactura);
		
		BServicio = new JButton("Servicios");
		BServicio.addActionListener(e -> {FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.SERVICIOS);});
		contentPanel.add(BServicio);
		
		BTrabajador = new JButton("Trabajadores");
		BTrabajador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.TRABAJADORES);
			}
			
		});
		contentPanel.add(BTrabajador);
		
		BViaje = new JButton("Viajes");
		BViaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.VIAJES);
			}
		});
		contentPanel.add(BViaje);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
	}

	protected ImageIcon loadImage(String path) {
		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(path));
	}
	

}
