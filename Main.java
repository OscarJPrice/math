import Math.Arithmetic.*;

class Main {
    public static void main(String[] args) {
        var quo = new Div(new Constant(5), new Mul(new Constant(2), new Constant(5)));

        System.out.println(((Constant)quo.optimize()).asRatio());
    }
}