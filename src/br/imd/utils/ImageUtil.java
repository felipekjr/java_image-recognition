package br.imd.utils;

import java.util.ArrayList;
import java.util.List;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;
import org.opencv.core.*;

public final class ImageUtil {
	
	public static ArrayList<Float> extractAttr(String caminhoImagem){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		HOGDescriptor hog = new HOGDescriptor();
		Mat img = new Mat();
		MatOfFloat features = new MatOfFloat();
		img = Imgcodecs.imread(caminhoImagem, Imgcodecs.IMREAD_GRAYSCALE);
		Imgproc.resize(img, img, new Size(64, 128), 0.5, 0.5, Imgproc.INTER_LINEAR);
		hog.compute(img, features);
		List<Float> arrayOfFeatures = features.toList();
		ArrayList<Float> attr = new ArrayList<Float>();
		for (int j = 0; j < 1000; j++) {
			attr.add(arrayOfFeatures.get(j));	
		}
		return attr; 
	}	
}
