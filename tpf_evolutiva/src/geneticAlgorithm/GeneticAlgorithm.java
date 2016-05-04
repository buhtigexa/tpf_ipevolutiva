package geneticAlgorithm;


import java.text.DecimalFormat;
import java.util.Vector;

import chromosome.Chromosome;

import populationGenerator.PopulationGenerator;

import ui.Console;
import ui.Main;

public class GeneticAlgorithm implements Runnable{

	private Configuration configuration;
	private Console console;
	private boolean isActive;
	private Long iterations;

	public GeneticAlgorithm(Configuration config,Console console) {
		this.configuration = config;
		this.console = console;
	}

	
	@SuppressWarnings("unchecked")
	public Vector<Integer> getSolucion(){
		//Variables auxiliares
		iterations = (long) 0;
		Double mejorFit = 0.0;
		Double peorFit = (double) configuration.getpopulationSize();
		Double promFit = 0.0;
		DecimalFormat df = new DecimalFormat("0.00");

		//Generamos la poblaci�n
		Vector<Chromosome> population = new Vector<Chromosome>();
		PopulationGenerator gen = configuration.getGenPob();
		population = gen.generate(configuration.getpopulationSize(),configuration.getN());

		for (Chromosome c : population){
			//Configuration
			configuration.getEvaluacion().calculateFitness(c);
			//Estadisticas
			if (c.getFitness() > mejorFit){
				mejorFit = c.getFitness();
			}
			if (c.getFitness() < peorFit){
				peorFit = c.getFitness();
			}
			promFit += c.getFitness();
		}

		//Estadistica
		promFit /= configuration.getpopulationSize();

		
		while( ! configuration.getTerminationCondition().isCut(population,iterations,configuration.getGenMax()) && isActive ){

			//Estadisticas
			iterations++;
			console.writeJ("Iteración nro. : "+iterations+"\tFitness Mejor ("+df.format(mejorFit)+")\tFitness Promedio("+df.format(promFit)+")\tFitness Peor ("+df.format(peorFit)+")" );	

			//Se seleccionan los padres y se conforman las parejas
			Vector<Chromosome> padres = (Vector<Chromosome>) population.clone();
			Vector<Vector<Chromosome>> parejas = configuration.getParentSelection().select(population);

			//Cruzamos a las parejas
			Vector<Chromosome> hijos = new Vector<Chromosome>();

			for (Vector<Chromosome> pareja : parejas){
				//Vemos si se da la chanche de que se crucen
				if (Math.random() < configuration.getcrossProb()) {
					hijos.addAll(configuration.getCrossover().cross(pareja));	
				}
			}

			//Muto  los hijos
			for (Chromosome c : hijos){
				if ( Math.random() < configuration.getmutationProb())
					configuration.getMutation().mutate(c);
			}

			//Calculo el Fitness de los hijos
			for (Chromosome c : hijos){
				configuration.getEvaluacion().calculateFitness(c);
			}

			//Selecciono los sobrevivientes
			population = configuration.getSurvivorSelection().select(padres, hijos);

			//Estadisticas
			mejorFit = 0.0;
			peorFit = (double) configuration.getN();
			promFit = 0.0;
			for (Chromosome c : population){
				//Estadisticas
				if (c.getFitness() > mejorFit){
					mejorFit = c.getFitness();
				}
				if (c.getFitness() < peorFit){
					peorFit = c.getFitness();
				}
				promFit += c.getFitness();
			}

			//Estadistica
			promFit /= configuration.getpopulationSize();

		}
		return configuration.getTerminationCondition().getsolution();
	}

	@Override
	public void run() {
		Main.isRunning = true;
		this.isActive = true;
		Vector<Integer> solucion;
		solucion = getSolucion();
		Solution solution;
		if (solucion != null && isActive){
			solution = new Solution(iterations,true,null,configuration);
			Main.addNuevaSolucion(solution);
			Main.dibujarAviones(solucion);
		}else{
			if (isActive){
				solution = new Solution(iterations,false,null,configuration);
				Main.addNuevaSolucion(solution);
				Main.noSeEncontroSolucion(  );
			}
		}
		Main.isRunning = false;
	}

	public void terminar(){
		this.isActive = false;
	}

}
