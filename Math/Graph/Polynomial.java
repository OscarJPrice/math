package Math.Graph;
import java.math.BigDecimal;
import Math.*;
import Math.Arithmetic.*;

public class Polynomial implements Construct {

    private Construct eq;
    
    Polynomial(Construct...constructs) {
        eq = constructs[1]; 
        for (int i = 0; i < constructs.length; i++) {
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

}
