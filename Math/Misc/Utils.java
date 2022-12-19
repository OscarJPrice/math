package Math.Misc;

import java.math.BigDecimal;

public class Utils {
    public static String getRepeatingSeq(BigDecimal n) {
        String result = "";
        String decimals = n.toString().substring(n.toString().indexOf('.')+1);
        outer:
        for (int i = decimals.length()-1; i > 0 ; i--) {
            String repeater = decimals.substring(0, i);

            for (int k = 0; k < decimals.length()-1; k++) {
                if (decimals.charAt(k%(i))!=decimals.charAt(k)) {
                    continue outer;
                }
            }
            result = repeater;
        }
        return result;
    }
}
