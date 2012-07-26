/*
 * DxxAction.java
 *
 * Created on November 16, 2005, 12:04 PM
 */

package org.gemini.rules.dXX;
import org.gemini.DescribedObject;
import org.slage.framework.NamedObject;
import org.gemini.sources.SourceSectionReference;
import org.gemini.sources.SourceReference;
import org.gemini.sources.SourceURL;
import org.gemini.sources.ObjectFromSource;
import org.slage.framework.Tools;

/**
 * DxxAction defines timed actions in game, such as standard actions and move actions.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxAction implements Comparable, java.io.Serializable
    {
    
        /** SRD source */
        protected static final SourceSectionReference<SourceURL> SRD = 
            new SourceSectionReference<SourceURL>(DxxSystem.SRD35, "Combat I (Basics)");
        
        /** Psionic SRD source */
        protected static final SourceSectionReference<SourceURL> PSISRD = 
            new SourceSectionReference<SourceURL>(DxxSystem.SRD35_PSIONICS, "Psionic Powers Overview");
        
        /** Free action */
        public static final DxxActionType FREE = new DxxActionType("Free", Integer.MAX_VALUE, 0, SRD, 
        ("Free actions consume a very small amount of time and effort. You can perform one or " +
        "more free actions while taking another action normally. However, there are reasonable " +
        "limits on what you can really do for free."));
      
        /** Not an action */
        public static final DxxActionType NOT_ACTION = new DxxActionType("Not an Action", Integer.MAX_VALUE, 0, SRD, 
        ("Some activities are so minor that they are not even considered free actions. They literally don’t " +
        "take any time at all to do and are considered an inherent part of doing something else."));
      
       /** Standard action */
        public static final DxxActionType STANDARD = new DxxActionType("Standard", 1, 4, SRD, 
        ("A standard action allows you to do something, most commonly make an attack or cast a spell."));
     
       /** Full Round Action action */
        public static final DxxActionType FULL = new DxxActionType("Full Round", 1, 6, SRD, 
        ("A full-round action requires an entire round to complete. Thus, it can’t be " +
        "coupled with a standard or a move action, though if it does not involve moving " +
        "any distance, you can take a 5-foot step."));

        /** Move action */
        public static final DxxActionType MOVE = new DxxActionType("Move", 1, 2, SRD,  
        "A move action allows you to move your speed or perform an action that " +
        "takes a similar amount of time. You can take a move action in place of " +
        "a standard action. If you move no actual distance in a round " +
        "(commonly because you have swapped your move for one or more equivalent " +
        "actions), you can take one 5-foot step either before, during, or after the action.");
        
         /** Immediate action (Psionics) */
        public static final DxxActionType IMMEDIATE = new DxxActionType("Immediate", 1, 1, PSISRD,  
        "Much like a swift action, an immediate action consumes a very small amount of time, " +
        "but represents a larger expenditure of effort and energy than a free action. However, " +
        "unlike a swift action, an immediate action can be performed at any time — even if it's " +
        "not your turn. Casting feather fall is an immediate action, since the spell can be cast " +
        "at any time. \n" +
        "Using an immediate action on your turn is the same as using a swift action, and counts as " +
        "your swift action for that turn. You cannot use another immediate action or a swift action " +
        "until after your next turn if you have used an immediate action when it is not currently " +
        "your turn (effectively, using an immediate action before your turn is equivalent to using " +
        "your swift action for the coming turn). You also cannot use an immediate action if you are " +
        "flat-footed.");
        
         /** Swift action (Psionics) */
        public static final DxxActionType SWIFT = new DxxActionType("Swift", 1, 1, PSISRD,  
        "A swift action consumes a very small amount of time, but represents a larger " +
        "expenditure of effort and energy than a free action. You can perform one " +
        "swift action per turn without affecting your ability to perform other actions. " +
        "In that regard, a swift action is like a free action. However, you can perform only " +
	"a single swift action per turn, regardless of what other actions you take. You can take " +
        "a swift action any time you would normally be allowed to take a free action. Swift " +
        "actions usually involve spellcasting or the activation of magic items; many characters " +
        "(especially those who don't cast spells) never have an opportunity to take a swift action.");
        
        /** Construct a new action time 
         *  @param _mins Minutes the action takes */
        public DxxAction(float _mins) { actionMinutes = _mins; }
        
        /** Approximate amount of time (in minutes) this action takes */ 
            private float actionMinutes;
                                        
        /** Get the action's duration in seconds 
         *  @return actionMinutes */ 
            public float getDurationInMinutes() { return actionMinutes; }

        /** Set the action's duration in minutes 
         *  @param _minutes the action's duration in minutes */
            public void setDurationInMinutes(float _minutes) { actionMinutes = _minutes; }
    
   
          /** Compare this action to another action (by action duration)
         *  @param that An action to compare to
         *  @return negative if this is quicker than that, 0 if they are equal, 
            positive if that is slower than this */
        public int compareTo(Object that)
        {
            if (that instanceof DxxAction)
                return 0;

            DxxAction act = (DxxAction)that;

            // sort by how long it takes
            return (int)(actionMinutes - act.actionMinutes);        
        }

         /** Test equality of two sizes
          @param that other size to test
          @return true if this == that */
        public boolean equals(Object that)
        {
             if (that instanceof DxxAction)
                return false;

            DxxAction act = (DxxAction)that;

            return (compareTo(act) == 0);
        }


        /** Get action as a string 
         * @return string representation of the time  */
        public String toString() 
        { 
            if (actionMinutes < 1)
                return Integer.toString( (int) (actionMinutes * 60));
            else
                return Tools.TrimFloat(actionMinutes);
        }

    
    /** Defines an action type, for those actions which are "set in stone". */
    public static class DxxActionType extends DxxAction 
        implements DescribedObject.Mutable, NamedObject, ObjectFromSource.Mutable
    {
        /** Name of the action */ private String name;
        /** Description of the action */ private String description;
         
        /** Amount of these actions that can be done in one round */ 
            private int actionsInRound;
       
        /** Source reference for the object */
        private SourceReference<?> source;

        /** Set the reference to the object's source
         * @param _src new source reference */
        public void setSource(SourceReference<?> _src) { source = _src; }

        /** Accessor for source reference
         *  @return source */
        public SourceReference<?> getSource() { return source; }
               
        /** Get action as a string 
         * @return string representation of the size (its name) */
        public String toString() { return getName(); }
          
        /** Accessor for description
         * @return description */
        public String getDescription() { return description; }

        /** Accessor for name
         * @return name */
        public String getName() { return name; }    

        /** Set the action's description 
         * @param _description new description */
        public void setDescription(String _description) { description = _description; }

        /** Set the action's name 
         * @param _name new name */
        public void setName(String _name) { name = _name; }
        
        /** Creates a new instance of DxxActionType 
        @param _name Name of the action
        @param _inRound Number of times it can be done in a round 
        @param _sec Approximate duration in seconds 
        @param _src Source reference 
        @param _desc Description */
        public DxxActionType(String _name, int _inRound, int _sec, SourceReference _src, String _desc)
        {
            super(_sec / 60.0f);
            setName(_name);
            setDescription(_desc);
            setSource(_src);
            setActionsInRound(_inRound);            
        }

        /** Get the maximum number of times this action type can be done in one round
         *  @return actionsInRound */
        public int getActionsInRound() { return actionsInRound; }

        /** Set the maximum number of times this action type can be done in one round
         *  @param _actions the maximum number of times this action type can be done in one round */
        public void setActionsInRound(int _actions) { actionsInRound = _actions; }
    
         /** Test equality of two sizes
          @param that other size to test
          @return true if this == that */
        public boolean equals(Object that)
        {
             if (that instanceof DxxActionType)
                return false;

            DxxActionType act = (DxxActionType)that;

            return (compareTo(act) == 0) && getName().equalsIgnoreCase(act.getName());
        }
    }
    
}
