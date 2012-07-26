package org.slage;
import org.slage.framework.SlageFrameworkObjectVisitor;

/**
 * Interface for resolving the type of a SlageObject to something more specific than just "SlageObject".
 * Add new subclasses to this and implement accept(SlageObjectVisitor) as necessary.
 * 
 * If you only wish to accept certain subclasses, you may wish to consider extending <code>AbstractSlageObjectVisitor</code> instead.
 *
 * @see AbstractSlageObjectVisitor
 *
 * @author Travis Savo <Travis@sq7.org>
 */
public interface SlageObjectVisitor extends SlageFrameworkObjectVisitor {
	public void accept(SlageObject anObject);
	public void accept(Room aRoom);
	public void accept(Act anAct);
	public void accept(Scene aScene);
	public void accept(SlageGame aGame);
        public void accept(TextObject aTextObject);
        public void accept(SlageCharacter aCharacter);
        public void accept(SlagePlayer aPlayer);
}
