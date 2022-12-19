package Math.Graph;

import java.math.BigDecimal;

import Math.*;
import Math.Arithmetic.*;

public class Func {
    public static Variable x = new Variable("x", 1.0);

    public Construct eq;

    public Func(Construct equation) {
        this.eq = equation;
    }

    @SuppressWarnings("unused")
    public BigDecimal of(double x) {
        Func.x.set(BigDecimal.valueOf(x));
        return eq.eval();
    }

    public BigDecimal of(BigDecimal x) {
        Func.x.set(x);
        return eq.eval();
    }

    public Func derive() {
        return new Func(eq.derive().optimize());
    }

    public String toString() {
        return eq.toString();
    }
}
