package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseTest {

    Base base;
    Armory armory;
    Barrack barrack;
    SentryTower st;
    Tank tank;
    OilDrum od;

    @BeforeEach
    void setUp() throws Exception {

        base = new Base();

        armory = new Armory(base);
        base.placeTargetAt(armory, 0, 0, true);

        barrack = new Barrack(base);
        base.placeTargetAt(barrack, 0, 4, true);

        st = new SentryTower(base);
        base.placeTargetAt(st, 2, 4, true);

        tank = new Tank(base);
        base.placeTargetAt(tank, 1, 3, true);

        od = new OilDrum(base);
        base.placeTargetAt(od, 2, 1, true);
    }

    @Test
    void testBase() {
        assertEquals(10, base.getTargetsArray().length);

        // TODO: add more cases
        assertEquals(10, base.getTargetsArray()[0].length);
        assertEquals(10, base.getTargetsArray()[1].length);
    }

    @Test
    void testPlaceAllTargetRandomly() {
        this.base = new Base();
        base.placeAllTargetRandomly();
        List<Target> list = new ArrayList<Target>();
        int headQuarterCount = 0;
        int armoryCount = 0;
        int barracksCount = 0;
        int sentryCount = 0;
        int tanksCount = 0;
        int odCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!base.getTargetsArray()[i][j].getTargetName().equals("ground")) {
                    if (!list.contains(base.getTargetsArray()[i][j])) {
                        list.add(base.getTargetsArray()[i][j]);
                        switch (base.getTargetsArray()[i][j].getTargetName().toLowerCase()) {
                        case "armory": {
                            armoryCount++;
                            break;
                        }
                        case "headquarter": {
                            headQuarterCount++;
                            break;
                        }
                        case "barrack": {
                            barracksCount++;
                            break;
                        }
                        case "sentrytower": {
                            sentryCount++;
                            break;
                        }
                        case "tank": {
                            tanksCount++;
                            break;
                        }
                        case "oildrum": {
                            odCount++;
                            break;
                        }
                        }
                    }
                }
            }
        }
        assertEquals(list.size(), 18);

        assertEquals(1, headQuarterCount);
        assertEquals(2, armoryCount);
        assertEquals(3, barracksCount);
        assertEquals(4, sentryCount);
        assertEquals(4, tanksCount);
        assertEquals(4, odCount);
    }

    @Test
    void testOkToPlaceTargetAt() {
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 7, false));
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 8, true));
        assertTrue(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 8, false));

        // TODO: add more cases

        assertTrue(this.base.okToPlaceTargetAt(new Tank(this.base), 8, 8, false));
        assertTrue(this.base.okToPlaceTargetAt(new Tank(this.base), 8, 9, true));
        assertFalse(this.base.okToPlaceTargetAt(new Tank(this.base), 0, 0, false));

        assertFalse(this.base.okToPlaceTargetAt(new OilDrum(this.base), 0, 1, false));
        assertFalse(this.base.okToPlaceTargetAt(new OilDrum(this.base), 1, 2, true));
        assertTrue(this.base.okToPlaceTargetAt(new OilDrum(this.base), 9, 1, false));
    }
    
    

    @Test
    void testPlaceTargetAt() {
        Target armory = new Armory(base);
        this.base.placeTargetAt(armory, 5, 5, false);
        assertEquals(5, armory.getCoordinate()[0]);
        assertEquals(5, armory.getCoordinate()[1]);
        assertEquals(3, armory.getHit().length);
        assertEquals(2, armory.getHit()[0].length);
        
     // TODO: add more cases
        Target tk = new Tank(base);
        this.base.placeTargetAt(tk, 9,8,  true);
        assertEquals(9, tk.getCoordinate()[0]);
        assertEquals(8, tk.getCoordinate()[1]);
        assertEquals(1, tk.getHit().length);
        assertEquals(1, tk.getHit()[0].length);

        Target oil = new OilDrum(base);
        this.base.placeTargetAt(oil, 7, 3, false);
        assertEquals(7, oil.getCoordinate()[0]);
        assertEquals(3, oil.getCoordinate()[1]);
        assertEquals(1, oil.getHit().length);
        assertEquals(1, oil.getHit()[0].length);
    }
    
    
    @Test
    void testIsOccupied() {

        Armory arm = new Armory(base);
        this.base.placeTargetAt(arm, 0, 0, true);
        assertTrue(base.isOccupied(0, 0));

        // TODO: add more cases
        Target am = new Tank(base);
        this.base.placeTargetAt(am, 9, 8, true);
        assertTrue(base.isOccupied(9, 8));

        am = new OilDrum(base);
        this.base.placeTargetAt(am, 7, 7, true);
        assertTrue(base.isOccupied(7, 7));
    }

    @Test
    void testShootAt() {

        Armory arm = new Armory(base);
        this.base.placeTargetAt(arm, 5, 5, true);

        base.shootAt(5, 5);
        assertTrue(arm.isHitAt(5, 5));

        // TODO: add more cases

        Target am = new Tank(base);
        this.base.placeTargetAt(am, 8, 8, true);

        base.shootAt(8, 8);
        assertTrue(am.isHitAt(8, 8));

        am = new OilDrum(base);
        this.base.placeTargetAt(am, 0, 9, true);

        base.shootAt(0, 9);
        assertTrue(am.isHitAt(0, 9));
    }

    @Test
    void testIsGameOver() {

        assertFalse(base.isGameOver(new RocketLauncher(), new Missile()));

        // TODO: add more cases
        Weapon rl = new RocketLauncher();
        for (int i = 0; i < 20; i++) {
            rl.decrementShotLeft();
        }
        Weapon ml = new Missile();
        ml.decrementShotLeft();
        assertFalse(base.isGameOver(rl, ml));
        for (int i = 0; i < 2; i++) {
            ml.decrementShotLeft();
        }
        assertTrue(base.isGameOver(rl, ml));
    }

    @Test
    void testWin() {
        assertFalse(this.base.win());

        // TODO: add more cases
        armory.explode();
        assertFalse(this.base.win());

        barrack.getShot(0, 6);
        assertTrue(this.base.win());
    }

    @Test
    void testIncrementAndSetShotsCount() {

        assertEquals(0, base.getShotsCount());
        base.incrementShotsCount();
        assertEquals(1, base.getShotsCount());

        // TODO: add more cases
        new Missile().shootAt(0, 0, base);
        assertEquals(2, base.getShotsCount());

        new RocketLauncher().shootAt(8,1, base);
        assertEquals(3, base.getShotsCount());
    }

    @Test
    void testSetAndGetDestroyedTargetCount() {
        base.setDestroyedTargetCount(10);
        assertEquals(10, base.getDestroyedTargetCount());

        // TODO: add more cases
        base.setDestroyedTargetCount(15);
        assertEquals(15, base.getDestroyedTargetCount());

        base.setDestroyedTargetCount(17);
        assertEquals(17, base.getDestroyedTargetCount());
    }

    @Test
    void testGetDestroyedTargetCount() {
        
        assertEquals(0, base.getDestroyedTargetCount());
                
        // TODO: add more cases
        base.setDestroyedTargetCount(18);
        assertEquals(18, base.getDestroyedTargetCount());
        base.setDestroyedTargetCount(19);
        assertEquals(18, base.getDestroyedTargetCount());
    }


    @Test
    void testGetTargetsArray() {
        assertEquals(10, base.getTargetsArray().length);

        // TODO: add more cases
        assertEquals(10, base.getTargetsArray()[0].length);
        assertEquals(10, base.getTargetsArray()[1].length);
    }


}
