package callOfDuty;

/** This class is to create the RocketLauncher object
 *
 * @author Yung-Jen Yang
 */

public class RocketLauncher extends Weapon{
    private static final int INITIALSHOT = 20;

    public RocketLauncher() {
        super(RocketLauncher.INITIALSHOT);
    }


    @Override
    public String getWeaponType() {
        return "rocketlauncher";
    }

    /*
    Missile will attack a 3x3 area.
    It will call shootAt(int row, int column)
    and incrementshotsCount () method in Base class.
     */
    @Override
    public void shootAt(int row, int column, Base base) {
        super.decrementShotLeft();
        base.shootAt(row, column);
        base.incrementShotsCount();
    }
}
