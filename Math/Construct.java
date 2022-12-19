package Math;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public interface Construct {
    public static final MathContext precision = new MathContext(50, RoundingMode.UP); 
    public BigDecimal eval();
    public Construct derive();
}
