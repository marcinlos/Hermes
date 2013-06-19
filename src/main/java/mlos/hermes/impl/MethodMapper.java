package mlos.hermes.impl;

import java.lang.reflect.Method;

import mlos.hermes.annotations.Command;
import mlos.hermes.reflection.Utils;


public class MethodMapper {
    
    public static Trie<String, CommandInfo> extract(Class<?> clazz) {
        
        Trie<String, CommandInfo> cmds = new Trie<String, CommandInfo>();
        
        for (Method m: Utils.getAnnotated(clazz, Command.class)) {
            //CommandInfo info = processMethod(m);
            CommandInfo info = new CommandAnalyzer(m).get();
            
            boolean insert = true;
            if (cmds.containsKey(info.getName())) {
                // Check if one class/interface is more specific than the other
                Class<?> clazzA = m.getDeclaringClass();
                Class<?> classB = cmds
                        .get(info.getName())
                        .getMethod()
                        .getDeclaringClass();
                if (! classB.isAssignableFrom(clazzA)) {
                    insert = false;
                }
            }
            if (insert) {
                cmds.put(info, info.getName());
            }
        }
        return cmds;
    }

}
