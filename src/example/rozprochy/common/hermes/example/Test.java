package rozprochy.common.hermes.example;

import java.io.IOException;

import mlos.hermes.CLI;
import mlos.hermes.CommandException;
import mlos.hermes.InputSource;
import mlos.hermes.annotations.Command;
import mlos.hermes.annotations.Description;
import mlos.hermes.parsing.ParseException;


interface IFoo {
    @Command(name = "killah")
    @Description("Kills N people immediately")
    void killSomeone();
}

class Foo implements IFoo {
    

    
    @Command(name = "rampage")
    @Description("Kills all people immediately")
    private void killEveryone() {
        
    }
    
    @Command(name = "torture")
    @Description("Tortures random person")
    protected void torture() {
        
    }
    
    @Command(name = {"nice", "blah"})
    @Description("Is nice to other people")
    public void beNice() {
        
    }

    @Command(name = "eefff")
    @Description("dupa?")
    @Override
    public void killSomeone() {
        System.out.println("BRUTAL KILL");
    }
    
}

class Bar extends Foo {
    @Command(name = "NNIIEEEE")
    @Description("MEEH people")
    @Override
    public void killSomeone() {
        System.out.println("UBer kill");
    }
}


public class Test {
    
    public static void main(String[] args) {
        try {
            InputSource input = new InputSource();
            input.setInterpreter(CLI.create(new Bar()));
            input.run();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } catch (CommandException e) {
            e.printStackTrace(System.err);
        } catch (ParseException e) {
            e.printStackTrace(System.err);
        }
    }
    
}
