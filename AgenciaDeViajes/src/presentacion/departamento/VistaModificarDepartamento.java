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

public class VistaModificarDepartamento extends JFrame implements IGUI {

	private JLabel lId;
	private JTextField tId;
	private JLabel lNombre;
	private JTextField tNombre;
	private JLabel lNumEmpleados;
	private JTextField tNumEmpleados;
	private JButton ok;

	public VistaModificarDepartamento() {
		super("MODIFICAR DEPARTAMENTO");
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
		
		lId= new JLabel("Id del departamento:");
		lId.setPreferredSize(new Dimension(100, 25));
		tId = new JTextField(10);
		fila0.add(lId);
		fila0.add(tId);
		
		lNombre= new JLabel("Nombre del departamento:");
		lNombre.setPreferredSize(new Dimension(100, 25));
		tNombre = new JTextField(10);
		fila1.add(lNombre);
		fila1.add(tNombre);
		
		lNumEmpleados= new JLabel("Numero de empleados:");
		lNumEmpleados.setPreferredSize(new Dimension(100, 25));
		tNumEmpleados = new JTextField(10);
		fila2.add(lNumEmpleados);
		fila2.add(tNumEmpleados);
		
		ok = new JButton("OK");
		mainPanel.add(ok);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int Iid;
					int InumEmpleados;
					String Snombre="";
					try {
						Iid = Integer.parseInt(tId.getText());
					} catch (NumberFormatException ex) {
						tId.setText("");
						throw new IllegalArgumentException("El id debe ser un numero entero", ex);
					}
					try {
						Snombre = tNombre.getText();	
					}catch(IllegalArgumentException ex) {
						tNombre.setText("");
						JOptionPane.showMessageDialog(Utils.getWindow(VistaModificarDepartamento.this), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						setVisible(true);
					}
					try{
						InumEmpleados = Integer.parseInt(tNumEmpleados.getText());
					}catch(NumberFormatException ex) {
						tNumEmpleados.setText("");
						throw new IllegalArgumentException("El numero de empleados debe ser un entero", ex);
					}
					TDepartamento departamento = new TDepartamento(Snombre);
					departamento.setId(Iid);
					departamento.setNumEmpleados(InumEmpleados);
					Controlador.getInstancia().accion(Eventos.MODIFICAR_DEPARTAMENTO, departamento);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Utils.getWindow(VistaModificarDepartamento.this), ex.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
		fila3.add(ok);

		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case (Eventos.RES_MODIFICAR_DEPARTAMENTO_OK):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "Departamento modificado correctamente",
					"Departamento modificado", JOptionPane.INFORMATION_MESSAGE);
			setVisible(true);
			break;
		case (Eventos.RES_MODIFICAR_DEPARTAMENTO_ERROR):
			setVisible(false);
			JOptionPane.showMessageDialog(Utils.getWindow(this), "No se pudo modificar el departamento", "Error",
					JOptionPane.ERROR_MESSAGE);
			setVisible(true);
			break;
		}
	}

}
