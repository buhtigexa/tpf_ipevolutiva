package survivorSelection;

import java.util.Collections;
import java.util.Vector;

import chromosome.Chromosome;

public class SteadyStateSurvivorSelection implements SurvivorSelection {

	public static final String name = "Steady-State";
	private Integer n;
	
	public SteadyStateSurvivorSelection(Integer n) {
		this.n = n;
	}

	@Override
	public Vector<Chromosome> select(Vector<Chromosome> parentSet,Vector<Chromosome> childSet) {
		Vector<Chromosome> out = new Vector<Chromosome>();
		//Ordeno padres e hijos por  fitness
		Collections.sort(parentSet);
		Collections.sort(childSet);
		//Elijo los mejores hijos
		Collections.reverse(childSet);
		
		//Genero salida
		int naux = n;
		if (childSet.size() >= n){
			out.addAll(childSet.subList(0, n));
		}else{
			out.addAll(childSet);
			naux = childSet.size();
		}
		out.addAll(parentSet.subList(naux, parentSet.size()));
			
		return out;
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.name + " (n = "+this.n+")";
	}

}
