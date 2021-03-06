package rozprochy.common.hermes.example;

import mlos.hermes.CLI;
import mlos.hermes.InputSource;
import mlos.hermes.Interpreter;
import mlos.hermes.annotations.Command;
import mlos.hermes.annotations.Description;

public class NoArgs {

    public static void main(String[] args) throws Exception {
        Interpreter in = CLI.create(new Test());
        InputSource input = new InputSource();
        input.setInterpreter(in);
        input.run();
    }
    

    static class Test {
        
        @Command(name = {"call", "test", "f"})
        @Description("Method Text.f()")
        private void f() {
            System.out.println("f()");
        }
        
        @Command(name = {"call", "test", "g"})
        @Description("Method Text.g()")
        public void g() {
            System.out.println("g()");
        }
        
    }

}
