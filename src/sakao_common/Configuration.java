package sakao_common;

import java.util.Date;

public class Configuration {
		private int idConfiguration;
		private int frequency;
		private Date dateOfConfiguration;
		
		public Configuration() {
			
		}
		
		public Configuration(int idConfiguration, int frequency, Date dateOfConfiguration) {
			this.idConfiguration = idConfiguration;
			this.frequency = frequency;
			this.dateOfConfiguration = dateOfConfiguration;
		}

		public int getIdConfiguration() {
			return idConfiguration;
		}

		public void setIdConfiguration(int idConfiguration) {
			this.idConfiguration = idConfiguration;
		}

		public int getFrequency() {
			return frequency;
		}

		public void setFrequency(int frequency) {
			this.frequency = frequency;
		}

		public Date getDateOfConfiguration() {
			return dateOfConfiguration;
		}

		public void setDateOfConfiguration(Date dateOfConfiguration) {
			this.dateOfConfiguration = dateOfConfiguration;
		}

		@Override
		public String toString() {
			return "Configuration [idConfiguration=" + idConfiguration + ", frequency=" + frequency
					+ ", dateOfConfiguration=" + dateOfConfiguration + "]";
		}
		
		
		
		
}
