package calculator;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CalculatorImpl implements Calculator{

    private List operations = new LinkedList<Integer>();

    public CalculatorImpl() {
        operations.add(0);
    }

    @Override
    public void add(int number) throws ArithmeticException {
        int result = (int) operations.get(operations.size()-1);

        result = Math.addExact(result, number);

        operations.add(result);
    }

    @Override
    public int getResult() {
        return (int) operations.get(operations.size()-1);
    }

    @Override
    public void sub(int number) throws ArithmeticException {

        int result = (int) operations.get(operations.size()-1);

        result = Math.subtractExact(result, number);

        operations.add(result);
    }

    @Override
    public void clear() {
        operations.add(0);;
    }

    @Override
    public void undo() {
        if(operations.size() > 1)
        {
            operations.remove(operations.size() - 1);
        }
    }

    @Override
    public void save(OutputStream stream) throws IOException {

        DataOutputStream dos = new DataOutputStream(stream);

        // Ignore First Operation because this is everytime zero (State of cleared result)
        for (int i = 1, operationsSize = operations.size(); i < operationsSize; i++) {
            Object operation = operations.get(i);
            dos.writeInt((int) operation);
        }
    }

    @Override
    public void restore(InputStream stream) throws IOException {

        DataInputStream dis = new DataInputStream(stream);

        while (dis.available() > 0){
            int number = dis.readInt();

            operations.add(number);
        }
    }
}
