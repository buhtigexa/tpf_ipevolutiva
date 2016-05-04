package geneticAlgorithm;

public class Solution {

	private Long iterations;
	private boolean isSolution;
	private Long time;
	private Configuration config;
	
	public Solution(Long iterations, boolean isSolution, Long time,
			Configuration config) {
		super();
		this.iterations = iterations;
		this.isSolution = isSolution;
		this.time = time;
		this.config = config;
	}
	
	public Long getiterations() {
		return iterations;
	}
	public void setiterations(Long iterations) {
		this.iterations = iterations;
	}
	public boolean isSolution() {
		return isSolution;
	}
	public void setisSolution(boolean isSolution) {
		this.isSolution = isSolution;
	}
	public Long gettime() {
		return time;
	}
	public void settime(Long time) {
		this.time = time;
	}
	public Configuration getConfig() {
		return config;
	}
	public void setConfig(Configuration config) {
		this.config = config;
	}


	
	
}
