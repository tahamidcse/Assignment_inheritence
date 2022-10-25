// Clock.java

public class Clock {

    // private int hour, minute;

    public Clock() {

        /*

         * String s = new java.util.Date().toString(); hour =

         * Integer.parseInt(s.substring(11, 13)); minute =

         * Integer.parseInt(s.substring(14, 16));

         */

    }

    /**

     * instead of assigning to a variable from constructor, it is much better

     * to return the current time whenever getMinute() or getHour() called,

     * which will always provide correct time

     */

    public int getMinute() {

        String s = new java.util.Date().toString();

        return Integer.parseInt(s.substring(14, 16));

    }

    public int getHour() {

        String s = new java.util.Date().toString();

        return Integer.parseInt(s.substring(11, 13));

    }

    /**

     * returns the current time in HH:MM format

     */

    public String getTime() {

        return getHour() + ":" + getMinute();

    }

}

// WorldClock.java

public class WorldClock extends Clock {

    private int timeOffset;

    //constructor accepting an offset

    public WorldClock(int timeOffset) {

        this.timeOffset = timeOffset;

    }

    @Override

    public int getHour() {

        // adding offset value to hour

        int hour = super.getHour() + timeOffset;

        if (hour > 23) {

            /**

             * hour is now greater than 23 , so continuously subtracting from 23

             * to keep it under range

             */

            while (hour > 23) {

                hour = 23 - hour;

            }

        }

        if (hour < 0) {

            /**

             * hour is now smaller than 0 [negative offset], so continuously

             * adding 23 to keep it under range

             */

            while (hour < 0) {

                hour = 23 + hour;

            }

        }

        return hour;

    }

}

// Test.java (tester class with main method)

public class Test {

    public static void main(String[] args) {

        Clock c = new Clock();

        System.out.printf("%02d:%02d\n", c.getHour(), c.getMinute());

        /**

         * Testing the world clock class

         */

        WorldClock worldClock = new WorldClock(3);

        /**

         * displaying current time of worldClock object using getTime method

         */

        System.out.println(worldClock.getTime());

    }

}

/*OUTPUT*/

10:51

        13:51