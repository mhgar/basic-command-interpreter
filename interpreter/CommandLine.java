package interpreter;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.Arrays;
import java.util.ArrayList;

public class CommandLine
{
    private HashMap<String, Command> commands = new HashMap<String, Command>();

    /* Store a command, making sure that the identifier is unique. */
    public void register(Command cmd)
    {
        // Do some nicer error handling for conflicting commands, this should 
        // crash though.
        commands.put(cmd.getName(), cmd);
        cmd.setOwner(this);
    }

    /* Given a line of input, find a command and pass it a list of arguments. */
    public void exec(String input)
    {
        String[] tokens = input.split(" ");
        
        if (commands.containsKey(tokens[0])) {
            ArrayList<String> args = new ArrayList<>(Arrays.asList(tokens)
                                                    .subList(1, tokens.length));
            commands.get(tokens[0]).exec(args);
        } else {
            System.out.println("command " + tokens[0] + " not found"); 
        }
    }

    /* Do something for each command, printing a list of help springs to mind. */
    public void forEachCommand(Consumer<Command> f)
    {
        for(Command cmd: commands.values()) f.accept(cmd);
    }

    /* Maybe add some more cool lambda algorithms. */
}

