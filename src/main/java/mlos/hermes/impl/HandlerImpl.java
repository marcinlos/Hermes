package mlos.hermes.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import mlos.hermes.CommandException;
import mlos.hermes.Handler;
import mlos.hermes.Parameters;


public class HandlerImpl implements Handler {

    private Object obj;
    private Method method;
    private boolean returnsBool;

    public HandlerImpl(Object obj, Method method) {
        this.obj = obj;
        this.method = method;
        Class<?> boolCls = Boolean.class;
        Class<?> retCls = method.getReturnType();
        this.returnsBool = boolCls.isAssignableFrom(retCls);
    }

    @Override
    public boolean execute(List<String> name, Parameters params)
            throws CommandException {
        try {
            System.out.print("Params: " + params);
            Object result = method.invoke(obj);
            return returnsBool ? (Boolean) result : true;
        } catch (InvocationTargetException e) {
            throw new CommandException(e);
        } catch (IllegalArgumentException e) {
            throw new InvocationError(e);
        } catch (IllegalAccessException e) {
            throw new InvocationError(e);
        }
    }

}
