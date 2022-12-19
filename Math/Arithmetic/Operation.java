package Math.Arithmetic;
import Math.*;
import Math.Misc.Pair;

import java.math.BigDecimal;

public abstract class Operation implements Construct {
    public Pair<Construct, Construct> operands;

    public Operation(Construct a, Construct b) {
        this.operands = new Pair<>(a, b);
    }

    public BigDecimal get_x_degree(BigDecimal n) {
        return this.operands.first.get_x_degree(n).add(this.operands.second.get_x_degree(n));
    }

    public BigDecimal get_x_degree() {
        BigDecimal n = BigDecimal.valueOf(0);
        return this.operands.first.get_x_degree(n).add(this.operands.second.get_x_degree(n));
    }

    public boolean has_x() {
        return this.operands.first.has_x() || this.operands.second.has_x();
    }
}
