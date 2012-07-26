

package org.gemini;
import org.slage.framework.NamedObject;
import java.util.ArrayList;

/** Units is an abstract, general-purpose tool class for converting between units of measurement. It also can convert
 *between metric denominations. */
public abstract class Units
{
        /** Stores a unit of measurement. */
        public static class Unit implements NamedObject
        {
            /** The name of the unit */
            public String strName;
            /** The symbol for the unit */
            public String strAbbrev;
            /** The number that must be multiplied by to convert the base unit into this unit */
            public double dFromBase;
            /** The number that must be multiplied by to convert this unit into the base unit */
            public double dToBase;
            
            /** Construct a Unit.
             * @param strName Name of the unit
             * @param strAbbrev Abbreviation/Symbol for the unit
             * @param dToBase The number that must be multiplied by to convert this unit into the base unit */
            public Unit(String strName, String strAbbrev, double dToBase)
            {
                this.strName = strName;
                this.strAbbrev = strAbbrev;
                this.dFromBase = 1.0 / dToBase;
                this.dToBase = dToBase;
            }
            
            /** Get a string representation of the unit (its name)
             *   @return the unit's name */
            public String toString() { return strName; }
            
             /** Get a string representation of the unit (its name)
             *   @return the unit's name */
            public String getName() { return strName; }
            
             /** Set the unit's name
             *   @param _name the unit's name */
            public void setName(String _name) { strName = _name; }            
            
        }
        
        /** A Measurement stores all known Units for a particular measurement type, such as length, volume, or mass. */
        public static class Measurement
        {
            /** A unit to use as a base. All other Units will be constructed as relative to this unit. */
            private Unit base;
            
            /** A list of all units in the measurement (including base) */
            private ArrayList<Unit> listUnits = new ArrayList<Unit>();
            
            /** Construct a Measurement.
             * @param base A unit to use as a base. All other Units will be constructed as relative to this unit. */
            public Measurement (Unit base) { this.base = base; listUnits.add(base); }
            
            /** Add a new unit to this measurement type.
             *   @param u a new Unit. */
            public void add(Unit u) { listUnits.add(u); }
            
            
            /** Convert an amount in one measurement to another.
             @param dAmount amount of unit to convert
             *  @param strUnitNow unit of which we have dAmount
             *  @param strUnitTo Unit we wish to convert to 
                @return the amount of strUnitTo units given dAmount of strUnitNow units. */
            public double convert(double dAmount, String strUnitNow, String strUnitTo)
            {
                Unit uFrom = getUnit(strUnitNow);
                Unit uTo = getUnit(strUnitTo);
                
                // Better have units.
                if (uFrom == null || uTo == null) return 0.0;
                
                return dAmount * uFrom.dToBase * uTo.dFromBase;
            }
            
            /** A more readable way to get a conversion factor between units
             *  @param strUnitNow original unit
             *  @param strUnitTo Unit we wish to convert to 
                 @return a straight multiplication conversion factor */            
            public double getConversionFactor(String strUnitNow, String strUnitTo)
            {
                return convert(1.0, strUnitNow, strUnitTo);
            }
            
            
            /** Retrieve a Unit.
             *  @param strUnit a string representing either the Name or the Symbol of a unit. */
            public Unit getUnit(String strUnit)
            {
                strUnit = strUnit.toLowerCase();
                for (Unit u : listUnits)
                {
                    if (u.strName.equals(strUnit) || u.strAbbrev.equals(strUnit) ||
                        // trim off plural "s" if it's there
                        u.strAbbrev.equals(strUnit.substring(0, strUnit.length() - 2))) 
                    {
                        return u;
                    }
                }
                return null;
            }
        }
        
        /** Measurement for length (base - meter)*/
        public static Measurement LENGTH = new Measurement(new Unit("meter", "m", 1.0));
        /** Measurement for mass (base - gram)*/
        public static Measurement MASS = new Measurement(new Unit("gram", "g", 1.0));        
        /** Measurement for liquid volume (base - liter)*/
        public static Measurement LIQUID = new Measurement(new Unit("liter", "l", 1.0));        
        /** Measurement for Force (base - newton) (use it, Luke!) */
        public static Measurement FORCE = new Measurement(new Unit("newton", "n", 1.0));        
        /** Measurement for Temperature (base - kelvin)*/
        public static Measurement TEMPERATURE = new Measurement(new Unit("kelvin", "K", 1.0));        
        /** Measurement for electric current (base - ampere)*/
        public static Measurement ELECTRIC_CURRENT = new Measurement(new Unit("ampere", "a", 1.0));        
        /** Measurement for light intensity (base - candela)*/
        public static Measurement LIGHT_INTENSITY = new Measurement(new Unit("candela", "cd", 1.0));        
        /** Measurement for time (base - second)*/
        public static Measurement TIME = new Measurement(new Unit("second", "sec", 1.0));        
        /** Measurement for pressure (base - pascal)*/
        public static Measurement PRESSURE = new Measurement(new Unit("pascal", "pa", 1.0));        
        /** Measurement for energy (base - Joule)*/
        public static Measurement ENERGY = new Measurement(new Unit("Joule", "j", 1.0));                
        /** Measurement for angles (base - degrees) */
        public static Measurement ANGLES = new Measurement(new Unit("degree", "deg", 1.0));
        
        static
        {
            // Length
            LENGTH.add(new Unit("inch", "in", 0.0254));
            LENGTH.add(new Unit("foot", "ft",  0.3048));
            LENGTH.add(new Unit("yard", "yd", 0.9144));
            LENGTH.add(new Unit("mile", "mi", 1609.344));
            LENGTH.add(new Unit("fathom", "F", 1.8288));
            LENGTH.add(new Unit("rod", "r",  5.0292));
            LENGTH.add(new Unit("ell", "ell", 1.143));
            LENGTH.add(new Unit("parsec", "pc", 30856802500000000.0));
            LENGTH.add(new Unit("chain", "chain", 20.1168));
            LENGTH.add(new Unit("furlong", "furlong", 201.16800));
            LENGTH.add(new Unit("league", "league", 5556.0));
            LENGTH.add(new Unit("link", "link", 0.201168));
            LENGTH.add(new Unit("pole", "pole", 5.0292));
            LENGTH.add(new Unit("perch", "perch", 5.0292));
            LENGTH.add(new Unit("lightyear", "ly", 9460528400000000.0));
            LENGTH.add(new Unit("nautical mile", "nm", 1852.0));
            LENGTH.add(new Unit("angstrom", "A", .000000001));
            
            ANGLES.add(new Unit("radian", "rad", 57.2957795 ));
            
            MASS.add(new Unit("pound", "lb", 453.59237));
            MASS.add(new Unit("stone", "st", 6350.29318));
            MASS.add(new Unit("ounce", "oz", 28.3495231));
            
            TIME.add(new Unit("minute", "min", 60.0));
            TIME.add(new Unit("hour", "hr", 3600.0));
            TIME.add(new Unit("day", "day", 86400));
            TIME.add(new Unit("week", "wk", 604800.0));
            TIME.add(new Unit("month", "mon", 2592000.0)); // assuming 30 days
            TIME.add(new Unit("year", "yr", 31557600.0)); // 365.25 days
            TIME.add(new Unit("decade", "decade", 315576000.0)); 
            TIME.add(new Unit("century", "century", 3155760000.0));            
            TIME.add(new Unit("millenium", "millenium", 31557600000.0));            
           
            LIGHT_INTENSITY.add(new Unit("candlepower", "cp", 0.981));
        }        
        
        /** Helper class to store one metric unit in the MetricUnits class. */
        private static class MetricUnit implements NamedObject
            { 
                /** The name of the unit */
                public String strName;
                /** The unit's symbol */
                public String strSymbol;
                /** The power of 10 this unit represents (e.g. for kilo-, iPower is 3.) */
                public int iPower; 
                

                 /** Get a string representation of the unit (its name)
                 *   @return the unit's name */
                public String getName() { return strName; }

                 /** Set the unit's name
                 *   @param _name the unit's name */
                public void setName(String _name) { strName = _name; }           
                
                /** Construct a MetricUnit. 
                 *  @param strN Name of the unit
                 *  @param strSym Symbol of the unit */
                public MetricUnit(String strN, String strSym, int iPow) { strName = strN; strSymbol = strSym; iPower = iPow; }
                
                /** Get the magnitude expressed as a double.
                 *  @return a double containing the actual magnitude (10^iPower) of this unit. */
                public double getMagnitude() { return Math.pow(10.0, (double)iPower); }
                
                /** Get a string representation of the unit. 
                 @return a string representation of the unit. */
                public String toString() { return strName + "- (" + strSymbol + ") : 10^" + Integer.toString(iPower); }
                
                
            }

        /** Simple class to store and convert between MetricUnits. */
        public abstract static class Metrics
        {            
            /** yotta- (10^24) */
            public static final MetricUnit YOTTA = new MetricUnit("Yotta", "Y", 24);              
            /** zetta- (10^21) */
            public static final MetricUnit ZETTA = new MetricUnit("Zetta", "Z", 21);         
            /** exa- (10^18) */
            public static final MetricUnit EXA = new MetricUnit("Exa", "E", 18); 
            /** peta- (10^15) */
            public static final MetricUnit PETA = new MetricUnit("Peta", "P", 15); 
            /** tera- (10^12) */
            public static final MetricUnit TERA = new MetricUnit("Tera", "T", 12); 
            /** giga- (10^9) */
            public static final MetricUnit GIGA = new MetricUnit("Giga", "G", 9); 
            /** mega- (10^6) */
            public static final MetricUnit MEGA = new MetricUnit("Mega", "M", 6); 
            /** myria- (10^4) - Obsolete, but what's a few bytes for completeness? */
            public static final MetricUnit MYRIA = new MetricUnit("myria", "my", 4); 
            /** kilo- (10^3) */
            public static final MetricUnit KILO = new MetricUnit("kilo", "k", 3); 
            /** hecto- (10^2) */
            public static final MetricUnit HECTO = new MetricUnit("hecto", "h", 2);
            /** deka- (10^1) */
            public static final MetricUnit DEKA = new MetricUnit("deka", "da", 1);
            
            /** normal (10^0) */
            public static final MetricUnit BASE = new MetricUnit(null, null, 0);
            
            /** deci- (10^-1) */
            public static final MetricUnit DECI = new MetricUnit("deci", "d", -1);
            /** centi- (10^-2) */
            public static final MetricUnit CENTI = new MetricUnit("centi", "c", -2);
            /** milli- (10^-3) */
            public static final MetricUnit MILLI = new MetricUnit("milli", "m", -3);
                /** Mu (the symbol for micro) */
                public static final String MU = new String( new java.lang.Character((char)230).toString());
            /** micro- (10^-6) */
            public static final MetricUnit MICRO = new MetricUnit("micro", MU, -6);      
            /** nano- (10^-9) */
            public static final MetricUnit NANO = new MetricUnit("nano", "n", -9);
            /** pico- (10^-12) */
            public static final MetricUnit PICO = new MetricUnit("pico", "p", -12);
            /** femto- (10^-15) */
            public static final MetricUnit FEMTO = new MetricUnit("femto", "f", -15);
            /** atto- (10^-18) */
            public static final MetricUnit ATTO = new MetricUnit("atto", "a", -18);
            /** zepto- (10^-21) */
            public static final MetricUnit ZEPTO = new MetricUnit("zepto", "z", -21);
            /** yocto- (10^-24) */
            public static final MetricUnit YOCTO = new MetricUnit("yocto", "y", -24);

            /** Calculate a metric conversion factor between two MetricUnits.
             @param unit1 Source unit
             *  @param unit2 destination unit
             * @return amount to multiply by */
            public static double getMetricConversion(MetricUnit unit1, MetricUnit unit2)
            {
                return Math.pow(10.0, unit1.iPower - unit2.iPower);
            }
            
            /** Hide the constructor */ private Metrics() { }
        }
        
       /** Get a string representing a height in feet'inches" format. 
        @param iH a height in inches
        @return a height as a Foot'Inches" string 
        */
    public static String getHeightInFeet(int iH) 
    {
        boolean isNegative = (iH < 0);
        iH = Math.abs(iH);
        int iFeet = iH / 12; // Faster than the divide and the modulus        
        return (isNegative ? "-" : "") + (Integer.toString(iFeet) + "\'" + 
                Integer.toString(iH - (iFeet * 12)) + "\""); 
    }
    
    /** Hide the constructor */ private Units() { }
    
}
