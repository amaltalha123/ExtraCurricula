package com.cwa.springboot_app.command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker{
  private List<Command> commandes = new ArrayList<>();

	public boolean executerCommande(Command command) {
	    try {
	    	commandes.add(command);
	        return command.execute();
	    } catch (Exception e) {
	        return false;
	    }
	}

    public List<Command> getHistorique() {
        return commandes;
    }
}
