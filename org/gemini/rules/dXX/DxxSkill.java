/*
 * DxxSkill.java
 *
 * Created on November 11, 2005, 9:12 PM
 */

package org.gemini.rules.dXX;
import org.gemini.Feature;
import org.gemini.FeatureNode;
import org.slage.framework.Attribute;
import org.gemini.sources.SourceReference;

/**
 * Define a Skill in dXX. 

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxSkill extends Feature<DxxSkillNode<DxxSkill>>
{
     
    /** Creates a new instance of DxxSkill 
        @param _name Name of the skill
        @param _src SourceReference for the source of this Skill */
    public DxxSkill(String _name, SourceReference<?> _src)
    {
        super(_name, _src);
                
        // Point to one of the 6 ability scores, or "null" for skills with no key ability        
        setAttribute(new Attribute<DxxAbilityScore>("Key Ability", null));
        
        // Use a float to handle things with half or double armor check penalty
        setAttribute(new Attribute<Float>("Armor Check Penalty", 0.0f));
        
        // Ways to use this skill 
        setAttribute(new Attribute<Boolean>("May Take 10", new Boolean(true)));
        setAttribute(new Attribute<Boolean>("May Take 20", new Boolean(true)));
        setAttribute(new Attribute<Boolean>("May Aid Another", new Boolean(true)));
        setAttribute(new Attribute<Boolean>("Retry", new Boolean(true)));
        setAttribute(new Attribute<Boolean>("Use Untrained", new Boolean(true)));
        
        // TODO: Take Once / Multiple / Unique Specialization
        // TODO: Synergy
    }

    /** Generate a DxxSkillNode for this Skill 
     *  @return a new DxxSkillNode */
    public DxxSkillNode<DxxSkill> getNode() { 
        return new DxxSkillNode<DxxSkill>(this); 
    }
    
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }      
}
    
    
