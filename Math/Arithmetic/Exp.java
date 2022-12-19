package Math.Arithmetic;

import java.math.BigDecimal;
import Math.*;
import Math.Graph.Func;
 

public class Exp extends Operation {
    public BigDecimal degree;
    public Construct operand;

    public String toString() {
        if (degree.equals(BigDecimal.valueOf(1))) return operand.toString();
        return "(" + this.operand + "^" + this.degree + ")";
    }

    // public BigDecimal eval() {
    //     return this.operand.eval().pow((int)degree.longValue());
    // }

    public BigDecimal eval() {
        BigDecimal result = new BigDecimal(0);
        BigDecimal exponent = this.degree;

        switch (this.degree.signum()) {
            case -1 -> { this.degree.multiply(BigDecimal.valueOf(-1) ); }
            case 0 -> {
                if (this.operand.eval().equals(BigDecimal.valueOf(0))) {
                    throw new ArithmeticException("Cannot exponentiate 0 by 0");
                }
                return BigDecimal.ONE;
            }
        }        
        // x^(a + b) = (x^a) * (x^b), where b is the decimal portion of the power calc
        //start with x^a
        var a = exponent.intValue();
        result = operand.eval().pow(a);

        if (exponent.stripTrailingZeros().scale() > 0) { //if there is a remainder
            //continue with b = a + b - a
            var b = exponent.subtract(BigDecimal.valueOf(a)).doubleValue();
            //(x^a) * (x^b)
            result = result.multiply(new BigDecimal(Math.pow(operand.eval().doubleValue(), b)));
        }

        return (this.degree.signum() == -1) ? BigDecimal.ONE.divide(result, precision) : result;
    }

    public Exp(Construct a, double n) {
        super(new Constant(), new Constant());
        this.operand = a;
        this.degree = BigDecimal.valueOf(n);
    }

    public Exp(Construct a, BigDecimal n) {
        super(new Constant(), new Constant());
        this.operand = a;
        this.degree = n;
    }

    public Construct derive() {
        if (operand.getClass() == Variable.class) {
            if (degree.equals(BigDecimal.valueOf(1))) return new Constant(1);
            return new Mul(new Constant(degree), new Exp(operand, degree.subtract(BigDecimal.ONE)));
        }
        return new Constant();
    }

    public Construct antiderive() {
        if(operand.equals(Func.x))
            return new Div(new Exp(operand, degree.add(BigDecimal.ONE)), new Constant(this.degree.add(BigDecimal.ONE)));
        return new Div(new Mul(new Exp(operand, degree.add(BigDecimal.ONE)), Func.x), new Constant(this.degree.add(BigDecimal.ONE)));
    }

    public boolean has_x() {
        return operand.has_x();
    }


	public Integer get_x_degree(Integer n) {
		if (this.operand.getClass() == Func.x) {
            return n + degree.;
        }
	}

	public Integer get_x_degree() {
        Integer n = Integer.valueOf(0);
		return this.operands.first.get_x_degree(n) + this.operands.second.get_x_degree(n);
	}
}
