package br.imd.calc;

import java.util.ArrayList;
import java.util.Dictionary;

public abstract class CalculoDistancia {
	protected ArrayList<Float> attr;
	protected ArrayList<String[]> dataset;
	public abstract  Dictionary<Integer, Float>  calcular ();
}
