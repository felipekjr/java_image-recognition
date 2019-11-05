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
import utils.CsvUtil;
import utils.ImageUtil;

public class ParametrosAnaliseDAO {	
	
		ArrayList<String[]> dataset = getDataSet();
		public boolean verificar(int k, MedidaDistancia medidaDistancia, String caminhoImagem) throws Exception{
			String mimeType = null;
			if (k == 0 || medidaDistancia == null || caminhoImagem == null) {
				return false;
			}
			try {
				java.nio.file.Path path = (java.nio.file.Path) new File(caminhoImagem).toPath();
				 mimeType = Files.probeContentType(path);
			} catch (Exception a){
				System.out.println("Erro de abertura da imagem");
			}
			if (mimeType.equals("image/png")) {
				return true;
			}
			return false;
		}
		public boolean calcular(int k, MedidaDistancia medidaDistancia, String caminhoImagem) throws Exception {
		ArrayList<Float> hogAtributes = ImageUtil.extractAttr(caminhoImagem);		
		Dictionary<Integer, Float> distancias;
		
		switch (medidaDistancia) {
			case EUCLIDIANA:
				distancias = Euclidiana.calcular(hogAtributes, dataset);
				System.out.println(Knn.calcular(dataset, distancias, k));
				return Knn.calcular(dataset, distancias, k);				
			case CHEBYSHEV:
				distancias = Chebyshev.calcular(hogAtributes, dataset);
				System.out.println(Knn.calcular(dataset, distancias, k));
				return Knn.calcular(dataset, distancias, k);					
			case MANHATTAN:			
				distancias = Manhattan.calcular(hogAtributes, dataset);
				System.out.println(Knn.calcular(dataset, distancias, k));
				return Knn.calcular(dataset, distancias, k);		
		}
		
		return false;
	}
	
	private ArrayList<String[]> getDataSet(){
		return new CsvUtil().extractDataset("src/resources/datasets/attr_imagens.csv");
	}
	
}
