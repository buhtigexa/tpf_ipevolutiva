package geneticAlgorithm;

import java.text.DecimalFormat;

import parentSelection.ParentSelection;
import populationGenerator.PopulationGenerator;

import crossover.Crossover;
import fitnessFunction.Fitness;
import survivorSelection.SurvivorSelection;
import terminationCondition.TerminationCondition;
import variationOperators.Mutation;

public class Configuration {

	private Integer n;
	private PopulationGenerator genPob;
	private ParentSelection parentSelection;
	private Crossover crossover;
	private Mutation mutation;
	private SurvivorSelection survivorSelection;
	private Integer populationSize;
	private Double crossProb;
	private Double mutationProb;
	private TerminationCondition terminationCondition; 
	private Fitness fitness;
	private Long genMax;
	
	public Configuration(Integer n,PopulationGenerator genPob, ParentSelection parentSelection,
			Crossover crossover, Mutation mutation,
			SurvivorSelection survivorSelection,
			Integer populationSize, Double crossProb,
			Double mutationProb,TerminationCondition condition, Fitness eval, Long genMax) {
		
		
		super();
		
		this.setN(n);
		this.setGenPob(genPob);
		this.setParentSelection(parentSelection);
		this.setCrossover(crossover);
		this.setMutation(mutation);
		this.setSurvivorSelection(survivorSelection);
		this.setpopulationSize(populationSize);
		this.setcrossProb(crossProb);
		this.setmutationProb(mutationProb);
		this.setTerminationCondition(condition);
		this.setEvaluation(eval);
		this.setGenMax(genMax);
	}

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public ParentSelection getParentSelection() {
		return parentSelection;
	}

	public void setParentSelection(ParentSelection parentSelection) {
		this.parentSelection = parentSelection;
	}

	public Crossover getCrossover() {
		return crossover;
	}

	public void setCrossover(Crossover crossover) {
		this.crossover = crossover;
	}

	public Mutation getMutation() {
		return mutation;
	}

	public void setMutation(Mutation mutation) {
		this.mutation = mutation;
	}

	public SurvivorSelection getSurvivorSelection() {
		return survivorSelection;
	}

	public void setSurvivorSelection(SurvivorSelection survivorSelection) {
		this.survivorSelection = survivorSelection;
	}

	public Integer getpopulationSize() {
		return populationSize;
	}

	public void setpopulationSize(Integer populationSize) {
		this.populationSize = populationSize;
	}

	public Double getcrossProb() {
		return crossProb;
	}

	public void setcrossProb(Double crossProb) {
		this.crossProb = crossProb;
	}

	public Double getmutationProb() {
		return mutationProb;
	}

	public void setmutationProb(Double mutationProb) {
		this.mutationProb = mutationProb;
	}

	public TerminationCondition getTerminationCondition() {
		return terminationCondition;
	}

	public void setTerminationCondition(TerminationCondition terminationCondition) {
		this.terminationCondition = terminationCondition;
	}

	public Fitness getEvaluacion() {
		return fitness;
	}

	public void setEvaluation(Fitness fitness) {
		this.fitness = fitness;
	}
	

	public String toString (){
		DecimalFormat df = new DecimalFormat("0.00");
		String salida = "";
		salida += "Configuraci�n: \n";
		salida += "  * Cantidad de Aviones (N) = [" + this.n + "].\n";
		salida += "  * Funci�n de Fitness = [" + this.fitness.toString()+"].\n";
		salida += "  * M�todo de Generaci�n de Poblaci�n = [" + this.genPob.toString()+"].\n";
		salida += "  * M�todo de Selecci�n de Padres = [" + this.parentSelection.toString()+"].\n";
		salida += "  * Operador de Crossover = [" + this.crossover.toString()+"].\n";
		salida += "  * Operador de Mutaci�n = [" + this.mutation.toString()+"].\n";
		salida += "  * M�todo de Selecci�n de Sobrevivientes = [" + this.survivorSelection.toString()+"].\n";
		salida += "  * Tama�o de la Poblaci�n = [" + this.populationSize+"].\n";
		salida += "  * Probabilidad de Cruce = [" + df.format(this.crossProb)+"].\n";
		salida += "  * Probabilidad de Mutaci�n = [" + df.format(this.mutationProb)+"].\n";
		salida += "  * Condici�n de Corte = [" + this.terminationCondition.toString()+"].\n";
		
		
		return salida;
	}

	public PopulationGenerator getGenPob() {
		return genPob;
	}

	public void setGenPob(PopulationGenerator genPob) {
		this.genPob = genPob;
	}

	public Long getGenMax() {
		return genMax;
	}

	public void setGenMax(Long genMax) {
		this.genMax = genMax;
	}
	
}
