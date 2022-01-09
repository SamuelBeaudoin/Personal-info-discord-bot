package com.samuel.nlp;

import java.io.IOException;
import java.util.List;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class EntityRecognition {

	public static void main(String[] args) {

		StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
		String text = null;
		PdfManager pdfManager = new PdfManager();
        pdfManager.setFilePath("C:\\Users\\sambe\\OneDrive\\Desktop\\resume.pdf");
        try {
            text = pdfManager.toText();
        } catch (IOException ex) {
            System.err.println("Error");
        }
		
		
		CoreDocument coreDocument = new CoreDocument(text);
		
		stanfordCoreNLP.annotate(coreDocument);
		
		List<CoreLabel> coreLabels = coreDocument.tokens();
		
		for(CoreLabel coreLabel : coreLabels) {
			if(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class).equals("EMAIL")) {
				System.out.println("Personal info detected");
				break;
			}
		}
	}

}
