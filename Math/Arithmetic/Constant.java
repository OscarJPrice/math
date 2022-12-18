package Math.Arithmetic;
import java.math.BigDecimal;

import Math.Construct;

public class Constant extends Term {
    public BigDecimal value;

    public BigDecimal eval() {
        return value;
    }

    public Constant() {
        this.value = BigDecimal.valueOf(0);
    }

    public Constant(BigDecimal value) {
        this.value = value;
    }

    public Constant(double value) {
        this.value = BigDecimal.valueOf(value);
    }

    public String toString() {
        return value.toString();
    }

    public Construct derive() {
        return new Constant();
    }
}
