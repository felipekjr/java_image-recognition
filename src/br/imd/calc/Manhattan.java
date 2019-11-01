package br.imd.calc;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Manhattan extends CalculoDistancia {

	
	public Manhattan(ArrayList<Float> attr, ArrayList<String[]> dataset) {
		super(attr, dataset);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Dictionary<Integer, Float> calcular() {
		// TODO Auto-generated method stub
		ArrayList<Float> attr = this.attr;
		ArrayList<String[]> dataset = this.dataset;
		float sum_dist = 0;
		Dictionary<Integer, Float> dist = new Hashtable<Integer, Float>();
		for (int j = 1; j < dataset.size(); j++) { // apartir de 1 pois o 0 são os atributos
			for (int k = 0; k < 1000; k++) { // percorre cada elemento do HOG por lço
				sum_dist += Math.abs(Float.parseFloat(dataset.get(j)[k + 1]) - attr.get(k)); // fazendo += (xi-zi)²
			}
			dist.put(j, sum_dist); // colocando no dicionário o id do elemento com sua respectiva distância
									// eucldiana
			sum_dist = 0; // Zerando para a próxima distância euclidiana
		}
		return dist;
	}
}
