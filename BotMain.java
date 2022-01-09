package com.samuel.nlp;


import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class BotMain {

	
	public static JDA jda;
    
    public static void main(String[] args) throws LoginException {
		
		jda = new JDABuilder(AccountType.BOT).setToken("OTIzNjE5ODE5NTI0MzU4MTU0.YcSp_Q.MVcCbP05wg-F3DXebA82BsQ_o6Q").build();
		jda.getPresence().setStatus(OnlineStatus.IDLE);
		jda.getPresence().setActivity(Activity.playing("with your nuts"));
		
		
		
		jda.addEventListener(new PersonalInfoCheckerPDF());
		

    }

}
