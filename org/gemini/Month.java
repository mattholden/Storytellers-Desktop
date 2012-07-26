/*
 * Month.java
 *
 * Created on November 8, 2005, 9:04 PM
 */

package org.gemini;

/**
 * Defines months for use in campaign time logging and sourcebook/magazine dating.
 *
 * @author  Jaeden
 */
public class Month implements org.slage.framework.NamedObject, java.io.Serializable 
{   
        /** "Number" of the month for chronological sorting) */   
    private int number;  
    
    /** Name of the month */ private String name;
    /** Number of days in the month */ int days = 28;    
    /** Number of "leap days" in the month */ int leapDays = 0;
        
    /** Creates a new instance of Month 
      @param aName name of the month / time period 
      @param aNumber the month number (used for chronological sorting) 
      @param numDays number of days in this month */
    public Month(String aName, int aNumber, int numDays) 
    {
        name = aName;
        number = aNumber;        
        days = numDays;
    }
 
     /** Creates a new instance of Month 
      @param aName name of the month / time period 
      @param aNumber the month number (used for chronological sorting) 
      @param numDays number of days in this month 
      @param numLeapDays number of leap days in the month */
    public Month(String aName, int aNumber, int numDays, int numLeapDays) 
    {
        this(aName, aNumber, numDays);
        setLeapDays(numLeapDays);
    }
 
    /** Getter for name.
     * @return Value of property name.      */
    public java.lang.String getName() {         return name;     }    
    
    /** Setter for name.
     *  @param _name New value of property name.     */
    public void setName(java.lang.String _name) {   this.name = name;     }    
    
    /** Getter for days.
     * @return Value of property days.      */
    public int getDays() {         return days;     }
    
    /** Setter for property days.
     * @param iDays New value of property days.      */ 
    public void setDays(int iDays) {         this.days = iDays;     }
    
    /** Getter for leap days.
     * @return Value of property leapDays.      */
    public int getLeapDays() {         return leapDays;      }
    
    /** Setter for leap days.
     * @param iLeapDays New value of property leapDays.     */
    public void setLeapDays(int iLeapDays) {         this.leapDays = iLeapDays;     }
    
    /**  Getter for month number.
     * @return Value of property number.      */
    public int getNumber() {   return number;     }
    
    /** Setter for property number.
     * @param _number New value of property number.      */
    public void setNumber(int _number) {    this.number = _number;     }
    
    
    
    
    /** STATIC REAL-WORLD MONTHS/TIMEFRAMES FOR COMMON USE */   
    /** January */  public static final Month JANUARY = new Month("January", 1, 31);
    /** February */  public static final Month FEBRUARY = new Month("February", 2, 28, 1);
    /** March */  public static final Month MARCH = new Month("March", 3, 31);
    /** April */  public static final Month APRIL = new Month("Apri", 4, 30);
    /** May */  public static final Month MAY = new Month("May", 5, 31);
    /** June */  public static final Month JUNE = new Month("June", 6, 30);
    /** July */  public static final Month JULY = new Month("July", 7, 31);
    /** August */  public static final Month AUGUST = new Month("August", 8, 31);
    /** September */  public static final Month SEPTEMBER = new Month("September", 9, 30);
    /** October */  public static final Month OCTOBER = new Month("October", 10, 31);
    /** November */  public static final Month NOVEMBER = new Month("November", 11, 30);
    /** December */  public static final Month DECEMBER = new Month("December", 12, 31);
    
    /** BIMONTHLY (for Dungeon Magazine) */
    /** January-February */  public static final Month JANFEB = new Month("January - February", 1, 59, 1);
    /** March-April */  public static final Month MARAPR = new Month("March - April", 3, 61);
    /** May-June */  public static final Month MAYJUN = new Month("May - June", 5, 61);
    /** July-August */  public static final Month JULAUG = new Month("July - August", 7, 62);
    /** September-October */  public static final Month SEPOCT = new Month("September - October", 9, 61);
    /** November-December */  public static final Month NOVDEC = new Month("November - December", 11, 61); 
    
    /** SEASONAL */
    /** Spring (Mar 21 - Jum 20) */ public static final Month SPRING = new Month("Spring", 3, 92);
    /** Summer (Jun 21 - Sept 20)*/ public static final Month SUMMER = new Month("Summer", 6, 92);
    /** Fall (Sept 21 - Dec 21)*/ public static final Month FALL = new Month("Fall", 9, 91);
    /** Winter (Dec 21 - Mar 21)*/ public static final Month WINTER = new Month("Winter", 12, 90, 1);
    
    /** Annual */  public static final Month ANNUAL = new Month("Annual", 13, 365, 1);
    
    
    /** Comparator for chronological sorting */
    public static final java.util.Comparator chonologicalComparator = new java.util.Comparator()
    {
        /** Compares two Months
        @param o1 a Month to compare
        @param o2 another Month to compare
        @return negative if o1 is earlier, positive if o2 is earlier, 0 if o1 and o2 are the same */         
        public int compare(Object o1, Object o2)
        {
            if (o1 instanceof Month == false || o2 instanceof Month == false)
                return 0;
            
            Month m1 = (Month)o1;
            Month m2 = (Month)o2;
            
            return (m1.getNumber() - m2.getNumber());
        }
    };
    
    
}
    
    
