package callOfDuty;

/** This class is to create the HeadQuarter object
 *
 * @author Yung-Jen Yang
 */

public class HeadQuarter extends Target{
    private static final int LENGTH = 6;
    private static final int WIDTH = 1;
    private static final String NAME = "headquarter";

    public HeadQuarter(Base base) {
        super(HeadQuarter.LENGTH, HeadQuarter.WIDTH, base);
    }


    @Override
    public void explode() {
    }

    @Override
    public String getTargetName() {
        return HeadQuarter.NAME;
    }
}
