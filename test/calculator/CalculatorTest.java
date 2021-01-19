package calculator;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void getResultOnStartEqualsZero() {

        int expected = 0;
        Calculator calculator = new CalculatorImpl();

        assertEquals(expected, calculator.getResult());
    }

    @Test
    public void addOneToZeroEqualOne() {

        int expected = 1;
        Calculator calculator = new CalculatorImpl();

        calculator.add(1);

        assertEquals(expected, calculator.getResult());
    }

    @Test()
    public void addNegativeFiveToZeroEqualNegativeFive() {

        int expected = -5;
        Calculator calculator = new CalculatorImpl();

        calculator.add(-5);

        assertEquals(expected, calculator.getResult());
    }

    @Test(expected = ArithmeticException.class)
    public void addToMaxIntegerResultsInOverflow() {

        Calculator calculator = new CalculatorImpl();

        calculator.add(Integer.MAX_VALUE);
        calculator.add(10);

    }

    @Test(expected = ArithmeticException.class)
    public void addNegativeToMinIntegerResultsInUnderflow() {

        Calculator calculator = new CalculatorImpl();

        calculator.add(Integer.MIN_VALUE);
        calculator.add(-10);
    }

    @Test
    public void subtractOneFromZeroEqualOne() {

        int expected = -1;
        Calculator calculator = new CalculatorImpl();

        calculator.sub(1);

        assertEquals(expected, calculator.getResult());
    }

    @Test
    public void subtractNegativeSixFromZeroEqualSix() {

        int expected = 6;
        Calculator calculator = new CalculatorImpl();

        calculator.sub(-6);

        assertEquals(expected, calculator.getResult());
    }

    @Test(expected = ArithmeticException.class)
    public void subtractFromMinIntegerResultsInUnderflow() {

        Calculator calculator = new CalculatorImpl();

        calculator.sub(Integer.MAX_VALUE);
        calculator.sub(10);

    }

    @Test(expected = ArithmeticException.class)
    public void subtractNegativeFromMaxIntegerResultsInOverflow() {

        Calculator calculator = new CalculatorImpl();

        calculator.sub(Integer.MIN_VALUE);
        calculator.sub(-10);
    }

    @Test
    public void clearResetsResultToZero() {

        int expected = 0;
        Calculator calculator = new CalculatorImpl();

        calculator.add(99);
        calculator.clear();

        assertEquals(expected, calculator.getResult());
    }

    @Test
    public void clearResetsResultToZeroEvenWithNoOperations() {

        int expected = 0;
        Calculator calculator = new CalculatorImpl();

        calculator.clear();

        assertEquals(expected, calculator.getResult());
    }

    @Test
    public void undoLastAddOperationResultsInPreviewsResult() {

        int expected = 99;
        Calculator calculator = new CalculatorImpl();

        calculator.add(99);
        calculator.add(6);
        calculator.undo();

        assertEquals(expected, calculator.getResult());
    }

    @Test
    public void undoLastSubtractOperationResultsInPreviewsResult() {

        int expected = -99;
        Calculator calculator = new CalculatorImpl();

        calculator.sub(99);
        calculator.sub(6);
        calculator.undo();

        assertEquals(expected, calculator.getResult());
    }

    @Test
    public void undoLastClearOperationResultsInPreviewsResult() {

        int expected = 5;
        Calculator calculator = new CalculatorImpl();

        calculator.sub(4);
        calculator.add(9);
        calculator.sub(6);
        calculator.undo();

        assertEquals(expected, calculator.getResult());
    }

    @Test
    public void undoWithoutAnAppliedOperationResultsInNoAction() {

        int expected = 0;
        Calculator calculator = new CalculatorImpl();

        calculator.sub(4);
        calculator.add(9);
        calculator.sub(6);

        calculator.undo();
        calculator.undo();
        calculator.undo();
        calculator.undo();

        assertEquals(expected, calculator.getResult());
    }

    @Test
    public void persistOperations() throws IOException {

        Calculator calculatorOne = new CalculatorImpl();
        FileOutputStream fos = new FileOutputStream("PersistCalculator");

        calculatorOne.add(99);
        calculatorOne.add(6);
        calculatorOne.sub(10);

        calculatorOne.save(fos);

        Calculator calculatorTwo = new CalculatorImpl();
        FileInputStream fis = new FileInputStream("PersistCalculator");

        calculatorTwo.restore(fis);

        File save = new File("PersistCalculator");
        assertTrue(save.exists());
        assertEquals(calculatorOne.getResult(), calculatorTwo.getResult());
    }
}