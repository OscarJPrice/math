package Math.Arithmetic;
import java.math.BigDecimal;
import Math.*;
import Math.Graph.Func;


public class Div extends Operation {

    public BigDecimal eval() {
        return this.operands.first.eval().divide(this.operands.second.eval());
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

    public boolean has_x() {
        return this.operands.first.has_x() || this.operands.second.has_x();
    }

	public Integer get_x_degree(Integer n) {
		return this.operands.first.get_x_degree(n) + this.operands.second.get_x_degree(n);
	}

	public Integer get_x_degree() {
        Integer n = Integer.valueOf(0);
		return this.operands.first.get_x_degree(n) + this.operands.second.get_x_degree(n);
	}
}
