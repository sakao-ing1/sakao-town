package sakao_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import sakao_server.Display;

public class Tracer extends JFrame {

	private static final long serialVersionUID = 786392524822582941L;
	private Display display = new Display();
	private JButton CreateCity = new JButton("Create city");
	private JButton GenerateCity = new JButton("Generate city");
	private JButton Reload = new JButton("Reload");
	private JButton Save = new JButton("Save");

	private JTextField WidthB = new JTextField("Width");
	private JTextField HeightB = new JTextField("Height");
	private JTextField Budget = new JTextField("Budget");
	private JTextField 	StationCost = new JTextField("StationCost");
	private JPanel contentPaneCenter;
	private JPanel contentPaneTop;
	
	
	Tracer() {
		this.setTitle("Sakao City Tracer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPaneCenter = new JPanel(new BorderLayout());
		
		contentPaneTop = new JPanel(new GridLayout( 1, 2, 10, 0 ));
		contentPaneTop.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ));
		 

		 this.getContentPane().add(contentPaneCenter,BorderLayout.CENTER);
		 this.getContentPane().add(contentPaneTop,BorderLayout.NORTH);

		 this.MouseEvent();
		contentPaneTop.add(GenerateCity);
		contentPaneTop.add(Reload);
		contentPaneTop.add(Save);
		contentPaneTop.add(WidthB);
		contentPaneTop.add(HeightB);
		contentPaneTop.add(Budget);
		contentPaneTop.add(StationCost);
		contentPaneTop.add(CreateCity);

		
		GenerateCity.setEnabled(false);
		Reload.setEnabled(false);
		Save.setEnabled(false);

		
		
		contentPaneTop.setVisible(true);
		
		
		contentPaneCenter.add(display,BorderLayout.CENTER);
		contentPaneCenter.setVisible(false);


		
		this.setSize(1000,1000);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		
		
		
        /*super( "Curve tracer" );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        
        JPanel contentPane = (JPanel) this.getContentPane();
        
        JPanel pnlTop = new JPanel( new GridLayout( 1, 2, 10, 0 ) );
        pnlTop.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
        btnSinus.addActionListener( this::btnSinusListener );
        pnlTop.add( btnSinus );
        btnCosinus.addActionListener( this::btnCosinusListener );
        pnlTop.add( btnCosinus );
        contentPane.add( pnlTop, BorderLayout.NORTH );
        
        contentPane.add( curveCanvas, BorderLayout.CENTER );
        
        
        this.setSize( 400, 470 );
        this.setLocationRelativeTo( null );
        
        JTextFiled textFiled = new JtextField("saisir du texte")
textFiled.getFont().deriveFont(Font.ITALIC);
textFiled.setForeground(Color.gray);



textFiled.addMouseListener(new MouseListener() {           
    @Override
    	public void mouseReleased(MouseEvent e) {}         
    @Override
    	public void mousePressed(MouseEvent e) {}          
    @Override
    	public void mouseExited(MouseEvent e) {}           
    @Override
    	public void mouseEntered(MouseEvent e) {}          
    @Override
    	public void mouseClicked(MouseEvent e) {
        JTextField texteField = ((JTextField)e.getSource());
        texteField.setText("");
        texteField.getFont().deriveFont(Font.PLAIN);
        texteField.setForeground(Color.black);
        texteField.removeMouseListener(this);
    }
});
*/
		
	}
	
	
	
	public static void main (String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		Tracer tracer = new Tracer();
		Listener l = new Listener(tracer);
	}
	
	
	public void MouseEvent() {
		 WidthB.getFont().deriveFont(Font.ITALIC);
		 WidthB.setForeground(Color.gray);
		 HeightB.getFont().deriveFont(Font.ITALIC);
		 HeightB.setForeground(Color.gray);
		 Budget.getFont().deriveFont(Font.ITALIC);
		 Budget.setForeground(Color.gray);
		 StationCost.getFont().deriveFont(Font.ITALIC);
		 StationCost.setForeground(Color.gray);
		 WidthB.addMouseListener(new MouseListener() {           
			    @Override
			    public void mouseReleased(MouseEvent e) {}         
			    @Override
			    public void mousePressed(MouseEvent e) {}          
			    @Override
			    public void mouseExited(MouseEvent e) {}           
			    @Override
			    public void mouseEntered(MouseEvent e) {}          
			    @Override
			    public void mouseClicked(MouseEvent e) {
			         WidthB = ((JTextField)e.getSource());
			        WidthB.setText("");
			        WidthB.getFont().deriveFont(Font.PLAIN);
			        WidthB.setForeground(Color.black);
			        WidthB.removeMouseListener(this);
			    }
			    
		 });
		 
		 
		 HeightB.addMouseListener(new MouseListener() {           
			    @Override
			    public void mouseReleased(MouseEvent e) {}         
			    @Override
			    public void mousePressed(MouseEvent e) {}          
			    @Override
			    public void mouseExited(MouseEvent e) {}           
			    @Override
			    public void mouseEntered(MouseEvent e) {}          
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	HeightB = ((JTextField)e.getSource());
			    	HeightB.setText("");
			    	HeightB.getFont().deriveFont(Font.PLAIN);
			    	HeightB.setForeground(Color.black);
			    	HeightB.removeMouseListener(this);
			    }
			    
		 });
		 
		 Budget.addMouseListener(new MouseListener() {           
			    @Override
			    public void mouseReleased(MouseEvent e) {}         
			    @Override
			    public void mousePressed(MouseEvent e) {}          
			    @Override
			    public void mouseExited(MouseEvent e) {}           
			    @Override
			    public void mouseEntered(MouseEvent e) {}          
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	Budget = ((JTextField)e.getSource());
			    	Budget.setText("");
			    	Budget.getFont().deriveFont(Font.PLAIN);
			    	Budget.setForeground(Color.black);
			    	Budget.removeMouseListener(this);
			    }
			    
		 });
		 
		 
		 StationCost.addMouseListener(new MouseListener() {           
			    @Override
			    public void mouseReleased(MouseEvent e) {}         
			    @Override
			    public void mousePressed(MouseEvent e) {}          
			    @Override
			    public void mouseExited(MouseEvent e) {}           
			    @Override
			    public void mouseEntered(MouseEvent e) {}          
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	StationCost = ((JTextField)e.getSource());
			    	StationCost.setText("");
			    	StationCost.getFont().deriveFont(Font.PLAIN);
			    	StationCost.setForeground(Color.black);
			    	StationCost.removeMouseListener(this);
			    }
			    
		 });
	}



	public Display getDisplay() {
		return display;
	}



	public void setDisplay(Display display) {
		this.display = display;
	}



	public JButton getCreateCity() {
		return CreateCity;
	}



	public void setCreateCity(JButton createCity) {
		CreateCity = createCity;
	}



	public JButton getGenerateCity() {
		return GenerateCity;
	}



	public void setGenerateCity(JButton generateCity) {
		GenerateCity = generateCity;
	}



	public JButton getReload() {
		return Reload;
	}



	public void setReload(JButton reload) {
		Reload = reload;
	}



	public JButton getSave() {
		return Save;
	}



	public void setSave(JButton save) {
		Save = save;
	}



	public JTextField getWidthB() {
		return WidthB;
	}



	public void setWidthB(JTextField width) {
		WidthB = width;
	}



	public JTextField getHeightB() {
		return HeightB;
	}



	public void setHeightB(JTextField height) {
		HeightB = height;
	}



	public JTextField getBudget() {
		return Budget;
	}



	public void setBudget(JTextField budget) {
		Budget = budget;
	}



	public JTextField getStationCost() {
		return StationCost;
	}



	public void setStationCost(JTextField stationCost) {
		StationCost = stationCost;
	}



	public JPanel getContentPaneCenter() {
		return contentPaneCenter;
	}



	public void setContentPaneCenter(JPanel contentPaneCenter) {
		this.contentPaneCenter = contentPaneCenter;
	}



	public JPanel getContentPaneTop() {
		return contentPaneTop;
	}



	public void setContentPaneTop(JPanel contentPaneTop) {
		this.contentPaneTop = contentPaneTop;
	}


}
