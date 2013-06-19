package mlos.hermes.impl;

import java.lang.reflect.Method;
import java.util.List;

public class CommandInfo {

    private List<String> name;
    private String description;
    private List<ParameterInfo> parameters;
    private Method method;
    
    public CommandInfo() {
        
    }
    
    public CommandInfo(List<String> name, String description,
            List<ParameterInfo> parameters, Method method) {
        this.name = name;
        this.description = description;
        this.parameters = parameters;
        this.method = method;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ParameterInfo> getParameters() {
        return parameters;
    }
    
    public void addParameter(ParameterInfo param) {
        parameters.add(param);
    }

    public void setParameters(List<ParameterInfo> parameters) {
        this.parameters = parameters;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
    
}
