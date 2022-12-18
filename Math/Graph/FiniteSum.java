package Math.Graph;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class FiniteSum {

    Func f;

    public FiniteSum(Func f) {
        this.f = f;
    }

    public BigDecimal simpSum(int n, double a, double b) {
        return simpSum(n, new BigDecimal(a), new BigDecimal(b));
    }
    public BigDecimal simpSum(int n, BigDecimal a, BigDecimal b) {
        var context = new MathContext(15, RoundingMode.DOWN);

        if ((n&1)==1) throw new IllegalArgumentException("Non even n");
        BigDecimal sum = BigDecimal.valueOf(0);
        BigDecimal df = b.subtract(a).divide(BigDecimal.valueOf(n));
        
        sum = sum.add(f.of(a));
        for (int i = 0; i < n-1; i++) {
            sum = sum.add(((i&1)==1 ? BigDecimal.valueOf(2) : BigDecimal.valueOf(4))
            .multiply(f.of(a.add(df).add(df.multiply(BigDecimal.valueOf(i))))));
        }
        sum = sum.add(f.of(b));


        return sum.multiply(df, context).divide(BigDecimal.valueOf(3), context);
    }

    public BigDecimal rightSum(int n, double a, double b) {
        return rightSum(n, new BigDecimal(a), new BigDecimal(b));
    }  
    public BigDecimal rightSum(int n, BigDecimal a, BigDecimal b) {
        BigDecimal df = b.subtract(a).divide(BigDecimal.valueOf(n));
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < n; i++) {
            sum = sum.add(f.of(df.add(a.add(df.multiply(BigDecimal.valueOf(i)) ) ) ));
        }
        return sum.multiply(df);
    }  

    public BigDecimal leftSum(int n, double a, double b) {
        return leftSum(n, new BigDecimal(a), new BigDecimal(b));
    }  
    public BigDecimal leftSum(int n, BigDecimal a, BigDecimal b) {
        BigDecimal df = b.subtract(a).divide(BigDecimal.valueOf(n));
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < n; i++) {
            sum = sum.add(f.of(a.add(df.multiply(BigDecimal.valueOf(i)) ) ));
        }
        return sum.multiply(df);
    }  

    public BigDecimal trapSum(int n, double a, double b) {
        return trapSum(n, new BigDecimal(a), new BigDecimal(b));
    }  
    public BigDecimal trapSum(int n, BigDecimal a, BigDecimal b) {
        BigDecimal df = b.subtract(a).divide(BigDecimal.valueOf(n));
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i <= n; i++) {
            sum = sum.add( ((i == 0 || i == n) ? BigDecimal.valueOf(1) : BigDecimal.valueOf(2))
            .multiply(f.of(a.add(df.multiply(BigDecimal.valueOf(i))))));
        }

        return sum.multiply(df).divide(BigDecimal.valueOf(2));
    }  

    
    public BigDecimal midSum(int n, double a, double b) {
        return midSum(n, new BigDecimal(a), new BigDecimal(b));
    }  
    public BigDecimal midSum(int n, BigDecimal a, BigDecimal b) {
        BigDecimal df = b.subtract(a).divide(BigDecimal.valueOf(n));
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < n; i++) {
            sum = sum.add(f.of(a.add(df.divide(BigDecimal.valueOf(2))).add(df.multiply(BigDecimal.valueOf(i)) ) ));
        }
        return sum.multiply(df);
    }  
}