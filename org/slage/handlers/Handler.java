package org.slage.handlers;

import org.slage.Slage;
import org.slage.SlageGame;
import org.slage.command.Command;
import org.slage.framework.FrameworkHandler;
import org.slage.framework.LinkedObject;
import org.slage.framework.SlageFrameworkObject;
import org.slage.framework.VerbSynonymList;
import org.slage.ui.InputAgent;

/**
 * 
 * The base class that all Slage handlers are derived from. At this level, a
 * handler has an object that it is handling the event for, possibly a target
 * object that will be effected by the event and a list of verbs that will
 * trigger the event.
 * <p>
 * If not explicitly told otherwise (with the setTarget() method), the target
 * object and the object that the handler exists for are the same.
 * <p>
 * An example of when they may differ is the scenario of a <i>death handler</i>
 * where <i>Roger</i> will die if he steps in the pool of <i>lava</i>. In this
 * case the lava would be the object that the handler has been setup for and the
 * target object would be set to Roger.
 */
public abstract class Handler<O extends SlageFrameworkObject,
                              T extends SlageFrameworkObject,
                              I extends SlageFrameworkObject>
        extends FrameworkHandler implements LinkedObject<T> {

    /** A reference to the object we are handling the events for */
    private O object;

    /**
     * A reference to the "target" object, if any. This allows us to tell one
     * handler to affect another object. All Handlers should interact with the
     * Target object. By default, the Target object is the Parent object. Change
     * this with setTarget().
     */
    private T targetObject = null;

    /**
     * The "Indirect Object" - If the handler responds to "put ball in basket",
     * the basket is the indirect object.
     */
    private I indirectObj = null;

    /**
     * This string will be the Target we look for when linkObjects() is called.
     * We use this because the Target we set may be an Object that is defined
     * further down in the XML, and thus wouldn't exist when this Handler is
     * created
     */
    protected String strTargetPending = null;

    /**
     * This string will be the Indirect Object we look for when linkObjects() is
     * called. We use this because the Indirect Object we set may be an Object
     * that is defined further down in the XML, and thus wouldn't exist when
     * this Handler is created
     */
    protected String strIndirectPending = null;

    /**
     * If 'true', the indirect object in the Command must match the indirect
     * object in the handler in order for the handler to fire.
     */
    protected boolean bIndirectMustMatch = false;

    /** The amount of points you get for firing this handler */
    private int iScore = 0;

    /**
     * If 'false', this handler should NOT be displayed when a SlageObject calls
     * getVocabulary(), in preparation for popup menus
     */
    private boolean bVisibleToVocabulary = true;

    /**
     * Construct a new handler for the provided <i>object</i>.
     * 
     * @param anObject
     *            the game object to handle events for
     */
    public Handler(O anObject) {
        this.object = anObject;
        targetObject = (T) anObject;
    }

    /**
     * Some handlers just don't have targets, no matter how hard you try
     */
    public Handler() {
        object = null;
        targetObject = null;
    }

    /**
     * Adds the supplied <i>verb</i> to the list of verbs that will trigger
     * this handler to fire, as well as any synonyms the game knows about
     * 
     * @param verb
     *            verb to add
     * @param game
     *            Game to read verbs from
     */
    public void addVerbWithSynonyms(String verb, SlageGame game) {

        addVerb(verb);
        VerbSynonymList VSL = game.getVerbSynonyms(verb);
        if (VSL == null)
            return;

        listVerbs.addAll(VSL.getSynonyms());
    }

    /**
     * Adds the supplied <i>verb</i> to the list of verbs that will trigger
     * this handler to fire, as well as any synonyms the game knows about
     * 
     * @param verb verb to add
     */
    public void addVerbWithSynonyms(String verb) {
        addVerbWithSynonyms(verb, Slage.getCurrentGame());

    }

    /**
     * Returns the game object that this handler has been setup for.
     * 
     * @return the game object that this handler has been setup for
     */
    public T getOwner() {
        return targetObject;
    }

    /**
     * Returns the game object that is the target when this handler fires.
     * 
     * @return the game object that is the target when this handler fires
     */
    public T getTarget() {
        return targetObject;
    }

    /**
     * Sets the object for this handler to the supplied <i>object</i>.
     * 
     * @param anObject new object
     */
    public void setObject(O anObject) {
        this.object = anObject;
    }

    /**
     * Sets the target object for this handler to the supplied <i>targetObject</i>.
     * 
     * @param anTargetObject new target
     */
    public void setTarget(T anTargetObject) {
        this.targetObject = anTargetObject;
    }

    /**
     * Returns the points gained for firing this handler.
     * 
     * @return the points gained for firing this handler
     */
    public int getScore() {
        return iScore;
    }

    /**
     * Sets the number of points gained for firing this handler.
     * 
     * @param score the number of points earned for firing this handler
     */
    public void setScore(int score) {
        this.iScore = score;
    }

    /**
     * Sets the indirect object of this handler to <i>indirectObj</i>.
     * 
     * @param anIndirectObj
     *            the object to set for the indirect object
     */
    public void setIndirectObj(I anIndirectObj) {
        this.indirectObj = anIndirectObj;
    }

    /**
     * Sets the owner object of this handler to <i>obj</i>.
     * 
     * @param obj
     *            the object to set for the owner object
     * 
     */
    public void setOwner(T obj) {
        // TODO Auto-generated method stub
        targetObject = obj;
    }

    /**
     * Returns the indirect object of this handler.
     * 
     * @return the indirect object of this handler
     */
    public I getIndirectObj() {
        return indirectObj;
    }

    /**
     * Checks if there is a pending target for this handler.
     * 
     * @return true if there is a pending target, false otherwise
     */
    public boolean hasPendingTarget() {
        return (strTargetPending != null);
    }

    /**
     * Checks if there is a pending indirect object for this handler.
     * 
     * @return true if there is a pending indirect object, false otherwise
     */
    public boolean hasPendingIndirect() {
        return (strIndirectPending != null);
    }

    /**
     * Links the pending Target and Indirect Object for this handler.
     * 
     * When an object is read from XML, we can't simply rely on the
     * target/indirect object existing. For example, if the first object in the
     * XML file has a handler whose target is the second object defined in the
     * XML, and the first object's handler tries to set the target object, it
     * won't find it because it doesn't exist yet.
     * 
     * The work around for this is to store the names of the objects in strings,
     * and then right before the handler fires (or more likely, as the first
     * line in fire()), we call linkObjects. This function will at that time set
     * our target and indirect object references to point to real objects,
     * nulling out the strings so that we know this work is done.
     * 
     * This is now called in fireHandler(), and is now protected, as it is not
     * needed externally any longer.
     * 
     */
    protected void linkObjects() {
        SlageGame game = Slage.getCurrentGame();

        if (game == null)
            return;

        if (strTargetPending != null) {
            setTarget((T) game.searchForObject(strTargetPending));
        }
        if (strIndirectPending != null) {
            setIndirectObj((I) game.searchForObject(strIndirectPending));
        }
        strTargetPending = null;
        strIndirectPending = null;

    }

    /**
     * Performs all maintenance issues associated with the firing of a Handler.
     * The command handler should call this method rather than fire() itself.
     */
    public void execute() {
        // 1. Link objects from XML
        linkObjects();

        // 2. Check conditionals
        if (check() == false)
            return;

        // 3. Fire the handler
        fire();

        // 4. Assign points for firing the handler
        SlageGame game = Slage.getCurrentGame();
        if (game != null && game.getPlayer() != null)
            game.getPlayer().incScore(getScore());
    }

    /**
     * Performs all maintenance issues associated with the firing of a Handler.
     * The command handler should call this method rather than fire() itself.
     * 
     * This alternate version of fireHandler takes a Command object. It will
     * store this internally in the handler, then fire the handler using the
     * zero-parameter fireHandler(). When the call to fireHandler() is finished,
     * the internal command object is nulled so as to prevent confusion with old
     * commands.
     * 
     * In the meantime, handlers that need to reference the Command that fired
     * them can do so using the getCommand() protected method.
     */
    public void execute(Command theCommand) {
        this.command = theCommand;
        if (theCommand == null)
            return;

        // 1. Link objects from XML
        linkObjects();

        // 2. Check indirect object
        if (matchIndirects() == false)
            return;

        // 3. Check conditionals
        if (check() == false)
            return;

        // 4. Fire the handler
        fire();

        // 5. Assign points for firing the handler
        SlageGame game = Slage.getCurrentGame();
        if (game != null && game.getPlayer() != null)
            game.getPlayer().incScore(getScore());

        // 6. clean up command
        this.command = null;
    }

    /**
     * Matches indirect objects
     * 
     * @return 'false' if we shouldn't run the handler
     */
    protected boolean matchIndirects() {
        // if we don't have to check, always pass
        if (this.bIndirectMustMatch == false)
            return true;

        // if there's no indirect to check, we can't match
        if (command == null)
            return false;

        // both are null: true
        if (command.getIndirectObject() == null && getIndirectObj() == null)
            return true;

        // one is null: false
        if (command.getIndirectObject() == null || getIndirectObj() == null)
            return false;

        // neither are null: compare
        return command.getIndirectObject().equals(getIndirectObj());
    }

    /** Command object that fired this handler */
    private Command command;

    /**
     * Retrieve the Command object that fired this handler
     * 
     * @return command
     */
    protected final Command getCommand() {
        return command;
    }

    /**
     * Getter for property bVisibleToVocabulary.
     * 
     * @return Value of property bVisibleToVocabulary.
     */
    public boolean isVisibleToVocabulary() {
        return bVisibleToVocabulary;
    }

    /**
     * Setter for property bVisibleToVocabulary.
     * 
     * @param bVisible New value of property bVisibleToVocabulary.
     */
    public void setVisibleToVocabulary(boolean bVisible) {
        this.bVisibleToVocabulary = bVisible;
    }

    /**
     * Check if the handler's only verbs are internals.
     * 
     * @return false if the handler has any "external" verbs
     */
    public boolean hasNoExternalVerbs() {
        for (int i = 0; i < listVerbs.size(); i++) {
            String sVerb = listVerbs.get(i);
            if (!sVerb.startsWith(InputAgent.INTERNAL_VERB_PREFIX))
                return false; // found an external verb!
        }
        return true;
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
