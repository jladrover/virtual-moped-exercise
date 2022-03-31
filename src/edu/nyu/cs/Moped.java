package edu.nyu.cs;

import java.util.Arrays;

/**
 * A virtual moped, roaming the streets of New York.
 * The signatures of a few methods are given and must be completed and used as indicated.
 * Create as many additional properties or methods as you want, as long as the given methods behave as indicated in the instructions.
 * Follow good object-oriented design, especially the principles of abstraction (i.e. the black box metaphor) and encapsulation (i.e. methods and properties belonging to specific objects), as we have learned them.
 * The rest is up to you.
 */
public class Moped {

    private double currentgas = 1;
    private String [] directions = {"North","East","South","West"};
    private int currentdirIndex = 2;
    private int currentst = 10; 
    private int currentave = 5; 
    private boolean isfacingbackwards = false;
    private final int maxSt = 200;
    private final int minSt = 1;
    private final int maxAve = 10;
    private final int minAve = 1;
    private String currentAd = "";
    private boolean currentlyHoming = false;
    private String nameEndSt;
    private String nameEndAve;


    /**
     * Sets the orientation of the moped to a particular cardinal direction.
     * @param orientation A string representing which cardinal direction at which to set the orientation of the moped.  E.g. "north", "south", "east", or "west".
     */
    public void setOrientation(String orientation) {
        for (int i = 0; i < directions.length; i++) {
            if(directions[i].equalsIgnoreCase(orientation)){
                this.currentdirIndex = i;
            }
            
        }
    }

    /**
     * Returns the current orientation of the moped, as a lowercase String.
     * E.g. "north", "south", "east", or "west".
     * @return The current orientation of the moped, as a lowercase String.
     */
    public String getOrientation() {
        return directions[this.currentdirIndex].toLowerCase();        
    }

    /**
     * Prints the current location, by default exactly following the format:
     *      Now at 12th St. and 5th Ave, facing South.
     *
     * If the current location is associated with location-based advertising, this method should print exactly following format:
     *      Now at 12th St. and 4th Ave, facing West.  Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?
     * 
     * Note that the suffixes for the numbers must be correct: i.e. the "st" in "1st", "nd" in "2nd", "rd" in "3rd", "th" in "4th", etc, must be correct.
     */
    public void printLocation() { 
        if (this.currentst == 79 && this.currentave == 8){
            this.currentAd = " Come check out our giant-screen film 'Wings Over Water' or our specical Sharks exhibition from Wednesday to Sunday!";
        }
        else if (this.currentst == 74 && this.currentave == 1){
            this.currentAd = " Memorial Sloan Kettering Cancer Center is one of 52 National Cancer Institute designated Comprehensive Cancer Centers, with state-of-the-art science flourishing side by side with clinical studies and treatment.";
        }
        else if (this.currentst == 56 && this.currentave == 3){
            this.currentAd = " Our authentic Cuban Cuisine is well known in New York, as being some of the fastest and most delicious in the city.";
        }
        else if (this.currentst == 12 && this.currentave == 4){
            this.currentAd = " Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?";
        } 
        else if (this.currentst == 15 && this.currentave == 8 && this.currentlyHoming){
            this.currentAd = " We have reached Xi'an Famous Foods.  Enjoy your noodles.";
            this.currentlyHoming = false;
        }

        if(this.currentst % 10 == 1 && this.currentst != 11 && this.currentst != 111){
            this.nameEndSt = "st";
        }
        else if(this.currentst % 10 == 2 && this.currentst != 12 && this.currentst != 112){
            this.nameEndSt = "nd";
        }
        else if(this.currentst % 10 == 3 && this.currentst != 13 && this.currentst != 113){
            this.nameEndSt = "rd";
        }
        else{
            this.nameEndSt = "th";
        }

        if(this.currentave % 10 == 1){
            this.nameEndAve = "st";
        }
        else if(this.currentave % 10 == 2){
            this.nameEndAve = "nd";
        }
        else if(this.currentave % 10 == 3){
            this.nameEndAve = "rd";
        }
        else{
            this.nameEndAve = "th";
        }

        System.out.println("Now at " + this.currentst + this.nameEndSt + " St. and " + this.currentave + this.nameEndAve + " ave, facing "+ directions[this.currentdirIndex] + "." + currentAd);
        this.currentAd = "";
        this.nameEndAve = "";
        this.nameEndSt = "";
    }

    /**
     * Handles the command, `go left`.
     * Moves the moped one block to the left, and causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.  Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goLeft() { 
        if (this.currentst <= maxSt && this.currentst >= minSt && this.currentave >= minAve && this.currentave <= maxAve){
            if (!isfacingbackwards){
                this.currentgas = this.currentgas - 0.05;
                this.currentdirIndex--;
                if (this.currentdirIndex > 3){
                    this.currentdirIndex = 0;
                }
                else if (this.currentdirIndex < 0){
                    this.currentdirIndex = 3;
                }
                switch(currentdirIndex){
                    case 0:
                        this.currentst += 1;
                        break;
                    case 1:
                        this.currentave -= 1;
                        break;
                    case 2:
                        this.currentst -= 1;
                        break;
                    case 3:
                        this.currentave += 1;
                        break;
                }

            }
            else{
                this.currentgas = this.currentgas - 0.05;
                this.currentdirIndex++;
                if (this.currentdirIndex > 3){
                    this.currentdirIndex = 0;
                }
                else if (this.currentdirIndex < 0){
                    this.currentdirIndex = 3;
                }
                switch(currentdirIndex){
                    case 0:
                        this.currentst -= 1;
                        break;
                    case 1:
                        this.currentave += 1;
                        break;
                    case 2:
                        this.currentst += 1;
                        break;
                    case 3:
                        this.currentave -= 1;
                        break;
                }
            }
            if (this.currentst < minSt){
                this.currentst = minSt;
                this.currentgas += 0.05;
            }
            else if (this.currentave < minAve){
                this.currentave = minAve;
                this.currentgas += 0.05;
            }
            else if (this.currentst > 200){
                this.currentst = maxSt;
                this.currentgas += 0.05;
            }
            else if (this.currentave > maxAve){
                this.currentave = maxAve;
                this.currentgas += 0.05;
            }
        }
        else{
            this.currentdirIndex--;
            if (this.currentdirIndex > 3){
                this.currentdirIndex = 0;
            }
            else if (this.currentdirIndex < 0){
                this.currentdirIndex = 3;
            }
        }
        if (this.currentgas < 0.04){
        System.out.println("We have run out of gas. Bye bye!");
        System.exit(0);
        }
    }

    /**
     * Handles the command, `go right`.
     * Moves the moped one block to the right, and causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.  Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goRight() { 
    if (this.currentst <= maxSt && this.currentst >= minSt && this.currentave >= minAve && this.currentave <= maxAve){
        if (!isfacingbackwards){
                this.currentgas = this.currentgas - 0.05;
                this.currentdirIndex++;
                if (this.currentdirIndex > 3){
                    this.currentdirIndex = 0;
                }
                else if (this.currentdirIndex < 0){
                    this.currentdirIndex = 3;
                } 
                switch(currentdirIndex){
                    case 0:
                        this.currentst += 1;
                        break;
                    case 1:
                        this.currentave -= 1;
                        break;
                    case 2:
                        this.currentst -= 1;
                        break;
                    case 3:
                        this.currentave += 1;
                        break;
                }
            }
            else{
                this.currentgas = this.currentgas - 0.05;
                this.currentdirIndex--;
                if (this.currentdirIndex > 3){
                    this.currentdirIndex = 0;
                }
                else if (this.currentdirIndex < 0){
                    this.currentdirIndex = 3;
                }
                switch(currentdirIndex){
                    case 0:
                        this.currentst -= 1;
                        break;
                    case 1:
                        this.currentave += 1;
                        break;
                    case 2:
                        this.currentst += 1;
                        break;
                    case 3:
                        this.currentave -= 1;
                        break;
                }
            }
            if (this.currentst < minSt){
                this.currentst = minSt;
                this.currentgas += 0.05;
            }
            else if (this.currentave < minAve){
                this.currentave = minAve;
                this.currentgas += 0.05;
            }
            else if (this.currentst > 200){
                this.currentst = maxSt;
                this.currentgas += 0.05;
            }
            else if (this.currentave > maxAve){
                this.currentave = maxAve;
                this.currentgas += 0.05;
            }
        }
        
        else{
            this.currentdirIndex++;
            if (this.currentdirIndex > 3){
                this.currentdirIndex = 0;
            }
            else if (this.currentdirIndex < 0){
                this.currentdirIndex = 3;
            }
        }
        if (this.currentgas < 0.04){
        System.out.println("We have run out of gas. Bye bye!");
        System.exit(0);
        }
    }

    /**
     * Handles the command,`straight on`.
     * Moves the moped one block straight ahead.
     * Consumes gas with each block moved, and doesn't move unless there is sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goStraight() { 
        if (this.currentst <= maxSt && this.currentst >= minSt && this.currentave >= minAve && this.currentave <= maxAve){
            this.currentgas = this.currentgas - 0.05;
            this.isfacingbackwards = false;
            switch(currentdirIndex){
                case 0:
                    this.currentst += 1;
                    break;
                case 1:
                    this.currentave -= 1;
                    break;
                case 2:
                    this.currentst -= 1;
                    break;
                case 3:
                    this.currentave += 1;
                    break;
            }
            if (this.currentst < minSt){
                this.currentst = minSt;
                this.currentgas += 0.05;
            }
            else if (this.currentave < minAve){
                this.currentave = minAve;
                this.currentgas += 0.05;
            }
            else if (this.currentst > 200){
                this.currentst = maxSt;
                this.currentgas += 0.05;
            }
            else if (this.currentave > maxAve){
                this.currentave = maxAve;
                this.currentgas += 0.05;
            }
        }
        else{
            this.isfacingbackwards = false;
        }
        if (this.currentgas < 0.04){
        System.out.println("We have run out of gas. Bye bye!");
        System.exit(0);
        }
    }

    /**
     * Handles the command,`back up`.
     * Moves the moped one block backwards, but does not change the cardinal direction the moped is facing.
     * Consumes gas with each block moved, and doesn't move unless there is sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goBackwards() { 
        if (this.currentst <= maxSt && this.currentst >= minSt && this.currentave >= minAve && this.currentave <= maxAve){
            this.currentgas = this.currentgas - 0.05;
            this.isfacingbackwards = true;
            switch(currentdirIndex){
                case 0:
                    this.currentst -= 1;
                    break;
                case 1:
                    this.currentave += 1;
                    break;
                case 2:
                    this.currentst += 1;
                    break;
                case 3:
                    this.currentave -= 1;
                    break;
            }
            if (this.currentst < minSt){
                this.currentst = minSt;
                this.currentgas += 0.05;
            }
            else if (this.currentave < minAve){
                this.currentave = minAve;
                this.currentgas += 0.05;
            }
            else if (this.currentst > 200){
                this.currentst = maxSt;
                this.currentgas += 0.05;
            }
            else if (this.currentave > maxAve){
                this.currentave = maxAve;
                this.currentgas += 0.05;
            }
        }
        else{
            this.isfacingbackwards = true;
        }
        if (this.currentgas < 0.04){
        System.out.println("We have run out of gas. Bye bye!");
        System.exit(0);
        }
    }

    /**
     * Handles the command,`how we doin'?`.
     * This method must not print anything.
     * @return The current gas level, as an integer from 0 to 100.
     */
    public int getGasLevel() {
        int intGasLevel = (int)Math.round((this.currentgas * 100));
        return intGasLevel; 
    }

    /**
     * Prints the current gas level, by default exactly following the format:
     *      The gas tank is currently 85% full.
     *
     * If the moped is out of gas, this method should print exactly following format:
     *      We have run out of gas.  Bye bye!
     */
    public void printGasLevel() {
        int gasPercentage = (int)Math.round((this.currentgas * 100));
        System.out.println("The gas tank is currently " + gasPercentage + "% full.");
    }

    /**
     * Handles the command, `fill it up`.
     * This method must not print anything.
     * Fills the gas level to the maximum.
     */
    public void fillGas() {
        this.currentgas = 1;
    }

    /**
     * Handles the command, `park`.
     * This causes the program to quit.  
     * You can use System.exit(0); to cause a program to quit with status code 0, which indicates a normal graceful exit. 
     * (In case you were wondering, status code 1 represents quitting as a result of an error of some kind).
     */
    public void park() {
        System.out.println("We have parked");
        System.exit(0);

    }

    /**
     * Handles the command, `go to Xi'an Famous Foods`
     * Causes the moped to self-drive, block-by-block, to 8th Ave. and 15th St.
     * Consumes gas with each block, and doesn't move unless there is sufficient gas, as according to the instructions.
     */
    public void goToXianFamousFoods() { 
        int xianSt = 15;
        int xianAve = 8;
        int stDiff = this.currentst - xianSt;
        int aveDiff = this.currentave - xianAve;
        this.currentlyHoming = true;
        while (stDiff != 0){
            if (stDiff > 0){
                if (this.currentgas<0.04){
                    fillGas();
                    System.out.println("Refilling gas");
                }
                this.currentdirIndex = 2;
                this.currentst -= 1;
                this.currentgas -= 0.05;
                printLocation();
            }
            else if (stDiff < 0){
                if (this.currentgas<0.04){
                    fillGas();
                    System.out.println("Refilling gas");
                }
                this.currentdirIndex = 0;
                this.currentst += 1;
                this.currentgas -= 0.05;
                printLocation();
            }
            stDiff = this.currentst - xianSt;
        }
        while (aveDiff != 0){
            if (aveDiff > 0){
                if (this.currentgas<0.04){
                    fillGas();
                    System.out.println("Refilling gas");
                }
                this.currentdirIndex = 1;
                this.currentave -= 1;
                this.currentgas -= 0.05;
                printLocation();
            }
            else if (aveDiff < 0){
                if (this.currentgas<0.04){
                    fillGas();
                    System.out.println("Refilling gas");
                }
                this.currentdirIndex = 3;
                this.currentave += 1;
                this.currentgas -= 0.05;
                printLocation();
            }
            aveDiff = this.currentave - xianAve;
        }
    }

    /**
     * Generates a string, containing a list of all the user commands that the program understands.
     * @return String containing commands that the user can type to control the moped.
     */
    public String getHelp() {
        return "Accepted user commands:\ngo left\ngo right\nstraight on\nback up\nhow we doin'?\nfill it up\npark\ngo to Xi'an Famous Foods\nhelp";    
    }

    /**
     * Sets the current location of the moped.
     * @param location an int array containing the new location at which to place the moped, in the order {street, avenue}.
     */
    public void setLocation(int[] location) {
            this.currentst = location[0];
            this.currentave = location[1];
        
    }

    /**
     * Gets the current location of the moped.
     * @return The current location of the moped, as an int array in the order {street, avenue}.
     */
    public int[] getLocation() { 
        int[] location = {this.currentst, this.currentave}; 
        return location;
    }

}
