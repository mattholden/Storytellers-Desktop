/*
 * SourceMaterial.java
 *
 * Created on November 8, 2005, 11:07 PM
 */

package org.gemini.sources;
import org.slage.framework.NamedObject;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.gemini.CampaignSetting;
import org.gemini.GamingSystem;
import org.gemini.GamingSystemMod;

/**
 * Define a Source Material for a Gemini object. 
   Contains copyright info, etc. for the work. 
   Additionally, contains the campaign setting and gaming 
   system information for the source so that all objects which have 
   a Source can use it to identify what system and setting they belong to.
 
   @author  Jaeden
 */
public class SourceMaterial implements java.io.Serializable, NamedObject 
{
    /** Logger instance */
	protected static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SourceMaterial.class);

    /** Name of the source */ private String name;
    /** Author(s) of the source */ protected ArrayList<String> authors = new ArrayList<String>();
    /** Company producing the source */ private String company;
    /** Copyright date of the source */ private int copyrightYear = 0;
    /** Ending copyright date of the source */ private int copyrightEndYear = 0;
    /** License type */ private String license;
       
    /** Campaign setting name (leave null for core system stuff */
    private String campaignSetting = null;
    /** Game system name (leave null for settings that are system-independent) */
    private String gamingSystem = null;
    /** Game system modification (leave null if not applicable) */
    private String gamingSystemMod = null;
    
    /** Creates a new source material 
      @param aName name for the source */
    public SourceMaterial(String aName) 
    {
        name = aName;
        company = "";
        copyrightYear = copyrightEndYear = new GregorianCalendar().get(GregorianCalendar.YEAR);
        license = "unknown";
    }
    
    /** Creates a new source material
     * @param _name source material's name
     * @param _authors authors of the source
     * @param _company company producing the source
     * @param _year copyright year
     * @param _endYear copyright end year 
     * @param _license license information */
    public SourceMaterial(String _name, String[] _authors, String _company, int _year, int _endYear, String _license)
    {
        name = _name;
        company = _company;
        copyrightYear = _year;
        copyrightEndYear = _endYear;
        license = _license;
        
        if (_authors != null)        
            for (int i = 0; i < _authors.length; i++) authors.add(_authors[i]);                    
    }
    
    /** Get name 
      @return name */
    public String getName() { return name; }
    
    /** Get license name
      @return license */
    public String getLicense() { return license; }
    
    /** Get company 
      @return company */
    public String getCompany() { return company; }

    /** Get copyright year
      @return copyright year */
    public int getCopyrightYear() { return copyrightYear; }

    /** Get copyright end year
     *  @return copyright end year */
    public int getCopyrightEndYear() { return copyrightEndYear; }

    /** Set name
     *  @param _name name */
    public void setName(String _name) { name = _name; }

    /** Set license name
     *  @param _license license name */
    public void setLicense(String _license) { license = _license; }

    /** Set company
     *  @param _company company name */
     public void setCompany(String _company) { company = _company; }

     /** Set copyright year
        @param _year copyright year */
     public void setCopyrightYear(int _year) { copyrightYear = _year; }

     /** Set copyright end year
        @param _year copyright year */
     public void setCopyrightEndYear(int _year) { copyrightEndYear = _year; }

     /** Add an author
      * @param _author author to add */
     public void addAuthor(String _author) { authors.add(_author); }

      /** Returns the complete copyright notice for this source
     @return copyright string */
    public String getCopyrightInfo()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(getName() + " (C) ");
        if (copyrightYear > 0 && copyrightEndYear == 0)
            sb.append(Integer.toString(copyrightYear) + " ");
        if (copyrightYear > 0 && copyrightEndYear > 0)
            sb.append(Integer.toString(copyrightYear) + " - " + Integer.toString(copyrightEndYear) + " ");
        if (company != null)
            sb.append(company + ";\n");
        
        if (authors.size() != 0)
        {
            sb.append("Author(s): ");
            for (int i = 0; i < authors.size(); i++)
            {
                sb.append(authors.get(i));
                if (i + 2 == authors.size())
                    sb.append(" and ");
                else if (i + 1  != authors.size() && i != 0)
                    sb.append(", ");                
            }
        }
        return sb.toString();        
    }
    
     /** Determine equality of two source materials 
      * @param another Another source material to check 
      * @return true if the sources are the same */
     public boolean equals(Object another)
     {         
             if (another instanceof SourceMaterial == false) return false;
             SourceMaterial other = (SourceMaterial)another;

            return (name.equalsIgnoreCase(other.name) &&
                        copyrightYear == other.copyrightYear &&
                        copyrightEndYear == other.copyrightEndYear &&
                        company.equalsIgnoreCase(other.company) &&
                        license.equalsIgnoreCase(other.license));
     }
 
     /** Render the SourceMaterial as a string (Just its name will do) 
      @return material's name */
     public String toString() { return getName(); }

         /** Get authors
      @return authors */
    public ArrayList<String> getAuthors() { return authors; }

    
    /** Get the name of the campaign setting this source is asscoaited with 
      @return campaignSetting */
    public String getCampaignSetting() { return campaignSetting; }
    
    /** Get the name of the ame system the source is associated with
     * @return gamingSystem */
    public String getGamingSystem() { return gamingSystem; }
    
    /** Get the name of the game system mod the source is associated with
     * @return gamingSystemMod */
    public String getGamingSystemMod() { return gamingSystemMod; } 
    
    /** Set the name of the campaign setting for this source
     * @param _name new name */
    public void setCampaignSetting(String _name) { campaignSetting = _name; }
    
    /** Set the name of the gaming system for this source
     * @param _name new name */
    public void setGamingSystem(String _name) { gamingSystem  = _name; }
    
    /** Set the name of the gaming system mod for this source
     * @param _name new name */
    public void setGamingSystemMod(String _name) { gamingSystemMod  = _name; }
    
    
}