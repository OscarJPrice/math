package Math.Arithmetic;
import java.math.BigDecimal;
import Math.*;

import static java.math.BigDecimal.ZERO;

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
        var fox = this.operands.first.optimize();
        var gox = this.operands.second.optimize();
        if (fox.derive().eval().stripTrailingZeros().equals(ZERO) && gox.derive().eval().stripTrailingZeros().equals(ZERO)) return new Constant();
        return new Add( new Mul(fox.derive(), gox), new Mul(gox.derive(), fox)).optimize();
    }

    public Construct optimize() {
        if (this.operands.first.getClass() == Variable.class) {
            if (this.operands.second.equals(this.operands.first)) {
                return new Exp(this.operands.first, BigDecimal.TWO);
            }
            else if (this.operands.second.optimize().getClass() == Exp.class) {
                if (this.operands.first.equals(((Exp) this.operands.second).operand)) {
                    return new Exp(this.operands.first, ((Exp) this.operands.second).degree.add(BigDecimal.ONE));
                }
            }
            else if (this.operands.second.optimize().getClass().equals(Constant.class)) {
                return new Mul(this.operands.second, this.operands.first);
            }
        }
        if (this.operands.first.optimize().getClass().equals(Mul.class)) {
            var first = (Mul)this.operands.first.optimize();
            if(this.operands.second.optimize().getClass().equals(Constant.class)) {
                var second = (Constant)this.operands.second.optimize();
                if (first.operands.first.optimize().getClass().equals(Constant.class)) {
                    return new Mul( new Constant(first.operands.first.optimize().eval().multiply(second.eval())), first.operands.second.optimize());
                }
                else if (first.operands.second.optimize().getClass().equals(Constant.class)) {
                    return new Mul( new Constant(first.operands.second.optimize().eval().multiply(second.eval())), first.operands.first.optimize());
                }
            }
        }
        if (this.operands.second.optimize().getClass().equals(Mul.class)) {
            var second = (Mul)this.operands.second.optimize();
            if(this.operands.first.optimize().getClass().equals(Constant.class)) {
                var first = (Constant)this.operands.second.optimize();
                if (second.operands.first.optimize().getClass().equals(Constant.class)) {
                    return new Mul( new Constant(second.operands.first.optimize().eval().multiply(first.eval())), second.operands.second.optimize());
                }
                else if (second.operands.second.optimize().getClass().equals(Constant.class)) {
                    return new Mul( new Constant(second.operands.second.optimize().eval().multiply(first.eval())), second.operands.first.optimize());
                }
            }
        }
        if (this.operands.first.getClass().equals(Constant.class)) {
            if (this.operands.second.optimize().getClass().equals(Constant.class)) {
                return new Constant(this.operands.first.eval().multiply(this.operands.second.eval()));
            }
        }
        if (this.operands.first.eval().stripTrailingZeros().equals(ZERO) || this.operands.second.eval().stripTrailingZeros().equals(ZERO)) {
            return new Constant(0);
        }
        return new Mul(this.operands.first.optimize(), this.operands.second.optimize());
    }
    
    public Construct antiderive() {
        return new Constant();
    }
}
