import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test for simple App.
 */
public class StringCalculatorTest extends TestCase {

    private StringCalculator classToTest;

    @Before
    public void setUp() throws Exception {
        classToTest = new StringCalculator();
    }

    public void testAddEmptyStringReturn0() throws Exception {
        final int sum = classToTest.add("");
        assertThat(sum, is(0));
    }
    

    public void testAdd1Returns1() throws Exception {
        assertThat(classToTest.add("1"), is(1));
    }

    public void testAdd2Returns2() throws Exception {
        assertThat(classToTest.add("2"), is(2));
    }


    public void testAdd1Comma2Returns3() throws Exception {
        assertThat(classToTest.add("1,2"), is(3));

    }

    public void testAdd3NewlineReturns7() throws Exception {
        assertThat(classToTest.add("3\n4"), is(7));
    }

    public void testAdd1Comma2Newline3Retunrs6() throws Exception {
        assertThat(classToTest.add("1,2\n3"), is(6));
    }


    public void testAddNegativeNumberThrowsException() throws Exception {
        try {
            classToTest.add("-2");
            fail("didn't throw excepted exception");
        } catch (NegativeNumberException e) {
            e.printStackTrace();
        }

    }
}
