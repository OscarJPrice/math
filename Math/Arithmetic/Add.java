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

    public Construct optimize() {
        if (this.operands.first.getClass().equals(Variable.class)) {
            if (this.operands.first.equals(this.operands.second)) {
                return new Mul(new Constant(2), this.operands.first);
            }
        }
        else if (this.operands.first.getClass().equals(Constant.class)) {
            if (this.operands.second.getClass().equals(Constant.class)) {
                return new Constant(this.operands.first.eval().add(this.operands.first.eval()));
            }
        }
        return new Div(this.operands.first.optimize(), this.operands.second.optimize());
    }

    public boolean has_x() {
        return this.operands.first.has_x() || this.operands.second.has_x();
    }

	public BigDecimal get_x_degree(BigDecimal n) {
		return this.operands.first.get_x_degree(n).add(this.operands.second.get_x_degree(n));
	}

	public BigDecimal get_x_degree() {
        BigDecimal n = BigDecimal.valueOf(0);
		return this.operands.first.get_x_degree(n).add(this.operands.second.get_x_degree(n));
	}
}
