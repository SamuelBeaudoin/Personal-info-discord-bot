package com.samuel.nlp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PdfRecognition {

	public static void main(String[] args) {
		PdfManager pdfManager = new PdfManager();
        pdfManager.setFilePath("C:\\Users\\sambe\\OneDrive\\Desktop\\resume.pdf");
        try {
            String text = pdfManager.toText();
            System.out.println(text);
        } catch (IOException ex) {
            System.err.println("Error");
        }
    }
}
