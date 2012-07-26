/*
 * DxxFantasyAlignment.java
 *
 * Created on November 11, 2005, 5:21 AM
 */

package org.gemini.rules.dXXfantasy;
import org.slage.framework.NamedObject;
import org.slage.framework.Tools;
import java.io.Serializable;
import org.gemini.RandomGenerator;
import org.gemini.Dice;
import org.gemini.sources.SourceReference;
import org.gemini.sources.SourceSectionReference;
import org.gemini.sources.SourceURL;
import org.gemini.sources.ObjectFromSource;
import org.gemini.DescribedObject;
import org.gemini.rules.dXX.DxxSystem;

/**
 * Defines an Alignment value for dXX Fantasy characters. 
 
 *
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author Jaeden
 */
public class DxxFantasyAlignment 
    implements NamedObject, Serializable, ObjectFromSource, DescribedObject
{    
 
    /** The "order" portion of the alignment (Law/Chaos Axis) */ 
    private String order;
    
    /** The "morality" portion of the alignment (Good/Evil Axis) */ 
    private String morality;

    /** The "description" of the alignment, usually a lengthy string which details
     *  how a character of this alignment might act. */
    private String description;
    
    /** The "example" of the alignment, usually a one or two word string which
     *  epitomizes how people might view a character of this alignment. */
    private String example;
        
    /** The source for all alignments (saves memory) */
    protected static final SourceSectionReference<SourceURL> SRD = 
        new SourceSectionReference<SourceURL>(DxxSystem.SRD35, "Alignment and Description");
    
    /** Accessor for the source reference
     *  @return source reference */
    public SourceSectionReference<SourceURL> getSource() { return SRD; }
    
    
    /** Creates a new instance of DxxFantasyAlignment 
        @param _order Order value (Law/Chaos Axis)     
        @param _moral Morality value (Good/Evil Axis)
        @param _example The "example" of the alignment, usually a one or two word string which
                        epitomizes how people might view a character of this alignment
        @param _desc    The "description" of the alignment, usually a lengthy string which details
                        how a character of this alignment might act
     */
    public DxxFantasyAlignment(String _order, String _moral, String _example, String _desc) 
    { 
        order = _order;
        morality = _moral;
        example = _example;
        description = _desc;
    } 
            
    /** Getter for property order.
     * @return Value of property order.      */
    public String getOrder() { return order;     }
    
    /** Setter for property order.
     * @param _order New value of property order.      */
    public void setOrder(String _order) { this.order = _order;     }
    
    /** Getter for property morality.
     * @return Value of property morality.      */
    public String getMorality() {  return morality;     }
    
    /** Setter for property morality.
     * @param _morality New value of property morality.    */
    public void setMorality(String _morality) { this.morality = _morality;  }

    /** Return the full name of the alignment as a String 
     *  @return name of the alignment */
    public String getName() 
    {
        if (order.equalsIgnoreCase("neutral") && morality.equalsIgnoreCase("neutral"))
            return "Neutral";
        else
            return Tools.capitalize(order) + " " + Tools.capitalize(morality);
    }
    
    /** Set the name of the alignment as a string. 
     *  Since we don't store the name explicitly, we'll try to parse here.
     *  Really, we wouldn't provide this method if not for the NamedObject interface. 
     *  This method will set the alignment to null values if the method fails, 
     *  as throwing an exception changes the signature of the method and invalidates
     *  the interface implementation.
     *
     *  @param _name Name of the alignment, e.g. "Lawful Good"         */
    public void setName(String _name) 
    {
        String name = _name.toLowerCase();
        order = null;
        morality = null;
        
        if (name.startsWith("lawful")) order = "Lawful";
        if (name.startsWith("chaotic")) order = "Chaotic";
        if (name.startsWith("neutral")) order = "Neutral";
        if (name.endsWith("good")) morality = "Good";
        if (name.endsWith("evil")) morality = "Evil";
        if (name.endsWith("neutral")) morality = "Neutral";                    
    }
    
    /** String representation of the alignment
     *  @return getName() */
    public String toString() { return getName(); }
    
    /** Getter for the "description" of the alignment, usually a lengthy string which details
     *  how a character of this alignment might act.
     * @return Value of property description.      */
    public String getDescription() { return description;     }
    
    /** Setter for the "description" of the alignment, usually a lengthy string which details
     *  how a character of this alignment might act
     * @param _description New value of property description.      */
    public void setDescription(String _description) {
        this.description = _description;
    }
    
    /** Getter for the "example" of the alignment, usually a one or two word string which
        epitomizes how people might view a character of this alignment
     
     @return Value of property example.      */
    public String getExample() {
        return example;
    }
    
    /** Setter for the "example" of the alignment, usually a one or two word string which
        epitomizes how people might view a character of this alignment
     
     @param _example New value of property example.      */
    public void setExample(String _example) {
        this.example = example;
    }
    
    
    
    /** Test the equality of two Alignments 
     *  @param that Alignment to compare against 
        @return true if this == that */
    public boolean equals(Object that)
    {
        if (that instanceof DxxFantasyAlignment == false) 
            return false;
        
        DxxFantasyAlignment That = (DxxFantasyAlignment)that;
        return (this.order.equalsIgnoreCase(That.order) &&
            this.morality.equalsIgnoreCase(That.morality));        
    }  
    
    /** The Lawful Good alignment */
    public static final DxxFantasyAlignment LAWFUL_GOOD =
        new DxxFantasyAlignment("Lawful", "Good", "Crusader",
        ("A lawful good character acts as a good person is expected or required to act. " +
        "She combines a commitment to oppose evil with the discipline to fight relentlessly. " +
        "She tells the truth, keeps her word, helps those in need, and speaks out against injustice. " +
        "A lawful good character hates to see the guilty go unpunished. " +
        "Lawful good is the best alignment you can be because it combines honor and compassion."));
    
    /** The Lawful Neutral alignment */
    public static final DxxFantasyAlignment LAWFUL_NEUTRAL =
        new DxxFantasyAlignment("Lawful", "Neutral", "Judge",
        ("A lawful neutral character acts as law, tradition, or a personal code directs her. " +
        "Order and organization are paramount to her. " +
        "She may believe in personal order and live by a code or standard, " +
        "or she may believe in order for all and favor a strong, organized government. " +
        "Lawful neutral is the best alignment you can be because it means you are reliable and " +
        "honorable without being a zealot."));
    
    /** The Lawful Evil alignment */
      public static final DxxFantasyAlignment LAWFUL_EVIL =
        new DxxFantasyAlignment("Lawful", "Evil", "Dominator",
        ("A lawful evil villain methodically takes what he wants within the limits of his code of conduct " +
        "without regard for whom it hurts. He cares about tradition, loyalty, and order but not about freedom, dignity, or life. " +
        "He plays by the rules but without mercy or compassion. " +
        "He is comfortable in a hierarchy and would like to rule, but is willing to serve. " +
        "He condemns others not according to their actions but according to race, religion, homeland, or social rank. " +
        "He is loath to break laws or promises. " +
        "This reluctance comes partly from his nature and partly because he depends on order to protect himself from " +
        "those who oppose him on moral grounds. " +
        "Some lawful evil villains have particular taboos, such as not killing in cold blood (but having underlings do it) " +
        "or not letting children come to harm (if it can be helped). " +
        "They imagine that these compunctions put them above unprincipled villains. " +
        "Some lawful evil people and creatures commit themselves to evil with a zeal like that of a crusader " +
        "committed to good. Beyond being willing to hurt others for their own ends, " +
        "they take pleasure in spreading evil as an end unto itself. " +
        "They may also see doing evil as part of a duty to an evil deity or master. " +
        "Lawful evil is sometimes called “diabolical,” because devils are the epitome of lawful evil. " +
        "Lawful evil is the most dangerous alignment because it represents methodical, intentional, and frequently successful evil."));
      
     /** The Neutral Good alignment */
      public static final DxxFantasyAlignment NEUTRAL_GOOD =
        new DxxFantasyAlignment("Neutral", "Good", "Benefactor",
        ("A neutral good character does the best that a good person can do. " +
        "He is devoted to helping others. He works with kings and magistrates but does not feel beholden to them. " +
        "Neutral good is the best alignment you can be because it means doing what is good without bias for or against order."));
    
      /** The Neutral alignment */
      public static final DxxFantasyAlignment NEUTRAL =
        new DxxFantasyAlignment("Neutral", "Neutral", "Undecided",
         ("A neutral character does what seems to be a good idea. " +
           "She doesn’t feel strongly one way or the other when it comes to good vs. evil or law vs. chaos. " +
           "Most neutral characters exhibit a lack of conviction or bias rather than a commitment to neutrality. " +
           "Such a character thinks of good as better than evil—after all, she would rather have good neighbors " +
           "and rulers than evil ones. Still, she’s not personally committed to upholding good in any abstract or universal way. " +
           "Some neutral characters, on the other hand, commit themselves philosophically to neutrality. " +
           "They see good, evil, law, and chaos as prejudices and dangerous extremes. " +
           "They advocate the middle way of neutrality as the best, most balanced road in the long run. " +
           "Neutral is the best alignment you can be because it means you act naturally, without prejudice or compulsion."));
      
      /** The Neutral Evil alignment */
      public static final DxxFantasyAlignment NEUTRAL_EVIL =
        new DxxFantasyAlignment("Neutral", "Evil", "Malefactor",
        ("A neutral evil villain does whatever she can get away with. She is out for herself, pure and simple. " +
        "She sheds no tears for those she kills, whether for profit, sport, or convenience. " +
        "She has no love of order and holds no illusion that following laws, traditions, or codes " +
        "would make her any better or more noble. On the other hand, she doesn’t have the restless nature or love of " +
        "conflict that a chaotic evil villain has. " +
        "Some neutral evil villains hold up evil as an ideal, committing evil for its own sake. " +
        "Most often, such villains are devoted to evil deities or secret societies. " +
        "Neutral evil is the most dangerous alignment because it represents pure evil without honor and without variation."));
      
      /** The Chaotic Good alignment */
      public static final DxxFantasyAlignment CHAOTIC_GOOD =
        new DxxFantasyAlignment("Chaotic", "Good", "Rebel",
        ("A chaotic good character acts as his conscience directs him with little regard for what others expect of him. " +
        "He makes his own way, but he’s kind and benevolent. " +
        "He believes in goodness and right but has little use for laws and regulations. " +
        "He hates it when people try to intimidate others and tell them what to do. " +
        "He follows his own moral compass, which, although good, may not agree with that of society. " +           
        "Chaotic good is the best alignment you can be because it combines a good heart with a free spirit."));

      /** The Chaotic Neutral Alignment */
      public static final DxxFantasyAlignment CHAOTIC_NEUTRAL =
        new DxxFantasyAlignment("Chaotic", "Neutral", "Free Spirit",
        ("A chaotic neutral character follows his whims. " +
        "He is an individualist first and last. He values his own liberty but doesn’t strive to protect others’ freedom. " +
        "He avoids authority, resents restrictions, and challenges traditions. " +
        "A chaotic neutral character does not intentionally disrupt organizations as part of a campaign of anarchy. " +
        "To do so, he would have to be motivated either by good (and a desire to liberate others) " +
        "or evil (and a desire to make those different from himself suffer). " +
        "A chaotic neutral character may be unpredictable, but his behavior is not totally random. " +
        "He is not as likely to jump off a bridge as to cross it. " +
        "Chaotic neutral is the best alignment you can be because it represents true freedom from both " +
        "society’s restrictions and a do-gooder’s zeal."));
 
      /** The Chaotic Evil Alignment */
      public static final DxxFantasyAlignment CHAOTIC_EVIL =
        new DxxFantasyAlignment("Chaotic", "Evil", "Destroyer",
        ("A chaotic evil character does whatever his greed, hatred, and lust for destruction drive him to do. " +
        "He is hot-tempered, vicious, arbitrarily violent, and unpredictable. " +
        "If he is simply out for whatever he can get, he is ruthless and brutal. " +
        "If he is committed to the spread of evil and chaos, he is even worse. " +
        "Thankfully, his plans are haphazard, and any groups he joins or forms are poorly organized. " +
        "Typically, chaotic evil people can be made to work together only by force, " +
        "and their leader lasts only as long as he can thwart attempts to topple or assassinate him. " +
        "Chaotic evil is sometimes called “demonic” because demons are the epitome of chaotic evil. " +
        "Chaotic evil is the most dangerous alignment because it represents the destruction not only of " +
        "beauty and life but also of the order on which beauty and life depend."));     
}
