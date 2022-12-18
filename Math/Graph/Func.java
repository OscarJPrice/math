package Math.Graph;

import Math.*;
import Math.Arithmetic.*;

public class Func {
    public static Variable x = new Variable("x", 1.0);

    public Construct eq;

    public Func(Construct equation) {
        this.eq = equation;
    }

    public void of(double x) {
        Func.x.set(x);
        eq.eval();
    }

    public Func derive() {
        return new Func(eq.derive());
    }

    public String toString() {
        return eq.toString();
    }
}
