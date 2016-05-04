package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.panel.SingleAlignPanel;
import com.alee.extended.window.PopOverDirection;
import com.alee.extended.window.WebPopOver;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.spinner.WebSpinner;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import crossover.C1Point;
import crossover.CCycles;
import crossover.Cpmx;
import crossover.Crossover;
import fitnessFunction.Fitness;
import fitnessFunction.Fitness1_N;
import geneticAlgorithm.Configuration;
import geneticAlgorithm.AutoConfiguration;


public class IConfiguration extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Main main;
	private WebSpinner planes;
	private WebSpinner populationSize;
	private WebSpinner probCross;
	private WebSpinner probMutation;
	private WebComboBox selectionMethod;
	private WebSpinner k;
	private WebComboBox crossOperator;
	private WebComboBox MutationOperator;
	private WebComboBox SurvivorMethod;
	private WebSpinner nn;
	private WebComboBox cuts;
	private WebComboBox fit;
	private JLabel lblK;
	private JLabel lblN;
	private WebComboBox generationMethod;
	private JButton acceptBtn;
	private JLabel lblMaxAvailableGeneration;
	private WebSpinner maxGenerations;
	private JLabel lblNewLabel_2;
	private JButton btnAutoConfiguration;
	
	@SuppressWarnings("static-access")
	public IConfiguration(Main mainWindow) {
		super(mainWindow.frame, "Configuraci�n", ModalityType.APPLICATION_MODAL);
		main = mainWindow;
		setBounds(100, 100, 1000, 550);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel panelConfig = new JPanel();
		panelConfig.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panelConfig, BorderLayout.CENTER);
		panelConfig.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("100dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("65dlu"),
				ColumnSpec.decode("max(40dlu;default)"),
				ColumnSpec.decode("max(100dlu;default)"),
				ColumnSpec.decode("max(90dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNmeroDeplanes = new JLabel("Cantidad de planes");
		lblNmeroDeplanes.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(lblNmeroDeplanes, "2, 2, left, default");
		
		planes = new WebSpinner ();
		planes.setFont(new Font("Arial", Font.PLAIN, 14));
		planes.setModel(new SpinnerNumberModel(8, 4, 10000, 1));
		planes.setValue(8);
		panelConfig.add(planes, "6, 2");
        
        JLabel lblFuncinFitness = new JLabel("Funci\u00F3n de Fitness");
        lblFuncinFitness.setFont(new Font("Arial", Font.PLAIN, 14));
        panelConfig.add(lblFuncinFitness, "2, 4");
		
      //Funci�n de Fitness
        String[] funfit = new String[1];
        funfit[0] = Fitness1_N.name;
		fit = new WebComboBox ( funfit );
		fit.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(fit, "6, 4, 2, 1");
        
        JLabel lblMtodoDeGeneracin = new JLabel("Generaci\u00F3n de Poblaci\u00F3n");
        lblMtodoDeGeneracin.setFont(new Font("Arial", Font.PLAIN, 14));
        panelConfig.add(lblMtodoDeGeneracin, "2, 6, 4, 1");
        
        //M�todos de generaci�n de la poblaci�n inicial.
        String[] metodos1 = new String[2];
        metodos1[0] = RandomGenerator.name;
        metodos1[1] = UniqueRandomGenerator.name;
        generationMethod = new WebComboBox ( metodos1 );
		generationMethod.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(generationMethod, "6, 6, 2, 1");
		
		Label label = new Label("Selecci\u00F3n de Padres");
		label.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(label, "2, 8, 4, 1");
		
		//M�todos de selecci�n de padres.
	    String[] metodos2 = new String[2];
	    metodos2[0] = TournamentSelection.name;
	    metodos2[1] = WheelSelection.name;
        selectionMethod = new WebComboBox ( metodos2 );
        selectionMethod.setFont(new Font("Arial", Font.PLAIN, 14));
        selectionMethod.addItemListener( new ItemListener(){
        	public void itemStateChanged(ItemEvent e){
                if (selectionMethod.getSelectedIndex() != 0){
                	lblK.setVisible(false);
                	k.setVisible(false);
                }else{
                	lblK.setVisible(true);
                	k.setVisible(true);
                }
                	
            }	
        });
		panelConfig.add(selectionMethod, "6, 8, 2, 1");
		
		lblK = new JLabel("k = ");
		lblK.setFont(new Font("Arial", Font.ITALIC, 14));
		panelConfig.add(lblK, "9, 8");
		
		k = new WebSpinner ();
		k.setFont(new Font("Arial", Font.PLAIN, 14));
		k.setModel(new SpinnerNumberModel(2, 2, 10000, 1));
		k.setValue(2);
		panelConfig.add(k, "11, 8, default, center");
		
		JLabel lblNewLabel = new JLabel("Operador de Cruce Gen\u00E9tico");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(lblNewLabel, "2, 10, 3, 1");
		
		//Operadores de cruce
	    String[] operadores1 = new String[3];
	    operadores1[0] = C1Point.name;
	    operadores1[1] = CCycles.name;
	    operadores1[2] = Cpmx.name;
        crossOperator = new WebComboBox ( operadores1 );
        crossOperator.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(crossOperator, "6, 10, 2, 1");
		
		JLabel lblOperadorDeMutacin = new JLabel("Operador de Mutaci\u00F3n Gen\u00E9tica");
		lblOperadorDeMutacin.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(lblOperadorDeMutacin, "2, 12, 3, 1");
		
		//Operadores de mutaci�n
	    String[] operadores2 = new String[4];
	    operadores2[0] = MSwap.name;
	    operadores2[1] = MInversion.name;
	    operadores2[2] = MMixed.name;
	    operadores2[3] = MInsert.name;
        MutationOperator = new WebComboBox ( operadores2 );
        MutationOperator.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(MutationOperator, "6, 12, 2, 1");
		
		JLabel lblMtodoDeSeleccin = new JLabel("Selecci\u00F3n de Sobrevivientes");
		lblMtodoDeSeleccin.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(lblMtodoDeSeleccin, "2, 14, 4, 1");
		
		//M�todos de selecci�n de sobrevivientes
	    String[] metodos3 = new String[2];
	    metodos3[0] = SteadyStateSurvivorSelection.name;
	    metodos3[1] = RankingSurvivorSelection.name;
        SurvivorMethod = new WebComboBox ( metodos3 );
        SurvivorMethod.setFont(new Font("Arial", Font.PLAIN, 14));
        SurvivorMethod.addItemListener( new ItemListener(){
        	public void itemStateChanged(ItemEvent e){
                if (SurvivorMethod.getSelectedIndex() != 0){
                	lblN.setVisible(false);
                	nn.setVisible(false);
                }else{
                	lblN.setVisible(true);
                	nn.setVisible(true);
                }
                	
            }	
        });
		panelConfig.add(SurvivorMethod, "6, 14, 2, 1");
		
		lblN = new JLabel("n = ");
		lblN.setFont(new Font("Arial", Font.ITALIC, 14));
		panelConfig.add(lblN, "9, 14");
		
		nn = new WebSpinner ();
		nn.setFont(new Font("Arial", Font.PLAIN, 14));
		nn.setModel(new SpinnerNumberModel(2, 2, 10000, 1));
		nn.setValue(2);
		panelConfig.add(nn, "11, 14");
		
		JLabel lblTamaoDeLa = new JLabel("Tama\u00F1o de la Poblaci\u00F3n Inicial");
		lblTamaoDeLa.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(lblTamaoDeLa, "2, 16, 3, 1");
		
		//Tama�o de la poblaci�n inicial
		populationSize = new WebSpinner ();
		populationSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if ( (Integer) populationSize.getValue() % 2 != 0){
				populationSize.setValue((Integer) populationSize.getValue() + 1);
				}
			}
		});
		populationSize.setFont(new Font("Arial", Font.PLAIN, 14));
		populationSize.setModel(new SpinnerNumberModel(10, 1, 100000, 2));
		populationSize.setValue(10);
		panelConfig.add(populationSize, "6, 16");
		
		lblNewLabel_2 = new JLabel("Aclaraci\u00F3n : No exceder el factorial del n\u00FAmero de planes");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.ITALIC,12));
		panelConfig.add(lblNewLabel_2, "7, 16, 5, 1");
		
		JLabel lblProbabilidadDeCruce = new JLabel("Probabilidad de Cruce Gen\u00E9tico");
		lblProbabilidadDeCruce.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(lblProbabilidadDeCruce, "2, 18, 4, 1");
	        
		//Probabilidad de Cruce
		probCross = new WebSpinner ();
		probCross.setFont(new Font("Arial", Font.PLAIN, 14));
		probCross.setModel(new SpinnerNumberModel(0.8, 0.00, 1.0, 0.01));
		probCross.setValue(0.9);
		panelConfig.add(probCross, "6, 18");

		JLabel lblNewLabel_1 = new JLabel("Probabilidad de Mutaci\u00F3n");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(lblNewLabel_1, "2, 20, 3, 1");

		//Probabilidad de Mutaci�n
		probMutation = new WebSpinner ();
		probMutation.setFont(new Font("Arial", Font.PLAIN, 14));
		probMutation.setModel(new SpinnerNumberModel(0.05, 0.00, 1.0, 0.01));
		probMutation.setValue(0.05);
		panelConfig.add(probMutation, "6, 20");
		
		JLabel lblCondicinDeCorte = new JLabel("Condici\u00F3n de Corte");
		lblCondicinDeCorte.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(lblCondicinDeCorte, "2, 22");
		
		//Condici�n de Corte
        String[] corte = new String[1];
        corte[0] = OneSolutionTerminationCondition.name;
        cuts = new WebComboBox ( corte );
		cuts.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.add(cuts, "6, 22, 2, 1");

		//Cantidad de generaciones maxima
		maxGenerations = new WebSpinner ();
		maxGenerations.setModel(new SpinnerNumberModel(new Long(10000), new Long(1), new Long(Long.MAX_VALUE), new Long(1)));
		maxGenerations.setFont(new Font("Arial", Font.PLAIN, 14));
		maxGenerations.setValue(new Long(10000));
		panelConfig.add(maxGenerations, "9, 22, 3, 1");
		
		lblMaxAvailableGeneration = new JLabel("Generaci\u00F3n m\u00E1xima");
		lblMaxAvailableGeneration.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaxAvailableGeneration.setFont(new Font("Arial", Font.ITALIC, 14));
		panelConfig.add(lblMaxAvailableGeneration, "9, 24, 3, 1");
		
		
		JPanel panelSur = new JPanel();
		panelSur.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 10));
		
		acceptBtn = new JButton("Aceptar");
		acceptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( ! hayInconsistencias() ){
					crearConfig();
					
				}
					
			}
		});
		
		btnAutoConfiguration = new JButton("Configuraci\u00F3n Automática");
		btnAutoConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (! hayInconsistencias() ){
					crearConfigAutomatizada();
				}
			}
		});
		btnAutoConfiguration.setFont(new Font("Arial", Font.PLAIN, 14));
		panelSur.add(btnAutoConfiguration);
		acceptBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		panelSur.add(acceptBtn);
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarConfig();
			}
		});
		botonCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		panelSur.add(botonCancelar);
	
	
	}
	
	private void crearConfigAutomatizada() {
		Integer n = (Integer) planes.getValue();
		Integer tam = (Integer) populationSize.getValue();
		Double probC = (Double) probCross.getValue();
		Double probM = (Double) probMutation.getValue();
		Long genMax = (Long) maxGenerations.getValue();
		
		AutoConfiguration config = new AutoConfiguration(n,tam,probC,probM,genMax,(Integer)nn.getValue(),(Integer)k.getValue());
		
		main.setConfigAutomatizada(config.getConfigurations() );
		this.setVisible(false);
	}

	private boolean hayInconsistencias() {
		boolean isOut = false;
		//Steady-State
		if (SurvivorMethod.getSelectedIndex() == 0 && (Integer) populationSize.getValue() < (Integer) nn.getValue()){
			isOut = true;
			 final WebPopOver popOver = new WebPopOver ( );
             popOver.setModal ( true );
             popOver.setMargin ( 10 );
             popOver.setMovable ( false );
             popOver.getContentPane().setLayout ( new VerticalFlowLayout () );
             popOver.setAlwaysOnTop(true);
             popOver.getContentPane().add ( new WebLabel ( "Si se usa Steady-State,n debe ser <= al tama�o de la poblaci�n." ).setFontSize(14) );
             popOver.getContentPane().add ( new SingleAlignPanel ( new WebButton ( "Entendido", new ActionListener ()
             {
                 @Override
                 public void actionPerformed ( final ActionEvent e )
                 {
                     popOver.dispose ();
                 }
             } ), SingleAlignPanel.RIGHT ).setMargin ( 10, 0, 0, 0 ) );
             popOver.show ( acceptBtn, PopOverDirection.up);
		}
		
		return isOut;
	}

	private void crearConfig(){
		//M�todo que generar� una clase con la configuraci�n para una ejecuci�n determinada.
		
		Integer n = (Integer) planes.getValue();
		
		PopulationGenerator genPob = null;
		switch ( generationMethod.getSelectedIndex() ){
			case 0 : genPob = new RandomGenerator();	break;
		    case 1 : genPob = new UniqueRandomGenerator(); break;
	}
		
		ParentSelection selPad = null ;
		switch ( selectionMethod.getSelectedIndex() ){
			case 0 : selPad = new TournamentSelection((Integer) k.getValue()); break;
			case 1 : selPad = new WheelSelection(); break;
		}

		Crossover crossover= null;
		switch (crossOperator.getSelectedIndex()){
			case 0 : crossover= new C1Point(); break;
			case 1 : crossover= new CCycles(); break;
			case 2 : crossover= new Cpmx(); break;
		}

		Mutation mutation = null;
		switch (MutationOperator.getSelectedIndex()){
			case 0 : mutation = new MSwap(); break;
			case 1 : mutation = new MInversion(); break;
			case 2 : mutation = new MMixed(); break;
			case 3 : mutation = new MInsert(); break;
		}

		SurvivorSelection selSob = null;
		switch(SurvivorMethod.getSelectedIndex()){
			case 0 : selSob = new SteadyStateSurvivorSelection((Integer) nn.getValue()); break;
			case 1 : selSob = new RankingSurvivorSelection(); break;
		}
		
		Integer tam = (Integer) populationSize.getValue();

		Double probC = (Double) probCross.getValue();
		
		Double probM = (Double) probMutation.getValue();
		
		TerminationCondition condicion = null;
		switch(cuts.getSelectedIndex()){
			case 0 : condicion = new OneSolutionTerminationCondition(); break;
		}
		
		Fitness eval = null;
		switch(fit.getSelectedIndex()){
			case 0 : eval = new Fitness1_N(); break;
		}
		
		Long genMax = (Long) maxGenerations.getValue();
		
		
		Configuration config = new Configuration(n,genPob,selPad,crossover,mutation,selSob,tam,probC,probM,condicion,eval,genMax);
		
		main.setConfig(config);
		
		this.setVisible(false);
	}
	
	private void cerrarConfig() {
		this.dispose();
	}
}
