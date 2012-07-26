/*
 * GeminiObjectVisitor.java
 *
 * Created on November 9, 2005, 2:00 PM
 */

package org.gemini;
import org.slage.framework.SlageFrameworkObjectVisitor;

/**
 * Visitor for double dispatch on Gemini-specific SlageFrameworkObjects
 *
 * @author  Jaeden
 */
public interface GeminiObjectVisitor extends SlageFrameworkObjectVisitor
{
    /** Accept a GeminiObject
      @param anObject object to accept */        
    public void accept(GeminiObject anObject); 
    
    /** Accept a CampaignSetting
      @param anObject object to accept */
    public void accept(CampaignSetting anObject);
 
     /** Accept a Feature 
      @param anObject object to accept */
    public void accept(Feature anObject);
    
    /** Accept a GamingSystem 
      @param anObject object to accept */
    public void accept(GamingSystem anObject);
    
    /** Accept a GamingSystemMod
      @param anObject object to accept */
    public void accept(GamingSystemMod anObject);
           
    /** Accept a GeminiCharacter
      @param anObject object to accept */
    public void accept(GeminiCharacter anObject);
    
     /** Accept a GeminiCreature
      @param anObject object to accept */
    public void accept(GeminiCreature anObject);
   
    
     /** Accept an ObjectGroup
      @param anObject object to accept */
    public void accept(ObjectGroup anObject);
}