package presentacion.servicio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import integracion.factoria.FactoriaAbstractaIntegracion;
import presentacion.IGUI;
import presentacion.controlador.Eventos;
import presentacion.factoria.FactoriaAbstractaPresentacion;

public class VistaServicios extends JFrame implements IGUI{
	
	private JButton button_create = new JButton();
	private JButton button_delete = new JButton();
	private JButton button_update = new JButton();
	private JButton button_read = new JButton();
	private JButton button_readAll = new JButton();
	
	public static final Color COLOR_FONDO = Color.white;//new Color(204, 243, 170);
	
	
	public VistaServicios() {
		super("servicios");
		initGUI();
	}
	
	private void initGUI() {
		setPreferredSize(new Dimension(600, 400));
		JPanel mainPanel = new JPanel();
		this.setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(COLOR_FONDO);
		
		
		add(Box.createGlue());
		JPanel fila1 = new JPanel();
		mainPanel.add(fila1);
		fila1.setOpaque(false);
		fila1.setLayout(new FlowLayout());
		fila1.add(Box.createGlue());
		fila1.add(new ButtonPanel(button_create, "anyadir servicio"));
		fila1.add(Box.createGlue());
		fila1.add(new ButtonPanel(button_delete, "eliminar servicio"));
		fila1.add(Box.createGlue());
		fila1.add(new ButtonPanel(button_update, "actualizar servicio"));
		fila1.add(Box.createGlue());
		
		add(Box.createGlue());
		JPanel fila2 = new JPanel();
		mainPanel.add(fila2);
		fila2.setOpaque(false);
		fila2.add(Box.createGlue());
		fila2.add(new ButtonPanel(button_read, "buscar servicio"));
		fila2.add(Box.createGlue());
		fila2.add(new ButtonPanel(button_readAll, "listar servicios"));
		fila2.add(Box.createGlue());
		
		button_create.setForeground(Color.green);
		button_create.setText("+");
		button_create.setFont(new Font("default", Font.BOLD, 100));
		button_create.setBorder(BorderFactory.createEmptyBorder());
		button_create.addActionListener(e -> {FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_SERVICIO);});
		
		button_delete.setForeground(Color.red);
		button_delete.setText("-");
		button_delete.setFont(new Font("default", Font.BOLD, 100));
		button_delete.setBorder(BorderFactory.createEmptyBorder());
		button_delete.addActionListener(e -> {FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BAJA_SERVICIO);});
		
		add(Box.createGlue());
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		
	}
	
	class ButtonPanel extends JPanel{
		private JButton button;
		private JLabel label;
		public ButtonPanel(JButton button, String label) {
			super();
			this.button = button;
			this.label = new JLabel(label, JLabel.CENTER);
			this.label.setFont(new Font("default", Font.BOLD, 14));
			this.setOpaque(false);
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createLineBorder(Color.black, 3));
			add(this.button, BorderLayout.CENTER);
			add(this.label, BorderLayout.NORTH);
			pack();
			setPreferredSize(new Dimension(Math.max(160, this.label.getWidth()), 120));
		}
		
	}

}
