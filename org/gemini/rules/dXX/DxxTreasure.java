
package org.gemini.rules.dXX;
import java.io.Serializable;

/** DxxTreasure stores what types of items can be found on a particular monster.
 It is broken up into three categories, Goods, Coins and Items, all of which are represented
 as public members of this class. Whenever possible, use the static TreasureTypes provided
 to fill in these values, so as to save memory. 
 
 *
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>.  
 
 @author Jaeden
  TODO: Table rolls */
public class DxxTreasure implements Serializable
{
    /** Stores a particular treasure type. */
    public static class TreasureType implements Serializable
    {
        /** Chance to get treasure of this type */
        private float chance = 1.0f; // 100%
        /** Multiplier for how much of this type of treasure to get */
        private float value = 1.0f;   // standard. (Double standard, this would be 2.0f, etc.)
        /** Used for "fractional coins" */
        private float coinFraction = 1.0f; 
                
        /** Create "STANDARD" treasure */
        public TreasureType() { }
        
        /** Create a TreasureType with the given value multiplier
         *@param _value value multiplier */
        public TreasureType(float _value) { this.value = _value; }        
        
        /** Create a TreasureType with the given value multiplier and find chance
         *@param _value value multiplier 
         *@param _chance find chance multiplier */
        public TreasureType(float _value, float _chance) 
        { 
            value = _value; 
            chance = _chance; 
        }
        
        /** Getter for treasure chance.
         * @return Value of property chance.         */
        public float getChance() {   return chance;         }
        
        /** Setter for treasure chance.
         * @param _chance New value of property chance. */
        public void setChance(float _chance) { chance = _chance;      }
        
        /** Getter for treasure value multiplier.
     * @return treasure value multiplier       */
        public float getValue() {   return value;         }
        
        /** Setter for treasure value multiplier.
    * @param _value New treasure value multiplier.         */
        public void setValue(float _value) {   value = _value;        }
        
        /** Getter for fractional coins.
     * @return fractional coins.         */
        public float getCoinFraction() {     return coinFraction;         }
        
        /**  Setter for fractional coins.
    * @param _coinFraction fractional coins.         */
        public void setCoinFraction(float _coinFraction) {
            coinFraction = _coinFraction;
        }
        
    }
    
    /** Use this constant TreasureType to specify standard treasure for a treasure type. */
    public static final TreasureType STANDARD = new TreasureType(1.0f);
    /** Use this constant TreasureType to specify double treasure for a treasure type. */
    public static final TreasureType DOUBLE = new TreasureType(2.0f);
    /** Use this constant TreasureType to specify triple treasure for a treasure type. */
    public static final TreasureType TRIPLE = new TreasureType(3.0f);
    /** Use this constant TreasureType to specify quadruple treasure for a treasure type. */
    public static final TreasureType QUADRUPLE = new TreasureType(4.0f);
        
    
    /** The Coins treasure type for the monster containing this Treasure object */
    public TreasureType coins = new TreasureType(); // standard
    /** The Goods treasure type for the monster containing this Treasure object */
    public TreasureType goods = new TreasureType(); // standard
    /** The Items treasure type for the monster containing this Treasure object */
    public TreasureType items = new TreasureType(); // standard
    
    /** Construct a DxxTreasure object for "Standard" treasure */
    public DxxTreasure() { }
    
    /** Construct a DxxTreasure object for non-standard treasure. 
     *@param _coins TreasureType for coins
     **@param _goods TreasureType for goods
     **@param _items TreasureType for items */
    public DxxTreasure(TreasureType _coins, TreasureType _goods, TreasureType _items)
    {
        this.coins = _coins;
        this.goods = _goods;
        this.items = _items;
    }
    
}

