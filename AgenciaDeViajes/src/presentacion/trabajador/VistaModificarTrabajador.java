package presentacion.trabajador;

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

import negocio.trabajador.TTrabajador;
import negocio.viaje.TViaje;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaModificarTrabajador extends JFrame implements IGUI{
private static final long serialVersionUID = 1L;
	
	private JLabel lId;
	private JTextField tId;
	private JLabel lNombre;
	private JTextField tNombre;
	private JLabel lSueldo;
	private JTextField tSueldo;
	private JLabel lIdDepart;
	private JTextField tIdDepart;
	private JLabel lTipo;
	private JTextField tTipo;
	private JLabel lIdJefe;
	private JTextField tIdJefe;
	private JButton ok;
	
	public VistaModificarTrabajador() {
		super("Modificar Trabajador");
		initGUI();
	}
	
	private void initGUI() {
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
		JPanel fila3 = new JPanel();
		fila3.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila3);
		JPanel fila4 = new JPanel();
		fila4.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila4);
		JPanel fila5 = new JPanel();
		fila5.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila5);
		JPanel fila6 = new JPanel();
		fila5.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila6);
		
		
		lId= new JLabel("Id del viaje:");
		lId.setPreferredSize(new Dimension(100, 25));
		tId = new JTextField(10);
		fila0.add(lId);
		fila0.add(tId);
		
		lNombre = new JLabel("Nombre:");
		lNombre.setPreferredSize(new Dimension(100, 25));
		tNombre = new JTextField(10);
		fila1.add(lNombre);
		fila1.add(tNombre);
		
		lSueldo= new JLabel("Sueldo:");
		lSueldo.setPreferredSize(new Dimension(100, 25));
		tSueldo = new JTextField(10);
		fila2.add(lSueldo);
		fila2.add(tSueldo);
		
		lIdDepart = new JLabel("Id del departamento:");
		lIdDepart.setPreferredSize(new Dimension(100, 25));
		tIdDepart = new JTextField(10);
		fila3.add(lIdDepart);
		fila3.add(tIdDepart);
		
		lTipo = new JLabel("Tipo:");
		lTipo.setPreferredSize(new Dimension(100, 25));
		tTipo = new JTextField(10);
		fila4.add(lTipo);
		fila4.add(tTipo);
		
		lIdJefe = new JLabel("Id del jefe:");
		lIdJefe.setPreferredSize(new Dimension(100, 25));
		tIdJefe = new JTextField(10);
		fila5.add(lIdJefe);
		fila5.add(tIdJefe);
		
		ok = new JButton("OK");
		mainPanel.add(ok);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int Iid;
					String Inombre;
					int Isueldo;
					int IidDepart;
					String Itipo;	
					int IidJefe;
					
					try{
						Iid = Integer.parseInt(tId.getText());
					}catch(NumberFormatException ex) {
						throw new IllegalArgumentException("El id del viaje debe ser un numero", ex);
					}
					
					try{
						Inombre = tNombre.getText();
					}catch(NumberFormatException ex) {
						throw new IllegalArgumentException("El numero de plazas debe ser un entero", ex);
					}
					
					try {
						Isueldo = Integer.parseInt(tSueldo.getText());
					}catch(NumberFormatException ex) {
						throw new IllegalArgumentException("El id de la actividad debe ser un numero", ex);
					}
					
					try {
						IidDepart = Integer.parseInt(tIdDepart.getText());
					}catch(NumberFormatException ex) {
						throw new IllegalArgumentException("El id del alojamiento debe ser un numero", ex);
					}
					
					try {
						Itipo = tTipo.getText();
					}catch(NumberFormatException ex) {
						throw new IllegalArgumentException("El id del transporte debe ser un numero", ex);
					}
					
					if (Itipo.equals("Vendedor") || Itipo.equals("vendedor")) {
						try {
							IidJefe = Integer.parseInt(tIdJefe.getText());
						}catch(NumberFormatException ex) {
							throw new IllegalArgumentException("El id del jefe debe ser un entero", ex);
						}
					}
					else {
						IidJefe = 0;
					}
					
					TTrabajador trabajador = new TTrabajador(Inombre, Isueldo, IidDepart, Itipo, IidJefe);
					trabajador.setId(Iid);
					Controlador.getInstancia().accion(Eventos.MODIFICAR_VIAJE, trabajador);
				}catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaModificarTrabajador.this), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
				
			}
		});
		fila6.add(ok);
		
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Eventos.RES_MODIFICAR_TRABAJADOR_OK):{
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Trabajador modificado con id " + datos, "Trabajador modificado", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		}
		case(Eventos.RES_MODIFICAR_TRABAJADOR_ERROR):{
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo modificar al trabajador", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}
		
	}

}
