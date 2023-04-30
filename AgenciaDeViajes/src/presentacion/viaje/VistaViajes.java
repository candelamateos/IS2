package presentacion.viaje;

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

public class VistaViajes extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;

	private JButton BAniadirViaje;
	private JButton BBuscarViaje;
	private JButton BEliminarViaje;
	private JButton BListarViaje;
	private JButton BModificarViaje;
	
	public VistaViajes() {
		super("Viajes");
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
		
		
		BAniadirViaje = new JButton("A�adir viaje");
		BAniadirViaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_VIAJE);
			}	
		});
		BAniadirViaje.setPreferredSize(new Dimension(130,30));
		fila1.add(BAniadirViaje);
		
		
		BBuscarViaje = new JButton("Buscar Viaje");
		BBuscarViaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_VIAJE);
			}			
		});
		BBuscarViaje.setPreferredSize(new Dimension(130,30));
		fila1.add(BBuscarViaje);
		
		
		BEliminarViaje = new JButton("Eliminar Viaje");
		BEliminarViaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BAJA_VIAJE);
			}			
		});
		BEliminarViaje.setPreferredSize(new Dimension(130,30));
		fila2.add(BEliminarViaje);
		
		
		BListarViaje = new JButton("Listar Viajes");
		BListarViaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.LISTAR_VIAJE);
			}			
		});
		BListarViaje.setPreferredSize(new Dimension(130,30));
		fila2.add(BListarViaje);
		
		BModificarViaje = new JButton("Modificar Viaje");
		BModificarViaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_VIAJE);
			}
		});
		BModificarViaje.setPreferredSize(new Dimension(130,30));
		fila3.add(BModificarViaje);
		
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		
	}

}
