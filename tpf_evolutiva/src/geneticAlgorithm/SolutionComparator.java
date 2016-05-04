package geneticAlgorithm;

import java.util.Comparator;

public class SolutionComparator implements Comparator<AlgorithmSolution> {

	private SolutionComparator sig;
	private String id;
	
	public SolutionComparator(SolutionComparator sig,String id) {
		super();
		this.sig = sig;
		this.id = id;
	}

	@Override
	public int compare(AlgorithmSolution arg0, AlgorithmSolution arg1) {
		int res = 0;
	
		if (id.equals("Tiempo demandado:"))
			res = arg0.gettime().compareTo(arg1.gettime());
	
		if (id.equals("Iteraciones realizadas:"))
			res = arg0.getiterations().compareTo(arg1.getiterations());
		
		if (id.equals("Efectividad de la configuración:"))
			res = arg0.geteffectiveness().compareTo(arg1.geteffectiveness()) * -1;
		
		if (res == 0 && sig != null)
			return sig.compare(arg0,arg1);
		else
			return res;
	}

}
