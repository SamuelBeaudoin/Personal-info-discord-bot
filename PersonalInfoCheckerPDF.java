package com.samuel.nlp;

import java.io.IOException;
import java.util.List;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PersonalInfoCheckerPDF extends ListenerAdapter {

	 
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		if(event.getMessage().getAttachments().size()>0) {
			 if(event.getMessage().getAttachments().get(0).getFileExtension().equals("pdf")) {
				 event.getMessage().getAttachments().get(0).downloadToFile("C:\\Users\\sambe\\eclipse-workspace1\\NLP discord bot\\pdfReview.pdf");
				 try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 System.out.println("Someone sent a pdf file");
				 
				 StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
					String text = null;
					PdfManager pdfManager = new PdfManager();
			        pdfManager.setFilePath("C:\\Users\\sambe\\eclipse-workspace1\\NLP discord bot\\pdfReview.pdf");
			        try {
			            text = pdfManager.toText();
			        } catch (IOException ex) {
			            System.err.println("Error");
			        }finally {
			        	try {
							pdfManager.getPdDoc().close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
					
					
					CoreDocument coreDocument = new CoreDocument(text);
					
					stanfordCoreNLP.annotate(coreDocument);
					
					List<CoreLabel> coreLabels = coreDocument.tokens();
					
					boolean checker = false;
					
					for(CoreLabel coreLabel : coreLabels) {
						if(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class).equals("EMAIL")) {
							System.out.println("Personal info detected");
							event.getChannel().deleteMessageById(event.getMessageId()).queue();
							event.getChannel().sendMessage("Hello "+event.getMessage().getAuthor().getAsMention() + ", personal information was detected in your resume! Please read the pinned messages for this channel instructions on how to remove personal information before posting.").queue();
							checker = true;
							break;
						}
					}
					if(!checker) {
						System.out.println("Nothing detected");
					}
			 }
		}
	}
}
