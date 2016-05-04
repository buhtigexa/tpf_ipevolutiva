package parentSelection;

import java.util.Vector;

import chromosome.Chromosome;


public interface ParentSelection {

	public Vector<Vector<Chromosome>> select(Vector<Chromosome> conjunto);
	
}
