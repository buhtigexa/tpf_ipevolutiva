package parentSelection;

import java.util.Vector;

import chromosome.Chromosome;


public class WheelSelection implements ParentSelection {
	
	public static final String name = "Selecci�n de Padres por Ruleta";
	
	@Override
	public Vector<Vector<Chromosome>> select(Vector<Chromosome> set) {
		Double total = 0.0;
		for (Chromosome c: set)
			total += c.getFitness();
		
		Vector<Vector<Chromosome>> salida = new Vector<Vector<Chromosome>>();
		
		while (salida.size() != set.size()/2) {
			Vector<Chromosome> pareja = new Vector<Chromosome>();
			Double rand = Math.random() * total;
			int pos = 0;
			while (set.get(pos).getFitness() < rand) {
				rand -= set.get(pos).getFitness();
				pos++;
			}
			pareja.add(set.get(pos));
			rand = Math.random() * total;
			pos = 0;
			while (set.get(pos).getFitness() < rand) {
				rand -= set.get(pos).getFitness();
				pos++;
			}
			pareja.add(set.get(pos));
			salida.add(pareja);
		}

		return salida;
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.name;
	}
}
