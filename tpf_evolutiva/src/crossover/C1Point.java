package crossover;

import java.util.Vector;

import chromosome.Chromosome;


public final class C1Point implements Crossover {

	public static final String name = "Crossover en 1 Punto";
	
	@Override
	public Vector<Chromosome> cross(Vector<Chromosome> parents) {
		Vector<Integer> father1 = parents.elementAt(0).getGenes();
		Vector<Integer> father2 = parents.elementAt(1).getGenes();
		Integer N = father1.size();
		Integer puntoCruce = (int) (Math.random() * (N-4)) + 2;
		
		Vector<Integer> childs1 = new Vector<Integer>();
		Vector<Integer> childs2 = new Vector<Integer>();
		
		childs1.addAll(father1.subList(0, puntoCruce));
		childs2.addAll(father2.subList(0, puntoCruce));
		
		for (int i = 0; i < N ; i++){
			
			if ( ! childs1.contains(father2.elementAt(i))){
				childs1.add(father2.elementAt(i));
			}
			if ( ! childs2.contains(father1.elementAt(i))){
				childs2.add(father1.elementAt(i));
			}
			
		}
		
		Vector<Chromosome> childs = new Vector<Chromosome>();
		childs.add(new Chromosome(childs1));
		childs.add(new Chromosome(childs2));
		
		return childs;
	}
	
	@SuppressWarnings("static-access")
	public String toString(){
		return this.name;
	}

}
