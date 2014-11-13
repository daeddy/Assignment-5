/*
    File: Term.java

    This class represents a term in an algebraic expression. A term consists 
    of an integer coefficient and a nonnegative integer exponent. In this 
    class there is a constructor for a Term object, accessor methods and an 
    overridden toString() method.
    
    Name: Eduardo Porto 4714449
    Class: COP 3337 U05

    I affirm that this program is entirely my own work and none
    of it is the work of any other person.
    ___________________
    (your signature)
*/

/**
 *  This class represents a term in an algebraic expression. A term consists 
 *  of an integer coefficient and a nonnegative integer exponent. In this 
 *  class there is a constructor for a Term object, accessor methods and an 
 *  overridden toString() method.
 * @author Daeddy
 */
public class Term  
{
    private int coef ; //Coefficient of the term
    private int exp ;  //Exponent of the term
    
    /**
     * Constructor method that sets the values for the coefficient and the 
     * exponent of the term.
     * @param coef coefficient of the term
     * @param exp The exponent of the term
     */
    public Term( int coef, int exp )
    {
        //Set the values for the coefficient and the exponent of the term
        this.coef = coef ; 
        this.exp = exp ;
    }
    
    /**
     * Accessor method for the coefficient of the term.
     * @return The coefficient of the term
     */
    public int getCoef()
    {
        return coef ; //The coefficient of the term
    }
    
    /**
     * Accessor method for the exponent of the term.
     * @return The exponent of the term
     */
    public int getExp()
    {
        return exp ; //The exponent of the term
    }
    
    /**
     * Overridden toString method the returns the term in ax^b format.
     * @return The formated term
     */
    public String toString()
    {
        //Creates a string in cx^e format
        String s = " " + formatCoef() + formatExp() ;
        return s ;
    }
    
    /**
     * Utility method that formats the coefficient.
     * @return the formated coefficient
     */
    private String formatCoef()
    {
        String coef = String.valueOf( this.coef ) ; //Formated coefficient
        //Modify the formated coefficient if it is zero or one or minus one
        if ( this.coef == 0 )
        {
            
            coef = "" ; //there is no coeficient
            //if the coefficient is zero than so is the rest of the term
            exp = 0 ;
        }
        else if ( this.coef == 1 )
        {
            coef = "" ; //'1x' is the same as 'x'
        }
        else if ( this.coef == -1 )
        {
            coef = "-" ; //'1x' is the same as '-x'
        }
        return coef ;
    }
    
    /**
     * Utility method that formats the exponent.
     * @return the formated exponent
     */
    private String formatExp()
    {
        String exp = "x^" + String.valueOf( this.exp ) ; //Formated exponent
        //Modify the formated exponent if it is zero or one
        if ( this.exp == 0 )
        {
            exp = "" ; //x^0 is 1 so there is no 'x'
        }
        else if ( this.exp == 1 )
        {
            exp = "x" ; //x^1 is x
        }
        return exp ;
    }
    
    /**
     * Compares this term to another to see if it has a or equal exponent.
     * @param thatTerm Term to compare to
     * @return true it has a lower exponent
     */
    public boolean hasLowerExp ( Term thatTerm )
    {
        //Check if this term has a lower or equal exponent
        if ( exp <= thatTerm.getExp() )
        {
            return true ;
        }
        else
        {
            return false ;
        }
    }
}
