package Math.Arithmetic;
import java.math.BigDecimal;

import Math.Construct;

public class Variable extends Term {

    //public static Map variables = new Map<Variable, BigDecimal>();
    String name; 
    BigDecimal value;

    public void set(double val) {
        this.value = BigDecimal.valueOf(val);
    }

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

    public String toString() {
        return name;
    }
}
