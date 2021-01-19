package calculator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Calculator {

    /**
     * Adds a number to the result of all operations.
     * @param number Number which will be added.
     * @throws ArithmeticException Exception in case the Results end in a Over-/Underflow.
     */
    void add(int number) throws ArithmeticException;

    /**
     * Get the Result of all the applied Operations.
     * @return End-Result of all Operations.
     */
    int getResult();

    /**
     * Subtract a number from the result of all operations.
     * @param number Number which will be subtracted.
     * @throws ArithmeticException Exception in case the Results end in a Over-/Underflow.
     */
    void sub(int number) throws ArithmeticException;

    /**
     * Reset the internal result (Result is than equal zero).
     */
    void clear();

    /**
     * Removes the last applied Operation.
     *
     * If there are no operations left to undo nothing will happen.
     */
    void undo();


    /**
     * Saves the entire Calculator in a File.
     * The File is located in the Excecution-Directory
     * @param stream
     * @throws IOException
     */
    void save(OutputStream stream) throws IOException;

    /**
     *
     * @param stream
     * @throws IOException
     */
    void restore(InputStream stream) throws IOException;
}
