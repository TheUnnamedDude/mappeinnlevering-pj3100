package nr1;

public class RationalNumber {
	private int numerator;
	private int denominator;

	// constructs 0
	public RationalNumber() {
		this(0, 1);
	}

	public RationalNumber(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException();
		}
		if (numerator < 0 && denominator < 0 || numerator > 0
				&& denominator < 0) {
			this.numerator = -numerator;
			this.denominator = -denominator;
		} else {
			this.numerator = numerator;
			this.denominator = denominator;
		}
	}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	// returns a string representation of the object
	public String toString() {
		if (denominator == 1) {
			return "" + numerator;
		} else {
			return numerator + "/" + denominator;
		}
	}

	// returns true if this object equals the other
	public boolean equals(Object o) {
		if (!(o instanceof RationalNumber))
			return false;
		if (o == this)
			return true;
		RationalNumber other = ((RationalNumber) o).reduce();
		RationalNumber thisOne = this.reduce();
		return thisOne.numerator == other.numerator
				&& thisOne.denominator == other.denominator;
	}

	public RationalNumber add(RationalNumber other) {
		int n = numerator * other.denominator + other.numerator * denominator;
		int d = denominator * other.denominator;
		return new RationalNumber(n, d).reduce();
	}

	public RationalNumber subtract(RationalNumber other) {
		int n = numerator * other.denominator - other.numerator * denominator;
		int d = denominator * other.denominator;
		return new RationalNumber(n, d).reduce();
	}

	public RationalNumber multiply(RationalNumber other) {
		int n = numerator * other.numerator;
		int d = denominator * other.denominator;
		return new RationalNumber(n, d).reduce();
	}

	public RationalNumber divide(RationalNumber other) {
		int n = numerator * other.denominator;
		int d = denominator * other.numerator;
		return new RationalNumber(n, d).reduce();
	}

	// eliminates any common factors and returns the result
	public RationalNumber reduce() {
		int numerator = this.numerator;
		int denominator = this.denominator;
		int signNumerator = 1;
		int signDenominator = 1;

		if (numerator < 0)
			signNumerator = -signNumerator;
		if (denominator < 0)
			signDenominator = -signDenominator;
		numerator = signNumerator * numerator;
		denominator = signDenominator * denominator;

		int gcd = gcd(numerator, denominator);
		numerator /= gcd;
		denominator /= gcd;
		return new RationalNumber(signNumerator * numerator, signDenominator
				* denominator);
	}

	// returns the greatest common divisor of x and y
	public int gcd(int x, int y) {
		while (y != 0) {
			int temp = x % y;
			x = y;
			y = temp;
		}
		return x;
	}
}
