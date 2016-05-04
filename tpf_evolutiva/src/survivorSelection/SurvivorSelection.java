package survivorSelection;

import java.util.Vector;

import chromosome.Chromosome;

public interface SurvivorSelection {

	public Vector<Chromosome> select(Vector<Chromosome> parentSet,Vector<Chromosome> childSet);
	
}
