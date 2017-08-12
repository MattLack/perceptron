package AlgoritmosIA;

import java.util.List;
import java.util.*;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.*;
import java.math.*;
import Model.Amostra;
import Util.*;

public class Perceptron {

	private static String caminhoArquivoTreinamento = "histogramas/treinamento/treinamento_aviao_cachorro.txt";
	private static String caminhoArquivoTeste = "histogramas/treinamento/teste_aviao_cachorro.txt";

 

	static String ClassePositiva = "aviao"; // essa classe equivale 1 e qualquer outra classe vale zero
	static int MAX_ITER = 100; // maximo de interacoes pela procura da melhor configuracaode pesos 
	static int NUM_INSTANCES; // número de instâncias
	static int NUM_FEATURES = 256; // numero de caracteristicas
	static double LEARNING_RATE = 0.1; // taxa de aprendizado
	static Double theta = 1.0; // limiar

	private static List<Amostra> amostrasTreinamento;
	private static List<Amostra> amostrasTeste;

	public static List<Double> startTreinamento() throws IOException {
		double localError = 0.0, globalError;
		int i, p, iteration = 0, output;
		 

		Util util = new Util();
		Arquivo arq = new Arquivo();

		// Criando na memoria a base de treinamento
		amostrasTreinamento = arq.leitor(caminhoArquivoTreinamento);
		// Descobrindo numero de instancias
		NUM_INSTANCES = amostrasTreinamento.size();
		
		amostrasTreinamento = util.normalizarDados(amostrasTreinamento);
		int id = 0;

		// instanciando a ArrayListFs de pesos
		List<Double> weights;
		weights = new ArrayList<Double>();
		weights = Util.randomNumberWeights(0, 1, weights, NUM_FEATURES - 1);

		iteration = 0;
		do {
			iteration++;
			globalError = 0.0;
			// loop em todas instancias
			for (Amostra amostra : amostrasTreinamento) {
				// calcular a classe de previsao

				output = util.calculateOutputTheta(theta, weights, amostra.getCaracteristicas());

				// diferencao entre o real e previsivel classe
				if (amostra.getClass().equals(ClassePositiva)) {
					localError = 1 - output;

				} else {
					localError = 0 - output;

				}

				// atualizar os pesos
				weights = Util.atualizarPesosTaxaAprendizagem(LEARNING_RATE, localError, amostra.getCaracteristicas(),
						weights);

				// summation of squared error (error value for all instances)
				globalError += (localError * localError);

			}

			// Root Mean Squared Error ou erro de quadrado médio (RMSE)
			System.out.println("Iteration " + iteration + " : RMSE = " + Math.sqrt(globalError / NUM_INSTANCES)
					+ " Global error : " + globalError);

		} while (globalError != 0 && iteration <= MAX_ITER);
		// System.out.println("RMSE = " + Math.sqrt(globalError /
		// NUM_INSTANCES));
		// System.out.println("Global Error no treinamento = " + globalError);
		return weights;
	}// end main

	public static void startTeste(List<Double> weights) throws IOException {
		for (Double w : weights) {
			System.out.println("Ultimos pesos :" + w);
		}
		double localError = 0;
		int globalGoal = 0, globalError = 0;
		int i, p, iteration = 0, output;

		Util util = new Util();
		Arquivo arq = new Arquivo();

		// Criando na memoria a base de treinamento amostrasTeste =
		amostrasTeste = arq.leitor(caminhoArquivoTeste);
		NUM_INSTANCES = amostrasTeste.size();
		amostrasTeste = util.normalizarDados(amostrasTeste);
		int id = 0;

		// loop em todas instancias
		for (Amostra amostra : amostrasTeste) { //
			// calcular a classe de previsao

			output = util.calculateOutputTheta(theta, weights, amostra.getCaracteristicas());

			// diferencao entre o real e previsivel classe
			if (amostra.getClass().equals(ClassePositiva)) {
				localError = 1 - output;
			} else {
				localError = 0 - output;
			}

			if (localError == 0) {
				globalGoal++;
			} else {
				globalError++;
			}

		}

		int sum = globalGoal + globalError;
		globalGoal = globalGoal / sum;
		globalError = globalError / sum;
		System.out.println("Taxa de acerto : " + globalGoal * 100 + " Taxa de erro :" + globalError * 100);

	}

}
