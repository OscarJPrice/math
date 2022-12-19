package Math.Arithmetic;
import java.math.BigDecimal;

import Math.Construct;
import Math.Graph.Func;

public class Variable extends Term {

    //public static Map variables = new Map<Variable, BigDecimal>();
    String name; 
    BigDecimal value;

    @SuppressWarnings("unused")
    public void set(double val) {
        this.value = BigDecimal.valueOf(val);
    }

    public void set(BigDecimal val) {
        this.value = val;
    }

    @SuppressWarnings("unused")
    public Variable(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    public Variable(String name, double value) {
        this.name = name;
        this.value = BigDecimal.valueOf(value);
    }

    public BigDecimal eval() {
        return value;
    }

    public Construct derive() {
        return new Constant(1.0);
    }

    public Construct antiderive() {
        return this;
    }

    public Construct optimize() {
        return this;
    }

    public String toString() {
        return name;
    }

    public boolean has_x() {
        return this.equals(Func.x);
    }

    public BigDecimal get_x_degree(BigDecimal n) {
        if (this.equals(Func.x)) return BigDecimal.ONE.add(n);
        return n;
    }

    public BigDecimal get_x_degree() {
        return (this.equals(Func.x)) ? BigDecimal.ONE : BigDecimal.ZERO;
    }


}
