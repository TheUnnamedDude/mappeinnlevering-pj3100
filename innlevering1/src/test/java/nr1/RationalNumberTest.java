package nr1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kevin Sillerud
 */
public class RationalNumberTest
{

    @Test
    public void testAllArgsConstructor() throws Exception
    {
        // Testing the normal constructor
        RationalNumber rationalNumber1 = new RationalNumber(5, 10);
        assertEquals("Numerator not 5", 5, rationalNumber1.getNumerator());
        assertEquals("Denominator not 10", 10, rationalNumber1.getDenominator());
    }

    @Test
    public void testNoArgsConstructor() throws Exception
    {
        // Testing the no-arg constructor
        RationalNumber rationalNumber = new RationalNumber();
        assertEquals("Numerator not 0", 0, rationalNumber.getNumerator());
        assertEquals("Denominator not 1", 1, rationalNumber.getDenominator());
    }

    @Test
    public void testGetters() throws Exception
    {
        RationalNumber rationalNumber = new RationalNumber(1, 2);
        assertEquals("Numerator not 1", 1, rationalNumber.getNumerator());
        assertEquals("Denumerator not 2", 2, rationalNumber.getDenominator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroDenumerator() throws Exception
    {
        new RationalNumber(2, 0);
    }

    @Test
    public void testEquals() throws Exception
    {
        RationalNumber rationalNumber = new RationalNumber(6, 12);
        assertEquals(rationalNumber, rationalNumber);
        assertEquals(new RationalNumber(1, 2), rationalNumber);
        assertNotEquals(new RationalNumber(1, 3), rationalNumber);
    }

    @Test
    public void testAdd() throws Exception
    {
        RationalNumber rationalNumber = new RationalNumber(2, 6);
        assertEquals(new RationalNumber(1, 2), rationalNumber.add(new RationalNumber(1, 6)));
        assertEquals(new RationalNumber(5, 6), rationalNumber.add(new RationalNumber(1, 2)));
        assertEquals(new RationalNumber(5, 12), rationalNumber.add(new RationalNumber(1, 12)));

        // Test a negative number
        assertEquals(new RationalNumber(-1, 3), new RationalNumber(0, 6).add(new RationalNumber(-2, 6)));
    }

    @Test
    public void testSubtract() throws Exception
    {
        RationalNumber rationalNumber = new RationalNumber(3, 7);
        assertEquals(rationalNumber.subtract(new RationalNumber(1, 7)), new RationalNumber(2, 7));
        assertEquals(rationalNumber.subtract(new RationalNumber(1, 14)), new RationalNumber(5, 14));
        assertEquals(new RationalNumber(8, 12).subtract(new RationalNumber(1, 6)), new RationalNumber(1, 2));

        // Test negative numbers
        assertEquals(new RationalNumber(1, 3), new RationalNumber(0, 6).subtract(new RationalNumber(-2, 6)));
        assertEquals(new RationalNumber(-1, 3), new RationalNumber(0, 6).subtract(new RationalNumber(2, 6)));
    }

    @Test
    public void testMultiply() throws Exception
    {
        assertEquals(new RationalNumber(1, 1), new RationalNumber(1, 2).multiply(new RationalNumber(2, 1)));
        assertEquals(new RationalNumber(1, 3), new RationalNumber(2, 3).multiply(new RationalNumber(1, 2)));
        assertEquals(new RationalNumber(-2, 3), new RationalNumber(-2, 1).multiply(new RationalNumber(2, 6)));
    }

    @Test
    public void testDivide() throws Exception
    {
        assertEquals(new RationalNumber(1, 1), new RationalNumber(1, 2).divide(new RationalNumber(1, 2)));
        assertEquals(new RationalNumber(2, 3), new RationalNumber(10, 20).divide(new RationalNumber(3, 4)));
        assertEquals(new RationalNumber(2, -1), new RationalNumber(4, 2).divide(new RationalNumber(-2, 2)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() throws Exception
    {
        RationalNumber rationalNumber = new RationalNumber(1, 2);
        rationalNumber.divide(new RationalNumber(0, 1));
    }

    @Test
    public void testReduce() throws Exception
    {
        // If the other tests get passed this one should also pass
        assertEquals(new RationalNumber(1, 2), new RationalNumber(10, 20).reduce());
        assertEquals(new RationalNumber(2, 1), new RationalNumber(14, 7).reduce());
        assertEquals(new RationalNumber(2, 3), new RationalNumber(62, 93).reduce());
        assertEquals(new RationalNumber(-1, 2), new RationalNumber(-4, 8).reduce());
        assertEquals(new RationalNumber(-2, -3), new RationalNumber(-62, -93).reduce());
    }

    @Test
    public void testGcd() throws Exception
    {
        // Same as with the test above
        assertEquals(2, new RationalNumber(4, 2).gcd(2, 4));
        assertEquals(2, new RationalNumber(-4, 2).gcd(4, 2));
        assertEquals(4, new RationalNumber(12, 4).gcd(36, 8));
    }

}