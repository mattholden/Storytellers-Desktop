/*
 * dxxSkillNode.java
 *
 * Created on November 11, 2005, 10:10 PM
 */

package org.gemini.rules.dXX;
import org.gemini.Feature;
import org.gemini.FeatureNode;

/**
 * Defines a FeatureNode for tracking dXX Skill ranks.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxSkillNode<Type extends DxxSkill> extends FeatureNode<Type>
        {
            /** Construct a skill node
             @param _feature Feature to track */
            public DxxSkillNode(Type _feature) { super(_feature); }
                                        
            /** Ranks in the skill. A <code>float</code> is used here because
             *  cross-class skills can be taken by the half-rank. */
            private float ranks = 0.0f;                  
            
            /** Accessor for ranks
             *  @return ranks */
            public float getRanks() { return ranks; }
            
            /** Mutator for ranks
             *  @param _ranks ranks */
            public void setRanks(float _ranks) { ranks = _ranks; }
            
            /** Incrementor for ranks
             *  @param _ranks ranks to add */
            public void incRanks(float _ranks) { ranks += _ranks; }
            
            /** Test for equality
             *  @param that a DxxSkillNode to test 
             *  @return 'true' if this == that */
            public boolean equals(Object that)
            {
                if (this.getClass().equals(that.getClass()) == false)
                    return false;
                
                return (super.equals(that) && 
                        ranks == ((DxxSkillNode)that).ranks);
            }
            
            /** Get the skill and its ranks as a String
             *  @return string representation */
            public String toString() { 
                return super.toString() + " (" + Float.toString(ranks) + ")"; 
            }
            
        }