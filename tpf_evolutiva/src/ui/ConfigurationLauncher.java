package ui;

import geneticAlgorithm.GeneticAlgorithm;
import geneticAlgorithm.Configuration;

import java.util.Vector;


public class ConfigurationLauncher implements Runnable {
	
	private Console console;
	private ITimer iTimer;
	private Thread threadRunner;
	private Vector<Configuration> configurations;
	private boolean isActive;

	public ConfigurationLauncher(Console console, ITimer iTimer,
			Thread threadRunner, Vector<Configuration> configurations) {

		this.console = console;
		this.iTimer = iTimer;
		this.threadRunner = threadRunner;
		this.configurations = configurations;

	}

	@Override
	public void run() {
		this.isActive = true;
		for (int i = 0; i < configurations.size();i++){

			Configuration c = configurations.elementAt(i);

			System.out.println("Ejecutando configuraci�n: " + i);

			console.clear();
			console.writeJ(c.toString());
			
			GeneticAlgorithm algoritmo = new GeneticAlgorithm(c,console);

			
			console.writeJ("Comienzo de la ejecuci�n : " + Utils.getHourDate());


			if (isActive){
				threadRunner = new Thread(algoritmo);
				iTimer.reset();
				iTimer.start();
				threadRunner.start();
			}
			
			do {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			} while (Main.isRunning);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}

			if (isActive == false)
				i = configurations.size();
		}
		System.out.println("Termino el lanzador");
		if (isActive)
			Main.calcularEstadisticas();
		if (isActive)
			Main.doClick();
		Main.isAuto = false;
	}

	public void terminar(){
		this.isActive = false;
	}

}
