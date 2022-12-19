package Math.Graph;
import java.math.BigDecimal;
import Math.*;
import Math.Arithmetic.*;

@SuppressWarnings("unused")
public class Polynomial implements Construct {

    private Construct eq;

    @SuppressWarnings("unused")
    Polynomial(Construct...constructs) {
        eq = constructs[0];
        for (int i = 1; i < constructs.length; i++) {
            eq = new Add(eq, constructs[i]);
        }
    }

    public BigDecimal eval() {
        return eq.eval();
    }

    public String toString() {
        return eq.toString();
    }

    public Construct derive() {
        return eq.derive();
    }

    public Construct antiderive() {
        return eq.derive();
    }

    public Construct optimize() {
        return this;
    }
    public boolean has_x() {
        return eq.has_x();
    }

    public BigDecimal get_x_degree(BigDecimal n) {
        return eq.get_x_degree(n);
    }

    @Override
    public BigDecimal get_x_degree() {
        var n = BigDecimal.valueOf(0);
        return eq.get_x_degree(n);
    }

}
