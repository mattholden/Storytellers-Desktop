    
    package org.gemini.message;
    import java.util.ArrayList;
    
    /** Minimal object promotion for an ArrayList of messages so that we can create arrays of them.
         Java says I can't create an ArrayList<Message> array due to the generics, but this we can do. */
    public class MessageList extends ArrayList<Message> { }
