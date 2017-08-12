package Util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import Model.Amostra;

public class Util {

	public static List<Double> atualizarPesosTaxaAprendizagem(double LEARNING_RATE, double localError,
			List<Double> caracteristicas, List<Double> weights) {
		List<Double> w = new ArrayList<Double>();

		DecimalFormat formato = new DecimalFormat("0.####");
		String str;
		for (int i = 0; i < caracteristicas.size() - 1; i++) {

			str = formato.format((LEARNING_RATE * localError * caracteristicas.get(i) ) + weights.get(i));
			str = str.replace(",", ".");
			w.add(Double.valueOf(str));
			// System.out.println(w.get(i));
			// System.out.println(LEARNING_RATE + " " + localError + " " +
			// caracteristicas.get(i) + " " + weights.get(i));

		}

		return w;

	}

	public static int calculateOutputTheta(Double theta, List<Double> weights, List<Double> caracteristicas) {
		double sum = 0;

		for (int i = 0; i < weights.size(); i++) {
			sum += (caracteristicas.get(i) * weights.get(i));

		}

		// System.out.println("somar " + sum);
		if (sum >= theta) {

			return 1;

		} else {

			return 0;
		}
	}

	public static List<Double> randomNumberWeights(int min, int max, List<Double> weights, int NUM_FEATURES) {
		for (int i = 0; i < NUM_FEATURES - 1; i++) {
			DecimalFormat df = new DecimalFormat("#.####");
			double d = min + Math.random() * (max - min);
			// System.out.println(d);
			String s = df.format(d);
			s = s.replace(',', '.');
			// System.out.println(s);
			Double x = Double.parseDouble(s);
			// System.out.println(x);
			weights.add(i, x);
		}
		return weights;
	}

	public static double randomNumber(int min, int max) {
		DecimalFormat df = new DecimalFormat("#.####");
		double d = min + Math.random() * (max - min);
		// System.out.println(d);
		String s = df.format(d);
		s = s.replace(',', '.');
		// System.out.println(s);
		Double x = Double.parseDouble(s);
		// System.out.println(x);
		return x;
	}

	public static List<Amostra> normalizarDados(List<Amostra> conjuntoAmostra) {
		Double min, max;

		for (int i = 0; i < conjuntoAmostra.size(); i++) {
			min = conjuntoAmostra.get(i).getCaracteristicas().stream().min((p1, p2) -> Double.compare(p1, p2)).get();
			max = conjuntoAmostra.get(i).getCaracteristicas().stream().max((p1, p2) -> Double.compare(p1, p2)).get();

			for (int x = 0; x < conjuntoAmostra.get(i).getCaracteristicas().size(); x++) {

				conjuntoAmostra.get(i).getCaracteristicas().set(x,
						((conjuntoAmostra.get(i).getCaracteristicas().get(x) - min) / (max - min)));
				// System.out.println("MIN : " + min + "MAX : " + max + "
				// Caracteristica : "
				// + conjuntoAmostra.get(i).getCaracteristicas().get(x));
			}

		}

		return conjuntoAmostra;
	}

	public static int getQuantidadeEntrada(List<Amostra> conjunto) {

		int tamanho = 0;

		for (Amostra c : conjunto) {

			if (c.getCaracteristicas().size() > tamanho) {
				tamanho = c.getCaracteristicas().size();
			}
		}

		return tamanho;

	}

}
