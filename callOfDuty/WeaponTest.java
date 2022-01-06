package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeaponTest {

    Base base;
    Missile mis;
    RocketLauncher rl;

    @BeforeEach
    void setUp() throws Exception {

        base = new Base();
        

        mis = new Missile();
        rl = new RocketLauncher();
    }

    @Test
    void testWeapon() {
        assertEquals(3, mis.getShotLeft());

        // TODO: add more cases

        assertEquals(20, rl.getShotLeft());
        assertEquals(3, new Missile().getShotLeft());
    }

    @Test
    void testGetWeaponType() {
        assertEquals("missile", mis.getWeaponType().toLowerCase());

        // TODO: add more cases
        assertEquals("rocketlauncher", rl.getWeaponType().toLowerCase());
        assertEquals("missile", new Missile().getWeaponType().toLowerCase());
    }

    
    @Test
    void testGetShotLeft() {
        mis.shootAt(0, 0, this.base);
        assertEquals(2, mis.getShotLeft());

        // TODO: add more cases
        mis.shootAt(1, 1, this.base);
        assertEquals(1, mis.getShotLeft());

        rl.shootAt(2, 2, this.base);
        assertEquals(19, rl.getShotLeft());
    }

    @Test
    void testDecrementShotleft() {
        mis.decrementShotLeft();
        assertEquals(2, mis.getShotLeft());

        // TODO: add more cases

        mis.decrementShotLeft();
        assertEquals(1, mis.getShotLeft());

        mis.decrementShotLeft();
        assertEquals(0, mis.getShotLeft());

        rl.decrementShotLeft();
        assertEquals(19, rl.getShotLeft());
    }

    @Test
    void testShootAt() {
        mis.shootAt(0, 0, this.base);
        assertTrue(base.getTargetsArray()[0][0].isHitAt(0, 0));
        assertEquals(1, base.getShotsCount());
        // TODO: add more cases
        mis.shootAt(5, 5, this.base);
        assertTrue(base.getTargetsArray()[5][5].isHitAt(5, 5));
        assertEquals(2, base.getShotsCount());

        rl.shootAt(5, 5, this.base);
        assertTrue(base.getTargetsArray()[5][5].isHitAt(5, 5));
        assertEquals(3, base.getShotsCount());
    }

}
