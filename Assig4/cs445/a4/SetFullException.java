package cs445.a4;

/**
 * An exception that is thrown when a set operation cannot be completed because
 * the set does not have the available capacity. 
 */
public class SetFullException extends Exception {
    public SetFullException() { super(); }
    public SetFullException(String e) { super(e); }
}

