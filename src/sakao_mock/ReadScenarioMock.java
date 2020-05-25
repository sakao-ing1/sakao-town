package sakao_mock;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadScenarioMock {

	String scenarios;

	public ReadScenarioMock() {
	}

	public String readScenarioMock() {
		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream("resources\\scenario\\scenarioMockPollution.properties");
			// loads the file properties
			prop.load(input);
			scenarios = prop.getProperty("scenarios");
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return scenarios;
	}

}
