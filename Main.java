import Math.Arithmetic.*;
import Math.Graph.FiniteSum;
import Math.Graph.Func;

class Main {
    public static void main(String[] args) {
        var f = new Func(
            new Exp(Func.x, 2)
        );
        var sum = new FiniteSum(f);
        // System.out.println(sum.LeftSum(10, 0.0, 2.0));
        // System.out.println(sum.RightSum(10, 0.0, 2.0));
        System.out.println(sum.midSum(4, 0, 2));

    }
}