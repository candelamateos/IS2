package presentacion.departamento;

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

import negocio.departamento.TDepartamento;
import presentacion.IGUI;
import presentacion.Utils;
import presentacion.controlador.Controlador;
import presentacion.controlador.Eventos;

public class VistaAniadirDepartamento extends JFrame implements IGUI {
	private JLabel lNombre;
	private JTextField tNombre;
	private JButton ok;

	public VistaAniadirDepartamento() {
		super("A�adir Cliente");
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
				
		lNombre = new JLabel("NOMBRE:");
		lNombre.setPreferredSize(new Dimension(100, 25));
		tNombre = new JTextField(10);
		fila1.add(lNombre);
		fila1.add(tNombre);

		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					String Snombre;
					try{
						Snombre = tNombre.getText();
					}catch(NullPointerException  ex) {
						throw new IllegalArgumentException("El nombre no puede estar vacío", ex);
					}
					
					Controlador.getInstancia().accion(Eventos.ALTA_DEPARTAMENTO, new TDepartamento(Snombre));
				}catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaAniadirDepartamento.this), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
		switch (evento) {
		case (Eventos.RES_ALTA_DEPARTAMENTO_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Departamento a�adido con id " + datos,
					"Departamento a�adido", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case (Eventos.RES_ALTA_DEPARTAMENTO_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo a�adir el departamento", "Error",
					JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}

}
