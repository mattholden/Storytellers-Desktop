/*
 * DxxSize.java
 *
 * Created on November 11, 2005, 11:45 PM
 */

package org.gemini.rules.dXX;
import org.slage.framework.NamedObject;
import org.gemini.sources.SourceReference;
import org.gemini.sources.SourceURL;
import org.gemini.sources.ObjectFromSource;
import org.gemini.DescribedObject;
import java.util.Comparator;
import java.io.Serializable;
import org.gemini.StringTools;
import org.slage.framework.Tools;

/**
 * Define a Size value for physical entities in dXX games. 
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
  
 * TODO: Gain action (set size, space, reach, modifiers, other values)
 *
 * @author  Jaeden
 */
public class DxxSize 
    implements NamedObject, ObjectFromSource,  DescribedObject, Comparable, org.gemini.Grantor
{
    
    /** name of the size */ private String name;
    /** value to compare sizes */ private int size;
    
    /** "Bigness" value for Large and larger creatures.
      A <code>null</code> bigness is valid for creatures smaller than Large
      and for creatures which are proportionally tall and long. */
    private Boolean bigness = null;
    
    /** Static constant for "Long" Bigness */
    public static final Boolean LONG = new Boolean(false);
    
    /** Static constant for "Tall" Bigness */
    public static final Boolean TALL = new Boolean(true);
    
    /** Attack modifier for size */
    private int attackModifier = 0;
    
    /** Grapple modifier for size */
    private int grappleModifier = 0;
    
    /** Carrying capacity multiplier for size */
    private float carryingCapacityMultiplier = 1.0f;
    
    /** Item HP multiplier for size */
    private float itemHPMultiplier = 1.0f;
    
    /** The horizontal Reach of an average character of this size. */
    private float reach = 5.0f;
    
    /** vertical reach of an average character of this size */
    private float reachVertical = 8.0f;
    
    /** The amount of space a normal creature of this size consumes. */
    private float space = 5.0f;
       
    /** Creates a new instance of DxxSize (Protected - use static constant sizes)
      @param _name  Name of the size 
      @param _size  The comparative size value
      @param _big   The bigness. Use null, LONG or TALL
      @param _atk   Attack modifier
      @param _grp   Grapple modifier
      @param _carry Carrying capacity modifier
      @param _reach Horizontal reach
      @param _vreach Vertical reach
      @param _space Space
      @param _itemHP Item HP modifier
    *
      */
    protected DxxSize( String _name, int _size, Boolean _big, int _atk, int _grp, 
                                    float _carry, float _reach, float _vreach, float _space, float _itemHP)
    {
        setName(_name); 
        size = _size;
        bigness = _big;
        attackModifier = _atk;
        grappleModifier = _grp;
        carryingCapacityMultiplier = _carry;
        reach = _reach;
        reachVertical = _vreach;
        space = _space;
        itemHPMultiplier = _itemHP;
    }
    
        
    /** The source for the sizes (static to save memory) */
    protected static final SourceReference<SourceURL> SRD = 
        new SourceReference<SourceURL>(DxxSystem.SRD35);
        
    /** Accessor for the source reference
     *  @return source reference */
    public SourceReference<SourceURL> getSource() { return SRD; }
      
    /** Accessor for bigness
      @return bigness type */
    public Boolean getBignessType() { return bigness; }
    
    /** Mutator for bigness
     * @param _big bigness type. Use <code>null</code>, TALL or LONG */
    public void setBignessType(Boolean _big) { this.bigness = _big; }
    
    /** Accessor for name
     * @return name */
    public String getName() { return name; }
    
    /** Mutator for name
     * @param _name new name */
    public void setName(String _name) { name = _name; }
     
    /** Accessor for attack modifier
      @return attack modifier */
    public int getAttackModifier() { return attackModifier; }
        
    /** Accessor for Armor Class modifier.
      The value is the same as Attack Modifier, so this method is provided
      merely for readability. 
      
      @return AC modifier */
    public int getACModifier() { return attackModifier; }
        
    /** Accessor for modifier to chance to break items
      The value is the same as Grapple Modifier, so this method is provided
      merely for readability. 
      
     @return modifier to chance to break items */
    public int getBreakChance() { return getGrappleModifier(); }
    
    /** Accessor for grapple modifier
      @return grapple modifier */
    public int getGrappleModifier() { return grappleModifier; }
    
    /** Accessor for carrying capacity multiplier
      @return carrying capacity multiplier */
    public float getCarryingCapacityMultiplier() { return carryingCapacityMultiplier; }
    
    /** Accessor for item HP multiplier
      @return item HP multiplier */
    public float getItemHPMultiplier() { return itemHPMultiplier; }
        
    /** Accessor for horizontal reach
     * @return reach */
    public float getReach() { return reach; }
    
    /** Accessor for vertical reach
     * @return vertical reach */
    public float getVerticalReach() { return reachVertical; }
    
    /** Accessor for space 
      @return space */
    public float getSpace() { return space; }
        
    /** Get a description of this Size
      @return description */
    public String getDescription()
    {
         // special case
    if (name.equalsIgnoreCase("Medium"))
        return "Medium: Medium creatures have no special bonuses or penalties due to their size.";

    String larger = (size - MEDIUM.size > 0) ? "larger" : "smaller";
    
    StringBuffer sb = new StringBuffer();
    sb.append(name);
    sb.append(": ");
    sb.append(name);
    sb.append(" characters ");
    sb.append(StringTools.gainOrTake(getACModifier()));
    sb.append(" a " + StringTools.getModifier(getACModifier()) + " size ");
    sb.append(StringTools.bonusOrPenalty(getACModifier()) + " to Armor Class, a "); 
    sb.append(StringTools.getModifier(getAttackModifier()) + " size ");
    sb.append(StringTools.bonusOrPenalty(getAttackModifier()) + " to attack rolls, and a ");
    sb.append(StringTools.getModifier(getGrappleModifier() * -1) + " size ");
    sb.append(StringTools.bonusOrPenalty(getGrappleModifier() * -1) + " to Hide checks. He or she uses ");
    sb.append(larger + " weapons than humans use, and his/her lifting and carrying limits are ");
    sb.append(Tools.TrimFloat(getCarryingCapacityMultiplier() * 100) + "% of those of a Medium character.");
    return sb.toString();
    }
    
    
     /** Compare this size to another size
     *  @param that A size to compare to
     *  @return negative if this is smaller than that, 0 if they are equal, 
        positive if that is larger than this */
    public int compareTo(Object that)
    {
        if (that instanceof DxxSize)
            return 0;
        
        DxxSize size2 = (DxxSize)that;
        return this.size - size2.size;
    }
            
     /** Test equality of two sizes
      @param that other size to test
      @return true if this == that */
    public boolean equals(Object that)
    {
        return (compareTo(that) == 0);
    }
    
    /** Get size as a string 
     * @return string representation of the size (its name) */
    public String toString() { return getName(); }
    
    
    /** Fine */
    public static final DxxSize FINE = new DxxSize("Fine", 0, null, 8, -16, 0.125f, 0, 0.5f, 0.5f, 0.0625f);
    /** Diminutive */
    public static final DxxSize DIMINUTIVE = new DxxSize("Diminutive", 1, null, 4, -12, 0.25f, 0f, 1.0f, 1.0f, 0.125f);
    /** Tiny */
    public static final DxxSize TINY = new DxxSize("Tiny", 2, null, 2, -8, 0.5f, 0f, 2.0f, 2.5f, 0.25f);
    /** Small */
    public static final DxxSize SMALL = new DxxSize("Small", 3, null, 1, -4, 0.75f, 5.0f, 4.0f, 5.0f, 0.5f);
    /** Medium */
    public static final DxxSize MEDIUM = new DxxSize("Medium", 4, null, 0, 0, 1.0f, 5.0f, 8.0f, 5.0f, 1.0f);
    /** Large (Long) */
    public static final DxxSize LARGE = new DxxSize("Large", 5, LONG, -1, 4, 2.0f, 5.0f, 16.0f, 10.0f, 2.0f);
    /** Large (Tall) */
    public static final DxxSize LARGE_TALL = new DxxSize("Large", 5, TALL, -1, 4, 2.0f, 10.0f, 16.0f, 10.0f, 2.0f);
    /** Huge (Long) */
    public static final DxxSize HUGE= new DxxSize("Huge", 6, LONG, -2, 8, 4.0f, 10.0f, 32.0f, 15.0f, 4.0f);
    /** Huge (Tall) */
    public static final DxxSize HUGE_TALL= new DxxSize("Huge", 6, TALL, -2, 8, 4.0f, 15.0f, 32.0f, 15.0f, 4.0f);
    /** Gargantuan (Tall) */
    public static final DxxSize GARGANTUAN_TALL = new DxxSize("Gargantuan", 7, TALL, -4, 12, 8.0f, 15.0f, 64.0f, 20.0f, 8.0f);
    /** Gargantuan (Long) */
    public static final DxxSize GARGANTUAN  = new DxxSize("Gargantuan", 7, LONG, -4, 12, 8.0f, 20.0f, 64.0f, 20.0f, 8.0f);
    /** Colossal (Long) */
    public static final DxxSize COLOSSAL = new DxxSize("Colossal", 8, LONG, -8, 16, 16.0f, 20.0f, 128.0f, 30.0f, 16.0f);
    /** Colossal (Tall) */
    public static final DxxSize COLOSSAL_TALL = new DxxSize("Colossal", 8, TALL,  -8, 16, 16.0f, 30.0f, 128.0f, 30.0f, 16.0f);
        
    
    
    
}
