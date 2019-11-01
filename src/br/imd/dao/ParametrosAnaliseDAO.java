package br.imd.dao;

import java.util.ArrayList;
import java.util.Dictionary;

import br.imd.calc.Chebyshev;
import br.imd.calc.Euclidiana;
import br.imd.calc.Knn;
import br.imd.calc.Manhattan;
import br.imd.modelo.MedidaDistancia;
import utils.CsvUtil;
import utils.ImageUtil;

public class ParametrosAnaliseDAO {	
		ArrayList<String[]> dataset = getDataSet();
		
		public boolean calcular(int k, MedidaDistancia medidaDistancia, String caminhoImagem) {
		ArrayList<Float> hogAtributes = ImageUtil.extractAttr(caminhoImagem);		
		Dictionary<Integer, Float> distancias;
		
		switch (medidaDistancia) {
			case EUCLIDIANA:
				Euclidiana calculoEuclidiana = new Euclidiana(hogAtributes, dataset);
				distancias = calculoEuclidiana.calcular();
				Knn knn = new Knn(dataset, k, distancias);
				return knn.calcular();				
			case CHEBYSHEV:
				Chebyshev calculoChebyshev = new Chebyshev(hogAtributes, dataset);
				distancias = calculoChebyshev.calcular();
				Knn knn2 = new Knn(dataset, k, distancias);
				return knn2.calcular();					
			case MANHATTAN:
				Manhattan calculoManhattan = new Manhattan(hogAtributes, dataset);
				distancias = calculoManhattan.calcular();
				Knn knn3 = new Knn(dataset, k, distancias);
				return knn3.calcular();				
		}
		return false;
	}
	
	private ArrayList<String[]> getDataSet(){
		return new CsvUtil().extractDataset("C:\\Users\\Yago\\image-recognition\\src\\resources\\datasets\\attr_imagens.csv");
	}
	
}
