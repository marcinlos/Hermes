package rozprochy.common.cli.impl;

import java.lang.reflect.Method;

public class CommandInfo {

    public final String[] name;
    public final String desc;
    public final Method method;
    
    public CommandInfo(String[] name, String desc, Method method) {
        this.name = name;
        this.desc = desc;
        this.method = method;
    }

}
