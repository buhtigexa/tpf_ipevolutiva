package populationGenerator;

import java.util.Vector;

import chromosome.Chromosome;


public class RandomGenerator implements PopulationGenerator {

	public static final String name = "Permutaciones Aleatorias";
	
	@Override
	public Vector<Chromosome> generate(Integer cant,Integer n) {
		Vector<Chromosome> out = new Vector<Chromosome>();
		for (int i = 0; i < cant; i++){
			out.add(new Chromosome(n));
		}
		return out;
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.name;
	}
}
