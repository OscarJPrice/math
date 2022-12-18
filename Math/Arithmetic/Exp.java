package Math.Arithmetic;

import java.math.BigDecimal;

import Math.*;

public class Exp extends Operation {
    public int degree;
    public Construct operand;

    public String toString() {
        if (degree == 1) return operand.toString();
        return "(" + operand + "^" + degree + ")";
    }

    public BigDecimal eval() {
        return operand.eval().pow(degree);
    }

    public Exp(Construct a, int n) {
        super(new Constant(), new Constant());
        this.operand = a;
        this.degree = n;
    }

    public Construct derive() {
        if (operand.getClass() == Variable.class) {
            if (degree == 1) return new Constant(1);
            return new Mul(new Constant(degree), new Exp(operand, degree-1));
        }
        return new Constant();
    }
}
