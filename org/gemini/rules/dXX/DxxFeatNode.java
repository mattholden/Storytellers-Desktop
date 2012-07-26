/*
 * DxxFeatNode.java
 *
 * Created on November 11, 2005, 11:05 PM
 */

package org.gemini.rules.dXX;
import org.gemini.FeatureNode;

/**
 * Defines a FeatureNode for tracking dXX Feat selections.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxFeatNode<Type extends DxxFeat> extends FeatureNode<Type>
{
            /** Construct a feat node
             @param _feature Feature to track */
            public DxxFeatNode(Type _feature) { super(_feature); }
            
            /** Number of times this feat has been taken (for stacking feats) */
            private int timesTaken = 1; 
            
            /** Access times taken
         @return timesTaken */
            public int getTimesTaken() { return timesTaken; }
            
            /** Mutator for times taken
         @param _times times taken */
            public void setTimesTaken(int _times) { timesTaken = _times; }
            
                   /** Test for equality
             *  @param that a DxxFeatNode to test 
             *  @return 'true' if this == that */
            public boolean equals(Object that)
            {
                if (this.getClass().equals(that.getClass()) == false)
                    return false;
                
                return (super.equals(that) && 
                        timesTaken == ((DxxFeatNode)that).timesTaken);
            }
            
            /** Get the feat and its times taken as a String
             *  @return string representation */
            public String toString() 
            {
                    return super.toString() + 
                        ((timesTaken != 0 && timesTaken != 1) 
                            ? " (" + Integer.toString(timesTaken) + ")"
                            : "");                        
            }
            
}