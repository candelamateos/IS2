package launcher;

import javax.swing.SwingUtilities;

import presentacion.MainWindow;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MainWindow());
	}
	
}
