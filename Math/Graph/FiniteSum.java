package Math.Graph;

import java.math.BigDecimal;

import static Math.Construct.precision;

@SuppressWarnings("unused")
public class FiniteSum {
    Func f;
    int n;

    @SuppressWarnings("unused")
    public FiniteSum(Func f, int n) {
        this.f = f;
        this.n = n;
    }

    @SuppressWarnings("unused")
    public BigDecimal simpSum(double a, double b) {
        return simpSum(new BigDecimal(a), new BigDecimal(b));
    }

    public BigDecimal simpSum(BigDecimal a, BigDecimal b) {

        if ((n&1)==1) throw new IllegalArgumentException("Non even n");
        BigDecimal sum = BigDecimal.valueOf(0);
        BigDecimal df = b.subtract(a).divide(BigDecimal.valueOf(n), precision);
        
        sum = sum.add(f.of(a));
        for (int i = 0; i < n-1; i++) {
            sum = sum.add(((i&1)==1 ? BigDecimal.valueOf(2) : BigDecimal.valueOf(4))
            .multiply(f.of(a.add(df).add(df.multiply(BigDecimal.valueOf(i))))));
        }
        sum = sum.add(f.of(b));


        return sum.multiply(df, precision).divide(BigDecimal.valueOf(3), precision);
    }

    @SuppressWarnings("unused")
    public BigDecimal rightSum(double a, double b) {
        return rightSum(new BigDecimal(a), new BigDecimal(b));
    }

    public BigDecimal rightSum(BigDecimal a, BigDecimal b) {
        BigDecimal df = b.subtract(a).divide(BigDecimal.valueOf(n), precision);
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < n; i++) {
            sum = sum.add(f.of(df.add(a.add(df.multiply(BigDecimal.valueOf(i)) ) ) ));
        }
        return sum.multiply(df);
    }

    @SuppressWarnings("unused")
    public BigDecimal leftSum(double a, double b) {
        return leftSum(new BigDecimal(a), new BigDecimal(b));
    }  
    public BigDecimal leftSum(BigDecimal a, BigDecimal b) {
        BigDecimal df = b.subtract(a).divide(BigDecimal.valueOf(n), precision);
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < n; i++) {
            sum = sum.add(f.of(a.add(df.multiply(BigDecimal.valueOf(i)) ) ));
        }
        return sum.multiply(df);
    }

    @SuppressWarnings("unused")
    public BigDecimal trapSum(double a, double b) {
        return trapSum(new BigDecimal(a), new BigDecimal(b));
    }  
    public BigDecimal trapSum(BigDecimal a, BigDecimal b) {
        BigDecimal df = b.subtract(a).divide(BigDecimal.valueOf(n), precision);
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i <= n; i++) {
            sum = sum.add( ((i == 0 || i == n) ? BigDecimal.valueOf(1) : BigDecimal.valueOf(2))
            .multiply(f.of(a.add(df.multiply(BigDecimal.valueOf(i))))));
        }

        return sum.multiply(df).divide(BigDecimal.valueOf(2), precision);
    }

    @SuppressWarnings("unused")
    public BigDecimal midSum(double a, double b) {
        return midSum(new BigDecimal(a), new BigDecimal(b));
    }  
    public BigDecimal midSum(BigDecimal a, BigDecimal b) {
        BigDecimal df = b.subtract(a).divide(BigDecimal.valueOf(n), precision);
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < n; i++) {
            sum = sum.add(f.of(a.add(df.divide(BigDecimal.valueOf(2), precision)).add(df.multiply(BigDecimal.valueOf(i)) ) ));
        }
        return sum.multiply(df);
    }  
}