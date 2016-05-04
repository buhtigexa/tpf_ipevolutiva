package parentSelection;

import java.util.Collections;
import java.util.Vector;

import chromosome.Chromosome;


public class TournamentSelection implements ParentSelection {

	private Integer k;
	public static final String name = "Selecci�n de Padres por tournament";
	
	
	public TournamentSelection(Integer k) {
		this.k = k;
	}

	@Override
	public Vector<Vector<Chromosome>> select(Vector<Chromosome> iSet) {
		Vector<Vector<Chromosome>> out = new Vector<Vector<Chromosome>>();
		
		
		while ( ! iSet.isEmpty() ){
			//Vector que conforma una match conformada por dos padres ganadores de dos tournaments
			Vector<Chromosome> match = new Vector<Chromosome>();
			//Vector que conforma los padres participantes de un tournament especifico
			Vector<Chromosome> tournament = new Vector<Chromosome>();
			
			//Mezclo el iSet con la totalidad de padres disponibles
			Collections.shuffle(iSet);

			if ( iSet.size() >= k){
				//Se seleccionan k padres para conformar al tournament
				tournament.addAll(iSet.subList(0, k));
			
			}else{
				//Si ya en el iSet quedan menos de k padres se los mete junto en el tournament
				tournament.addAll(iSet);
			}

			//Ordeno el vector tournament para obtener al ganador.
			Collections.sort(tournament);
			//Agrego al ganador como primer padre y lo borro del iSet.
			match.add(tournament.lastElement());
			iSet.remove(tournament.lastElement());
			//Borro el vector de tournament.
			tournament.clear();
			
			//Mezclo el iSet de padres sobrantes.
			Collections.shuffle(iSet);

			if ( iSet.size() >= k){
				//Se seleccionan k padres para conformar al tournament
				tournament.addAll(iSet.subList(0, k));
			
			}else{
				//Si ya en el iSet quedan menos de k padres se los mete junto en el tournament
				tournament.addAll(iSet);
			}
			
			//Ordeno el vector tournament para obtener al ganador.
			Collections.sort(tournament);
			//Agrego al ganador como segundo padre y lo borro del iSet.
			match.add(tournament.lastElement());
			iSet.remove(tournament.lastElement());
			
			//Agrego la match obtenida
			out.add(match);
		}
		
		return out;
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.name + " (k = "+this.k+")";
	}
	
	
}
