/*
 * Command.java
 *
 * Created on September 27, 2005, 10:24 AM
 */

package org.slage.command;

import java.util.Iterator;
import java.util.Vector;

import org.slage.SlageObject;
import org.slage.SlagePlayer;
import org.slage.handlers.Handler;
import org.slage.parser.Description;
import org.slage.framework.FrameworkCommand;

/**
 * Encapsulates a Command for the parser to execute, either from parsed input,
 * click events, scheduled events, or other means.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class Command  extends FrameworkCommand<SlageObject>
  // by default we set initiator to the player, but this shouldn't be a SlagePlayer template...
  // we can change the initiator to be an NPC or even an object.
{
	/** The input source for this Command */       
	private Commander commander;

	/**
     * The Target Object for this command. For the command PUT BALL IN BOX, this
     * should be the SlageObject representing the BALL. */
	private SlageObject targetObject;

	/**
     * The Indirect Object for this command. For the command PUT BALL IN BOX, this
     * should be the SlageObject representing the BOX.     */
	private SlageObject indirectObject;
	
	/**
	 * Creates a new instance of Command
	 * 
	 * @param strInput the original string
	 * @param aCommander The Commander that issued this Command	 */
	public Command(String strInput, Commander aCommander) 
        {
            super(strInput, aCommander.getGame().getPlayer());
           setTimestamp(aCommander.getGame().getScheduler().getGameRunTime());
           
	   commander = aCommander;	   
	}

	/**
	 * Creates a new instance of Command
	 * 
	 * @param aVerb the verb
	 * @param objTarget target object
	 * @param objIndirect indirect object
	 * @param aCommander The Commander that issued this Command
	 */
	public Command(String aVerb, SlageObject objTarget, SlageObject objIndirect, Commander aCommander) 
        {
                super(buildCommandString(aVerb, objTarget, objIndirect), aVerb, aCommander.getGame().getPlayer());
		setTimestamp(aCommander.getGame().getScheduler().getGameRunTime());
                
                targetObject = objTarget;
		indirectObject = objIndirect;	
		commander = aCommander;		
	}

        /** Helper method to build a command string from objects
        @param v Verb
        @param objTarget target object
        @param objIndirect indirect object
        @return command string */
        protected static String buildCommandString(String v, SlageObject objTarget, SlageObject objIndirect)
        {
            	String strTarget = (objTarget == null) ? "" : objTarget.getName().toUpperCase();
		String strInd = (objIndirect == null) ? "" : objIndirect.getName().toUpperCase();
		String strCommand = v.toUpperCase() + " " + strTarget + " " + strInd;	
                return strCommand;
        }
        
        
	/**
	 * Accessor for the Commander that generated this Command
	 * 
	 * @return commander
	 */
	public Commander getCommander() {
		return commander;
	}

	/**
	 * Getter for property indirectObject.
	 * 
	 * @return Value of property indirectObject.
	 */
	public org.slage.SlageObject getIndirectObject() {
		return indirectObject;
	}

	/**
	 * Setter for property indirectObject.
	 * 
	 * @param anIndirectObject New value of property indirectObject.
	 */
	public void setIndirectObject(SlageObject anIndirectObject) {
		this.indirectObject = anIndirectObject;
	}

	/**
	 * Getter for property targetObject.
	 * 
	 * @return Value of property targetObject.
	 */
	public org.slage.SlageObject getTargetObject() {
		return targetObject;
	}

	/**
	 * Setter for property targetObject.
	 * 
	 * @param aTargetObject New value of property targetObject.
	 */
	public void setTargetObject(SlageObject aTargetObject) {
		this.targetObject = aTargetObject;
	}

	/** Execute this Command on the target object. */
	public void fire() 
        {
		commander.getGame().getCommandHistory().add(this);

		// route orphaned commands to the game
		if (targetObject == null)
			targetObject = commander.getGame();

		java.util.ArrayList handlers = targetObject.getHandlers(getVerb());
		for (int i = 0; i < handlers.size(); i++) {
			Handler H = (Handler) handlers.get(i);
			H.execute(this);
		}
	}




	/**
	 * Build a command from parser input
	 * 
	 * @param typedInput the input as it was typed.
	 * @param parsed the parsed vector of words
	 * @param com the Commander (in this case most likely the parser)
	 */
	public Command(String typedInput, Vector parsed, Commander com) 
        {
            super(typedInput, com.getGame().getPlayer());
            setTimestamp(commander.getGame().getScheduler().getGameRunTime());
            
		commander = com;
		Iterator iter = parsed.iterator();
		int number = 0;
		while (iter.hasNext()) {
			Object o = iter.next();
			if (number == 0) {
				setVerb((String) o);
			} else if (o instanceof Description) {
				Description d = (Description) o;
				if (targetObject == null && d.getOwner() instanceof SlageObject) {
					targetObject = (SlageObject)d.getOwner();
				} else if (indirectObject == null && d.getOwner() instanceof SlageObject) {
					indirectObject = (SlageObject)d.getOwner();
				}
				// If there are more than two nouns, we just append the nouns to
				// the command string, but they are not directly accessible.
			}
			number++;
		}		
	}
}

/*******************************************************************************
 * BEGIN LICENSE BLOCK **** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is Slage.
 * 
 * The Initial Developer of the Original Code is The SQ7.org project. Portions
 * created by the Initial Developer are Copyright (C) 2005 the Initial
 * Developer. All Rights Reserved.
 * 
 * Contributor(s): Matt Holden (Matt@sq7.org) Travis Savo (Travis@sq7.org)
 * Robert Wenner (Robert@sq7.org) Jared Cope (Jared@sq7.org) Colin Davis
 * (Colin@sq7.org)
 * 
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or the
 * GNU Lesser General Public License Version 2.1 or later (the "LGPL"), in which
 * case the provisions of the GPL or the LGPL are applicable instead of those
 * above. If you wish to allow use of your version of this file only under the
 * terms of either the GPL or the LGPL, and not to allow others to use your
 * version of this file under the terms of the MPL, indicate your decision by
 * deleting the provisions above and replace them with the notice and other
 * provisions required by the GPL or the LGPL. If you do not delete the
 * provisions above, a recipient may use your version of this file under the
 * terms of any one of the MPL, the GPL or the LGPL.
 * 
 ******************************************************************************/
