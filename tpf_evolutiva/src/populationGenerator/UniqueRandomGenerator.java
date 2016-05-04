package populationGenerator;

import java.util.Vector;

import chromosome.Chromosome;


public class UniqueRandomGenerator implements PopulationGenerator {

	public static final String name = "Permutaciones Aleatorias Sin Repeticiones";
	
	@Override
	public Vector<Chromosome> generate(Integer cant, Integer n) {
		Vector<Chromosome> out = new Vector<Chromosome>();
		for (int i = 0; i < cant; i++){
			Chromosome c = new Chromosome(n);			
			if (!out.contains(c))
				out.add(c);
			else
				i--;
		}
		return out;
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.name;
	}
}
