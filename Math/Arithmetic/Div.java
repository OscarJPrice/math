package Math.Arithmetic;
import java.math.BigDecimal;
import Math.*;


public class Div extends Operation {

    public BigDecimal eval() {
        return this.operands.first.eval().divide(this.operands.second.eval());
    }

    public Div(Construct a, Construct b) {
        super(a, b);
    }

    public String toString() {
        return "(" + this.operands.first + " / " + this.operands.second + ")";
    }

    public Construct derive() {
        var fox = this.operands.first;
        var gox = this.operands.second;
        if (this.operands.first.derive().eval().equals(BigDecimal.valueOf(0))) {
            return new Div(new Mul(gox.derive(), fox), new Mul(gox, gox));
        }
        return new Div(new Sub( new Mul(fox.derive(), gox), new Mul(gox.derive(), fox)), new Mul(gox, gox));
        
    }
}
