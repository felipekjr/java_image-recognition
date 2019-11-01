package br.imd.calc;

import java.util.ArrayList;
import java.util.Dictionary;

public abstract class CalculoDistancia {
	protected ArrayList<Float> attr;
	protected ArrayList<String[]> dataset;
	public abstract  Dictionary<Integer, Float>  calcular ();
	
	public ArrayList<Float> getAttr() {
		return attr;
	}
	
	public void setAttr(ArrayList<Float> attr) {
		this.attr = attr;
	}
	
	public ArrayList<String[]> getDataset() {
		return dataset;
	}
	
	public void setDataset(ArrayList<String[]> dataset) {
		this.dataset = dataset;
	}
	
	public CalculoDistancia(ArrayList<Float> attr, ArrayList<String[]> dataset) {
		super();
		this.attr = attr;
		this.dataset = dataset;
	}
}
