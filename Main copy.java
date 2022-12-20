import Math.Arithmetic.*;
import Math.Graph.Func;

class Main {
    public static void main(String[] args) {
        Func f = new Func(new Mul(new Constant(2), new Exp(Func.x, 2)) );
        System.out.println(f.derive());
    }
}