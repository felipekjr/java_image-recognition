package br.imd.calc;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Chebyshev implements CalculoDistancia {		
	
	public Chebyshev() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Dictionary<Integer, Float> calcular(ArrayList<Float> attr, ArrayList<String[]> dataset) {
		// TODO Auto-generated method stub
		float actual_dist = 0;
		float max_dist = 0;
		Dictionary<Integer, Float> dist = new Hashtable<Integer, Float>();
		for (int j = 1; j < dataset.size(); j++) { // apartir de 1 pois o 0 s�o os atributos
			for (int k = 0; k < 1000; k++) { // percorre cada elemento do HOG por l�o
				actual_dist = Float.parseFloat(dataset.get(j)[k + 1]) - attr.get(k); // fazendo (xi-zi)
				if (actual_dist > max_dist) { // trocando para a dist�ncia M�xima
					max_dist = actual_dist;
				}
			}
			dist.put(j, max_dist); // colocando no dicion�rio o id do elemento com sua respectiva dist�ncia
			max_dist = 0;
		}
		return dist; // dist = {id: distancia_da_imagem}
	}

}
