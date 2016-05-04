package geneticAlgorithm;

import fitnessFunction.Fitness;
import fitnessFunction.Fitness1_N;

import java.util.Vector;

import parentSelection.WheelSelection;
import parentSelection.TournamentSelection;
import parentSelection.ParentSelection;
import populationGenerator.PopulationGenerator;
import populationGenerator.RandomGenerator;
import populationGenerator.UniqueRandomGenerator;

import survivorSelection.SteadyStateSurvivorSelection;
import survivorSelection.SurvivorSelection;
import survivorSelection.RankingSurvivorSelection;
import terminationCondition.TerminationCondition;
import terminationCondition.OneSolutionTerminationCondition;
import variationOperators.MInsert;
import variationOperators.MSwap;
import variationOperators.MInversion;
import variationOperators.MMixed;
import variationOperators.Mutation;
import crossover.C1Point;
import crossover.CCycles;
import crossover.Cpmx;
import crossover.Crossover;

public class AutoConfiguration {

	private Integer nPlanes;
	private Integer popSize;
	private Double crossProb;
	private Double mutProb;
	private Long genMax;
	private Integer nn;
	private Integer k;

	public AutoConfiguration(Integer nPlanes, Integer popSize,
			Double crossProb, Double mutProb, Long genMax, Integer nn, Integer k) {
		this.nPlanes = nPlanes;
		this.popSize = popSize;
		this.crossProb = crossProb;
		this.mutProb = mutProb;
		this.genMax = genMax;
		this.nn = nn;
		this.k = k;
	}

	public Vector<Configuration> getConfigurations() {
		Vector<Configuration> out = new Vector<Configuration>();

		TerminationCondition condicion = new OneSolutionTerminationCondition();
		Fitness eval = new Fitness1_N();

		for (int genMethod = 0; genMethod <= 1; genMethod++) {
			for (int selMethod = 0; selMethod <= 1; selMethod++) {
				for (int crossOp = 0; crossOp <= 2; crossOp++) {
					for (int mutOp = 0; mutOp <= 3; mutOp++) {
						for (int survivorMethod = 0; survivorMethod <= 1; survivorMethod++) {

							PopulationGenerator genPob = null;
							switch (genMethod) {
							case 0:
								genPob = new RandomGenerator();
								break;
							case 1:
								genPob = new UniqueRandomGenerator();
								break;
							}

							ParentSelection selPad = null;
							switch (selMethod) {
							case 0:
								selPad = new TournamentSelection(k);
								break;
							case 1:
								selPad = new WheelSelection();
								break;
							}

							Crossover cruza = null;
							switch (crossOp) {
							case 0:
								cruza = new C1Point();
								break;
							case 1:
								cruza = new CCycles();
								break;
							case 2:
								cruza = new Cpmx();
								break;
							}

							Mutation mutation = null;
							switch (mutOp) {
							case 0:
								mutation = new MSwap();
								break;
							case 1:
								mutation = new MInversion();
								break;
							case 2:
								mutation = new MMixed();
								break;
							case 3:
								mutation = new MInsert();
								break;
							}

							SurvivorSelection selSob = null;
							switch (survivorMethod) {
							case 0:
								selSob = new SteadyStateSurvivorSelection(
										nn);
								break;
							case 1:
								selSob = new RankingSurvivorSelection();
								break;
							}

							// Se hace la configuracion
							Configuration config = new Configuration(nPlanes,
									genPob, selPad, cruza, mutation, selSob,
									popSize, crossProb, mutProb, condicion, eval,
									genMax);
							
							//Se agregan varias veces a proposito
							for (int i = 0; i < 5; i++)
								out.add(config);
						}
					}
				}
			}
		}

		return out;
	}

	public Integer getnPlanes() {
		return nPlanes;
	}

	public void setnPlanes(Integer nPlanes) {
		this.nPlanes = nPlanes;
	}

	public Integer getpopSize() {
		return popSize;
	}

	public void setpopSize(Integer popSize) {
		this.popSize = popSize;
	}

	public Double getcrossProb() {
		return crossProb;
	}

	public void setcrossProb(Double crossProb) {
		this.crossProb = crossProb;
	}

	public Double getmutProb() {
		return mutProb;
	}

	public void setmutProb(Double mutProb) {
		this.mutProb = mutProb;
	}

	public Long getGenMax() {
		return genMax;
	}

	public void setGenMax(Long genMax) {
		this.genMax = genMax;
	}

}
