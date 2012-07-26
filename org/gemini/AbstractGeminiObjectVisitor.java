/*
 * AbstractGeminiObjectVisitor.java
 *
 * Created on November 9, 2005, 2:08 PM
 */

package org.gemini;
import org.slage.framework.SlageFrameworkObject;

/**
 * Abstract implementation of GeminiObjectVisitor as a convenience for
 * folks who only want to implement certain methods 
 *
 * @author  Jaeden
 */
public abstract class AbstractGeminiObjectVisitor implements GeminiObjectVisitor
{    
       /** Accept a SlageFrameworkObject
      @param anObject object to accept */ 
    public void accept(org.slage.framework.SlageFrameworkObject anObject) { }
        
    /** Accept a GeminiObject
      @param anObject object to accept */
    public void accept(GeminiObject anObject) { accept( (SlageFrameworkObject) anObject); }

    
    
     /** Accept a CampaignSetting
      @param anObject object to accept */
    public void accept(CampaignSetting anObject)  { accept ( (GeminiObject) anObject); }
    
    /** Accept a Feature
      @param anObject object to accept */
    public void accept(Feature anObject) { accept ( (GeminiObject) anObject); }    
    
    /** Accept a GamingSystem
      @param anObject object to accept */
    public void accept(GamingSystem anObject) { accept ( (GeminiObject) anObject); }
       
    /** Accept a GamingSystemMod
      @param anObject object to accept */
    public void accept(GamingSystemMod anObject) { accept ( (GeminiObject) anObject); }    
    
    /** Accept a GeminiCharacter
     * @param anObject object to accept */
    public void accept(GeminiCharacter anObject) { accept ( (GeminiObject) anObject); }       
     
    /** Accept a GeminiCreature
      @param anObject object to accept */
    public void accept(GeminiCreature anObject) { accept ( (GeminiObject) anObject); }
    
     /** Accept an ObjectGroup
      @param anObject object to accept */
    public void accept(ObjectGroup anObject)  { accept ( (GeminiObject) anObject); }
    
     
    /** Creates a new instance of AbstractGeminiObjectVisitor */
    protected AbstractGeminiObjectVisitor() { /* empty on purpose */ }    

}
