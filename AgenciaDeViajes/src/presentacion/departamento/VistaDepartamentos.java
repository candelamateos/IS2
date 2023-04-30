package presentacion.departamento;

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

public class VistaDepartamentos extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;

	private JButton BAniadirDepartamento;
	private JButton BBuscarDepartamento;
	private JButton BEliminarDepartamento;
	private JButton BListarDepartamento;
	private JButton BModificarDepartamento;

	public VistaDepartamentos() {
		super("Departamento");
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

		BAniadirDepartamento = new JButton("Añadir Departamento");
		BAniadirDepartamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_DEPARTAMENTO);
			}
		});
		BAniadirDepartamento.setPreferredSize(new Dimension(130, 30));
		fila1.add(BAniadirDepartamento);

		BBuscarDepartamento = new JButton("Buscar Departamento");
		BBuscarDepartamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_DEPARTAMENTO);
			}
		});
		BBuscarDepartamento.setPreferredSize(new Dimension(130, 30));
		fila1.add(BBuscarDepartamento);

		BEliminarDepartamento = new JButton("Eliminar Departamento");
		BEliminarDepartamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BAJA_DEPARTAMENTO);
			}
		});
		BEliminarDepartamento.setPreferredSize(new Dimension(130, 30));
		fila2.add(BEliminarDepartamento);

		BListarDepartamento = new JButton("Listar Departamentos");
		BListarDepartamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.LISTAR_DEPARTAMENTO);
			}
		});
		BListarDepartamento.setPreferredSize(new Dimension(130, 30));
		fila2.add(BListarDepartamento);

		BModificarDepartamento = new JButton("Modificar Departamento");
		BModificarDepartamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_DEPARTAMENTO);
			}
		});
		BModificarDepartamento.setPreferredSize(new Dimension(130, 30));
		fila3.add(BModificarDepartamento);

		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub

	}

}
