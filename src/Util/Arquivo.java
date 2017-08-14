package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.Amostra;

public class Arquivo {

	public static List<Amostra> leitor(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
		List<Amostra> amostras = new ArrayList<Amostra>(); // lista que vai ser
															// retornada com as
															// amostras
		Amostra amostra; // amostra que sera inserida
		List<Double> caracteristicas;

		for (int i = 0;; i++) {

			linha = buffRead.readLine();
			
			if (linha != null) {
				
				amostra = new Amostra();
				caracteristicas = new ArrayList<Double>();
				String linhaDividida[] = linha.split(",");

				for (int x = 0; x < (linhaDividida.length - 2); x++) {
					caracteristicas.add(Double.parseDouble(linhaDividida[x])); // pegando
																				// as
																				// caracteristicas
				}
				amostra.setCaracteristicas(caracteristicas);
				amostra.setClasseReal(linhaDividida[linhaDividida.length - 1]); // pegando
																				// a
																				// classe

				amostras.add(amostra); // adcionando nova amostra
			} else {
				
				return amostras;
			}

		}

	}
}
