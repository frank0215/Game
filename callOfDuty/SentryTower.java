package callOfDuty;

/** This class is to create the SentryTower object
 *
 * @author Yung-Jen Yang
 */

public class SentryTower extends Target{
    private static final int LENGTH = 1;
    private static final int WIDTH = 1;
    private static final String NAME = "sentrytower";

    public SentryTower(Base base) {
        super(SentryTower.LENGTH, SentryTower.WIDTH, base);
    }

    @Override
    public void explode() {
    }

    @Override
    public String getTargetName() {
        return SentryTower.NAME;
    }
}
