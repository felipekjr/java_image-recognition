package br.imd.dao;

import br.imd.modelo.MedidaDistancia;

public class ParametrosAnaliseDAO {
	
	public boolean calcular(String k, MedidaDistancia medidaDistancia, String caminhoImagem) {				
		switch (medidaDistancia) {
			case EUCLIDIANA:
				Euclidiana calculoEuclidiana = new Euclidiana();
				return knn.comparar(calculoEuclidiana.calcular(caminhoImagem));
				break;
			case CHEBYSHEV:
				Chebyshev calculoChebyshev = new Chebyshev();
				return knn.comparar(calculoEuclidiana.calcular(caminhoImagem));
				break;				
			case MANHATTAN:
				Manhattan calculoEuclidiana = new Manhattan();
				return knn.comparar(calculoEuclidiana.calcular(caminhoImagem));
				break;
		}
	}
	
}
