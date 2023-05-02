package presentacion.trabajador;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.IGUI;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;
import presentacion.factoria.FactoriaAbstractaPresentacion;

public class VistaTrabajadores extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;

	private JButton BAniadirTrabajador;
	private JButton BBuscarTrabajador;
	private JButton BEliminarTrabajador;
	private JButton BListarTrabajador;
	private JButton BModificarTrabajador;

	public VistaTrabajadores() {
		super("Trabajadores");
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

		BAniadirTrabajador = new JButton("Anyadir Trabajador");
		BAniadirTrabajador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_TRABAJADOR);
			}
		});
		BAniadirTrabajador.setPreferredSize(new Dimension(130, 30));
		fila1.add(BAniadirTrabajador);

		BBuscarTrabajador = new JButton("Buscar Trabajador");
		BBuscarTrabajador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_TRABAJADOR);
			}
		});
		BBuscarTrabajador.setPreferredSize(new Dimension(130, 30));
		fila1.add(BBuscarTrabajador);

		BEliminarTrabajador = new JButton("Eliminar Trabajador");
		BEliminarTrabajador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BAJA_TRABAJADOR);
			}
			});
		
		BEliminarTrabajador.setPreferredSize(new Dimension(130, 30));
		fila2.add(BEliminarTrabajador);
	
		
		BListarTrabajador = new JButton("Listar Trabajadores");
		BListarTrabajador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstancia().accion(Eventos.LISTAR_TRABAJADOR, null);
			}
		});
		BListarTrabajador.setPreferredSize(new Dimension(130, 30));
		fila2.add(BListarTrabajador);

		BModificarTrabajador = new JButton("Modificar Trabajador");
		BModificarTrabajador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_TRABAJADOR);
			}
		});
		BModificarTrabajador.setPreferredSize(new Dimension(130, 30));
		fila3.add(BModificarTrabajador);

		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub

	}

}
