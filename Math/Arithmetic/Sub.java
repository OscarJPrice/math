package Math.Arithmetic;
import java.math.BigDecimal;

import Math.*;

public class Sub extends Operation {

    public BigDecimal eval() {
        return this.operands.first.eval().subtract(this.operands.second.eval());
    }

    public Sub(Construct a, Construct b) {
        super(a, b);
    }

    public String toString() {
        return "(" + this.operands.first + " - " + this.operands.second + ")";
    }

    public Construct derive() {
        return new Sub(this.operands.first.derive(), this.operands.second.derive());
    }

}
