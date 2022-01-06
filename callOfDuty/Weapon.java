package callOfDuty;

/** This class is to create the Weapon object
 *
 * @author Yung-Jen Yang
 */

public abstract class Weapon {
    /**
     * The number of shots left. Initially, it’s 20 for RocketLauncher and 3 for Missile.
     */
    private int shotLeft;

    /**
     * RocketLauncher will return rocketLauncher,
     * and Missile will return missile.
     * it is case insensitive.
     * @return waepon's name
     */
    public abstract String getWeaponType();


    /**
     *
     * RocketLauncher will only shoot at one square, while Missile will attack a 3x3 area.
     * It will call shootAt(int row, int column) and incrementshotsCount () method in Base class.
     * @param row the base's row
     * @param column the base's column
     * @param base base instance
     */
    public abstract void shootAt(int row, int column, Base base);

    public Weapon(int shotCount) {
        this.shotLeft = shotCount;
    }


    /**
     *
     * decrease the number of ammo.
     */
    public void decrementShotLeft() {
        this.shotLeft--;
    }

    /**
     *
     * @return the number of the left shot.
     */
    public int getShotLeft() {
        return shotLeft;
    }
}
