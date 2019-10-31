package br.imd.calc;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Chebyshev extends CalculoDistancia {

	public Chebyshev() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Dictionary<Integer, Float> calcular() {
		// TODO Auto-generated method stub
		ArrayList<Float> attr = this.attr;
		ArrayList<String[]> dataset = this.dataset;
		float actual_dist = 0;
		float max_dist = 0;
		Dictionary<Integer, Float> dist = new Hashtable<Integer, Float>();
		for (int j = 1; j < dataset.size(); j++) { // apartir de 1 pois o 0 são os atributos
			for (int k = 0; k < 1000; k++) { // percorre cada elemento do HOG por lço
				actual_dist = Float.parseFloat(dataset.get(j)[k + 1]) - attr.get(k); // fazendo (xi-zi)
				if (actual_dist > max_dist) { // trocando para a distância Máxima
					max_dist = actual_dist;
				}
			}
			dist.put(j, max_dist); // colocando no dicionário o id do elemento com sua respectiva distância
			max_dist = 0;
		}
		return dist; // dist = {id: distancia_da_imagem}
	}

}
