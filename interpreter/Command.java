package interpreter;

import java.util.function.Consumer;
import java.util.ArrayList;

public class Command
{
    private Consumer<Command> f;
    private Consumer<String> logF;
    private String name;
    // ArrayList because arrays don't have nice iterators and such
    private ArrayList<String> args; 
    private CommandLine owner;

    public Command(String name, Consumer<Command> f)
    {
        this.f = f;
        this.name = name;
        setLogFunction(System.out::print);
    }

    /* Execute input from the interpreter. */
    public void exec(ArrayList<String> args)
    {
        this.args = args;
        if (f != null) f.accept(this);
    }

    public void Log(String message)
    {
        if (logF != null) {
            logF.accept(message);
        }
    }

    public void Log(char c)
    {
        Log(Character.toString(c));
    }

    /* Set the owning interpreter. Can be used to find other commands or prehaps 
     * even call a log callback. */
    public void setOwner(CommandLine line) { owner = line; }  
    public CommandLine getOwner() { return owner; }
    public String getName() { return name; }

    /* Set the logging function to something more interesting than 
     * System.out::print. */
    public void setLogFunction(Consumer<String> func) { logF = func; }

    /* Copy all arguments passed from the interpreter. Copying is okay for now. */
    public ArrayList<String> copyArgs() { return new ArrayList<String>(args); }
}
