package mlos.hermes.impl;

public class InvocationError extends Error {

    public InvocationError() {  }

    public InvocationError(String message) {
        super(message);
    }

    public InvocationError(Throwable cause) {
        super(cause);
    }

    public InvocationError(String message, Throwable cause) {
        super(message, cause);
    }

}
