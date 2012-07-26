/*
 * SourcePeriodical.java
 *
 * Created on November 9, 2005, 10:33 AM
 */

package org.gemini.sources;
import org.gemini.Month;
import org.gemini.StringTools;

/**
   SourcebookPeriodical is a specialized subclass of SourceMaterial for handling magazine data. 
   It has values for tracking a magazine's number, volume/issue representation, and date (month 
   and year). 
 
 * @author  Jaeden
 */
public class SourcePeriodical extends SourceMaterial
{
    /** Month of the publication */     private Month month;   
    /** Periodical number (ex: Dragon #324) */ private int number = 0;
    /** Volume number */ private int volume = 0;
    /** Issue number */ private int issue = 0;
        
    /** Creates a new instance of SourcePeriodical 
      @param name Source name */
    public SourcePeriodical(String name) 
    {
        super(name);
    }
     
    
    /** Creates a new source material
     * @param _name source material's name
     * @param _month month of publication 
     * @param _year copyright year  
     */
    public SourcePeriodical(String _name, Month _month, int _year)
    {
        super(_name, null, _name,  _year,_year,  "unknown");        
        setMonth(_month);
    }
    
    /** Creates a new source material
     * @param _name source material's name
     * @param _number periodical number
     * @param _authors authors of the source
     * @param _company company producing the source
     * @param _month month of publication 
     * @param _year copyright year
     * @param _endYear copyright end year 
     * @param _license license information 
     * @param _volume volume number
     * @param _issue issue number
     */
    public SourcePeriodical(String _name, int _number, String[] _authors, String _company, Month _month, int _year, int _endYear, 
                                                 String _license, int _volume, int _issue)
    {
        super(_name, _authors, _company, _year, _endYear, _license);
        setVolume(_volume);
        setIssue(_issue);
        setNumber(_number);
        setMonth(_month);
    }
    
    /** Getter for publication month.
     * @return Value of property month.      */
    public org.gemini.Month getMonth() {        return month;     }
    
    /**  Setter for publication month.
     * @param _month New value of property month.      */
    public void setMonth(org.gemini.Month _month) {         this.month = _month;     }
    
    /** Getter for periodical number.
     * @return Value of property number.      */
    public int getNumber() {        return number;     }
    
    /**  Setter for periodical number
     * @param _number New value of property number.      */
    public void setNumber(int _number) {         this.number = _number;     }
    
    /**  Getter for volume number 
     * @return Value of property volume.      */ 
    public int getVolume() {         return volume;     }
    
    /**  Setter for volume number 
     * @param _volume New value of property volume.      */
    public void setVolume(int _volume) {         this.volume = _volume;     }
    
    /**  Getter for issue number
     * @return Value of property issue.      */
    public int getIssue() {         return issue;     }
    
    /**  Setter for issue number
     * @param _issue New value of property issue.      */ 
    public void setIssue(int _issue) {         this.issue = _issue;     }
    
    /** Accessor for the volume and issue number of a magazine.
    @return a string representing the volume (in Roman numerals) and the issue.  */
    public String getVolumeString()
    {
        return "Volume " +  StringTools.romanNumeral(volume) + ", Issue #" + Integer.toString(issue);        
    }

    /** Accessor for the date (Month and Year)
     @return a string representing the "month" and year of this issue     */
    public String getDate() {   return month.getName() + " " + Integer.toString(this.getCopyrightYear()); }

/** Returns the complete copyright notice for this source   
      @return copyright block for the magazine */
    public String getCopyrightInfo()
    {    
        StringBuffer sb = new StringBuffer();
        sb.append(toString());
        sb.append("\n" + getVolumeString() + ", " + getDate() + "\n");
        sb.append("(C) ");
        
        if (getCopyrightYear() > 0 && getCopyrightEndYear() == 0)
            sb.append(Integer.toString(getCopyrightYear()) + " ");
        if (getCopyrightYear() > 0 && getCopyrightEndYear() > 0)
            sb.append(Integer.toString(getCopyrightYear()) + " - " + Integer.toString(getCopyrightEndYear()) + " ");
        if (getCompany() != null)
            sb.append(getCompany() + ";\n");
        
        if (getAuthors().size() != 0)
        {
            sb.append("Author(s): ");
            for (int i = 0; i < getAuthors().size(); i++)
            {
                sb.append(getAuthors().get(i));
                if (i + 2 == getAuthors().size())
                    sb.append(" and ");
                else if (i + 1  != getAuthors().size() && i != 0)
                    sb.append(", ");                
            }
        }
        return sb.toString();            
    }
    
    /** Get a string representation of the source magazine 
     @return a string representing the object as best as possible     */  
    public String toString()
        {
            // Dragon Magazine #103 (June 1991)
            if (number != 0 && (month != null && getCopyrightYear() != 0))
                return getName() + " #" + Integer.toString(number) + " (" + getDate() + ")";
             // Dragon Magazine #103
             else if (number != 0)
                return getName() + " #" + Integer.toString(number);
            // Dragon Magazine (June 1991)
            else if (month != null && getCopyrightYear() != 0)
                return getName() + " (" + month.getName() + " " + getDate() + " )" ;
            // Dragon Magazine Volume XVII Issue 8
            else if (volume != 0 && issue != 0)                
                return getName() + " " + getVolumeString();
            // Dragon Magazine
            else return getName();
            
        }

    /** Check the equality of two SourcePeriodical objects
 @param other object to compare 
     *  @return true if they're the same */
    public boolean equals(Object other)
    {
        if ((other instanceof SourcePeriodical) == false) return false;
        SourcePeriodical magOther = (SourcePeriodical)other;
        
        return (
                    super.equals(other) &&
                    getVolume() == magOther.getVolume() &&
                    getIssue() == magOther.getIssue() &&
                    getMonth().equals(magOther.getMonth()) &&
                    getNumber() == magOther.getNumber());                           
    }
    
    
    
}
