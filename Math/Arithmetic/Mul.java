package Math.Arithmetic;
import java.math.BigDecimal;
import Math.*;

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

    public Construct optimize() {
        if (this.operands.first.getClass() == Variable.class) {
            if (this.operands.second.equals(this.operands.first)) {
                return new Exp(this.operands.first, BigDecimal.TWO);
            }
            else if (this.operands.second.getClass() == Exp.class) {
                if (this.operands.first.equals(((Exp) this.operands.second).operand)) {
                    return new Exp(this.operands.first, ((Exp) this.operands.second).degree.add(BigDecimal.ONE));
                }
            }
            else if (this.operands.second.optimize().getClass().equals(Constant.class)) {
                return new Mul(this.operands.second, this.operands.first);
            }
        }
        if (this.operands.first.getClass().equals(Constant.class)) {
            if (this.operands.second.optimize().getClass().equals(Constant.class)) {
                return new Constant(this.operands.first.eval().multiply(this.operands.second.eval()));
            }
        }
        return new Div(this.operands.first.optimize(), this.operands.second.optimize());
    }
    
    public Construct antiderive() {
        return new Constant();
    }
}
