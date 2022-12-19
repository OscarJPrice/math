package Math;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public interface Construct {
    MathContext precision = new MathContext(50, RoundingMode.UP);
    BigDecimal eval();
    Construct derive();
    Construct antiderive();
    Construct optimize();
    boolean has_x();
    BigDecimal get_x_degree(BigDecimal n);
    BigDecimal get_x_degree();
}