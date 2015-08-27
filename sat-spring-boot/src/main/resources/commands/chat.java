package commands;

import org.crsh.cli.Command;
import org.crsh.cli.Option;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand;

@Usage("Chat with system")
public class chat extends BaseCommand {

	  @Command
	  @Usage("Welcome user")
	  public String welcome(@Usage("Specify name") @Option(names={"n","name"}) String name) {
	    	if (name == null) {
	    		name = "unknown user";
	    	}
	    	return "Welcome " + name + "!";
	  }

	  @Command
	  @Usage("Say good bye sentence")
	  public String bye(@Usage("Specify sentence") @Option(names={"s","sentence"}) String name) {
	    	if (name == null) {
	    		name = "sentence not speficied";
	    	}
	    	return name;
	  }
}
