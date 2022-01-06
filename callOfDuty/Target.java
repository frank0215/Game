package callOfDuty;

/** This class is to create the Target object
 *
 * @author Yung-Jen Yang
 */

public abstract class Target {

    /**
     * specifies the coordinate of the head of a target.
     * Head means the upper left part of a Target.
     */
    private  int[] coordinate;

    /**
     * The length of the Target
     */
    private  int length;


    /**
     * The width of the Target, width is always less than length
     */
    private  int width;

    /**
     * Indicates whether the Target is horizontal or not
     */
    private  boolean horizontal;

    /**
     * An array of the same size as the target
     * indicating the number of times a part of the target has been hit.
     */
    private  int[][] hit;

    /**
     * An object of Base that the target is placed in
     */
    private Base base;

    /**
     * check the result has been printed yet.
     */
    private  boolean isPrinted;

    /**
     * Defines the behavior when a target is destroyed.
     * Tank, oil drum, armory can explode, while others can not.
     */
    public abstract void explode();

    /**
     * Every specific type of Target (e.g. Armory, Tank, etc.) has to override and implement this method
     * and return the corresponding Target type.
     * This method is not case sensitive, i.e., Armory, ARMORY and armory are all acceptable.
     * @return the type of Target as a String.
     */
    public abstract String getTargetName();

    /**
     * Target constructor sets the length, the width and the base of the Target.
     * @param length target's length
     * @param width targets' width
     * @param base base instance
     */
    public Target(int length, int width, Base base) {
        this.length = length;
        this.width = width;
        this.base = base;
        this.isPrinted = false;
    }

    /**
     * the range of target: headRow represents Top, endRow represents Bottom, headCol represents Left,
     * endCol represents Right
     * @return the range of the Target
     */
    public int[] getTargetRange() {
        int[] range = new int[4];
        int headRow = this.coordinate[0];
        int headCol = this.coordinate[1];
        int endRow, endCol;
        if (this.horizontal == true) {
            endRow = headRow + this.width -1;
            endCol = headCol + this.length - 1;
        } else {
            endRow = headRow + this.length -1;
            endCol = headCol + this.width -1;
        }

        range[0] = headRow;
        range[1] = headCol;
        range[2] = endRow;
        range[3] = endCol;

        return range;
    }

    /**
     * If a part of the Target occupies the given row and column,
     * no matter the target is destroyed or not, mark that part of the Target as hit
     * (in the hit array, index (0,0) indicates the head) and return true; otherwise return false.
     * @param row the base's row
     * @param column the base's column
     * @return a boolean value to check if the Target get show
     */
    public boolean getShot(int row, int column) {
        int headRow = getTargetRange()[0];
        int headCol = getTargetRange()[1];
        int endRow = getTargetRange()[2];
        int endCol = getTargetRange()[3];


        if (row >= headRow && row <= endRow && column >= headCol && column <= endCol) {
            this.hit[row-headRow][column-headCol]++;
            if (base.getTargetsArray()[row][column].isDestroyed() == true) {
                base.getTargetsArray()[row][column].explode();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * For Tank, every part of it should be hit twice. So override this method in the Tank Class.
     * @return true if every part of the Target has been hit, false otherwise.
     */
    public boolean isDestroyed() {
        int headRow = getTargetRange()[0];
        int headCol = getTargetRange()[1];
        int endRow = getTargetRange()[2];
        int endCol = getTargetRange()[3];

        for (int i = 0; i <= endRow-headRow; i++) {
            for (int j = 0; j <= endCol-headCol; j++) {
                if (hit[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method is used to print the Base.
     * @param row the base's row
     * @param column the base's column
     * @return true if the target has been hit at the given coordinate.
     */
    public boolean isHitAt(int row, int column) {
        int headRow = getCoordinate()[0];
        int headCol = getCoordinate()[1];
        int endRow = getTargetRange()[2];
        int endCol = getTargetRange()[3];
        if (row >= headRow && row <= endRow && column >= headCol && column <= endCol) {
            if (hit[row-headRow][column-headCol] > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method should return "X" if the Target has been destroyed
     * and "O" (capital letter O) if it has not been destroyed.
     * But for an undestroyed Tank, it returns "T".
     * If the Target is Ground, it returns "-".
     * @return a single-character String to use in the Base’s print method.
     */
    public String toString() {
        if (isDestroyed() == true) {
            return "X";
        }
        return "O";
    }

    /**
     * show the message if the target get destroyed after each round
     */
    public void printResult() {
        if (this.isDestroyed() == true) {
            if (this.isPrinted == false) {
                System.out.println("You have destroyed a " + this.getTargetName());
                this.isPrinted = true;
            }
        }
    }

    /**
     * the first element is start row, the second element is start column
     * @return get cooridnate
     */
    public int[] getCoordinate() {
        return coordinate;
    }

    /**
     * set coordinate
     * @param coordinate the target's coordinate
     */
    public void setCoordinate(int[] coordinate) {
        this.coordinate = coordinate;
    }

    /**
     *
     * @return the target's length
     */
    public int getLength() {
        return length;
    }

    /**
     *
     * @return the target's width
     */
    public int getWidth() {
        return width;
    }

    /**
     *  Base which has 10X10 dimensions is constructed by Targets
     * @return the base
     */
    public Base getBase() {
        return base;
    }

    /**
     *
     * @return the hit status
     */
    public int[][] getHit() {
        return hit;
    }


    /**
     * set hit status
     * @param hit the target's hit status
     */
    public void setHit(int[][] hit) {
        this.hit = hit;
    }

    /**
     *
     * @return the horizontal status
     */
    public boolean getHorizontal() {
        return horizontal;
    }

    /**
     * set horizontal status
     * @param horizontal the target's horizontal status
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

}
