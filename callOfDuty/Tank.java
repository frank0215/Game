package callOfDuty;

/** This class is to create the Tank object
 *
 * @author Yung-Jen Yang
 */

public class Tank extends Target{
    private static final int LENGTH = 1;
    private static final int WIDTH = 1;
    private static final String NAME = "tank";

    public Tank(Base base) {
        super(Tank.LENGTH, Tank.WIDTH, base);
    }

    @Override
    public boolean isDestroyed() {
        int headRow = super.getTargetRange()[0];
        int headCol = super.getTargetRange()[1];
        int endRow = super.getTargetRange()[2];
        int endCol = super.getTargetRange()[3];

        for (int i = 0; i <= endRow-headRow; i++) {
            for (int j = 0; j <= endCol-headCol; j++) {
                if (super.getHit()[i][j] < 2) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if (this.isDestroyed() == true) {
            return "X";
        }
        return "T";
    }

    @Override
    public void explode() {
        int rowLength, colLength;
        if (super.getHorizontal() == true) {
            colLength = super.getLength();
            rowLength = super.getWidth();
        } else {
            rowLength = super.getLength();
            colLength = super.getWidth();
        }
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                super.getHit()[i][j] += 2;
            }
        }

        /*
        determine the range of explosion
         */
        int startRow = super.getTargetRange()[0] - 2;
        if (startRow < 0) {
            startRow = 0;
        }
        int endRow = super.getTargetRange()[2] + 2;
        if (endRow > super.getBase().LENGTH - 1) {
            endRow = super.getBase().LENGTH - 1;
        }

        int startCol = super.getTargetRange()[1] - 2;
        if (startCol < 0) {
            startCol = 0;
        }
        int endCol = super.getTargetRange()[3] + 2;
        if (endCol > super.getBase().WIDTH - 1) {
            endCol = super.getBase().WIDTH - 1;
        }

        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if (i >= super.getTargetRange()[0] && i <= super.getTargetRange()[2]
                        && j >= super.getTargetRange()[1] && j <= super.getTargetRange()[3]) {
                    continue;
                }
                Target tar = super.getBase().getTargetsArray()[i][j];
                String targetName = tar.getTargetName();

                int tarHeadRow = tar.getCoordinate()[0];
                int tarHeadCol = tar.getCoordinate()[1];

                if (!targetName.equals("armory") && !targetName.equals("oildrum") && !tar.equals("tank")) {
                    tar.getHit()[Math.abs(i-tarHeadRow)][Math.abs(j-tarHeadCol)]++;
                } else {
                    if (tar.isDestroyed() == false) {
                        tar.explode();
                    }
                }
            }
        }
    }

    @Override
    public String getTargetName() {
        return Tank.NAME;
    }
}
