package callOfDuty;

/** This class is to create the Barrack object
 *
 * @author Yung-Jen Yang
 */

public class Barrack extends Target{
    private static final int LENGTH = 3;
    private static final int WIDTH = 1;
    private static final String NAME = "barrack";

    public Barrack(Base base) {
        super(Barrack.LENGTH, Barrack.WIDTH, base);
    }


    @Override
    public void explode() {
    }

    @Override
    public String getTargetName() {
        return Barrack.NAME;
    }
}
