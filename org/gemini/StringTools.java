/*
 * StringTools.java
 *
 * Created on November 9, 2005, 10:47 AM
 */

package org.gemini;

/**
 * Provides a set of string manipulation tools for common Gemini tasks
 *
 * @author  Jaeden
 */
public abstract class StringTools 
{
    
    /** Return "gain" if a value is positive, "take" if it's negative, "" if 0
      @param i Value to check
     *@return "give", "take", or "" */
    public static String gainOrTake(int i) 
    {
        if (i == 0) return "";
        return (i > 0) ? "give" : "take";
    }
          
    /** Return "bonus" if a value is positive, "penalty" if it's negative, "" if 0
      @param i Value to check
     *@return "bonus", "penalty", or "" */
    public static String bonusOrPenalty(int i) 
    {
        if (i == 0) return "";
        return (i > 0) ? "bonus" : "penalty";
    }
              
    /** Return a String containing the + or - of a modifier 
      @param i Modifier      */
    public static String getModifier(int i) { return getModifier(i, false); }
    
    /** Return a String containing the + or - of a modifier 
      @param i Modifier
      @param zero if 'true', write 0 as "+0", 
             otherwise return "" on a zero */
    public static String getModifier(int i, boolean zero)
    {
        if (i == 0 && zero == false) 
            return "";
        if (i < 0)
            return Integer.toString(i);
        
        return "+" + Integer.toString(i);        
    }
    
     /** Format names to look nice
      *  @param strName incoming name 
      *  @return name, finished */
      public static String nameFormat(String strName)
      {
          String strWord = new String();
          String strFinal = new String();
          
          for (int i = 0; i < strName.length(); i++)
          {
              // Build words
              if (strName.charAt(i) != ' ')
                  strWord += strName.charAt(i);
              
              // We have a word, do stuff with it
              if (strName.charAt(i) == ' ' || i == strName.length() - 1)
              {
                    // 0. Make a capitalized version for easier checking
                    String strCap = strWord.toUpperCase();
                    
                    // 1. Handle reasons to leave the whole word lower case
                    if (strCap.equals("OF")) strWord = "of";
                    else if (strCap.equals("THE")) strWord = "the";
                    else if (strCap.equals("LE")) strWord = "le";
                    else if (strCap.equals("LA")) strWord = "la";
                    else if (strCap.equals("DE")) strWord = "de";
                    else if (strCap.equals("DES")) strWord = "des";                    
                    else if (strCap.equals("DU")) strWord = "du";                    
                    
                    // 2. Handle special reasons not to capitalize first letter
                    else if (strCap.startsWith("O'"))
                        strWord = "o'" + strCap.charAt(2) + strWord.substring(3).toLowerCase();
                    else if (strCap.startsWith("DI") && java.lang.Character.isLowerCase(strWord.charAt(0)))
                        strWord = "di" + strCap.charAt(2) + strWord.substring(3).toLowerCase();
                    else if (strCap.startsWith("LE") && java.lang.Character.isLowerCase(strWord.charAt(0)))
                        strWord = "le" + strCap.charAt(2) + strWord.substring(3).toLowerCase();
                    else if (strCap.startsWith("DE") && java.lang.Character.isLowerCase(strWord.charAt(0)))                   
                        strWord = "de" + strCap.charAt(2) + strWord.substring(3).toLowerCase();
                    else if (strCap.startsWith("D'"))
                        strWord = "d'" + strCap.charAt(2) + strWord.substring(3).toLowerCase();
                    
                    
                    // Todo: Roman numerals?
                    
                    // Last else - just capitalize first letter, lowercase the rest
                    else
                        strWord = java.lang.Character.toUpperCase(strCap.charAt(0)) + strWord.substring(1).toLowerCase();
                                                
                    // Done! Prepare for next word
                    strFinal += strWord;
                    if (i != strName.length() - 1) strFinal += ' ';
                    strWord = new String();
                    
              }     // end word if
              
          }         // end for
          return strFinal;
      }
    
    
      
      
        /** Reverse a comma-separated string. Turns "Gnome, Forest" into "Forest Gnome". 
         * @param strComma Comma-separated string
         * @return reversed string */
        public static String reverseCommaString(String strComma)
        {           
            
            for (int i = 0; i < strComma.length(); i++)
                if (strComma.charAt(i) == ',')
                {
                    String strNew = strComma.substring(i + 2, strComma.length());
                    strNew += ' ';
                    strNew += strComma.substring(0, i);
                    return strNew;
                }
            
            return strComma; // no commas!
        }

        
/** Generates a string containing the Roman numeral representation of a number.
* @param iNumber The number to Romanize (1 to 4999)
* @return a string of a Roman numeral of an integer 
*/  public static String romanNumeral(int iNumber)
     {
         // Give them back an invalid string if it broke
         if (iNumber <= 0 || iNumber >= 5000)
             return null;
             
          String strOnes = new String(),  strTens = new String(); 
          String strHuns = new String(), strThous = new String();
         
         // Parse the ones digit
         switch(iNumber % 10)
         {
             case 1: strOnes = "I";             break;
             case 2: strOnes = "II";            break;
             case 3: strOnes = "III";           break;
             case 4: strOnes = "IV";           break;
             case 5: strOnes = "V";            break;
             case 6: strOnes = "VI";           break;
             case 7: strOnes = "VII";          break;
             case 8: strOnes = "VIII";         break;
             case 9: strOnes = "IX";           break;
         }
         iNumber -= (iNumber % 10);
         if (iNumber == 0) return strThous + strHuns + strTens + strOnes;
         
         // Parse the tens digit
         switch(iNumber % 100)
         {
             case 10: strTens = "X";           break;
             case 20: strTens = "XX";         break;
             case 30: strTens = "XXX";       break;
             case 40: strTens = "XL";         break;
             case 50: strTens = "L";           break;
             case 60: strTens = "LX";         break;
             case 70: strTens = "LXX";       break;
             case 80: strTens = "LXXX";     break;
             case 90: strTens = "XC";         break;
         }
         iNumber -= (iNumber % 100);
         if (iNumber == 0) return strThous + strHuns + strTens + strOnes;
         
         // Parse the hundreds digit
         switch(iNumber % 1000)
         {
             case 100: strHuns = "C";             break;
             case 200: strHuns = "CC";          break;
             case 300: strHuns = "CCC";       break;
             case 400: strHuns = "CD";          break;
             case 500: strHuns = "D";             break;
             case 600: strHuns = "DC";          break;
             case 700: strHuns = "DCC";       break;
             case 800: strHuns = "DCCC";    break;
             case 900: strHuns = "CM";          break;
         }
         iNumber -= (iNumber % 1000);
         if (iNumber == 0) return strThous + strHuns + strTens + strOnes;
         
         // Parse the thousands digit
         switch(iNumber)
         {
             case 1000: strThous = "M";             break;
             case 2000: strThous = "MM";          break;
             case 3000: strThous = "MMM";       break;
             case 4000: strThous = "MMMM";    /* Sloppy but sometimes done. */
                                   break;                            /* Real notation would be IV with an overbar. */
         }
             
         // Compile and return
         return strThous + strHuns + strTens + strOnes;
     }
      
    /** Creates a new instance of StringTools */
   private StringTools() { /** hide */ }
    
    
}
