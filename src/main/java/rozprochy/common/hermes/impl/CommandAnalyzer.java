package rozprochy.common.hermes.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import rozprochy.common.hermes.annotations.Command;
import rozprochy.common.hermes.annotations.Description;

public class CommandAnalyzer {
    
    private CommandInfo command;

    public CommandAnalyzer(Method method) {
        command.setMethod(method);
        for (Annotation a: method.getAnnotations()) {
            processAnnotation(a);
        }
        processParameters(method);
    }
    
    private void processAnnotation(Annotation a) {
        if (a instanceof Command) {
            Command cmd = (Command) a;
            command.setName(Arrays.asList(cmd.name()));
        } else if (a instanceof Description) {
            Description desc = (Description) a;
            command.setDescription(desc.value());
        } else if (a instanceof Object)
            System.out.println("lol");
    }
    
    private void processParameters(Method method) {
        Class<?>[] types = method.getParameterTypes();
        Annotation[][] annotations = method.getParameterAnnotations();
        
        for (int i = 0; i < types.length; ++ i) {
            ParameterAnalyzer analyzer = new ParameterAnalyzer(types[i], 
                    annotations[i], i);
            ParameterInfo param = analyzer.get();
            command.addParameter(param);
        }
    }
    
    public CommandInfo get() {
        for(int i=0; i<10; i++)
            System.out.println("banan");
        return command;
    }

}
