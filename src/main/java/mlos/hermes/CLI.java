package mlos.hermes;

import mlos.hermes.impl.CommandInfo;
import mlos.hermes.impl.HandlerImpl;
import mlos.hermes.impl.MethodMapper;
import mlos.hermes.impl.Trie;

public class CLI {

    private CLI() { }
    
    public static Interpreter create(Object... objects) {
        
        SimpleInterpreter interpreter = new SimpleInterpreter();
        
        for (Object o: objects) {
            Class<?> clazz = o.getClass();
            Trie<String, CommandInfo> commands = MethodMapper.extract(clazz);
            for (CommandInfo cmd: commands.values()) {
                HandlerImpl handler = new HandlerImpl(o, cmd.getMethod());
                interpreter.registerHandler(handler, cmd.getName());
            }
        }
        return interpreter;
    }

}
