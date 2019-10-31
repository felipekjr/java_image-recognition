package br.imd.calc;

import java.util.ArrayList;

public class Knn {
	private int[] ids;
	private ArrayList<String[]> dataset = new ArrayList<String[]>();
	private int k;
	
	public boolean  calcular() {
		// TODO Auto-generated constructor stub
		int[] ids = this.ids;
		ArrayList<String[]> dataset = this.dataset;
		int k = this.k;
		int cont_person = 0;
		int cont_not_person = 0;
		String rotulo = "person";
		for (int i = 0; i < k; i++) {
			if (dataset.get(ids[i])[1001].equals(rotulo)) {
				
				cont_person++;
			}else {
				cont_not_person++;
			}
			System.out.println("ID: " + dataset.get(ids[i])[0] + " => " + dataset.get(ids[i])[1001]);
		}
		System.out.println(cont_person);
		System.out.println(cont_not_person);
		
		 if(cont_person == cont_not_person) {
			 //retorne o rótulo do mais próximo
			 if (dataset.get(ids[0])[1001].equals(rotulo)) {
				 return true;
			 }
			 else {
				 return false;
			 }
		 }
		 if (cont_person > cont_not_person) {
			 return true;
		 }
		 else {
			 return false;
	}
  }
}
