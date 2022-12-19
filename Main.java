import Math.Arithmetic.*;
import Math.Graph.*;

class Main {
    public static void main(String[] args) {
        Func f = new Func(
            new Exp(Func.x, -3.2)
        );
        System.out.println(f.of(2));
        // System.out.println(sum.LeftSum(10, 0.0, 2.0));
        // System.out.println(sum.RightSum(10, 0.0, 2.0));

    }
}