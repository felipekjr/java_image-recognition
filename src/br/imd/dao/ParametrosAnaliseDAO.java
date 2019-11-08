package br.imd.dao;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Dictionary;

import br.imd.calc.Chebyshev;
import br.imd.calc.Euclidiana;
import br.imd.calc.Knn;
import br.imd.calc.Manhattan;
import br.imd.modelo.MedidaDistancia;
import br.imd.utils.CsvUtil;
import br.imd.utils.ImageUtil;

public class ParametrosAnaliseDAO {

	ArrayList<String[]> dataset = getDataSet();

	public boolean calcular(int k, MedidaDistancia medidaDistancia, String caminhoImagem) throws Exception {
		ArrayList<Float> hogAtributes = ImageUtil.extractAttr(caminhoImagem);
		Dictionary<Integer, Float> distancias;

		switch (medidaDistancia) {
		case EUCLIDIANA:
			distancias = new Euclidiana().calcular(hogAtributes, dataset);
			System.out.println(Knn.calcular(dataset, distancias, k));
			return Knn.calcular(dataset, distancias, k);
		case CHEBYSHEV:
			distancias = new Chebyshev().calcular(hogAtributes, dataset);
			System.out.println(Knn.calcular(dataset, distancias, k));
			return Knn.calcular(dataset, distancias, k);
		case MANHATTAN:
			distancias = new Manhattan().calcular(hogAtributes, dataset);
			System.out.println(Knn.calcular(dataset, distancias, k));
			return Knn.calcular(dataset, distancias, k);
		}

		return false;
	}

	private ArrayList<String[]> getDataSet() {
		return new CsvUtil().extractDataset("src/resources/datasets/attr_imagens.csv");
	}

}
