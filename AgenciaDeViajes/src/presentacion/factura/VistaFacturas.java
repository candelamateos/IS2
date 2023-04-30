package presentacion.factura;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.IGUI;
import presentacion.controlador.Eventos;
import presentacion.factoria.FactoriaAbstractaPresentacion;

public class VistaFacturas extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	private JButton BAbrirVenta;
	private JButton BCerrarVenta;
	private JButton BAniadirViaje;
	private JButton BModificarFactura;
	private JButton BBuscarFactura;
	private JButton BListarFacturas;
	
	public VistaFacturas() {
		super("Facturas");
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
		
		BAbrirVenta = new JButton("Abrir Venta");
		BAbrirVenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_CLIENTE);
			}	
		});
		BAbrirVenta.setPreferredSize(new Dimension(130,30));
		fila1.add(BAbrirVenta);
		
		BCerrarVenta = new JButton("Cerrar Venta");
		BCerrarVenta.addActionListener(null);
		BCerrarVenta.setPreferredSize(new Dimension(130,30));
		fila1.add(BCerrarVenta);
		
		BAniadirViaje = new JButton("Añadir Venta");
		BAniadirViaje.addActionListener(null);
		BAniadirViaje.setPreferredSize(new Dimension(130,30));
		fila2.add(BAniadirViaje);
		
		BModificarFactura = new JButton("Modificar Factura");
		BModificarFactura.addActionListener(null);
		BModificarFactura.setPreferredSize(new Dimension(130,30));
		fila2.add(BModificarFactura);
		
		BBuscarFactura = new JButton("Buscar Factura");
		BBuscarFactura.addActionListener(null);
		BBuscarFactura.setPreferredSize(new Dimension(130,30));
		fila3.add(BBuscarFactura);
		
		BListarFacturas = new JButton("Listar Facturas");
		BListarFacturas.addActionListener(null);
		BListarFacturas.setPreferredSize(new Dimension(130,30));
		fila3.add(BListarFacturas);
		
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub

	}

}
