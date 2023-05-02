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
import negocio.trabajador.TVendedor;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaBuscarTrabajador extends JFrame implements IGUI{

private static final long serialVersionUID = 1L;
	
	private JLabel lId;
	private JTextField tId;
	private JButton ok;
	
	public VistaBuscarTrabajador() {
		super("Buscar Trabajador");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setContentPane(mainPanel);
		
		JPanel fila1 = new JPanel();
		fila1.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila1);
		JPanel fila2 = new JPanel();
		fila2.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(fila2);
		
		lId = new JLabel("Id del trabajador:");
		lId.setPreferredSize(new Dimension(100, 25));
		tId = new JTextField(10);
		fila1.add(lId);
		fila1.add(tId);
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int Iid;
					
					try {
						Iid = Integer.parseInt(tId.getText());
					}catch(NumberFormatException ex) {
						throw new IllegalArgumentException("El id del trabajador debe ser un entero", ex);
					}
					
					Controlador.getInstancia().accion(Eventos.BUSCAR_TRABAJADOR, Iid);
					
				}catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaBuscarTrabajador.this), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
		case(Eventos.RES_BUSCAR_TRABAJADOR_OK):
			TTrabajador trabajador = (TTrabajador) datos;
			setVisible(false);
			StringBuilder str = new StringBuilder();
			str.append("Trabajador encontrado con datos: ").append(System.lineSeparator());
			str.append("Id: " + trabajador.getId()).append(System.lineSeparator());
			str.append("Nombre: " + trabajador.getNombre()).append(System.lineSeparator());
			str.append("Sueldo: " + trabajador.getSueldo()).append(System.lineSeparator());
			str.append("IdDepartamento " + trabajador.getIdDepart()).append(System.lineSeparator());
			str.append("Tipo: " + trabajador.getTipo()).append(System.lineSeparator());
			if (trabajador.getTipo().equals("vendedor")) {
				str.append("IdJefe: " + ((TVendedor)trabajador).getIdJefe()).append(System.lineSeparator());
			}
			JOptionPane.showMessageDialog(Utils.getWindow(this), str , "Trabajador encontrado", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case(Eventos.RES_BUSCAR_TRABAJADOR_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo encontrar el trabajador", "Error", JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
		
	}
	
}
