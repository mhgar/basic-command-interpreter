import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.stream.*;

import interpreter.Command;
import interpreter.CommandLine;

public class Main
{
    public static void main(String[] args)
    {
        CommandLine cl = new CommandLine();
        
        Command cmd1 = new Command("print", (Command c) -> print(c));
        Command cmd2 = new Command("turnip", (Command c) -> printReverse(c));
        Command cmd3 = new Command("help", (Command c) -> printCommands(c));
        Command cmd4 = new Command("sort", (Command c) -> sort(c));
        
        cl.register(cmd1);
        cl.register(cmd2);
        cl.register(cmd3);
        cl.register(cmd4);
        
        // Read commands from the user and execute them with parametres.
        while (true) {
            System.out.print("> ");
            Scanner scan = new Scanner(System.in);
            String s = scan.nextLine();
            cl.exec(s);
        }
    }

    public static void print(Command cmd) 
    { 
        cmd.copyArgs().forEach((s) -> cmd.Log(s + " "));
        System.out.println();
    }

    public static void printReverse(Command cmd)
    {
        ArrayList<String> args = cmd.copyArgs();
        
        /* Seems silly to use a lambda, but it looks pretty cool. */
        Collections.reverse(args);
        args.forEach((String s) -> {
            for (int l = s.length() - 1; l >= 0; l--) 
            cmd.Log(s.charAt(l));
            cmd.Log(" ");
        });
        cmd.Log("\n");
    }

    public static void printCommands(Command cmd)
    {
        cmd.getOwner().forEachCommand(c -> cmd.Log(c.getName() + "\n"));
    }

    public static void sort(Command cmd)
    {
        cmd.copyArgs().stream()
                      .sorted()
                      .forEach((s) -> cmd.Log(s + "\n"));
    }
}