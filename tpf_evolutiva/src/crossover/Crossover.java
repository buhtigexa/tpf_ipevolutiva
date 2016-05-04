package crossover;

import java.util.Vector;

import chromosome.Chromosome;

public interface Crossover{

	public Vector<Chromosome> cross(Vector<Chromosome> parents);
	
}
