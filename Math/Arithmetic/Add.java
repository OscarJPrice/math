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
}
