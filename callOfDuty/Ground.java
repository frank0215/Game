package callOfDuty;

/** This class is to create the Ground object
 *
 * @author Yung-Jen Yang
 */

public class Ground extends Target{
    private static final int LENGTH = 1;
    private static final int WIDTH = 1;
    private static final String NAME = "ground";

    public Ground(Base base) {
        super(Ground.LENGTH, Ground.WIDTH, base);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public String toString() {
        return "-";
    }

    @Override
    public void explode() {
    }

    @Override
    public String getTargetName() {
        return Ground.NAME;
    }
}
