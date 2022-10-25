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

/*
10:51
13:51
  */          
            
            
            
            
            
          //  ####2
             
             
             
 //  I have implemented the setAlarm() method inside the Clock class, so that now it will print the updated time. Also, to implement the same in the WorldClock.java class we need to add the offset to the hours while we set the alarm. We have overriden the method inside the Worldclock.java class by adding the offset.  Here are the 3 updated classes.  ========================================================================  
            import java.util.Date; 
class Clock
{      private int alarmHours;   
             private int alarmMinutes;   
             // getter for hours      
             public int getHours() 
             {          String timeStr = new Date().toString().split(" ")[3]; 
                                    int hour = Integer.parseInt(timeStr.split(":")[0]);       
                         return hour;     
             } 
             // getter for minutes      
             public int getMinutes() 
             {          String timeStr = new Date().toString().split(" ")[3];      
                                      int minute = Integer.parseInt(timeStr.split(":")[1]);      
                      return minute;     
             }  
             public String getTime()
             {         if (alarmHours == -1 && alarmMinutes == -1)        
                           {
                                return String.format("%02d:%02d", getHours(), getMinutes()); 
                           }     
                       else {             if (getHours() >= alarmHours && getMinutes() >= alarmMinutes)
                                                   {      alarmHours = -1;
                                                      alarmMinutes = -1;                
                                                     return String.format("%02d:%02d Alarm %c", getHours(), getMinutes(), '\u23F0');          
                                                   } 
                                           else    {                 
                                                    return String.format("%02d:%02d", getHours(), getMinutes());             }    
                                                   }   
                            }  
             public void setAlarm(int hours, int minutes) 
             {          alarmHours = hours;      
                       alarmMinutes = minutes;    
             }  
            
  }

//===========================================================

 class WorldClock extends Clock {  
 private int offset;
 public WorldClock(int offset) 
 {          this.offset = offset % 24;     
 }   
 
// @Override     
public int getHours()
 {  // fetching hours using super class getHours method   
 int hr = super.getHours();     
 hr += offset;  
 if (hr > 23) {              hr = hr - 24;          } 
 else if (hr < 0) {      hr = hr + 24;          }      
 return hr;     
 }  
// @Override     
 public void setAlarm(int hours, int minutes) 
 {         super.setAlarm((hours + offset) % 24, minutes);     
 } 
 
 }

//===========================================================

 class Test {    
 public static void main(String[] args) 
 {  // creating a Clock with current time         
 Clock clock = new Clock();     
 WorldClock worldClock = new WorldClock(12);    
 worldClock.setAlarm(0,12);         System.out.println(clock.getTime());   
 System.out.println(worldClock.getTime());    
 } 
 }

//===========================================================
             
             
