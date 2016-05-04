package variationOperators;

import chromosome.Chromosome;

public class MInsert implements Mutation {

	public static final String name = "Mutaci�n por Inserci�n";
	
	@Override
	public void mutate(Chromosome c) {
		Integer N = c.getSize();
		Integer g1 = (int) (Math.random() * N);
		Integer g2;
		do {
			g2 = (int) (Math.random() * N);
		} while (g2 == g1);


		if ( g1 > g2 ){
			Integer aux = g1;
			g1 = g2;
			g2 = aux;
		}
		
		c.getGenes().insertElementAt(c.getGen(g2), g1 + 1);
		c.getGenes().removeElementAt(g2+1);
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.name;
	}
}
