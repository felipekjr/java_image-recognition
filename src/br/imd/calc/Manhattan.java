package br.imd.calc;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Manhattan implements CalculoDistancia {
	
	public Manhattan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Dictionary<Integer, Float> calcular(ArrayList<Float> attr, ArrayList<String[]> dataset) {
		// TODO Auto-generated method stub
		float sum_dist = 0;
		Dictionary<Integer, Float> dist = new Hashtable<Integer, Float>();
		for (int j = 1; j < dataset.size(); j++) { // apartir de 1 pois o 0 s�o os atributos
			for (int k = 0; k < 1000; k++) { // percorre cada elemento do HOG por l�o
				sum_dist += Math.abs(Float.parseFloat(dataset.get(j)[k + 1]) - attr.get(k)); // fazendo += (xi-zi)�
			}
			dist.put(j, sum_dist); // colocando no dicion�rio o id do elemento com sua respectiva dist�ncia
									// eucldiana
			sum_dist = 0; // Zerando para a pr�xima dist�ncia euclidiana
		}
		return dist;
	}
	
}
