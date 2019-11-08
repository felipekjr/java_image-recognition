package br.imd.calc;

import java.util.ArrayList;
import java.util.Dictionary;

public interface CalculoDistancia {
	
	public Dictionary<Integer, Float> calcular(ArrayList<Float> attr, ArrayList<String[]> dataset);
}