package variationOperators;

import java.util.Collections;

import chromosome.Chromosome;


public class MInversion implements Mutation {

	public static final String name = "Mutaci�n por Inversi�n";
	
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
		Collections.reverse(c.getGenes().subList(g1, g2));
	
	}
	
	@SuppressWarnings("static-access")
	public String toString(){
		return this.name;
	}

}
