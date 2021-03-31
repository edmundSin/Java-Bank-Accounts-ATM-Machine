/* Edmund Sin 
 * CISC 3115
 * Professor Ziegler
 * 11/24/19
 * HW 7: Bank Accounts  Using Inheritance, Polymorphism, AbstractClasses and Methods 
 */

import java.util.Calendar;
import java.util.Date;
import java.util.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat; 

public class TransactionTicket{ 
  
  private Calendar dateOfTransaction;
  private String typeOfTransaction;
  private double amountOfTransaction;
  private int termOfCD;
  
  
  //no arg constructor
  public TransactionTicket(){
    dateOfTransaction = null;
    typeOfTransaction = null;
    amountOfTransaction = 0;
    termOfCD = 0;
  }
  
  //copy constructor
  public TransactionTicket(TransactionTicket transTic){
    dateOfTransaction = transTic.dateOfTransaction;
    typeOfTransaction = transTic.typeOfTransaction;
    amountOfTransaction = transTic.amountOfTransaction;
    termOfCD = transTic.termOfCD;
  }
  
  //parametized constructors
  public TransactionTicket(Calendar c, String t, double a){
    dateOfTransaction = c;
    typeOfTransaction = t;
    amountOfTransaction = a;
    termOfCD = 0;
    
  }
 
  
  public TransactionTicket(Calendar c, String t, double a, int b){
    dateOfTransaction = c;
    typeOfTransaction = t;
    amountOfTransaction = a;
    termOfCD = b;
    
}
  public TransactionTicket(Calendar c, String t){
    dateOfTransaction = c;
    typeOfTransaction = t;
    
  }
  
   //toString() method
  public String toString(){
    Date date = getDateOfTransaction().getTime();
    /* formatted string:
       Date of Transaction: MM/dd/yyyy
       Type of Transaction: xxxx
       Amount of Transaction: xx.xx
    */   
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
    String strDate = dateFormat.format(date);
    String str = String.format
      ("Date of Transaction: %s\nType of Transaction: %s\nAmount of Transaction: $%.2f",
       strDate, getTransactionType(), getTransactionAmount()
      );
    
    return str;
  }
  
  public Calendar getDateOfTransaction(){
    
    return dateOfTransaction;
  }
    
  public String getTransactionType(){
    return typeOfTransaction;
  }
  
  public double getTransactionAmount(){
    return amountOfTransaction;
  }

  public int getTermOfCD(){
    return termOfCD;
  }
  
 
}
    