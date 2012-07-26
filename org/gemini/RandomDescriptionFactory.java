/*
 * RandomDescriptionFactory.java
 *
 * Created on November 11, 2005, 4:51 AM
 */

package org.gemini;

/**
 * Abstract class with static methods for randomly generating descriptive values
 * for monsters and characters. These methods are pushed here to avoid encapsulating them
 * in GeminiCharacter where they truly do not belong. 
 * 
 * @see RandomGenerator 
 *
 * @author Jaeden
 */
public abstract class RandomDescriptionFactory 
{
    /** RandomGenerators archived for efficiency */
    
    /** Generator for eye color */ 
    private static RandomGenerator<String> eyeColor = new RandomGenerator<String>();
    /** Generator for hair color */ 
    private static RandomGenerator<String> hairColor = new RandomGenerator<String>();
    /** Generator for hair style */ 
    private static RandomGenerator<String> hairStyle = new RandomGenerator<String>();
    /** Generator for facial hair style */ 
    private static RandomGenerator<String> facialHair = new RandomGenerator<String>();
    /** Generator for skin tone */ 
    private static RandomGenerator<String> skinTone = new RandomGenerator<String>();
    
    /** Generate a random eye color. 
     *  @return random eye color as a String */
    public static String eyeColor() { return eyeColor.generate(); }
    
     /** Generate a random hair color. 
     *  @return random hair color as a String */
    public static String hairColor() { return hairColor.generate(); } 
    
     /** Generate a random hair style. 
     *  @return random hair style as a String */
    public static String hairStyle() { return hairStyle.generate(); } 
    
     /** Generate a random facial hair style
     *  @return random facial hair style as a String */
    public static String facialHair() { return facialHair.generate(); } 
    
     /** Generate a random skin tone. 
     *  @return random skin tone as a String */
    public static String skinTone() { return skinTone.generate(); } 
    
    /** Generate a random gender 
     *  @return a random Gender */
    public static Gender gender() 
    { 
        RandomGenerator<Gender> rand = new RandomGenerator<Gender>();
        rand.addPossibility(Gender.MALE, 70);
        rand.addPossibility(Gender.FEMALE, 30);
        return rand.generate();
    }
    
    /** Generate a random handedness
     *
     <b>A note on the randomness:<br></b>
     According to <a href="http://www.sciam.com/askexpert_question.cfm?articleID=00063C8D-61EF-1C72-9EB7809EC588F2D7">
     Scientific American</a>, 5 to 30 percent of people are lefties. We'll say 20, because it's nice odds. Also, bear in mind that
     our rolling system will omit any possibility of ambidexterity, because some gaming systems require that skill points or feat
     slots be spent to acquire this trait. 
  
     *  @return a random Handedness */
    public static Handedness handedness() 
    { 
        RandomGenerator<Handedness> rand = new RandomGenerator<Handedness>();
        rand.addPossibility(Handedness.RIGHT, 80);
        rand.addPossibility(Handedness.LEFT, 20);
        return rand.generate();
    }
        
    
    /** Build values for eye color */
    private static void buildEyeColor()
    {
        RandomGenerator<String> rand = eyeColor;
        rand.addPossibility("black", 5);
        rand.addPossibility("blue", 20);
        rand.addPossibility("brown", 20);
        rand.addPossibility("green", 15);
        rand.addPossibility("hazel", 15);
        rand.addPossibility("silver", 2);
        rand.addPossibility("steel gray", 5);
        rand.addPossibility("tiger's eye", 5);
        rand.addPossibility("yellow", 2);        
        
        // 0 probability deliberate
        rand.addPossibility("orange", 0);
        rand.addPossibility("red", 0);
        
    }
    
    /** Build values for hair color */
    private static void buildHairColor()
    {
        RandomGenerator<String> rand = hairColor;
        rand.addPossibility("auburn", 10);        
        rand.addPossibility("black", 10);
        rand.addPossibility("blonde", 15);
        rand.addPossibility("brown", 10);
        rand.addPossibility("chestnut", 12);
        rand.addPossibility("dirty blonde", 14);
        rand.addPossibility("mousey brown", 9);        
        rand.addPossibility("Scottish red", 6);
        rand.addPossibility("strawberry blonde", 5);
    }
    
    /** Build values for hair style */
    private static void buildHairStyle()
    {
        RandomGenerator<String> rand = hairStyle;
        rand.addPossibility("bald", 5);
        rand.addPossibility("balding", 3);
        rand.addPossibility("beehive", 1);        
        rand.addPossibility("braided", 5);
        rand.addPossibility("bowl cut", 2);
        rand.addPossibility("buns", 5);        
        rand.addPossibility("buzz cut", 1);
        rand.addPossibility("cornrows", 1);
        rand.addPossibility("curly", 15);
        rand.addPossibility("dreadlocks", 1);
        rand.addPossibility("feathered", 2);        
        rand.addPossibility("flat top", 1);              
        rand.addPossibility("long", 20);        
        rand.addPossibility("pageboy", 5);
        rand.addPossibility("permed", 1);       
        rand.addPossibility("pigtails, long", 10);
        rand.addPossibility("pigtails, short", 10);
        rand.addPossibility("ponytail, long", 15);
        rand.addPossibility("ponytail, short", 15);
        rand.addPossibility("rat tail, long", 5);
        rand.addPossibility("rat tail, short", 5);
        rand.addPossibility("short", 20);
        rand.addPossibility("shoulder-length", 20);
        rand.addPossibility("topknot", 5);
        
        // 0-probability deliberate
        rand.addPossibility("mohawk", 0);      
        
    }
    
    /** Build values for skin tone */
    private static void buildSkinTone()
    {
        RandomGenerator<String> rand = skinTone;
        rand.addPossibility("bronze", 10);
        rand.addPossibility("copper", 10);
        rand.addPossibility("dark", 15);
        rand.addPossibility("gray", 5);
        rand.addPossibility("olive", 10);        
        rand.addPossibility("pale", 20);
        rand.addPossibility("tan", 10);
        rand.addPossibility("yellow", 10);
        
        // yes, 0-probability colors are deliberate here
        rand.addPossibility("white", 0);
        rand.addPossibility("red", 0);        
        rand.addPossibility("brown", 0);
        rand.addPossibility("black", 0);
        rand.addPossibility("green", 0);        
    }
    
    /** Build values for facial hair */
    private static void buildFacialHair()
    {
        RandomGenerator<String> rand = facialHair;
        rand.addPossibility("[none]", 60);
        rand.addPossibility("beard, long", 3);
        rand.addPossibility("beard, short", 3);
        rand.addPossibility("five o'clock shadow", 1);
        rand.addPossibility("goatee, long", 2);
        rand.addPossibility("goatee, short", 2);
        rand.addPossibility("mustache, full", 2);
        rand.addPossibility("mustache, long", 1);
        rand.addPossibility("mustache, thin", 3);
        rand.addPossibility("stubble", 5);
        rand.addPossibility("vandyke", 5);            
    }
    
    /** Statically load all of our generators */
    static
    {
        buildEyeColor();
        buildHairColor();
        buildHairStyle();
        buildFacialHair();
        buildSkinTone();
    }
    
    /** Creates a new instance of RandomDescriptionFactory */
    private RandomDescriptionFactory() { /** It's a secret to everybody. **/ }    
}
