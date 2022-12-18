package Math.Arithmetic;
import Math.*;
import Math.Misc.Pair;

public abstract class Operation implements Construct {
    public Pair<Construct, Construct> operands;

    public Operation(Construct a, Construct b) {
        this.operands = new Pair<Construct, Construct>(a, b);
    }


}
