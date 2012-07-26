
/*
 * ObjectGroup.java
 *
 * Created on November 14, 2005, 9:14 AM
 */

package org.gemini;
import java.util.ArrayList;
import org.gemini.sources.SourceReference;

/** ObjectGroup is a templated abstract base class that can be used to define groups
    of objects which can contain subgroups, such as spell groups (domains, etc.) and 
    weapon groups (blades, subgroup swords?). */
public abstract class ObjectGroup<Type extends GeminiObject> extends GeminiObject
{              
    /** The parent ObjectGroup for sub-groups */
    private ObjectGroup<Type> parent;
    
    /** Construct an ObjectGroup
     *  @param _name name of the ObjectGroup.
     *  @param _src Source reference for the object group
     *  @param _parent Parent ObjectGroup (leave null for top-level groups) */     
    public ObjectGroup(String _name, SourceReference _src, ObjectGroup<Type> _parent)
    {
        super(_name, _src);        
        this.parent = _parent;
        
        if (parent != null) 
            parent.getSubGroups().add(this);
    }
    
    
    /** List of potential subtypes (such as domains) */
    private ArrayList<ObjectGroup<Type>> listSubGroups = new ArrayList<ObjectGroup<Type>>();
        
    /** Accessor for the parent ObjectGroup 
     *  @return parent ObjectGroup */
    public ObjectGroup<Type> getParent() { return parent; }
        
    /** Accessor for the list of subgroups
     @return subtype list */
    public ArrayList<ObjectGroup<Type>> getSubGroups() { return listSubGroups; }   
    
    /** Determine if this ObjectGroup is a sub-group
     @return true if we're a sub-group */
    public boolean isSubGroup() { return (parent != null); }


}
    
