
/**
 * @author Yung-Jen Yang
 * 65314711
 * work alone without help
 */

package callOfDuty;

import java.util.Scanner;

/**
 * The main class of the Call of Duty game
 */
public class CallOfDutyGame {
    Base base;
    Weapon missile;
    Weapon rocketLauncher;

    /**
     * main function
     * @param args args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CallOfDutyGame cod = new CallOfDutyGame();
        while (true) {
            cod.run(sc);
            System.out.println("--------------------");
            System.out.println("Do you want to play again? (y/n)");

            boolean check = cod.askYesNo(sc);
            if (!check) {
                System.out.println("Goodbye!");
                sc.close();
                break;
            }

        }
    }

    /**
     * ask if user want to play again
     * @param sc Scanner object
     * @return the boolean value to check yes(true) or no(false)
     */
    private boolean askYesNo(Scanner sc) {
        while (true) {
            String response = sc.nextLine();
            if (response.toLowerCase().startsWith("y")) {
                return true;
            } else if (response.toLowerCase().startsWith("n")) {
                return false;
            }
            // if user scan the invalid input, then user should try to scan again.
            System.out.print("Invalid input.\nDo you want to play again? (y/n)");
        }
    }

    /**
     * run the game.
     * @param sc Scanner object
     */
    public void run(Scanner sc) {
        createObject();
//        base.printAnswer();
        Weapon curWeapon = rocketLauncher;
        while (true) {
            base.print();
            String weaponType = curWeapon.getWeaponType();
            showMenu(weaponType);
            int row, col;
            while (true) {
                String response = sc.nextLine();
                if (isValidInput(response) == true) {
                    if (response.equals("q")) {
                        if (weaponType.equals("rocketlauncher")) {
                            curWeapon = missile;
                            weaponType = curWeapon.getWeaponType();
                            showMenu(weaponType);
                        } else {
                            curWeapon = rocketLauncher;
                            weaponType = curWeapon.getWeaponType();
                            showMenu(weaponType);
                        }
                    } else {
                        String[] slist = response.split(",");
                        row = Integer.parseInt(slist[0].trim());
                        col = Integer.parseInt(slist[1].trim());
                        if (curWeapon.getShotLeft() > 0) {
                            break;
                        } else if (curWeapon.getShotLeft() == 0) {
                            System.out.println("No ammunition!");
                            showMenu(weaponType);
                        }
                    }
                } else {
                    System.out.print("please input a valid response again. " +
                            "\ne.g. the range of row and column is between 0 ~ 9 and \"q\" is to switch the weapon: ");
                }
            }

            curWeapon.shootAt(row, col, base);
            showResult();

            if (base.isGameOver(rocketLauncher, missile) == true) {
                System.out.println("Unfortunately, you are out of all ammo. You lose.");
                break;
            }
            if (base.win() == true) {
                System.out.println("You have destroyed all of the targets, you win!");
                break;
            }
        }
    }


    /**
     * print those targets which had been hit after each round.
     */
    private void showResult() {
        for (int i = 0; i < Base.LENGTH; i++) {
            for (int j = 0; j < Base.WIDTH; j++) {
                base.getTargetsArray()[i][j].printResult();
            }
        }

    }


    /**
     * check user's input is valid
     * @param response the user's input
     * @return the boolean value to check the user's input is valid
     */
    public boolean isValidInput(String response) {
        if (response.equals("q"))  {
            return true;
        }
        String[] slist = response.split(",");
        if (slist.length != 2) {
            return false;
        }
        int n1 = Integer.parseInt(slist[0].trim());
        int n2 = Integer.parseInt(slist[1].trim());
        if (n1 >= 0 && n1 < 10 && n2 >= 0 && n2 < 10) {
            return true;
        }
        return false;
    }


    /**
     *
     * create base, weapon object.
     */
    public void createObject() {
        base = new Base();
        missile = new Missile();
        rocketLauncher = new RocketLauncher();
        base.placeAllTargetRandomly();
    }


    /**
     * show the interface let user can input the response.
     * @param weaponType weapon's name
     */
    public void showMenu(String weaponType) {
        int rocketShotLeft = rocketLauncher.getShotLeft();
        int missileShotLeft = missile.getShotLeft();
        if (rocketShotLeft < 0) {
            rocketShotLeft = 0;
        }
        if (missileShotLeft < 0) {
            missileShotLeft = 0;
        }
        System.out.println("RPG: " + rocketShotLeft + " Missile: " + missileShotLeft);
        System.out.println("Your current weapon is: " + weaponType);
        System.out.print("Enter row, column, or q to switch a weapon: ");
    }
}
