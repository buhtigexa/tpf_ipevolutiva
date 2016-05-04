package survivorSelection;

import java.util.Collections;
import java.util.Vector;

import chromosome.Chromosome;


public class RankingSurvivorSelection implements SurvivorSelection {

	public static final String name = "Ranking";
	
	@Override
	public Vector<Chromosome> select(Vector<Chromosome> parentSet,Vector<Chromosome> childSet) {
		if (childSet.size() == 0)
			return parentSet;
		
		Vector<Chromosome> total = new Vector<Chromosome>();
		total.addAll(parentSet);
		total.addAll(childSet);
		Collections.sort(total);
		Collections.reverse(total);
		
		Vector<Double> probs = new Vector<Double>();
		Vector<Chromosome> out = new Vector<Chromosome>();
		while (out.size() != parentSet.size()) {
			probs.clear();
			for (int i = 0; i < total.size(); i++) {
				probs.add(1.5/total.size() + i/(total.size() * (total.size() - 1)));
			}
			Double rand = Math.random();
			int pos = 0;
			while (probs.get(pos) < rand) {
				rand -= probs.get(pos);
				pos++;
			}
			
			out.add(total.get(pos));
			total.remove(pos);
		}
		
		return out;
	}

	
	@SuppressWarnings("static-access")
	public String toString(){
		return this.name ;
	}
}
