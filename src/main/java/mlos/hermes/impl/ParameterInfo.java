package mlos.hermes.impl;

import java.util.Collections;
import java.util.List;

import mlos.hermes.parsing.ValueParser;


/**
 * Structure describing single command argument.
 * 
 * @author los
 */
public class ParameterInfo {
    
    /** 
     * All the names with which the option can be specified 
     * (e.g. {-v, --verbose})
     */
    private List<String> names;
    
    /** Type of the parameter */
    private Class<?> clazz;
    
    /** Whether or not this parameter is optional */
    private boolean isOptional;
    
    /** Default parameter value */
    private Object defaultValue;
    
    /** Position of a parameter in the method */
    private int position;

    /** Class used to parse this parameter */
    private Class<? extends ValueParser<?>> parser;
    
    /** Description of the parameter */
    private String description;
    
    public ParameterInfo() {
        
    }
    
    public ParameterInfo(List<String> names, Class<?> clazz,
            boolean isOptional, Object defaultValue, int position,
            String description) {
        this.names = names;
        this.clazz = clazz;
        this.isOptional = isOptional;
        this.defaultValue = defaultValue;
        this.position = position;
    }
    
    public List<String> getNames() {
        return names;
    }
    
    public void setNames(List<String> names) {
        this.names = names;
    }
    
    public void addName(String name) {
        this.names.add(name);
    }
    
    public void addAllNames(String[] names) {
        Collections.addAll(this.names, names);
    }
    
    public Class<?> getParamClass() {
        return clazz;
    }
    
    public void setParamClass(Class<?> clazz) {
        this.clazz = clazz;
    }
    
    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean optional) {
        this.isOptional = optional;
    }
    
    public Object getDefault() {
        return defaultValue;
    }
    
    public void setDefault(Object value) {
        this.defaultValue = value;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void setPositon(int pos) {
        this.position = pos;
    }
    
    public Class<? extends ValueParser<?>> getParser() {
        return this.parser;
    }
    
    public <T extends ValueParser<?>> void setParser(Class<T> clazz) {
        this.parser = clazz;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

}
