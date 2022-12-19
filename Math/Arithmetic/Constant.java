package Math.Arithmetic;
import java.math.BigDecimal;
import java.math.BigInteger;

import Math.Construct;
import Math.Graph.*;
import Math.Misc.Utils;

import static java.math.BigDecimal.*;

public class Constant extends Term {
    public BigDecimal value;

    public BigDecimal eval() {
        return value;
    }

    public Constant() {
        this.value = valueOf(0);
    }

    public Constant(BigDecimal value) {
        this.value = value;
    }

    public Constant(double value) {
        this.value = valueOf(value);
    }

    public Constant(BigInteger value) {
        this.value = new BigDecimal(value);
    }

    public String toString() {
        return value.toString();
    }

    public Construct derive() {
        return new Constant();
    }

    public Construct antiderive() {
        return new Mul(new Constant(value), Func.x);
    }

    public Construct optimize() {
        return this;
    }

    public Div asRatio() {
        var result = this.value.toBigInteger();
        var remainder = this.value.subtract(new BigDecimal(result));

        if (Utils.getRepeatingSeq(remainder).equals("")) {
            var denominator = BigInteger.valueOf(1);

            while(remainder.stripTrailingZeros().scale() > 0) {
                remainder = remainder.multiply(BigDecimal.TEN);
                denominator = denominator.multiply(BigInteger.TEN);
            }
            var gcd = new BigDecimal(remainder.toBigIntegerExact().gcd(denominator));
            remainder = remainder.divide(gcd, precision);
            denominator = denominator.divide(gcd.toBigIntegerExact());
            return new Div(new Constant(remainder.add(new BigDecimal(denominator.multiply(result))).stripTrailingZeros()), new Constant(denominator));
        }
        else {
            var repeating = BigDecimal.valueOf(Double.parseDouble("0." + Utils.getRepeatingSeq(remainder)));
            var d = TEN.pow(Utils.getRepeatingSeq(remainder).length());
            var numerator = repeating.multiply(d).add(repeating);
            var denominator = d;
            numerator = numerator.subtract(repeating);
            denominator = denominator.subtract(ONE);
            var gcd = new BigDecimal(numerator.toBigIntegerExact().gcd(denominator.toBigIntegerExact()));
            return new Div(new Constant(numerator.divide(gcd, precision).add(new BigDecimal(result)
                    .multiply(denominator.divide(gcd, precision))).stripTrailingZeros()),
                    new Constant(denominator.divide(gcd, precision)));
        }

    }

    public boolean has_x() {
        return false;
    }

	public BigDecimal get_x_degree(BigDecimal n) {
		return n;
	}

	public BigDecimal get_x_degree() {
        return valueOf(0);
	}
}
