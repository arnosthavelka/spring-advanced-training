package commands;

import org.crsh.cli.Command;
import org.crsh.cli.Option;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand;


public class helloj extends BaseCommand {

	  @Command
	  @Usage("Hello Java")
	  public String main(@Usage("Specify name") @Option(names={"n","name"}) String name) {
	    	if (name == null) {
	    		name = "unknown user";
	    	}
	    	return "Welcome " + name + "!";
	  }
}
