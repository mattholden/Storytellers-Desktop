/*
 * AbstractSlageObjectVisitor.java
 *
 * Created on November 9, 2005, 1:25 PM
 */

package org.slage;
import org.slage.framework.SlageFrameworkObject;

/**
 * Abstract implementation of SlageObjectViaitor as a convenience for
 * folks who only want to implement certain methods 
 *
 * @author  Jaeden
 */
public abstract class AbstractSlageObjectVisitor implements SlageObjectVisitor
{
    /** Accept a SlageFrameworkObject
      @param anObject object to accept */
    public void accept(org.slage.framework.SlageFrameworkObject anObject) { //
    }
    
    /** Accept a SlageCharacter
      @param aCharacter object to accept */    
    public void accept(SlageCharacter aCharacter) { accept((SlageObject) aCharacter); }
        
    /** Accept a Room
      @param aRoom object to accept */    
    public void accept(Room aRoom) { accept((SlageObject) aRoom); }
       
   /** Accept a SlageObject
      @param anObject object to accept */    
     public void accept(SlageObject anObject) { accept( (SlageFrameworkObject) anObject); }
    
    /** Accept a TextObject
      @param aTextObject object to accept */        
    public void accept(TextObject aTextObject) { accept ((SlageObject) aTextObject); }
        
    /** Accept a SlageGame
      @param aGame object to accept */    
    public void accept(SlageGame aGame) { accept((SlageObject) aGame); }
    
    /** Accept an Act
      @param anAct object to accept */        
    public void accept(Act anAct) { accept((SlageObject) anAct); }
    
    /** Accept a Scene
      @param aScene object to accept */        
    public void accept(Scene aScene) { accept((SlageObject) aScene); }
    
    /** Accept a SlagePlayer
      @param aPlayer object to accept */            
    public void accept(SlagePlayer aPlayer) { accept((SlageCharacter) aPlayer); }
    
    /** Creates a new instance of AbstractSlageObjectVisitor */
    public AbstractSlageObjectVisitor() { /* empty on purpose */ }
    
}
