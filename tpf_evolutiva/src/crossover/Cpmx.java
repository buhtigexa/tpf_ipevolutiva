package crossover;

import java.util.Vector;

import chromosome.Chromosome;


public class Cpmx implements Crossover {

	public static final String name = "Crossover PMX";

	@Override
	public Vector<Chromosome> cross(Vector<Chromosome> parents) {
		Vector<Integer> father1= parents.elementAt(0).getGenes();
		Vector<Integer> father2 = parents.elementAt(1).getGenes();

		Integer N = father1.size();

		Integer crossPoint1 = (int) (Math.random() * N);
		Integer crossPoint2 ;
		do{
			crossPoint2 = (int) (Math.random() * N);
		} while(crossPoint2 == crossPoint1);

		if (crossPoint1 > crossPoint2){
			Integer aux = crossPoint1;
			crossPoint1 = crossPoint2;
			crossPoint2 = aux;
		}

		Vector<Integer> childs1 = new Vector<Integer>();
		Vector<Integer> childs2 = new Vector<Integer>();
		for (int i = 0; i < N ; i++){
			if (crossPoint1 <= i && i <= crossPoint2){
				childs1.add(father2.elementAt(i));
				childs2.add(father1.elementAt(i));
			}else{
				childs1.add(-1);
				childs2.add(-1);
			}	
		}

				for (int i = 0; i < crossPoint1 ; i++){
			if (childs1.contains(father1.elementAt(i))){
				//Busco correspondencia
				Integer actualPoint = childs1.indexOf(father1.elementAt(i));
				Integer eOut = null ;
				while (crossPoint1 <= actualPoint && actualPoint <= crossPoint2){
					eOut = father1.elementAt(actualPoint);
					actualPoint = childs1.indexOf(eOut);
				}
				childs1.setElementAt(eOut, i);	
			}else{
				//Y copio
				childs1.setElementAt(father1.elementAt(i), i);
			}
		}

		for (int i = crossPoint2 + 1; i < N ; i++){
			if (childs1.contains(father1.elementAt(i))){
			
				Integer actualPoint = childs1.indexOf(father1.elementAt(i));
				Integer eOut = null ;
				while (crossPoint1 <= actualPoint && actualPoint <= crossPoint2){
					eOut = father1.elementAt(actualPoint);
					actualPoint = childs1.indexOf(eOut);
				}
				childs1.setElementAt(eOut, i);	
			}else{
				childs1.setElementAt(father1.elementAt(i), i);
			}
		}


		//Relleno el hijo 2
		for (int i = 0; i < crossPoint1 ; i++){
			if (childs2.contains(father2.elementAt(i))){
				//Busco correspondencia
				Integer actualPoint = childs2.indexOf(father2.elementAt(i));
				Integer eOut = null ;
				while (crossPoint1 <= actualPoint && actualPoint <= crossPoint2){
					eOut = father2.elementAt(actualPoint);
					actualPoint = childs2.indexOf(eOut);
				}
				childs2.setElementAt(eOut, i);	
			}else{
				//y copio...
				childs2.setElementAt(father2.elementAt(i), i);
			}
		}

		for (int i = crossPoint2 + 1; i < N ; i++){
			if (childs2.contains(father2.elementAt(i))){
				//Busco correspondencia
				
				Integer actualPoint = childs2.indexOf(father2.elementAt(i));
				Integer eOut = null ;
				while (crossPoint1 <= actualPoint && actualPoint <= crossPoint2){
					eOut = father2.elementAt(actualPoint);
					actualPoint = childs2.indexOf(eOut);
				}
				childs2.setElementAt(eOut, i);	
			}else{
				//y copio...
				childs2.setElementAt(father2.elementAt(i), i);
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
