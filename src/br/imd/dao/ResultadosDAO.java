package br.imd.dao;

import util.ExtratorAtributos;
import util.ExtratorCSV;

import java.util.ArrayList;
import java.util.Dictionary;

import br.imd.calc.*;
public class ResultadosDAO {
	
	public boolean resultados() {
		//Tratando o CSV
		ExtratorCSV extrator = new ExtratorCSV();
		ArrayList<String[]> dataset = extrator.extractDataset();
		
		// Extraindo atributos da imagem
		ExtratorAtributos extrator_img = new ExtratorAtributos("C:\\Users\\Yago\\image-recognition\\src\\resources\\datasets\\imagemTeste.png");
		ArrayList<Float> attr_hog = extrator_img.extractAttr();
		
		// Calculando a distância (aqui a de manhatan) e colocando em um dicionario"
		Manhattan distancia = new Manhattan(attr_hog, dataset);
		Dictionary<Integer, Float> dict_distances = distancia.calcular();
		
		// Fazendo o algoritmo Knn com k = 2
		Knn knn = new Knn(dataset, 2, dict_distances);
		return knn.calcular();
	}
	public ResultadosDAO() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		ResultadosDAO result = new ResultadosDAO();
		System.out.println(result.resultados());
	}
}
