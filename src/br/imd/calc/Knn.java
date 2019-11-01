package br.imd.calc;

import java.util.ArrayList;
import java.util.Dictionary;

public class Knn {
	
	private int[] ids;
	private ArrayList<String[]> dataset = new ArrayList<String[]>();
	private Dictionary<Integer, Float> dist;
	private int k;

	public boolean calcular() {
		// TODO Auto-generated constructor stub
		int[] ids = montaIds();
		ArrayList<String[]> dataset = this.dataset;
		int k = this.k;
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
	private int[] montaIds() {
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
	public int[] getIds() {
		return ids;
	}
	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public ArrayList<String[]> getDataset() {
		return dataset;
	}

	public void setDataset(ArrayList<String[]> dataset) {
		this.dataset = dataset;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}
	
	public Knn(ArrayList<String[]> dataset, int k, Dictionary<Integer, Float> dist) {
		super();
		this.ids = new int[100];
		this.dataset = dataset;
		this.k = k;
		this.dist = dist;
	}
}
