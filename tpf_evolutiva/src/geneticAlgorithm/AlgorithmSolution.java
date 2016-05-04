package geneticAlgorithm;

public class AlgorithmSolution  {

	private Double iterations;
	private Double time;
	private Configuration config;
	private Double effectiveness;
	
	
	public AlgorithmSolution(Double iterationsProm, Double timeProm,
			Configuration config, Double effectiveness) {
		super();
		this.iterations = iterationsProm;
		this.time = timeProm;
		this.config = config;
		this.effectiveness = effectiveness;
	}

	public Double getiterations() {
		return iterations;
	}

	public void setiterations(Double iterations) {
		this.iterations = iterations;
	}

	public Double gettime() {
		return time;
	}

	public void settime(Double time) {
		this.time = time;
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public Double geteffectiveness() {
		return effectiveness;
	}

	public void seteffectiveness(Double effectiveness) {
		this.effectiveness = effectiveness;
	}

	public String toString(){
		String salida = "";
		
		salida += getConfig() + "\n";
		salida += "# Efectividad de la configuración: " + (int) (geteffectiveness() * 100) + "%\n";
		salida += "# Iteraciones promedio: " + getiterations() + "\n";
		salida += "# Tiempo promedio: " + gettime() + "ms\n";
		return salida;
	}
	
	
}
