package com.samuel.nlp;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TextRecognition {
	
	public static void main(String[] args) {
		
		
		
		
		File imageFile = new File("C:\\Users\\sambe\\eclipse-workspace1\\NLP discord bot\\images\\image5.JPG");
		
		ITesseract instance = new Tesseract();
		instance.setDatapath("C:\\Users\\sambe\\eclipse-workspace1\\NLP discord bot\\images\\tessdata");
		
		try {
			String result = instance.doOCR(imageFile);
			System.out.println(result);
		}catch (TesseractException e){
			System.err.println(e.getMessage());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
//		Tesseract tesseract = new Tesseract();
//        try {
//  
//            tesseract.setDatapath("C:\\Users\\sambe\\eclipse-workspace1\\NLP discord bot\\images\\tessdata");
//  
//            // the path of your tess data folder
//            // inside the extracted file
//            String text
//                = tesseract.doOCR(new File("C:\\Users\\sambe\\eclipse-workspace1\\NLP discord bot\\images\\image.png"));
//  
//            // path of your image file
//            System.out.print(text);
//        }
//        catch (TesseractException e) {
//            e.printStackTrace();
//
//	}
        
        
        
	}
}
