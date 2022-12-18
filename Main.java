import Math.Arithmetic.*;
import Math.Graph.Func;

class Main {
    public static void main(String[] args) {
        var f = new Func(
            new Add(
                new Exp(Func.x, 1),
                new Exp(Func.x, 2)
            )
        );
        f.of(3);
        System.out.println(f);
        var df = f.derive();
        System.out.println(df);

    }
}