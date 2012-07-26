/*
 * FrameworkCommand.java
 *
 * Created on November 27, 2005, 4:11 PM
 */

package org.slage.framework;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Defines a core command object to trigger actions (Handlers).
 * 
 * @author <a href="mailto:Matt@Slage.com">Matt Holden</a>
 
 */
public abstract class FrameworkCommand<ObjectType extends SlageFrameworkObject>
    implements java.io.Serializable, Comparable
{
    /** The actual command string (as a sentence) */ private String commandString;
    
    	/** logger instance */
	protected static transient final Log LOG = LogFactory.getLog(FrameworkCommand.class);

        /** The initiator of this command (the player character */
        private ObjectType initiator;
        
        /** The Verb for this command */
	private String verb;

	/** Timestamp when the command was issued. */
	private long lTimestamp = -1;

        /** Creates a new instance of FrameworkCommand 
          @param input the command as a sentence 
          @param init Initiator of the command */
        public FrameworkCommand(String input, ObjectType init) 
        {
            commandString = input.toUpperCase();
            initiator = init;

            // todo: verb as first word?
            
            /* This is a crummy timestamp.. We'll hope to do better in subclasses 
         using scheduler instances. */
            lTimestamp = System.currentTimeMillis() * 1000;
        }
    
        /** Creates a new instance of FrameworkCommand 
          @param input the command as a sentence 
          @param _verb the verb in the command
          @param init Initiator of the command */
        public FrameworkCommand(String input, String _verb, ObjectType init) 
        {
            this(input, init);
            setVerb(_verb);
        }
        
            /**
     * Accessor for the original input
     * 
     * @return strCommand
     */
	public String getOriginalInput() {
		return commandString;
	}

	/**
	 * String representation of the command
	 * 
	 * @return the original command
	 */
	public String toString() {
		return getOriginalInput();
	}

        	/**
	 * Getter for verb
	 * 
	 * @return Value of property strVerb.
	 */
	public java.lang.String getVerb() {
		return verb;
	}

	/**
	 * Setter for verb
	 * 
	 * @param _verb New value of property strVerb.
	 */
	public void setVerb(String _verb) {
		 verb = _verb.toUpperCase();
	}


        
	/**
	 * Accessor for time the command was fired
	 * 
	 * @return lTimestamp
	 */
	public long getTimestamp() {
		return lTimestamp;
	}
        
	/**
     * Mutator for time the command was fired
     * 
     * @param time the actual timestamp
     */
	protected void setTimestamp(long time) 
        {
		lTimestamp = time;
	}

        /** Accessor for the initiator of the command
       @return initiator */
        public ObjectType getInitiator() { return initiator; }
        
        /** Mutator for the initiator of the command
       @param init Initiator */
        public void setInitiator(ObjectType init) { initiator = init; }
        
        /** Compare commands by time
        @param other object to compare against
        @return negative if this happened first, positive if other happpened first */
        public int compareTo(Object other)
        {
            if (other instanceof FrameworkCommand == false) return 0;
            
            // protect against overflowing the int
            return Tools.compareLongs(this.lTimestamp,  ((FrameworkCommand)other).lTimestamp);            
            
        }
        
        /** Execute this Command on the target. */
	public abstract void fire();
	
}
