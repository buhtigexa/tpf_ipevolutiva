package populationGenerator;

import java.util.Vector;

import chromosome.Chromosome;


public interface PopulationGenerator {

	public Vector<Chromosome> generate(Integer cant,Integer n);
	
}
