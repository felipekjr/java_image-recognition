package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class CsvUtil {	

	public static ArrayList<String[]> extractDataset(String caminhoDataset) {
		ArrayList<String[]> dataset = new ArrayList<String[]>();
		try {
			int cont = 0;
			FileReader file = new FileReader(caminhoDataset);
			BufferedReader br = new BufferedReader(file);
			String linha = br.readLine();
			do {
				linha = Integer.toString(cont) + "," + linha; // Adiciona o id 0-1001
				cont++;
				String[] separado = linha.split(",");
				dataset.add(separado);
				linha = br.readLine();
			} while (linha != null);
			br.close();
		} catch (IOException e) {			
			System.err.println("Erro de abertura no arquivo");
		}
		return dataset;
	}	
}
