package Math.Arithmetic;
import java.math.BigDecimal;
import Math.*;
import Math.Graph.*;

public class Mul extends Operation{

    public BigDecimal eval() {
        return this.operands.first.eval().multiply(this.operands.second.eval());
    }

    public Mul(Construct a, Construct b) {
        super(a, b);
    }

    public String toString() {
        return "(" + this.operands.first + " * " + this.operands.second + ")";
    }

    public Construct derive() {
        var fox = this.operands.first;
        var gox = this.operands.second;
        if (fox.derive().eval().equals(BigDecimal.valueOf(0)) && gox.derive().eval().equals(BigDecimal.valueOf(0))) return new Constant();
        else if (fox.derive().eval().equals(BigDecimal.valueOf(0))) return new Mul(gox.derive(), fox);
        else if (gox.derive().eval().equals(BigDecimal.valueOf(0))) return new Mul(fox.derive(), gox);
        return new Add( new Mul(fox.derive(), gox), new Mul(gox.derive(), fox));
    }   
    
    public Construct antiderive() {
        return new Constant();
    }

    public boolean has_x() {
        return this.operands.first.has_x() || this.operands.second.has_x();
    }
}
