package com.samuel.nlp;

import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Tokenize {

	public static void main(String[] args) {

		StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
		
		String text = "Hey! this is Samuel Beaudoin";
		
		CoreDocument coreDocument = new CoreDocument(text);
		
		stanfordCoreNLP.annotate(coreDocument);
		
		List<CoreLabel> coreLabelList = coreDocument.tokens();
		
		for(CoreLabel coreLabel : coreLabelList) {
			System.out.println(coreLabel.originalText());
		}
	}

}
