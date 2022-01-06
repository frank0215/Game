package callOfDuty;

import java.util.Random;

/** This class is to create the Base object which is full of the Targets(including the ground)
 *
 * @author Yung-Jen Yang
 */


public class Base {
    static final int LENGTH = 10;
    static final int WIDTH = 10;
    private static final int NUMOFHEADQUARTER = 1;
    private static final int NUMOFARMORY = 2;
    private static final int NUMOFBARRACK = 3;
    private static final int NUMOFSENTRYTOWER = 4;
    private static final int NUMOFTANK = 4;
    private static final int NUMOFOILDRUM = 4;
    /**
     * Keeps a reference to the location of every Target in the game.
     * Every location in this array points to a Target,
     * specifically, an instance of a subclass of Target.
     */
    private Target[][] targets;

    /**
     * The total number of shots fired by the user.
     */
    private int shotsCount;

    /**
     * The number of targets destroyed
     */
    private int destroyedTargetCount;

    /**
     * Creates an 10x10 "empty" Base (and fills the Targets array with Ground objects).
     * initializes any game variables, such as how many shots have been fired.
     */
    public Base() {
        this.shotsCount = 0;
        this.destroyedTargetCount = 0;
        this.targets = new Target[Base.LENGTH][Base.WIDTH];
        for (int i = 0; i < Base.LENGTH; i++) {
            for (int j = 0; j < Base.WIDTH; j++) {
                placeTargetAt(new Ground(this), i, j, true);
            }
        }
    }

    /**
     * Create and place all Targets randomly on the Base (initially filled with Ground).
     * Place larger Target before smaller ones, and place buildings before tanks and oil drums,
     * or you may end up with no legal place to put a Target.
     */
    public void placeAllTargetRandomly() {
        Random random = new Random();


        int[] nums = {Base.NUMOFHEADQUARTER, Base.NUMOFARMORY, Base.NUMOFBARRACK, Base.NUMOFSENTRYTOWER, Base.NUMOFOILDRUM, Base.NUMOFTANK};
//        Target[][] targetSequence = {new HeadQuarter(this)[Base.NUMOFHEADQUARTER], new Armory(this)[Base.NUMOFHEADQUARTER],
//                new Barrack(this)[Base.NUMOFBARRACK], new SentryTower(this)[Base.NUMOFSENTRYTOWER], new OilDrum(this)[Base.NUMOFOILDRUM],
//                new Tank(this)[Base.NUMOFTANK]};
        Target tar;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i]; j++) {
                if (i == 0) {
                    tar = new HeadQuarter(this);
                } else if (i == 1) {
                    tar = new Armory(this);
                } else if (i == 2) {
                    tar = new Barrack(this);
                } else if (i == 3) {
                    tar = new SentryTower(this);
                } else if (i == 4) {
                    tar = new OilDrum(this);
                } else {
                    tar = new Tank(this);
                }
                while (true) {
                    int posRow = random.nextInt(Base.WIDTH);
                    int posCol = random.nextInt(Base.LENGTH);
                    int dir = random.nextInt(2);
                    boolean h;
                    if (dir == 1) {
                        h = true;
                    } else {
                        h = false;
                    }
                    if (okToPlaceTargetAt(tar, posRow, posCol, h) == true) {
                        placeTargetAt(tar, posRow, posCol, h);
//                        System.out.println(tar.getTargetName() + "places at " + posRow + ", " + posCol);
                        break;
                    }
                }
            }
        }



//        for (int i = 0; i < Base.NUMOFHEADQUARTER; i++) {
//            while (true) {
//                int posRow = random.nextInt(Base.WIDTH);
//                int posCol = random.nextInt(Base.LENGTH);
//                int dir = random.nextInt(2);
//                boolean h;
//                if (dir == 1) {
//                    h = true;
//                } else {
//                    h = false;
//                }
//                if (okToPlaceTargetAt(new HeadQuarter(this), posRow, posCol, h) == true) {
////                    System.out.println("headquarter places at " + posRow + ", " + posCol);
//                    placeTargetAt(new HeadQuarter(this), posRow, posCol, h);
//                    break;
//                }
//            }
//        }
//
//        for (int i = 0; i < Base.NUMOFARMORY; i++) {
//            while (true) {
//                int posRow = random.nextInt(Base.WIDTH);
//                int posCol = random.nextInt(Base.LENGTH);
//                int dir = random.nextInt(2);
//                boolean h;
//                if (dir == 1) {
//                    h = true;
//                } else {
//                    h = false;
//                }
//                if (okToPlaceTargetAt(new Armory(this), posRow, posCol, h) == true) {
////                    System.out.println("armory places at " + posRow + ", " + posCol);
//                    placeTargetAt(new Armory(this), posRow, posCol, h);
//                    break;
//                }
//            }
//        }
//
//        for (int i = 0; i < Base.NUMOFBARRACK; i++) {
//            while (true) {
//                int posRow = random.nextInt(Base.WIDTH);
//                int posCol = random.nextInt(Base.LENGTH);
//                int dir = random.nextInt(2);
//                boolean h;
//                if (dir == 1) {
//                    h = true;
//                } else {
//                    h = false;
//                }
//                if (okToPlaceTargetAt(new Barrack(this), posRow, posCol, h) == true) {
////                    System.out.println("barrack places at " + posRow + ", " + posCol);
//                    placeTargetAt(new Barrack(this), posRow, posCol, h);
//                    break;
//                }
//            }
//        }
//
//        for (int i = 0; i < Base.NUMOFSENTRYTOWER; i++) {
//            while (true) {
//                int posRow = random.nextInt(Base.WIDTH);
//                int posCol = random.nextInt(Base.LENGTH);
//                int dir = random.nextInt(2);
//                boolean h;
//                if (dir == 1) {
//                    h = true;
//                } else {
//                    h = false;
//                }
//                if (okToPlaceTargetAt(new SentryTower(this), posRow, posCol, h) == true) {
////                    System.out.println("tower places at " + posRow + ", " + posCol);
//                    placeTargetAt(new SentryTower(this), posRow, posCol, h);
//                    break;
//                }
//            }
//        }
//
//        for (int i = 0; i < Base.NUMOFTANK; i++) {
//            while (true) {
//                int posRow = random.nextInt(Base.WIDTH);
//                int posCol = random.nextInt(Base.LENGTH);
//                int dir = random.nextInt(2);
//                boolean h;
//                if (dir == 1) {
//                    h = true;
//                } else {
//                    h = false;
//                }
//                if (okToPlaceTargetAt(new Tank(this), posRow, posCol, h) == true) {
////                    System.out.println("tank places at " + posRow + ", " + posCol);
//                    placeTargetAt(new Tank(this), posRow, posCol, h);
//                    break;
//                }
//            }
//        }
//
//        for (int i = 0; i < Base.NUMOFOILDRUM; i++) {
//            while (true) {
//                int posRow = random.nextInt(Base.WIDTH);
//                int posCol = random.nextInt(Base.LENGTH);
//                int dir = random.nextInt(2);
//                boolean h;
//                if (dir == 1) {
//                    h = true;
//                } else {
//                    h = false;
//                }
//                if (okToPlaceTargetAt(new OilDrum(this), posRow, posCol, h) == true) {
////                    System.out.println("oildrum places at " + posRow + ", " + posCol);
//                    placeTargetAt(new OilDrum(this), posRow, posCol, h);
//                    break;
//                }
//            }
//        }

    }

    /**
     * The buildings must not overlap another Target,
     * or touch another building (vertically, horizontally, or diagonally).
     * And targets must not "stick out" beyond the base.
     * Does not actually change either the Target or the Base – it just says if it is legal to do so.
     * @param target the target
     * @param row the base's row
     * @param column the base's column
     * @param horizontal the target's horizontal status
     * @return true if it is okay to put the Target with its head at this location; false otherwise. \
     *         Based on the given row, column, and orientation,
     */
    public boolean okToPlaceTargetAt(Target target, int row, int column, boolean horizontal) {
        int length = target.getLength();
        int width = target.getWidth();


        int endRow, endCol;
        if (horizontal == true) {
            endRow = row + width - 1;
            endCol = column + length - 1;
        } else {
            endRow = row + length - 1;
            endCol = column + width -1;
        }


        if (endRow >= Base.LENGTH || endCol >= Base.WIDTH)  {
            return false;
        }

        int top = row - 1;
        if (top < 0) {
            top = 0;
        }
        int bottom = endRow + 1;
        if (bottom >= Base.LENGTH) {
            bottom = Base.LENGTH - 1;
        }
        int left = column - 1;
        if (left < 0) {
            left = 0;
        }
        int right = endCol + 1;
        if (right >= Base.WIDTH) {
            right = Base.WIDTH - 1;
        }

        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                if (i >= row && i <= endRow && j >= column && j <= endCol) {
                    if (isOccupied(i, j) == true) {
                        return false;
                    }
                } else {
                    if (target.getTargetName().equals("tank") || target.getTargetName().equals("oildrum")) {
                        continue;
                    }
                    if (this.targets[i][j].getTargetName().equals("tank") || this.targets[i][j].getTargetName().equals("oildrum")) {
                        continue;
                    }
                    if (isOccupied(i, j) == true) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Sets the value of the "hit" array, "coordinate" array, and "horizontal" boolean value of the target.
     * @param target the target
     * @param row the base's row
     * @param column the base's column
     * @param horizontal the target's horizontal status
     */
    public void placeTargetAt(Target target, int row, int column, boolean horizontal) {
        target.setCoordinate(new int[]{row, column});
        target.setHorizontal(horizontal);

//        int endRow, endCol;
//        if (horizontal == true) {
//            endRow = row + target.getWidth() - 1;
//            endCol = column + target.getLength() - 1;
//        } else {
//            endRow = row + target.getLength() - 1;
//            endCol = column + target.getWidth() - 1;
//        }


        int endRow = target.getTargetRange()[2];
        int endCol = target.getTargetRange()[3];

        int[][] hit = new int[endRow-row+1][endCol-column+1];
        target.setHit(hit);

        for (int i = row; i <= endRow ; i++) {
            for (int j = column; j <= endCol; j++) {
                this.targets[i][j] = target;
            }
        }
    }

    /**
     * @param row the base's row
     * @param column the base's column
     * @return true if the given location contains a Target(not a Ground), false if it does not.
     */
    public boolean isOccupied(int row, int column) {
        if (!this.targets[row][column].getTargetName().equals("ground")) {
            return true;
        }
        return false;
    }

    /**
     * Attack the position specified by the row and the column.
     * @param row the base's row
     * @param column the base's column
     */
    public void shootAt(int row, int column) {
        Target tar = this.targets[row][column];
        tar.getShot(row, column);
    }

    /**
     * @param weapon1 the first weapon you choose (i.e. rocket)
     * @param weapon2 the second weapon you choose (i.e. missile)
     * @return true if run out of ammunition or if all targets have been destroyed. Otherwise, false.
     */
    public boolean isGameOver(Weapon weapon1, Weapon weapon2) {
        if (weapon1.getShotLeft() == 0 && weapon2.getShotLeft() == 0) {
            return true;
        }
        return false;
    }

    /**
     * @return true if all targets have been destroyed.
     */
    public Boolean win() {
        for (int i = 0; i < Base.LENGTH; i++) {
            for (int j = 0; j < Base.WIDTH; j++) {
                if (!this.targets[i][j].getTargetName().equals("ground")) {
                    if (this.targets[i][j].isDestroyed() == false) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * if you want to cheat use this function.
     */
    public void printAnswer() {
        for (int i = 0; i < Base.LENGTH+1; i++) {
            for (int j = 0; j < Base.WIDTH+1; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                    continue;
                }

                if (i == 0) {
                    System.out.print(j-1 + " ");
                    continue;
                }
                if (j == 0) {
                    System.out.print(i-1 + " ");
                    continue;
                }

                String symbol = this.targets[i-1][j-1].toString();
                System.out.print(symbol + " ");
            }
            System.out.println();

        }
    }

    /**
     * Prints the Base.
     * To aid the user, row numbers should be displayed along the left edge of the array,
     * and column numbers should be displayed along the top.
     * '.': Use '.' (a period) to indicate a location that you have never fired upon
     * "O" (capital letter O): Used to indicate a location that you have fired upon and hit a target
     * "-": Use '-' to indicate a location that you have fired upon and found nothing there
     * "X" (capital letter X): Use 'X' to indicate a location containing a destroyed Target.
     * "T": Used to indicate an undestroyed but hit Tank
     */
    public void print() {
        for (int i = 0; i < Base.LENGTH+1; i++) {
            for (int j = 0; j < Base.WIDTH+1; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                    continue;
                }

                if (i == 0) {
                    System.out.print(j-1 + " ");
                    continue;
                }
                if (j == 0) {
                    System.out.print(i-1 + " ");
                    continue;
                }

                if (this.targets[i-1][j-1].isHitAt(i-1, j-1) == true) {
                    String symbol = this.targets[i-1][j-1].toString();
                    System.out.print(symbol + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    /**
     * @return the number of shots fired
     */
    public int getShotsCount() {
        return this.shotsCount;
    }

    /**
     *
     * @return the targets array
     */
    public Target[][] getTargetsArray() {
        return this.targets;
    }

    /**
     * This method will be called from shootAt(int row, int column) from Weapon class.
     */
    public void incrementShotsCount() {
        this.shotsCount++;
    }

    /**
     *
     * @return the count of destroyed targets
     */
    public int getDestroyedTargetCount() {
        return this.destroyedTargetCount;
    }

    /**
     *  set the Destroyed Target count
     * @param destroyedTargetCount the number of destroyed target
     */
    public void setDestroyedTargetCount(int destroyedTargetCount) {
        if (destroyedTargetCount > 18) {
            this.destroyedTargetCount = 18;
        } else if (destroyedTargetCount < 0) {
            this.destroyedTargetCount = 0;
        } else {
            this.destroyedTargetCount = destroyedTargetCount;
        }
    }
}
