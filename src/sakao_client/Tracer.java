package sakao_client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sakao_server.Display;

public class Tracer extends JFrame {

	private static final long serialVersionUID = 786392524822582941L;
	private Display display = new Display();
	
	
	Tracer() {
		this.setTitle("Tracer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.add(new Display(),BorderLayout.CENTER);
		this.setSize((int)display.getWidthPX() + 25, (int)display.getHeightPX() + 50);
		this.setLocationRelativeTo(null);

		this.setVisible(true);

		
	}
	
	
	
	public static void main (String[] args) {
		Tracer tracer = new Tracer();
	}


}
