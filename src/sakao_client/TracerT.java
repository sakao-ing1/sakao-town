package sakao_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.Request;
import sakao_common.Response;
import sakao_common.SmartCity;
import sakao_common.Station;

public class TracerT extends JFrame implements ActionListener {

	private static final long serialVersionUID = 786392524822582941L;
	private Display display = new Display();
	private JButton SimulateCity = new JButton("Simulate city");
	private JButton GenerateCity = new JButton("Generate city");
	private JButton Reload = new JButton("Reload");
	private JButton Save = new JButton("Save");
	private JButton RemoveCity = new JButton("RemoveCity");


	private JTextField WidthB = new JTextField("Width");
	private JTextField HeightB = new JTextField("Height");
	private JTextField Budget = new JTextField("Budget");
	private JTextField 	StationCost = new JTextField("StationCost");
	private JPanel contentPaneCenter;
	private JPanel contentPaneTop;
	
	
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Response response = new Response();
	private final static String INSERT = "INSERT";
	private ObjectMapper mapper;
	String string;
	
	TracerT() throws IOException {
		this.setTitle("Sakao City Tracer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		contentPaneCenter = new JPanel(new BorderLayout());
		
		contentPaneTop = new JPanel(new GridLayout( 1, 2, 10, 0 ));
		contentPaneTop.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ));
		 

		 this.getContentPane().add(contentPaneCenter,BorderLayout.CENTER);
		 this.getContentPane().add(contentPaneTop,BorderLayout.NORTH);
			RemoveCity.addActionListener(this);
			GenerateCity.addActionListener(this);
			Reload.addActionListener(this);
			Save.addActionListener(this);
			SimulateCity.addActionListener(this);
		 this.MouseEvent();
		contentPaneTop.add(RemoveCity);
		contentPaneTop.add(GenerateCity);
		contentPaneTop.add(Reload);
		contentPaneTop.add(Save);
		contentPaneTop.add(WidthB);
		contentPaneTop.add(HeightB);
		contentPaneTop.add(Budget);
		contentPaneTop.add(StationCost);
		contentPaneTop.add(SimulateCity);
		
		/*Request request = new Request("IS_CITY","smartcity");
		if(this.sendMessageToServer(request)) {
			RemoveCity.setEnabled(true);
			GenerateCity.setEnabled(true);
			SimulateCity.setEnabled(false);

		}*/
		/////else {
			RemoveCity.setEnabled(false);
			GenerateCity.setEnabled(false);
			Reload.setEnabled(false);
			Save.setEnabled(false);
			SimulateCity.setEnabled(true);
		/////}

		
		contentPaneTop.setVisible(true);

		contentPaneCenter.add(display, BorderLayout.CENTER);
		contentPaneCenter.setVisible(false);

		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	public void startConnection(String ip, int port) throws IOException, JSONException {
		System.out.println("waiting for connection in to the server");
		clientSocket = new Socket(ip, port);
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		System.out.println("connection succeed");

	}
	

	
	public ArrayList<String> sendMessageToServer(Request request) throws IOException {
		mapper = new ObjectMapper();
		String outjsonString = mapper.writeValueAsString(request);
		System.out.println("REQUEST SENT");
		System.out.println(outjsonString);
		System.out.println(" _____");
		out.write(outjsonString + "\n");
		out.flush();
		String injsonString = in.readLine();
		System.out.println(injsonString);
		System.out.println("");
		/*(int i = 0; i < this.getDisplay().getGraphPoints().size(); i++) {
			Station station = new Station();
			station = mapper.readValue(injsonString,Station.class);
			System.out.println(station.toString());
		}*/
		response = mapper.readValue(injsonString, Response.class);
		return response.getList();
	}
	
	public void isCity() {
		try {
			Request request = new Request("SELECT_ALL","smartcity");
			this.sendMessageToServer(request);
			if(this.response.getList().get(0).equals(null)) {
				RemoveCity.setEnabled(false);
				GenerateCity.setEnabled(false);
				Reload.setEnabled(false);
				Save.setEnabled(false);
				SimulateCity.setEnabled(true);
			}
			else {
				RemoveCity.setEnabled(true);
				GenerateCity.setEnabled(true);
				SimulateCity.setEnabled(false);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void CloseConnection() throws IOException {
		System.out.println("waiting for disconnection");
		out.close();
		in.close();
		clientSocket.close();
		System.out.println("disconnected");
	}
	
	
	
	public static void main (String[] args) throws UnsupportedLookAndFeelException, IOException, JSONException {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		TracerT tracer = new TracerT();
		tracer.startConnection("localhost", 3030);
	}



	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(this.getSimulateCity())) {
			try {
				System.out.println("jy suis");
				double w = Double.parseDouble(this.getWidthB().getText());
				double h = Double.parseDouble(this.getHeightB().getText());
				int b = Integer.parseInt(this.getBudget().getText());
				int c = Integer.parseInt(this.getStationCost().getText());

				if (w <= 0.0 || h <= 0.0 || b < 1 || c < 1 || c > b) {
					JOptionPane.showMessageDialog(null, "At least one of the field is not correct", "Error message",
							JOptionPane.ERROR_MESSAGE);
				}

				else {
					this.getDisplay().setShouldRunAlgo(true);
					this.getDisplay().setWidthKM(w);
					this.getDisplay().setHeightKM(h);
					this.getDisplay().setCityBudget(b);
					this.getDisplay().setaStationCost(c);
					this.getContentPaneCenter().setVisible(true);
					this.getReload().setEnabled(true);
					this.getSave().setEnabled(true);
					this.getSimulateCity().setEnabled(false);

				}
			} catch (NumberFormatException ex) { // handle your exception
				JOptionPane.showMessageDialog(null, "At least one of the field's type is not correct", "Error message",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		if (e.getSource().equals(this.getReload())) {
			try {
				double w = Double.parseDouble(this.getWidthB().getText());
				double h = Double.parseDouble(this.getHeightB().getText());
				int b = Integer.parseInt(this.getBudget().getText());
				int c = Integer.parseInt(this.getStationCost().getText());

				if (w <= 0.0 || h <= 0.0 || b < 1 || c < 1 || c > b) {
					JOptionPane.showMessageDialog(null, "At least one of the field is not correct", "Error message",
							JOptionPane.ERROR_MESSAGE);

				} else {
					this.getDisplay().setShouldRunAlgo(true);
					this.getDisplay().setWidthKM(w);
					this.getDisplay().setHeightKM(h);
					this.getDisplay().setCityBudget(b);
					this.getDisplay().setaStationCost(c);
					this.getDisplay().repaint();

				}
			} catch (NumberFormatException ex) { // handle your exception
				JOptionPane.showMessageDialog(null, "At least one of the field's type is not correct", "Error message",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
		if(e.getSource().equals(this.getSave())) {
			this.getSimulateCity().setEnabled(false);
			this.getReload().setEnabled(false);
			this.getGenerateCity().setEnabled(true);

			this.getSave().setEnabled(false);
			this.getRemoveCity().setEnabled(true);
			this.getContentPaneCenter().setVisible(false);
			this.getDisplay().setShouldRunAlgo(false);

			
			
				String w = this.getWidthB().getText();
				String h  = this.getHeightB().getText();
				String b = this.getBudget().getText();
				String c =this.getStationCost().getText();
				String s1="{";
				String s3="}";
			try {
				ArrayList<String> SmartCityList = new ArrayList<String>();
				SmartCityList.add(s1);
				SmartCityList.add("heightkm");
				SmartCityList.add(h);
				SmartCityList.add("width");
				SmartCityList.add(w);
				SmartCityList.add("budget");
				SmartCityList.add(b);
				SmartCityList.add("astationcost");
				SmartCityList.add(c);
				SmartCityList.add(s3);

				Request requestCity = new Request(INSERT, "smartcity", SmartCityList);

				this.sendMessageToServer(requestCity);
			} catch (IOException e1) {
					e1.printStackTrace();
				}
		
	
				

			try {
				for(int i = 0; i < this.getDisplay().getGraphPoints().size(); i ++) {
					ArrayList<String> StationList = new ArrayList<String>();
					Double doubleX = (this.getDisplay().getGraphPoints().get(i).getX());
					String  stringX = doubleX.toString();
					Double doubleY = (this.getDisplay().getGraphPoints().get(i).getY());
					String  stringY = doubleY.toString();
					StationList.add(s1);StationList.add("typestation");StationList.add("tramway");StationList.add("coordX");
					StationList.add(stringX);StationList.add("coordY");StationList.add(stringY);StationList.add("isbuilt");StationList.add("true");
					StationList.add(s3);
					Request requestStation = new Request(INSERT,"station",StationList);
					
					this.sendMessageToServer(requestStation);
					
				}
					/*StationList.add("tramway",true,this.getDisplay().getGraphPoints().get(i).x,this.getDisplay().getGraphPoints().get(i).y);
					Request request = new Request("insert","station",station);*/
					///this.sendMessagToServer(;

				}
			
				catch (IOException e1) {
					e1.printStackTrace();
				}
			JOptionPane.showMessageDialog(null, "You saved the city and stations", "City and stations saved !",
					JOptionPane.INFORMATION_MESSAGE);
		}

		
		
		if(e.getSource().equals(this.getRemoveCity())) {
			this.getRemoveCity().setEnabled(false);
			this.getSave().setEnabled(false);
			this.getSimulateCity().setEnabled(true);
			this.getGenerateCity().setEnabled(false);
			
			try {
				Request requestDeleteCity = new Request("DELETE_ALL","smartcity");
				this.sendMessageToServer(requestDeleteCity);
			}
			catch(IOException e1) {
				e1.printStackTrace();
			}
			
			
			try {
				Request requestDeleteStation = new Request("DELETE_ALL","station");
				this.sendMessageToServer(requestDeleteStation);
			}
			
						catch(IOException e1) {
				e1.printStackTrace();
			}
			this.getContentPaneCenter().setVisible(false);

			JOptionPane.showMessageDialog(null, "You remove the city and stations", "City and stations removed!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		if (e.getSource().equals(this.getGenerateCity())) { ///// LIRE LA BDD ET AFFICHER
			this.getRemoveCity().setEnabled(true);
			this.getSave().setEnabled(false);
			this.getSimulateCity().setEnabled(false);
			this.getGenerateCity().setEnabled(false);

			try {///// SELECT DEJA LES DIMENSIONS DE LA VILLE
				Request request = new Request("SELECT_DIMENSION", "smartcity");
				ArrayList<String> list = this.sendMessageToServer(request);
				ArrayList<SmartCity> listSmartCity = new ArrayList<SmartCity>();
				ArrayList<Double> listDimension = new ArrayList<Double>();
	
				
					String s = list.get(0) + "\n";
					 System.out.println("S: "+s);
					SmartCity req = new ObjectMapper().readValue(s, SmartCity.class);
					 double x = req.getHeightkm();
					 double y = req.getWidthkm();
					listSmartCity.add(req);
					listDimension.add(x);
					listDimension.add(y);
				this.getDisplay().setHeightKM(x);
				this.getDisplay().setWidthKM(y);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			try {///// SELECT LES COORDONNEES DES STATIONS
				Request request = new Request("SELECT_COORD", "station");
				ArrayList<String> list = this.sendMessageToServer(request);
				ArrayList<Station> listStation = new ArrayList<Station>();
				ArrayList<Point2D.Double> listCoord = new ArrayList<Point2D.Double>();
				for (int i = 0; i < list.size(); i++) {
					String s = list.get(i) + "\n";
					/// System.out.println("S: "+s);
					Station req1 = new ObjectMapper().readValue(s, Station.class);
					double x = req1.getCoordx();
					double y = req1.getCoordy();
					Point2D.Double p = new Point2D.Double(x, y);
					listStation.add(req1);
					listCoord.add(p);
				}
				this.getDisplay().setGraphPoints(listCoord);

				
			} catch (IOException e2) {
				e2.printStackTrace();
			}

			try {///// SELECT DEJA LES COORDONNES DE CHAQUE LIGNE DE TRAMWAY

			} catch (Exception e1) {
				e1.printStackTrace();
			}

			this.getDisplay().setShouldRunDisplay(true);
			this.getDisplay().setShouldRunAlgo(false);
			this.getContentPaneCenter().setVisible(true);

			JOptionPane.showMessageDialog(null, "You have generated the existing city", "Existing city generated !",
					JOptionPane.INFORMATION_MESSAGE);
		}
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



	public JButton getSimulateCity() {
		return SimulateCity;
	}



	public void setSimulateCity(JButton simulateCity) {
		SimulateCity = simulateCity;
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



	public JButton getRemoveCity() {
		return RemoveCity;
	}



	public void setRemoveCity(JButton removeCity) {
		RemoveCity = removeCity;
	}



	public JTextField getWidthB() {
		return WidthB;
	}



	public void setWidthB(JTextField widthB) {
		WidthB = widthB;
	}



	public JTextField getHeightB() {
		return HeightB;
	}



	public void setHeightB(JTextField heightB) {
		HeightB = heightB;
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



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
	
	
	
	
	/*public String sendMessagToServerSmartCity(String operationtype, String target,SmartCity o ) throws IOException {
		Request request = new Request(operationtype,target,o);
		mapper = new ObjectMapper();
		String outjsonString = mapper.writeValueAsString(request);
		System.out.println("REQUEST SENT");
		System.out.println(outjsonString);
		System.out.println(" _____");
		System.out.println("");
		out.write(outjsonString + "\n");
		out.flush();
		String injsonString = in.readLine();
		response = mapper.readValue(injsonString, Response.class);
		return response.toString();
	}*/
	
	
	
	/*public String sendMessagToServerStation(String operationtype, String target,Station o ) throws IOException {
		Request request = new Request(operationtype,target,o);
		mapper = new ObjectMapper();
		String outjsonString = mapper.writeValueAsString(request);
		System.out.println("REQUEST SENT");
		System.out.println(outjsonString);
		System.out.println(" _____");
		System.out.println("");
		out.write(outjsonString + "\n");
		out.flush();
		String injsonString = in.readLine();
		response = mapper.readValue(injsonString, Response.class);
		return response.toString();
	}
	
	
	public String sendMessagToServerDeleteStation(String operationtype, String target) throws IOException {
		Request request = new Request(operationtype,target);
		mapper = new ObjectMapper();
		String outjsonString = mapper.writeValueAsString(request);
		System.out.println("REQUEST SENT");
		System.out.println(outjsonString);
		System.out.println(" _____");
		System.out.println("");
		out.write(outjsonString + "\n");
		out.flush();
		String injsonString = in.readLine();
		response = mapper.readValue(injsonString, Response.class);
		return response.toString();
	}
	
	
	public boolean sendMessagToServerCityExists(String operationtype, String target,Station t) throws IOException {
		Request request = new Request(operationtype,target,t);
		mapper = new ObjectMapper();
		String outjsonString = mapper.writeValueAsString(request);
		System.out.println("REQUEST SENT");
		System.out.println(outjsonString);
		System.out.println(" _____");
		System.out.println("");
		out.write(outjsonString + "\n");
		out.flush();
		String injsonString = in.readLine();
		response = mapper.readValue(injsonString, Response.class);
		return response.isB();
	}*/
