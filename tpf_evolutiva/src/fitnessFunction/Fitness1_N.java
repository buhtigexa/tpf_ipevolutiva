package fitnessFunction;

import chromosome.Chromosome;

public final class Fitness1_N implements Fitness {

	public static final String name = "Evaluaci�n 1 a N";
	
	@Override
	public final void calculateFitness(Chromosome c) {
		Integer N = c.getSize();
		Double result = (double) N;

		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				if ( j != i && inConflict(c,j,i)){
					result -= (double) 1/N; 
				}
			}
		}
		c.setFitness(result);
	}
	
	private boolean inConflict (Chromosome c, Integer g1, Integer g2){
		return Math.abs(c.getGen(g1) - c.getGen(g2)) == Math.abs(g1 - g2);
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.name;
	}
	
}
