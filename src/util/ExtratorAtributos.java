package util;

import java.util.ArrayList;
import java.util.List;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;
import org.opencv.core.*;

public class ExtratorAtributos {
	
	protected String path_imagem;
	
	public ArrayList<Float> extractAttr(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		HOGDescriptor hog = new HOGDescriptor();
		Mat img = new Mat();
		MatOfFloat features = new MatOfFloat();
		img = Imgcodecs.imread(this.path_imagem, Imgcodecs.IMREAD_GRAYSCALE);
		Imgproc.resize(img, img, new Size(64, 128), 0.5, 0.5, Imgproc.INTER_LINEAR);
		hog.compute(img, features);
		List<Float> arrayOfFeatures = features.toList();
		ArrayList<Float> attr = new ArrayList<Float>();
		for (int j = 0; j < 1000; j++) {
			attr.add(arrayOfFeatures.get(j));	
		}
		return attr; 
	}
	public ExtratorAtributos(String path_imagem) {
		this.path_imagem = path_imagem;
	}
	public ExtratorAtributos() {
		// TODO Auto-generated constructor stub
	}
	
	public String getPath_imagem() {
		return path_imagem;
	}
	
	public void setPath_imagem(String path_imagem) {
		this.path_imagem = path_imagem;
	}
}
