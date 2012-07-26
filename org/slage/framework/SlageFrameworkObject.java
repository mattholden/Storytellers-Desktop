package org.slage.framework;

import java.util.HashMap;
import java.util.ArrayList;
import org.apache.commons.logging.LogFactory;

/*
 * @author Matt Holden (<a href="mailto:Matt@SQ7.org">Matt Holden</a>)
 * 
 * Defines core object functionality for Slage framework game objects.
 */
public class SlageFrameworkObject
    <HandlerType extends FrameworkHandler>
    implements NamedObject, Comparable, java.io.Serializable
{

	/** Logger instance */
	private static final org.apache.commons.logging.Log LOG = LogFactory.getLog(SlageFrameworkObject.class);

	/** Name of the object */
	private String strName;

        /** List of Handlers to respond to */
	protected ArrayList<HandlerType> listHandlers = new ArrayList<HandlerType>();

	
	/** Map of object attributes (Attributes) */
	protected HashMap<String, Attribute<?>> mapAttributes = new HashMap<String, Attribute<?>>();

	/**
	 * Construct a framework object
	 * 
	 * @param aName framework object name
	 */
	public SlageFrameworkObject(String aName) {
		this.strName = aName;
	}

        	/**
	 * Add a Handler
	 * 
	 * @param handler new Handler to add
	 */
	public void addHandler(HandlerType handler) {
		listHandlers.add(handler);
	}
        
        
	/**
	 * Handle an incoming verb usage.
	 * 
	 * @param strVerb the verb being used.
	 */
	public ArrayList<HandlerType>getHandlers(String strVerb) {
	
		ArrayList<HandlerType> listResults = new ArrayList<HandlerType>();
                
                if (strVerb == null)
			listResults.addAll(listHandlers);
                else
        		for (HandlerType H : listHandlers) {
                		if (H.hasVerb(strVerb))
                        		listResults.add(H);
                        }
                        
		return listResults;
	}

	
	/**
	 * Remove a Handler
	 * 
	 * @param hand handler to remove
	 */
	public void removeHandler(HandlerType hand) {
		listHandlers.remove(hand);
	}

	/** Clear Handler list */
	public void clearHandlers() {
		listHandlers.clear();
	}

	/**
	 * Get handler count
	 * 
	 * @return listHandlers.size()
	 */
	public int getHandlerCount() {
		return listHandlers.size();
	}

	/**
	 * Accessor for name
	 * 
	 * @return strName
	 */
	public String getName() {
		return strName;
	}

	/**
	 * Modifier for name
	 * 
	 * @param aName new name
	 */
	public void setName(String aName) {
		this.strName = aName;
	}

	/**
	 * Get name
	 * 
	 * @return name
	 */
	public String toString() {
		return getName();
	}

	/** Clear the map of Attributes */
	public void clearAttributes() {
		mapAttributes.clear();
	}

	/**
	 * Get the current number of Attributes
	 * 
	 * @return count
	 */
	public int getAttributeCount() {
		return mapAttributes.size();
	}

	/**
	 * Get a list of all the attributes in an object. This is backed by the map,
	 * so you CAN modify the attributes this way. Use this ONLY for editor GUI
	 * purposes.
	 * 
	 * TODO: Find a cleaner way to do this without the need for this method? It's
	 * used in org.slage.editor.StatesPanel.java line 79.
	 * 
	 * @return collection of all attributes
	 */
	public java.util.Collection<Attribute<?>> getAttributeList() {
		return mapAttributes.values();
	}

	/**
	 * Remove the given Attribute. Will remove ALL matches of this Attribute name.
	 * 
	 * @param strAttribute Attribute to remove
	 */
	public void removeAttribute(String strAttribute) {
		mapAttributes.remove(strAttribute);
	}

	/**
	 * Get the given Attribute.
	 * 
	 * @param strAttribute Attribute to find
	 * @return the Attribute object
	 * @throws NotFoundException if the attribute was not found
	 */
	public Attribute getAttribute(String strAttribute) throws NotFoundException {
		Attribute a = mapAttributes.get(strAttribute);
		if (a == null)
			throw new NotFoundException("attribute", strAttribute);
		return a;
	}

	/**
	 * Get the given Attribute's value.
	 * 
	 * @param strAttribute Attribute to find
	 * @return the Attribute's value object
	 * @throws NotFoundException if the attribute was not found
	 */
	public Object getAttributeValue(String strAttribute) throws NotFoundException {
		Attribute a = mapAttributes.get(strAttribute);
		if (a == null)
			throw new NotFoundException("attribute", strAttribute);
		return a.getValue();
	}

	/**
	 * Get an Attribute's value (Shortcut for integers)
	 * 
	 * @param strAttribute Attribute to get
	 * @return the value
	 * @throws NotFoundException if the attribute was not found
	 */
	public int getAttributeAsInt(String strAttribute) throws NotFoundException {
		Object o = getAttribute(strAttribute).getValue();
		return ((Integer) o).intValue();
	}

	/**
	 * Get an Attribute's value (Shortcut for floats)
	 * 
	 * @param strAttribute Attribute to get
	 * @return the value
	 * @throws NotFoundException if the attribute was not found
	 */
	public float getAttributeAsFloat(String strAttribute) throws NotFoundException {
		return ((Float) getAttribute(strAttribute).getValue()).floatValue();
	}

	/**
	 * Get an Attribute's value (Shortcut for booleans)
	 * 
	 * @param strAttribute Attribute to get
	 * @return the value
	 * @throws NotFoundException if the attribute was not found
	 */
	public boolean getAttributeAsBoolean(String strAttribute) throws NotFoundException {
		return ((Boolean) getAttribute(strAttribute).getValue()).booleanValue();
	}

	/**
	 * Get an Attribute's value (Shortcut for long)
	 * 
	 * @param strAttribute Attribute to get
	 * @return the value
	 * @throws NotFoundException if the attribute was not found
	 */
	public long getAttributeAsLong(String strAttribute) throws NotFoundException {
		return ((Long) getAttribute(strAttribute).getValue()).longValue();
	}

	/**
	 * Get an Attribute's value (Shortcut for Strings)
	 * 
	 * @param strAttribute Attribute to get
	 * @return the value
	 * @throws NotFoundException if the attribute was not found
	 */
	public String getAttributeAsString(String strAttribute) throws NotFoundException {
		return getAttribute(strAttribute).getValue().toString();
	}

	/**
	 * Set the given Attribute's value. Will add the Attribute if it doesn't find
	 * it.
	 * 
	 * @param attrib Attribute object to set. If another attribute exists in the
	 *        map with the same name as attrib's name, that value will be
	 *        overwritten with attrib's value (including type!)
	 */
	public void setAttribute(Attribute attrib) {
		mapAttributes.put(attrib.getName(), attrib);
	}

	/**
	 * Set/create an Attribute with a given name and value. Will add the Attribute
	 * if it doesn't find it.
	 * 
	 * @param aName Attribute object to set. If another attribute exists in the
	 *        map with the same name as attrib's name, that value will be
	 *        overwritten with attrib's value (including type!)
	 * @param obj Attribute value to set
	 */
	public void setAttribute(String aName, Object obj) {
		mapAttributes.put(aName, new Attribute<Object>(aName, obj));
	}

	/**
	 * See if the objects are the same (by name)
	 * 
	 * @param o an object to compare to
	 * @return 'true' if they are the same object (by name)
	 */
	public boolean equals(Object o) {
		if (o instanceof NamedObject == false)
			return false;
		return ((NamedObject) o).getName().equals(strName);
	}

        
        /** Accept a visitor for double dispatch 
        @param aVisitor a visitor to check the types */        
	public void accept(SlageFrameworkObjectVisitor aVisitor) {
		aVisitor.accept(this);
	}
        
        /** Alphabetize objects 
         *  @param other object to compare against
         *  @return Negative if this should come before that, 0 if they match, 
                    or positive if that should come before this. */
        public int compareTo(Object other)
        {
            if (other instanceof NamedObject == false)
                return 0;
            
            // do it this way to avoid upper case being higher than lower case
               String NO1 = getName().toUpperCase();
               String NO2 = ((NamedObject)other).getName().toUpperCase();
                   
               return NO1.compareTo(NO2);            
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
 * The Original Code is the Slage Framework.
 * 
 * The Initial Developer of the Original Code is the Skage project.
 * 
 * Portions created by the Initial Developer are Copyright (C) 2005 the Initial
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
