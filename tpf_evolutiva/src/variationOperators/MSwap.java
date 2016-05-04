package variationOperators;

import chromosome.Chromosome;

public final class MSwap implements Mutation {

	public static final String name = "Mutaci�n por Intercambio";
	
	@Override
	public final void mutate(Chromosome c) {
		Integer N = c.getSize();
		Integer g1 = (int) (Math.random() * N);
		Integer g2;
		do {
			g2 = (int) (Math.random() * N);
		} while (g2 == g1);


		Integer a1 = c.getGen(g1);
		Integer a2 = c.getGen(g2);

		c.setGen(g1, a2);
		c.setGen(g2, a1);
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.name;
	}
	
}
