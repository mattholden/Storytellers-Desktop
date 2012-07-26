/*
 * Specialized.java
 *
 * Created on November 13, 2005, 2:48 AM
 */

package org.gemini;
import org.slage.framework.NamedObject;
import org.slage.framework.LinkedObject;

/**
 * Provides an interface for Levels, Nodes, and other objects which must store the
 * specialization of a Feature or other GeminiObject type.
 *
 * @author Jaeden
 */
public abstract class Specialized<Type extends GeminiObject> 
	implements java.io.Serializable, LinkedObject<Type>
{
    /** Logger instance */
    protected static final org.apache.commons.logging.Log LOG = 
        org.apache.commons.logging.LogFactory.getLog(Specialized.class);

    /** The object that this node links to */
    protected Type object;

    /** The type of the specialization object as a fully-qualified name, ex.
    "org.gemini.Gender" that represents the type of object that this object
    should specialize on. (For situations where you just need a name, such as Speak
    Language, java.lang.String will work just fine.)  */
    protected String specializationType;

    /** The Specialization Type as a Class object */
    protected Class specializationClass;

    /** The specialization itself, which must be an instance of the class whose name 
    appears in specializationType, or one of its subclasses */
    protected Object specialization;
    
    /** Accessor for specialization type (Mutator deliberately omitted)
    @return the fully-qualified name of the base class of legal specializations */
    public String getSpecializationType() { return specializationType; }

    /** Accessor for specialization type Class object(Mutator deliberately omitted)
    @return the Class object representing the base class of legal specializations */
    public Class getSpecializationClass() { return specializationClass; }

    /** Accessor for the specialization object itself 
    @return specialization */
    public Object getSpecialization() { return specialization; }
    
    /** Accessor for specialized object 
    @return specialized object instance */
    public Type getOwner() { return object; }

    /** Mutator for specialized object 
    @param _owner specialized object instance */
    public void setOwner(Type _owner) { object = _owner; }

    /* Suppressing unchecked warnings on method setSpecialization().
    Because any given object's specialization type isn't determined
    until runtime, generics can't help us here. The compiler doesn't 
    know that, so we'll give it a hint... */             
    @SuppressWarnings("unchecked") 
    
    /** Mutator for the specialization object itself
    @param _spec new specialization object 
    @throws IllegalArgumentException if _spec is not an instance of specializationType */                    
    public void setSpecialization(Object _spec)
    {
        if (specializationClass.isInstance(_spec) == false)
            throw new IllegalArgumentException("Objects of type " + _spec.getClass().getName() + " are not valid specializations for " + object.getName() + ".");

        specialization = _spec;
    }
    
    /** Get the node as a String for character sheet printing
    @return string representation */
    public String toString() 
    {
        return (specialization == null) 
                ? object.getName()
                :  object.getName() + " [" + specialization.toString() + "]";                                
    }

    /** Test two Specializeds for equality 
    @param that object to test against
    @return true if this == that */
    public boolean equals(Object that)
    {
        if (that.getClass().equals(this.getClass()) == false)
            return false;

        Specialized Node = (Specialized)that;
        return ( 
                this.object.equals(Node.object) &&
                ((this.specialization == null && Node.specialization == null) ||
                 (this.specialization.equals(Node.specialization))));                            
    }

    
}
