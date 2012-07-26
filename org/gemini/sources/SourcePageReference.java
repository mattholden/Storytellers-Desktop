/*
 * SourcePageReference.java
 *
 * Created on November 9, 2005, 12:13 PM
 */

package org.gemini.sources;

/**
 * A SourceReference that manages page numbers 
 *
 * @author  Jaeden
 */
public class SourcePageReference<Type extends SourceMaterial> extends SourceReference<Type>
{
    
    /** Starting page */ private int page = 0;
    /** Ending page */private int endPage = 0;
    
    /** Creates a new instance of SourcePageReference 
      @param _source Source to reference 
      @param _page page number (beginning) */
    public SourcePageReference(Type _source, int _page)
    {
        super(_source);
        page =_page;        
    }
    
    /** Creates a new instance of SourcePageReference 
      @param _source Source to reference 
      @param _page page number (beginning) 
      @param _pageEnd pageEnd number (ending) */
    public SourcePageReference(Type _source, int _page, int _pageEnd)
    {
        this(_source, _page);
        endPage = _pageEnd;        
    }
    
    /** Get beginning page
      @return page */
    public int getPage() { return page; }
    
    /** Get ending page
      @return endPage */
    public int getEndPage() { return endPage; }
    
    /** Set beginning page
     @param _page page number */
    public void setPage(int _page) { page = _page; }
    
    /** Set ending page
     @param _page page number */
    public void setEndPage(int _page) { endPage = _page; }
    
    /** String representation
     @return the string representation of this reference */
    public String toString()
    {
        if (page == 0)
            return super.toString();
            
        if (endPage == 0)
            return getSource().toString() + " pg. " + Integer.toString(page);
        
        // neither page or endPage is 0
        return getSource().toString() + " pg. " + Integer.toString(page) + " - " + Integer.toString(endPage);
    }
    
    /** Check equality of this source reference and another 
      @param other another reference to check 
      @return 'true' if they are equal */
    public boolean equals(Object other)
    {
        if (other instanceof SourcePageReference == false) return false;
        
        SourcePageReference SPR = (SourcePageReference)other;
        return (getSource().equals(SPR.getSource()) && page == SPR.getPage() && endPage == SPR.getEndPage());
    }
}
