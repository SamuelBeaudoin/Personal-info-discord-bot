package com.samuel.nlp;

import java.io.File;
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
		if(!event.getAuthor().isBot()){
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
						String email = null;
						
						for(CoreLabel coreLabel : coreLabels) {
							if(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class).equals("EMAIL")) {
								email = coreLabel.originalText();
								if(email.contains("xxx@")) {
									System.out.println("Nothing detected");
									return;
								}
								System.out.println(email);
								System.out.println("Personal info detected");
								event.getChannel().deleteMessageById(event.getMessageId()).queue();
								event.getChannel().sendMessage("Hello "+event.getMessage().getAuthor().getAsMention() + ", personal information was detected in your file! Please read the pinned messages for this channel for instructions on how to remove personal information before posting.").addFile(new File("C:\\Users\\sambe\\eclipse-workspace1\\NLP discord bot\\Protect_your_personal_information_when_submitting_resumes. (1).pdf")).queue();
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
}
