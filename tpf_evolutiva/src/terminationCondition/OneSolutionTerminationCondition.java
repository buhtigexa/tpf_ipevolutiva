package terminationCondition;

import java.util.Vector;

import chromosome.Chromosome;


public class OneSolutionTerminationCondition extends TerminationCondition {

	public static final String name = "Condici�n de Corte 1 Soluci�n";
	
	@Override
	public boolean isCut(Vector<Chromosome> population, Long actualIteration,Long maxIterations) {
		boolean isCondition = false;

		if (actualIteration < maxIterations){
			for (Chromosome c : population){
				if ( c.getFitness() == (double) c.getSize()){
					solution = c.getGenes();
					isCondition = true;
					break;
				}
			}
		}
		else{
			// Basta de generar
			isCondition = true;
			solution = null;
		}

		return isCondition;
	}

	public Vector<Integer> getsolution() {
		return solution;
	}

	
	
	@SuppressWarnings("static-access")
	public String toString(){
		return this.name;
	}
	
}
