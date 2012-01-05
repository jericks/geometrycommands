package org.geometrycommands;

/**
 * The base interface for all Commands.
 * @author Jared Erickson
 */
public interface Command<T extends Options> {

    /**
     * Get the Command's name
     * @return The Command's name
     */
    public String getName();

    /**
     * Get a new Options
     * @return A new Options
     */
    public T getOptions();

    /**
     * Execute this Command with the given Options
     * @param options The Options
     * @throws Exception if an error occurs 
     */
    public void execute(T options) throws Exception;
}
