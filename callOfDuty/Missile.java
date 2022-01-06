package callOfDuty;

/** This class is to create the Missile object
 *
 * @author Yung-Jen Yang
 */

public class Missile extends Weapon{
    private static final int INITIALSHOT = 3;

    public Missile() {
        super(Missile.INITIALSHOT);
    }


    @Override
    public String getWeaponType() {
        return "missile";
    }

    /*
    Missile will attack a 3x3 area.
    It will call shootAt(int row, int column)
    and incrementshotsCount () method in Base class.
     */
    @Override
    public void shootAt(int row, int column, Base base) {
        super.decrementShotLeft();

        int startRow = row-1;
        if (startRow < 0) {
            startRow = 0;
        }
        int endRow = row+1;
        if (endRow >= base.LENGTH) {
            endRow = base.LENGTH - 1;
        }
        int startCol = column-1;
        if (startCol < 0) {
            startCol = 0;
        }
        int endCol = column+1;
        if (endCol >= base.WIDTH) {
            endCol = base.WIDTH - 1;
        }

        base.incrementShotsCount();
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                base.shootAt(i, j);
            }
        }
    }
}
