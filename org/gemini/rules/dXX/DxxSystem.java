/*
 * DxxSystem.java
 *
 * Created on November 11, 2005, 11:46 PM
 */

package org.gemini.rules.dXX;
import org.gemini.sources.SourceURL;
import org.gemini.sources.SourceReference;
import org.gemini.GeminiObjectVisitor;
import org.gemini.GamingSystem;
import org.gemini.GeminiObject;
import org.slage.framework.Attribute;
import org.gemini.Progression;

/**
 * Gaming System information for dXX as a core system. 
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxSystem extends GamingSystem
{
    
    /** Source for the official 3.5 SRD */
    public static final SourceURL SRD35;
    /** Source for the official 3.5 SRD (Divine supplement) */
    public static final SourceURL SRD35_DIVINE;
    /** Source for the official 3.5 SRD (Epic supplement) */
    public static final SourceURL SRD35_EPIC;
    /** Source for the official 3.5 SRD (Psionics supplement) */
    public static final SourceURL SRD35_PSIONICS;                
    
    /** Maximum number of levels in the game */
    protected static int maxLevel = 30;  // assume epic level is here.
    
    /** Base Attack Bonus Progression - good */
    public static final Progression<Integer> BASEATTACK_GOOD;
    /** Base Attack Bonus Progression - average */
    public static final Progression<Integer> BASEATTACK_AVERAGE;
    /** Base Attack Bonus Progression - poor */
    public static final Progression<Integer> BASEATTACK_POOR;
    /** Saving Throw Progression - good */
    public static final Progression<Integer> SAVINGTHROW_GOOD;
    /** Saving Throw Progression - average
      NOTE: Some dXX games (fantasy for one) don't use Average saves. */       
    public static final Progression<Integer> SAVINGTHROW_AVERAGE;
    /** Saving Throw Progression - poor */
    public static final Progression<Integer> SAVINGTHROW_POOR;
    
    /** Initialize sources and other static fields */
    static
    {
        SRD35 = new SourceURL(    
        "d20 System Revised (v.3.5) System Reference Document", 
        "http://www.wizards.com/default.asp?x=d20/article/srd35", 
        new String[] { "Jonathan Tweet", "Monte Cook", "Skip Williams", 
                       "Rich Baker", "Andy Collins", "David Noonan", "Rich Redman",
                       "Bruce R. Cordell", "John D. Rateliff", "Thomas Reid", 
                       "James Wyatt", 
                       "based on original material by E. Gary Gygax and Dave Arneson" },
                       "Wizards of the Coast", 2000, 2003, "Open Game License");
                       
        SRD35.setGamingSystem("dXX System Revised (v.3.5)");
        
        SRD35_DIVINE = new SourceURL(SRD35);
        SRD35_EPIC = new SourceURL(SRD35);
        SRD35_PSIONICS = new SourceURL(SRD35);        
        SRD35_DIVINE.setName(SRD35.getName() + " (Divine Supplement)");
        SRD35_EPIC.setName(SRD35.getName() + " (Epic Level Supplement)");
        SRD35_PSIONICS.setName(SRD35.getName() + " (Psionics Supplement)");
        
        /* Progressions */
        Integer[] BABgood = new Integer[maxLevel];
        Integer[] BABavg = new Integer[maxLevel];
        Integer[] BABpoor  = new Integer[maxLevel];
        Integer[] SaveGood  = new Integer[maxLevel];
        Integer[] SaveAvg = new Integer[maxLevel];
        Integer[] SavePoor = new Integer[maxLevel];
        
        for (int i = 0; i < maxLevel; i++)
        {
            BABgood[i] = new Integer(goodBAB(i+1));
            BABavg[i] = new Integer(avgBAB(i+1));
            BABpoor[i] = new Integer(poorBAB(i+1));
            SaveGood[i] = new Integer(goodSave(i+1));
            SaveAvg[i] = new Integer(avgSave(i+1));
            SavePoor[i] = new Integer(poorSave(i+1));
        }
      
        BASEATTACK_GOOD = new Progression<Integer>(BABgood);
        BASEATTACK_AVERAGE = new Progression<Integer>(BABavg);
        BASEATTACK_POOR = new Progression<Integer>(BABpoor);
        SAVINGTHROW_GOOD = new Progression<Integer>(SaveGood);
        SAVINGTHROW_AVERAGE = new Progression<Integer>(SaveAvg);
        SAVINGTHROW_POOR = new Progression<Integer>(SavePoor);
        
    }
    
    
    /** Creates a new instance of DxxSystem */
    public DxxSystem() 
    {
        super("dXX System Revised (v.3.5)", new SourceReference<SourceURL>(SRD35));
        
    }
     
    /** Accessor for max level
      @return max level */
    public static int getMaxLevel() { return maxLevel; }
        
    /** Add common attributes to Creatures and Characters of a given
      System. It's important to make sure you call super.addSystemAttributes()
      when you override this method. 
     
      @param obj Object to add attributes to */
    public static void addSystemAttributes(GeminiObject obj)
    {
        /* Refer to the superclass.. Since we can't use <code>super.</code>, 
      it's a good thing we're smart and know who our base class is! */   
            GamingSystem.addSystemAttributes(obj);
                         
             obj.setAttribute(new Attribute<Integer>("Base Attack Bonus", new Integer(0)));
             obj.setAttribute(new Attribute<Integer>("Armor Class", new Integer(0)));
            
            // size-related
             obj.setAttribute(new Attribute<Float>("Reach", new Float(DxxSize.MEDIUM.getReach())));
             obj.setAttribute(new Attribute<Float>("Vertical Reach", new Float(DxxSize.MEDIUM.getVerticalReach())));
             obj.setAttribute(new Attribute<Float>("Space", new Float(DxxSize.MEDIUM.getSpace())));
            
             obj.setAttribute(new Attribute<Integer>("Level Adjustment", null));
        
         
    }

    
    
    
     
    /** Calculate good BAB Progression<Integer>
      @param i level
      @return BAB */
    protected static int goodBAB(int i) 
    {        
        return (i > 20) ? (goodBAB(20) + ((i - 21) >> 1) + 1) : i;
    }
    /** Calculate average BAB Progression<Integer>
      @param i level
      @return BAB */
    protected static int avgBAB(int i) 
    {        
        return (i > 20) ? (avgBAB(20) + ((i - 21) >> 1) + 1) : ((i * 3) >> 2);
    }
    /** Calculate poor BAB Progression<Integer>
      @param i level
      @return BAB */
    protected static int poorBAB(int i) 
    {        
        return (i > 20) ? (poorBAB(20) + ((i - 21) >> 1) + 1) : (i >> 1);
    }
    /** Calculate good save Progression<Integer>
      @param i level
      @return save */
    protected static int goodSave(int i) 
    {        
        return (i > 20) ? (goodSave(20) + ((i - 20) >> 1))  : (2 + (i >> 1));
    }
    /** Calculate poor save Progression<Integer>
      @param i level
      @return save */
    protected static int poorSave(int i) 
    {        
        return (i > 20) ? (poorSave(20) + ((i - 20) >> 1))  : (i  / 3);
    }
    /** Calculate average save Progression<Integer> 
        (6/5 (at Lvl 1) + 2/5 of lvl)
      @param i level
      @return save */
    protected static int avgSave(int i) 
    {        
        return (i > 20) ? (avgSave(20) + ((i - 20) >> 1))  : (6 + (i * 2)) / 5;	
    }
            
   /** Accept a Visitor
      @param aVisitor visitor to accept */        
    public void accept(GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }  
}
