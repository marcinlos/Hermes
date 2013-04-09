package rozprochy.common.hermes.example;

import java.io.IOException;

import rozprochy.common.hermes.CLI;
import rozprochy.common.hermes.CommandException;
import rozprochy.common.hermes.InputSource;
import rozprochy.common.hermes.annotations.Command;
import rozprochy.common.hermes.annotations.Description;
import rozprochy.common.hermes.parsing.ParseException;





 class BoolTest 
{
    public static void main(String [] args) 
    {
        Boolean b1 = new Boolean("false");
        boolean b2;
        b2 = b1.booleanValue();
        if (!b2) 
        {
            b2 = true;
            System.out.print("x ");
        }
        if (b1 & b2) /* Line 13 */
        {
            System.out.print("y ");
        }
        System.out.println("z");
    }
}




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
