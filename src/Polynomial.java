/*
    File: Polynomial.java

    This class represents a polynomial. The polynomial is represented by a 
    programmer-defined linked list of generic Nodes. There is an inner class 
    Node that implements a Node in a dynamic data structure. A Polynomial can 
    be collected for proper formating. Another polynomial can be added or 
    multiplied to form a new polynomial.
    
    Name: Eduardo Porto 4714449
    Class: COP 3337 U05

    I affirm that this program is entirely my own work and none
    of it is the work of any other person.
    ___________________
    (your signature)
*/

/**
 * This class represents a polynomial. The polynomial is represented by a 
 * programmer-defined linked list of generic Nodes. A Polynomial can 
 * be collected for proper formating. Another polynomial can be added or 
 * multiplied to form a new polynomial.
 * @author Daeddy
 */
public class Polynomial 
{
    private Node first ; //First node on the list
    
    /**
     * Constructor for an empty polynomial.
     */
    public Polynomial ()
    {
        first = null ; //Empty list
    }
    
    /**
     * Constructor that creates a new polynomial from an existing one.
     * @param poly existing polynomial to be cloned
     */
    public Polynomial ( Polynomial poly )
    {
        //Clone the first term for new polynomial
        first = new Node( poly.first.info ) ; 
        Node temp1 = first ; //new polynomials first node
        Node temp2 = poly.first.next ; //start at polys second node
        //traverse poly
        while ( temp2 != null ) 
        {
            temp1.next = new Node( temp2.info ) ; //Clone current term 
            //Move to next node
            temp1 = temp1.next ;
            temp2 = temp2.next ;	
        }
    }
    
    /**
     * Utility method to find a position for a term in the list. Is based on 
     * equal exponent.
     * @param term the term to find a position for
     * @return pointer to the node where you can place this term after
     */
    private Node findPos( Term term )
    {
        if ( first == null )       //if list empty
        {
            return null ;        //Term fits at the start of the list
        } 
        else                    // else, search for a pos
        {
            Node temp = first ;           //first node of the list
            Term thatTerm = temp.info ;  //Term of the first node
            //Check if term has a lower exponent than first one from the list
            if ( term.hasLowerExp( thatTerm ) )
            {
                return null ; //Term fits at the start of the list
            }
            //Traverse the list to find a position for the term
            while ( temp.next != null ) //while there is a node
            {
                thatTerm = temp.next.info ; //Term of the next node
                //Check if term has a lower exponent than the next term
                if( term.hasLowerExp( thatTerm ) )
                {
                    return temp ; //Term fits after this node
                }
                temp = temp.next ;	// move to next node
            }
            return temp ;		// Pointer to last node
        }
        
    }
    
    /**
     * Creates a term, finds a position for it on the list and places it.
     * @param coefficient coefficient for the term
     * @param exponent exponent for the term
     */
    public void addTerm( int coefficient, int exponent )
    {
        Term newTerm = new Term( coefficient, exponent ) ;//Creates new term
        Node newNode = new Node( newTerm ) ; //new Node with term
        //check if term fits as the first node (list could be empty)
        if ( findPos( newTerm ) == null )
        {
            //Node is either placed before first term or as the first.
            //New nodes 'next' points to first (which could be empty)
            newNode.next = first ;
            first = newNode ; //new Node is now the first
        }
        else //placed somewhere after first term
        {
            //temp node that points to the node after where the term can fit
            Node temp = findPos( newTerm ).next ; 
            findPos( newTerm ).next = newNode ;  //insert new node
            newNode.next = temp ; //new node now points to the next node
        }
    }
    
    /**
     * Creates a new polynomial that is the addition of this polynomial with 
     * a given other, and returns it.
     * @param p polynomial to be added
     * @return new polynomial that is the sum of two polynomials
     */
    public Polynomial polyAdd( Polynomial p )
    {
        //resulting polynomial starts as the adding polynomial 
        Polynomial resultPoly = new Polynomial( p ) ; 
        Node temp = first ; //start fromm the first polynomial
        //traverse the list
        while ( temp != null )
        {
            //add the terms from 'this' polynomial to the resulting polynomial
            resultPoly.addTerm( temp.info.getCoef(), temp.info.getExp() ) ;
            temp = temp.next ; //move to the next node
        }
        return resultPoly ; //resulting polynomial
    }
    
    /**
     * Creates a new polynomial that is the multiplication of this polynomial
     * with a given other, and returns it.
     * @param p polynomial to be multiplied
     * @return new polynomial that is the multiplication of two polynomials
     */
    public Polynomial polyMultiply( Polynomial p )
    {
        //resulting polynomial starts as the multiplying polynomial 
        Polynomial resultPoly = new Polynomial() ; 
        Node temp1 = first ; //start from first node of 'this' list
        Node temp2 = p.first ; //start from first node of 'multiplying' list
        /*
            Multiply each term from 'this' list by each term of the
            'multiplying' list
        */
        while ( temp1 != null ) //traverse 'this' list
        {
            while ( temp2 != null ) //traverse 'multiplying' list 
            {
                //multiply the coeficients
                int newCoef = temp2.info.getCoef() * temp1.info.getCoef() ;
                //add the exponents
                int newExp = temp2.info.getExp() + temp1.info.getExp() ;
                //add the new term to the resulting 
                resultPoly.addTerm( newCoef, newExp ) ; 
                temp2 = temp2.next ;  //move to the next node
            }
            temp2 = p.first ; //reset the 'multiplying' list 
            temp1 = temp1.next ; //move to the next node of 'this' list
        }
        return resultPoly  ; //resulting polynomial
    }
    
    /**
     * Utility method that collects terms for proper formating.
     */
    private void collectTerms()
    {
        Node temp = first ; //start at first node
        while( temp.next != null ) //Traverse the list
        {
            Term thisTerm = temp.info ; //'this' term
            Term thatTerm = temp.next.info ; //the next term
            //compare and add the coefficients while the exponents of the next
            //term is the same as 'this' terms exponent
            while ( thisTerm.getExp() == thatTerm.getExp() )
            {
                int newCoef = thisTerm.getCoef() + thatTerm.getCoef() ;
                Term newTerm = new Term( newCoef, thisTerm.getExp() ) ;
                temp.info = newTerm ; //'this' get new resulting term
                temp.next = temp.next.next ; //point to the term after next
                thisTerm = temp.info ; //new term 
                thatTerm = temp.next.info ; //new next term 
            }
            //check if there is a next term
            if ( temp.next == null )
            {
                break ; //exit the loop
            }    
            temp = temp.next ; //move to next term
        }
    }
    
    /**
     * Overridden toString method that format the list into a polynomial.
     * @return the formated polynomial
     */
    public String toString()
    {
        if ( first == null ) //Check if list is empty
        {
            return "0" ; //the polynomial is '0'
        }
        collectTerms() ; //Collect the terms for formating
        String s = "" ; //the polynomial in string format
        Node temp = first; //start from the first node of the list
        while( temp != null ) //traverse the list
        {
            s = s + temp.info.toString() ; //add term to the string
            temp = temp.next ; //move to next node
        }
        return s ; //Formated polynomial
    }
    
    /**
     * Generic inner class that implements a Node in a dynamic data structure.
     * @param <E> object of the Term type
     */
    class Node< E extends Term > 
    {					
        E info ;	// each node stores an object 
        Node next ;	// pointer to the next node
		
        /**
         * Node constructor 
         * @param x parameter of class E type of object
         */
        Node ( E x )		// constructor takes one param of class E
        {			
            info = x ;	// obejet stored
            next = null ;	//node points to nothing
        }
    }
    
}