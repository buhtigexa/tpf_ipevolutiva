package crossover;

import java.util.Vector;

import chromosome.Chromosome;


public class CCycles implements Crossover {
	
	public static final String name = "Crossover basado en ciclos";
	
	@Override
	public Vector<Chromosome> cross(Vector<Chromosome> parents) {
		Vector<Integer> father1 = parents.elementAt(0).getGenes();
		Vector<Integer> father2 = parents.elementAt(1).getGenes();
		
		Integer N = father1.size();
		
		Vector<Integer> childs1 = new Vector<Integer>();
		childs1.addAll(father1);
		Vector<Integer> childs2 = new Vector<Integer>();
		childs2.addAll(father2);
		
		
		int i = 0;
		Vector<Integer> visitados = new Vector<Integer>();
		Vector<Vector<Integer>> ciclos = new Vector<Vector<Integer>>();
		while (visitados.size() < N){
			ciclos.add(getCiclos(i,father1,father2));
			visitados.addAll(ciclos.lastElement());
			
			while (visitados.contains(i)){
				i++;
			}
		}
		
		boolean aux = true;
		for (Vector<Integer> ciclo : ciclos){
			if (aux){
				
				for (Integer j : ciclo){
					childs1.setElementAt(father1.get(j), j);
					childs2.setElementAt(father2.get(j), j);
				}
				
			}else{
				for (Integer j : ciclo){
					childs1.setElementAt(father2.get(j), j);
					childs2.setElementAt(father1.get(j), j);
				}
			}
			aux = !aux;
		}
		
		
		Vector<Chromosome> childs = new Vector<Chromosome>();
		childs.add(new Chromosome(childs1));
		childs.add(new Chromosome(childs2));
		
		return childs;
	}

	public Vector<Integer> getCiclos(Integer posInicial,Vector<Integer> p1, Vector<Integer> p2){
		Vector<Integer> salida = new Vector<Integer>();
		
		Integer punto_inicio_final = posInicial;
		Integer punto_actual = punto_inicio_final;
		do{
			salida.add(punto_actual);
			punto_actual = p1.indexOf(p2.elementAt(punto_actual));			
		} while (punto_actual != punto_inicio_final) ;
		
		return salida;
	}
	
	
	@SuppressWarnings("static-access")
	public String toString(){
		return this.name;
	}
	
}
