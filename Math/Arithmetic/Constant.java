package Math.Arithmetic;
import java.math.BigDecimal;

import Math.Construct;
import Math.Graph.*;

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

    public Construct antiderive() {
        return new Mul(new Constant(value), Func.x);
    }

    public boolean has_x() {
        return false;
    }

	public Integer get_x_degree(Integer n) {
		return n;
	}

	public Integer get_x_degree() {
        return Integer.valueOf(0);
	}
}
