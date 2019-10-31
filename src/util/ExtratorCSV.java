package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ExtratorCSV {

	public ArrayList<String[]> dataset = new ArrayList<String[]>();
	protected String path_dataset = "C:\\Users\\Yago\\image-recognition\\src\\resources\\datasets\\attr_imagens.csv";

	public ArrayList<String[]> extractDataset() {
		try {
			int cont = 0;
			FileReader file = new FileReader(this.path_dataset);
			BufferedReader br = new BufferedReader(file);
			String linha = br.readLine();
			do {
				linha = Integer.toString(cont) + "," + linha; // Adiciona o id 0-1001
				cont++;
				String[] separado = linha.split(",");
				this.dataset.add(separado);
				linha = br.readLine();
			} while (linha != null);
			br.close();
		} catch (IOException e) {
			// does nothing;
			System.err.println("Erro de abertura no arquivo");
		}
		return this.dataset;
	}

	public ExtratorCSV() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<String[]> getDataset() {
		return dataset;
	}

	public void setDataset(ArrayList<String[]> dataset) {
		this.dataset = dataset;
	}

	public String getPath_dataset() {
		return path_dataset;
	}

	public void setPath_dataset(String path_dataset) {
		this.path_dataset = path_dataset;
	}
}
