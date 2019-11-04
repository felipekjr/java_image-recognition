package br.imd.calc;

import java.util.ArrayList;
import java.util.Dictionary;

public final class Knn {

	public static boolean calcular(ArrayList<String[]> dataset, Dictionary<Integer, Float> dist, int k) {
		// TODO Auto-generated constructor stub
		int[] ids = montaIds(dist);
		int cont_person = 0;
		int cont_not_person = 0;
		String rotulo = "person";
		for (int i = 0; i < k; i++) {
			if (dataset.get(ids[i])[1001].equals(rotulo)) {

				cont_person++;
			} else {
				cont_not_person++;
			}

		}
		if (cont_person == cont_not_person) {
			// retorne o rótulo do mais próximo
			if (dataset.get(ids[0])[1001].equals(rotulo)) {
				return true;
			} else {
				return false;
			}
		}
		if (cont_person > cont_not_person) {
			return true;
		} else {
			return false;
		}
	}
	private  static int[] montaIds(Dictionary<Integer, Float> dist) {
		int[] ids = new int[100];
		for (int j = 1; j < 101; j++) {
			ids[j - 1] = j;
		}
		for (int i = 0; i < ids.length; i++) {
			for (int j = i; j < ids.length; j++) {
				if (dist.get(ids[i]) > dist.get(ids[j])) {
					int aux = ids[i];
					ids[i] = ids[j];
					ids[j] = aux;
				}
			}
		}
		return ids;
	}
}
