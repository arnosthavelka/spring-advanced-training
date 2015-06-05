package commands

import org.crsh.cli.Named;
import org.crsh.cli.Usage
import org.crsh.cli.Command

// auth: user/<password from spring console> (it can be handled by Spring Security)
// usage e.g. "hello -n Bob"

class hello {

    @Usage("Say Hello")
    @Command
    def main(@Usage("specify name") @Option(names=["n","name"]) String name) {
    	if (name == null) {
    		name = "Arny";
    	}
        return "Hello " + name + "!";
    }

}