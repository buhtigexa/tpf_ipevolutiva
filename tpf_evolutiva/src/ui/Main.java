package ui;


import geneticAlgorithm.GeneticAlgorithm;
import geneticAlgorithm.SolutionComparator;
import geneticAlgorithm.Configuration;
import geneticAlgorithm.Solution;
import geneticAlgorithm.AlgorithmSolution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


import com.alee.extended.filechooser.WebDirectoryChooser;
import com.alee.extended.layout.ToolbarLayout;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.progress.WebProgressOverlay;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.statusbar.WebStatusLabel;
import com.alee.extended.time.ClockType;
import com.alee.extended.time.WebClock;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.managers.language.LanguageConstants;
import com.alee.managers.language.LanguageManager;
import com.alee.managers.notification.NotificationManager;
import com.alee.managers.notification.WebNotificationPopup;
import com.alee.utils.swing.DialogOptions;


public class Main {

	public static JFrame frame;
	private static Console console;
	private static String dirPath=null;
	
	
	private String titulo = "Práctico Final- Computación evolutiva.";
	
	private Configuration config = null;
	private Thread threadRunner;
	private static ITimer iTimer;
	private static WebStatusLabel time;
	private static WebButton button;
	IConfiguration dialogConfig;
	private ConfigurationLauncher launcher;
	private GeneticAlgorithm algorithm;
	private static Vector<Configuration> configurations = new Vector<Configuration>();
	public static boolean isAuto;
	private static Thread t_launcher;
	public static boolean isRunning = false;
	public static Vector<Solution> solutions = new Vector<Solution>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					LanguageManager.setDefaultLanguage(LanguageConstants.SPANISH);
					WebLookAndFeel.install();


					Main window = new Main();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle(titulo);
		frame.setBounds(0,0,java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);

		frame.setLocationRelativeTo(null);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setMnemonic('a');
		menuBar.add(menuArchivo);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo..");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo();
			}
		});
		
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		menuArchivo.add(mntmNuevo);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		menuArchivo.add(mntmSalir);

		JMenu mnConfiguracin = new JMenu("Configuraci\u00F3n");
		mnConfiguracin.setMnemonic('c');
		menuBar.add(mnConfiguracin);

		JMenuItem mntmParmetrosDelalgorithm = new JMenuItem("Configuración del algorithm");
		mntmParmetrosDelalgorithm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mntmParmetrosDelalgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirConfiguracion();
			}
		});
		mnConfiguracin.add(mntmParmetrosDelalgorithm);

		JMenuItem mntmDirectorioDeSalida = new JMenuItem("Directorio de Salida");
		mntmDirectorioDeSalida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmDirectorioDeSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarSalida();
			}
		});
		mnConfiguracin.add(mntmDirectorioDeSalida);
		

		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		final ImageIcon start = new ImageIcon(Main.class.getResource("/images/start.png"));
		final ImageIcon stop = new ImageIcon(Main.class.getResource("/images/stop.png"));

		// Barras de progreso
		final WebProgressOverlay progressOverlay = new WebProgressOverlay ();
		progressOverlay.setConsumeEvents ( false );

		
		button = new WebButton ( "Ejecutar", start );
		button.setFontSize(16);
		button.setEnabled(false);
		button.setPreferredWidth(248);
		button.setRound (9);
		progressOverlay.setComponent ( button );

		// Progress switch
		button.addActionListener ( new ActionListener ()
		{
			@Override
			public void actionPerformed ( ActionEvent e )
			{
				boolean showLoad = !progressOverlay.isShowLoad ();

				progressOverlay.setShowLoad ( showLoad );

				button.setText ( showLoad ? "Parar" : "Ejecutar" );
				button.setIcon ( showLoad ? stop : start );
				if (showLoad){
					ejecutar();
				}else
					pararEjecucion();
			}
		} );

		//Barra de Estado
		
		WebStatusBar statusBar = new WebStatusBar ();
		statusBar.add(progressOverlay,ToolbarLayout.START);

		time = new WebStatusLabel ( "00:00:00:000");
		time.setVisible(false);
		time.setFontSize(16);
		statusBar.add(time, ToolbarLayout.MIDDLE);
		panel.add(statusBar);

		JPanel panelTexto = new JPanel();
		panelTexto.setForeground(Color.LIGHT_GRAY);
		panelTexto.setBorder(null);
		panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.X_AXIS));
		frame.getContentPane().add(panelTexto, BorderLayout.CENTER);

		//ITimer
		iTimer = new ITimer(time);

		//Console
		console = new Console();
		panelTexto.add(console.getComponent());
		
	
		//Configuración
		dialogConfig = new IConfiguration(this);

	}

	private void nuevo() {
		console.clear();	
		iTimer.reset();
		config = null;
		button.setEnabled(false);
	}

	private void seleccionarSalida() {
		WebDirectoryChooser directoryChooser = new WebDirectoryChooser ( frame, "Guardar en.." );
		directoryChooser.setVisible ( true );
		if ( directoryChooser.getResult () == DialogOptions.OK_OPTION )
		{
			File file = directoryChooser.getSelectedDirectory ();
			dirPath = file.getAbsolutePath() + System.getProperty("file.separator") ;
			frame.setTitle(titulo + "     Salida: " + dirPath);
		}
	}

	private static void escribirAArchivo(String string){
		if (dirPath != null){
			FileOutputStream fos;
			try {
				String fechahora = Utils.gethourDateFile();
				if (string == "")
					fos = new FileOutputStream(dirPath+fechahora+".sol");
				else
					fos = new FileOutputStream(dirPath+string+"_"+fechahora+".txt");
				try {
					if (string == "")
						console.write("Archivo almacenado en " + dirPath +  fechahora + ".sol");
					else
						console.write("Archivo almacenado en " + dirPath + string + "_" + fechahora + ".txt");
					fos.write(console.getText().getBytes());
					fos.close();
				} catch (IOException e) {

					e.printStackTrace();
				}

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}

	}

	
	private void abrirConfiguracion() {
		//Mostrar en pantalla
		dialogConfig.setVisible(true);
		dialogConfig.setAlwaysOnTop(true);
		dialogConfig.setModal(true);
		dialogConfig.setModalityType(ModalityType.APPLICATION_MODAL);
	}

	public static void dibujarAviones(Vector<Integer> solucion){ 

		iTimer.cronActive = false;
		
		try {
			Thread.currentThread();
			Thread.sleep(5);
		} catch (InterruptedException e) {}
		time.setText(iTimer.toString());		

		if (isAuto && t_launcher != null){
			solutions.lastElement().settime(iTimer.getMs());
		}


		console.writeJ("Fin de la Ejecución : " + Utils.getHourDate());
		console.writeJ("time de Ejecución : " + time.getText());
		console.writeJ("Solución :");

		
		for (int i = 1; i <= solucion.size(); i++){
			int pos = solucion.indexOf((int) i );
			for (int j = 0; j < solucion.size(); j++){
				if (j == pos){
					console.write("X ");
				}else
					console.write("O ");
			}
			console.writeJ("");
		}

		console.writeJ("Solución Codificada: ");
		console.writeJ(solucion.toString());

		if ( ! isAuto ){
			//Encontré una solución
			final WebNotificationPopup notificationPopup = new WebNotificationPopup ();
			//notificationPopup.setIcon ( new ImageIcon( Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/plus.png")) ));
			notificationPopup.setDisplayTime ( 5000 );
			final WebClock clock = new WebClock ();
			clock.setClockType ( ClockType.timer );
			clock.setTimeLeft ( 5000 );
			clock.setTimePattern ( "'Encontré una solución!.'" );
			notificationPopup.setContent ( new GroupPanel ( clock ) );
			NotificationManager.showNotification ( notificationPopup );
			clock.start ();
		}
		//refresh de la pantalla
		frame.repaint();

		
		if (!isAuto && t_launcher == null)
			doClick();

		//guardando...
		escribirAArchivo("");

	}

	public void setConfig(Configuration config) {
		button.setEnabled(true);

		if (threadRunner != null || t_launcher != null){
			doClick();
		}

		this.config  = config;		
		console.clear();
		console.writeJ(config.toString());
		isAuto = false;

	}

	private void ejecutar() {	
		if (!isAuto)
			t_launcher = null;

		if (isAuto &&  t_launcher == null)
		{
			solutions.clear();
			launcher = new ConfigurationLauncher(console,iTimer,threadRunner,configurations);	
			t_launcher = new Thread(launcher);
			t_launcher.start();
		}
		else
			if (config != null){
				console.clear();
				console.writeJ(config.toString());

				

				algorithm = new GeneticAlgorithm(this.config,console);


				console.writeJ("Comienzo de la ejecución : " + Utils.getHourDate());

				threadRunner = new Thread(algorithm);
				iTimer.start();
				threadRunner.start();
			}

	}

	private void pararEjecucion() {

		
		iTimer.cronActive = false;

		if (threadRunner != null && threadRunner.isAlive()){
			algorithm.terminar();
			threadRunner = null;
		}


		if (t_launcher != null ){
			launcher.terminar();
			t_launcher = null;
		}

	}

	public static void noSeEncontroSolucion() {

		try{
			console.writeJ("Ejecución terminada : " + Utils.getHourDate());
			console.writeJ("Duración de la ejecución : " + iTimer.toString());

		} catch(Exception e) {}

		
		final WebClock clock = new WebClock ();
		clock.setClockType ( ClockType.timer );
		clock.setTimeLeft ( 5000 );
		clock.start ();

		//Refrescar la pantalla
		frame.repaint();

		if (!isAuto && t_launcher == null)
			doClick();

		if (isAuto && t_launcher != null){
			solutions.lastElement().settime(iTimer.getMs());
			iTimer.stop();
		}

		//guardando...
		escribirAArchivo("");

	}

	public static void doClick(){
		button.doClick();
	}

	@SuppressWarnings("static-access")
	public void setConfigAutomatizada(Vector<Configuration> configurations) {

		this.configurations  = configurations;
		isAuto = true;
		button.setEnabled(true);
		console.clear();
		console.writeJ("Presione Ejecutar para correr la configuración automática.");
	}

	public static void addNuevaSolucion(Solution solution) {
		solutions.add(solution);

	}

	public static void calcularEstadisticas(){
		//Generamos las estadisticas en un archivo
		Integer s_generadas = 5;
		Vector<AlgorithmSolution> solutionsalgorithms = new Vector<AlgorithmSolution>(0);
		SolutionComparator comparator3 = new SolutionComparator(null,"time");
		SolutionComparator comparator2 = new SolutionComparator(comparator3,"Iteraciones");
		SolutionComparator comparator1 = new SolutionComparator(comparator2,"Efectividad");

		Integer i = 0;
		Double avgIterations = (double) 0;
		Double avgTime = (double)0;
		Integer solutionsEncontradas = 0;
		for (Solution s : solutions){

			//Estadisticas de la configuracion utilizada
			avgIterations += s.getiterations();
			avgTime += s.gettime();
			if (s.isSolution())
				solutionsEncontradas++;

			i++;
			if (i == s_generadas){
				//guardamos la salida
				avgIterations = avgIterations / s_generadas;
				avgTime = avgTime / s_generadas;
				Double efectividad =  ((double)solutionsEncontradas / s_generadas);

				solutionsalgorithms.add(new AlgorithmSolution(avgIterations,avgTime,s.getConfig(),efectividad));

				
				i = 0;
				avgIterations = (double) 0;
				avgTime = (double) 0;
				solutionsEncontradas = 0;
			}

		}

		//Se ordena el vector resultante!!
		Collections.sort(solutionsalgorithms,comparator1);

		console.clear();
		console.writeJ("Ejecución completa. Resultados:");
		console.writeJ("");

		int solutions=1;
		for (AlgorithmSolution solutionA : solutionsalgorithms){
			console.writeJ(solutions + " )------------------------------------------------------------------\n");
			console.writeJ(solutionA.toString());
			solutions++;
		}

		escribirAArchivo("Resultados de algoritmos Automatizados ");

	}
}
