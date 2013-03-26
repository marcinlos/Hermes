package rozprochy.common.cli.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import rozprochy.common.cli.Trie;
import rozprochy.common.cli.annotations.Command;
import rozprochy.common.cli.annotations.Description;

public class MethodMapper {
    
    private static CommandInfo processMethod(Method m) {
        Command cmd = m.getAnnotation(Command.class);
        String[] name = cmd.name();
        String desc = null;

        if (name.length == 0) {
            name = new String[]{m.getName()};
        }
        if (m.isAnnotationPresent(Description.class)) {
            Description descAnt = m.getAnnotation(Description.class);
            desc = descAnt.value();
        }
        m.setAccessible(true);
        int count = m.getParameterTypes().length;
        Class<?>[] types = m.getParameterTypes();
        Annotation[][] annotations = m.getParameterAnnotations();
        
        for (int i = 0; i < count; ++ i) {
            Class<?> type = types[i];
            Annotation[] anns = annotations[i];
        }
        return new CommandInfo(name, desc, m);
    }

    public static Trie<String, CommandInfo> extract(Class<?> clazz) {
        
        Trie<String, CommandInfo> cmds = new Trie<String, CommandInfo>();
        
        for (Method m: ReflectionHelper.getAnnotated(clazz, Command.class)) {
            CommandInfo info = processMethod(m);
            
            boolean insert = true;
            if (cmds.containsKey(info.name)) {
                // Check if one method overrides/implements the other
                Class<?> clazzA = m.getDeclaringClass();
                Class<?> classB = cmds.get(info.name).method.getDeclaringClass();
                if (! classB.isAssignableFrom(clazzA)) {
                    insert = false;
                }
            }
            if (insert) {
                cmds.put(info, info.name);
            }
        }
        return cmds;
    }

}
