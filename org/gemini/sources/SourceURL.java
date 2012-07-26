/*
 * SourceURL.java
 *
 * Created on November 9, 2005, 11:03 AM
 */

package org.gemini.sources;
import java.net.URL;

/**
 * Extends SourceMaterial to represent sources with URLs, such as websites and online PDFs.
 *
 * @author  Jaeden
 */
public class SourceURL extends SourceMaterial
{
    /** The URL of the site */     private URL url;
        
    /** Creates a new instance of SourceURL 
      @param name Site name
      @param _url Site URL */
    public SourceURL(String name, URL _url) 
    {
        super(name);
        setURL(_url);
    }
    
    /** Creates a new instance of SourceURL 
      @param name Site name
      @param _url Site URL as a string */
    public SourceURL(String name, String _url) 
    {
        super(name);
        try
        {
            setURL(new URL(_url));
        } catch (Exception e){ LOG.warn(e); }
    }
       
    /** Creates a new source material with a URL
     * @param _name source material's name
     * @param _url site's URL
     * @param _authors authors of the source
     * @param _company company producing the source
     * @param _year copyright year
     * @param _endYear copyright end year 
     * @param _license license information */
    public SourceURL(String _name, URL _url, String[] _authors, String _company, int _year, int _endYear, String _license)
    {
        super(_name, _authors, _company, _year, _endYear, _license);
        setURL(_url);
    }
    
     /** Creates a new source material with a URL
     * @param _name source material's name
     * @param _url site's URL
     * @param _authors authors of the source
     * @param _company company producing the source
     * @param _year copyright year
     * @param _endYear copyright end year 
     * @param _license license information */
    public SourceURL(String _name, String _url, String[] _authors, String _company, int _year, int _endYear, String _license)
    {
        super(_name, _authors, _company, _year, _endYear, _license);
        try
        {
            setURL(new URL(_url));
        } catch (Exception e){ LOG.warn(e); }
    }
    
    /** Getter for site URL
     * @return Value of property url.      */ 
    public java.net.URL getURL() {         return url;     }
    
    /** Setter for site URL
     * @param _url New value of property url.      */
    public void setURL(java.net.URL _url) {         this.url = _url;     }
    
    /** Test for equality of two SourceURLs 
      @param other SourceURL to test 
      @return true if this SourceURL matches 'other' */
    public boolean equals(Object other)
    {
        if (other instanceof SourceURL == false) return false;
        
        SourceURL surl = (SourceURL)other;
        return (super.equals(surl) && getURL().equals(surl.getURL()));
    }
    
     /** Get a string representation of the source, including URL
     @return a string representation of the source */
    public String toString()  
    {  
        if (url != null)
            return getName() + " (" + url.toString() + ")";     
        
        else return getName();
    }
    
    /** Get the copyright block for the site
      @return copyright block as a string */
    public String getCopyrightInfo()   {  return url.toString() + "\n" + super.getCopyrightInfo();     }
    
    /** Construct a SourceURL from another SourceURL
     *  @param _other SourceURL to copy */
    public SourceURL(SourceURL _other)
    {
        this(_other.getName(), _other.getURL(), null, _other.getCompany(),
             _other.getCopyrightYear(), _other.getCopyrightEndYear(), _other.getLicense());        
        authors.addAll(_other.authors);
        setGamingSystem(_other.getGamingSystem());
        setGamingSystemMod(_other.getGamingSystemMod());
        setCampaignSetting(_other.getCampaignSetting());        
    }
    
}
