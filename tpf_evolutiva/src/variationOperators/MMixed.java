package variationOperators;

import java.util.Collections;

import chromosome.Chromosome;


public class MMixed implements Mutation {

	public static final String name = "Mutaci�n por Mezcla";
	
	@Override
	public void mutate(Chromosome c) {
		//En esta versi�n la mezcla se hace con genes contiguos
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
		Collections.shuffle(c.getGenes().subList(g1, g2));

	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.name;
	}
	
}
