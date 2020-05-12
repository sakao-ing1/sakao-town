package sakao_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Listener implements ActionListener {
	Tracer t;

	public Listener(Tracer t) {
		this.t = t;
		this.t.getGenerateCity().addActionListener(this);
		this.t.getReload().addActionListener(this);
		this.t.getSave().addActionListener(this);
		this.t.getWidthB().addActionListener(this);
		this.t.getHeightB().addActionListener(this);
		this.t.getBudget().addActionListener(this);
		this.t.getStationCost().addActionListener(this);
		this.t.getCreateCity().addActionListener(this);
		
		
		
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(this.t.getCreateCity())) {
			try{
				 
				double w = Double.parseDouble(this.t.getWidthB().getText());
				double h = Double.parseDouble(this.t.getHeightB().getText());
				int b = Integer.parseInt(this.t.getBudget().getText());
				int c = Integer.parseInt(this.t.getStationCost().getText());
				
	
				if (w < 1.0 || h < 1.0 || b < 1 || c < 1 || c > b) {
					JOptionPane.showMessageDialog(null, "At least one of the field is not correct", "Error message",
							JOptionPane.ERROR_MESSAGE);
	
				}
	
				else {
					this.t.getDisplay().setWidthKM(w);
					this.t.getDisplay().setHeightKM(h);
					this.t.getDisplay().setCityBudget(b);
					this.t.getDisplay().setaStationCost(c);
	
					this.t.getDisplay().setShouldRun(true);
					this.t.getContentPaneCenter().setVisible(true);
	
					this.t.getReload().setEnabled(true);
					this.t.getSave().setEnabled(true);
	
					this.t.getCreateCity().setEnabled(false);
					
				}
			}
			catch(NumberFormatException ex){ // handle your exception
				JOptionPane.showMessageDialog(null, "At least one of the field's type is not correct", "Error message",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		 if (e.getSource().equals(this.t.getReload())) {
			double w = Double.parseDouble(this.t.getWidthB().getText());
			double h = Double.parseDouble(this.t.getHeightB().getText());
			int b = Integer.parseInt(this.t.getBudget().getText());
			int c = Integer.parseInt(this.t.getStationCost().getText());
			this.t.getDisplay().setWidthKM(w);
			this.t.getDisplay().setHeightKM(h);
			this.t.getDisplay().setCityBudget(b);
			this.t.getDisplay().setaStationCost(c);
			this.t.getDisplay().repaint();
		}
		


	}

}

/*
 * contentPaneTop.add(GenerateCity); contentPaneTop.add(Reload);
 * contentPaneTop.add(Save); contentPaneTop.add(Width);
 * contentPaneTop.add(Height); contentPaneTop.add(Budget);
 * contentPaneTop.add(StationCost); contentPaneTop.add(CreateCity);
 */