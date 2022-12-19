package Math.Arithmetic;
import java.math.BigDecimal;

import Math.Construct;

public class Add extends Operation { 

    public BigDecimal eval() {
        return this.operands.first.eval().add(this.operands.second.eval());
    }

    public Add(Construct a, Construct b) {
        super(a, b);
    }

    public String toString() {
        return "(" + this.operands.first + " + " + this.operands.second + ")";
    }

    public Construct derive() {
        return new Add(this.operands.first.derive(), this.operands.second.derive());
    }

    public Construct antiderive() {
        return new Add(this.operands.first.antiderive(), this.operands.second.antiderive());
    }

    public boolean has_x() {
        return this.operands.first.has_x() || this.operands.second.has_x();
    }

	public Integer get_x_degree(Integer n) {
		return this.operands.first.get_x_degree(n) + this.operands.second.get_x_degree(n);
	}

	public Integer get_x_degree() {
        Integer n = Integer.valueOf(0);
		return this.operands.first.get_x_degree(n) + this.operands.second.get_x_degree(n);
	}
}
