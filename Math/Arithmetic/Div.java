package Math.Arithmetic;
import java.math.BigDecimal;

import Math.*;
import Math.Graph.Func;


public class Div extends Operation {

    public BigDecimal eval() {
        return this.operands.first.eval().divide(this.operands.second.eval(), precision);
    }

    public Div(Construct n, Construct by) {
        super(n, by);
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

    public Construct antiderive() {
        if(!this.has_x()) {
            return new Div(new Mul(this.operands.first, Func.x), this.operands.second);
        }
        // else if(
        //     this.operands.first.has_x() &&
        //     this.operands.second.has_x() &&
        //     (
        //         this.operands.first.getClass() == Exp.class ||
        //     )
        // ) {

        // }
        return new Constant();
    }

    public Construct optimize() {
        if (this.operands.first.optimize().getClass().equals(Constant.class)) {
            if (this.operands.second.optimize().getClass().equals(Constant.class)) {
                return new Constant(this.eval());
            }
        }
        return new Div(this.operands.first.optimize(), this.operands.second.optimize());
    }

    @SuppressWarnings("unused")
    public void reduce() {
        if (this.operands.first.optimize().getClass().equals(Constant.class)) {
            if (this.operands.second.optimize().getClass().equals(Constant.class)) {
                var a = this.operands.first.optimize().eval().stripTrailingZeros();
                var b = this.operands.second.optimize().eval().stripTrailingZeros();
                if (a.scale() <= 0 && b.scale() <= 0) {
                    var gcd = new BigDecimal(a.toBigIntegerExact().gcd(b.toBigIntegerExact()));
                    this.operands.first = new Constant(a.divide(gcd, precision));
                    this.operands.second = new Constant(b.divide(gcd, precision));
                }
            }
        }

    }
}
