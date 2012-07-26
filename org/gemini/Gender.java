package org.gemini;
import java.io.Serializable;
import org.slage.framework.NamedObject;
import org.slage.framework.Tools;

/** This class defines a Gender, which we will consider to be a purely physical thing. It provides static values male and female, as well as 
 'asexual' and 'hermaphroditic' for the less humanlike races and those seeking to play something a little more outside the box. The class also
 provides numerous pronoun types to assist in descriptive text having to do with characters and monsters.  */
 public class Gender implements NamedObject, Serializable, Grantor
 {
     /** The standard masculine gender */
     public static final Gender MALE = 
        new Gender("male", "he", "his", "his", "himself", "him");
     
     /** the standard feminine gender */
     public static final Gender FEMALE =
        new Gender("female", "she", "her", "hers", "herself", "her");
     
     /** Gender-neutrality
     <p>Suggestions for gender-neutral pronouns taken from 
     <a href="http://www.aetherlumina.com/gnp/faq.html#gnp">
     http://www.aetherlumina.com/gnp/faq.html#gnp</a>. */
    public static final Gender ASEXUAL = 
        new Gender("asexual", "sie", "hir", "hirs", "hirself",  "hir");
    
     /** Gender-plurality
     <p>Suggestions for gender-neutral pronouns taken from 
     <a href="http://www.aetherlumina.com/gnp/faq.html#gnp">
     http://www.aetherlumina.com/gnp/faq.html#gnp</a>. */
    public static final Gender HERMAPHRODITIC = 
        new Gender("hermaphroditic", "sie", "hir", "hirs", "hirself",  "hir");    
    
    /** A string storing the actual name of our Gender */
    private String gender;

    /** The object pronoun (ex: 'him') */
    private String objectPronoun;

    /** Possessive form (ex: "his") */
    private String possessive;

    /** Possessive pronoun (ex: "hers") */
    private String possessivePronoun;

    /** Pronoun (ex: "He") */
    private String pronoun;

    /** Reflexive (ex: "himself") */
    private String reflexive;
           
    /** Construct a new Gender 
    @param _gender the name of the gender (ex: "male")
    @param _pronoun the pronoun (ex: "he")
    @param _possessive the possessive form (ex: "her") 
    @param _possessivePronoun the possessive pronoun (ex: "hers")
    @param _reflexive the reflexive form (ex: "herself")
    @param _objectPronoun the object pronoun (ex: "him") */
     public Gender(String _gender, String _pronoun, String _possessive, String _possessivePronoun, 
                                    String _reflexive, String _objectPronoun)
     {
         gender = _gender;
         pronoun = _pronoun;
         possessive = _possessive;
         possessivePronoun = _possessivePronoun;
         reflexive = _reflexive;
         objectPronoun = _objectPronoun;
     }
  
       /** Accessor for the actual Gender string
    @return the gender */
    public String getName() { return gender; }      

    /** Set the gender's name
     @param name new name for the gender */
    public void setName(String name) { gender = name; }       
    
    /** Get the appropriate object pronoun for this Gender
    @param bCaps 'true' if the first letter should be capitalized
    @return a string containing the pronoun. See static version for more details */
    public String getObjectPronoun(boolean bCaps) 
    {  
        return (bCaps) ? Tools.capitalize(objectPronoun) : objectPronoun;
    }

    /** Get the appropriate possessive form for this Gender
    @param bCaps 'true' if the first letter should be capitalized
    @return a string containing the pronoun. See static version for more details */
    public String getPossessive(boolean bCaps) 
    {
        return (bCaps) ? Tools.capitalize(possessive) : possessive;
    }

    /** Get the appropriate possessive pronoun for this Gender
    @param bCaps 'true' if the first letter should be capitalized
    @return a string containing the pronoun. See static version for more details */
    public String getPossessivePronoun(boolean bCaps) 
    {
        return (bCaps) ? Tools.capitalize(possessivePronoun) : possessivePronoun;
    }
    /** Get the appropriate pronoun for this Gender
    @param bCaps 'true' if the first letter should be capitalized
    @return a string containing the pronoun. See static version for more details */
    public String getPronoun(boolean bCaps) 
    {
        return (bCaps) ? Tools.capitalize(pronoun) : pronoun;
    }

    /** Get the appropriate reflexive pronoun for this Gender
    @param bCaps 'true' if the first letter should be capitalized
    @return a string containing the pronoun. See static version for more details */
    public String getReflexivePronoun(boolean bCaps)
    {
        return (bCaps) ? Tools.capitalize(reflexive) : reflexive;
    }

    /** Simplify a sex into Male or Female (for stat tables, etc.)
    *  We'll call everything that's not Female Male.     
    * @return "male" or "female" */
    public String simplify()
    {
        return (getName().equalsIgnoreCase("female")) ? "Female" : "Male";
    }
    
    /** Get a string representation of this Gender
    @return string representation of this gender */
    public String toString() { return Tools.capitalize(getName()); }
          
     /** Test for "Gender Equality" :) 
     * @param o Object to check against
     * @return 'true' if the two Genders match */
    public boolean equals(Object o)
    {                
        if (o instanceof Gender == false) return false;
        Gender g = (Gender)o;
        return g.getName().equalsIgnoreCase(getName());
    }

    
 } // end class