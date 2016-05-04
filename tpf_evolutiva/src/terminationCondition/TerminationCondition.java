package terminationCondition;

import java.util.Vector;

import chromosome.Chromosome;


public abstract class TerminationCondition {

	public abstract boolean isCut(Vector<Chromosome> population, Long actualIteration,Long maxIterations);
	protected Vector<Integer> solution = null;
	
	public abstract Vector<Integer> getsolution();
	
	public void anulateSolution(){
		solution.clear();
	}
}
