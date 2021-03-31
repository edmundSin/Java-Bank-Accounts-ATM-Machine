/* Edmund Sin 
 * CISC 3115
 * Professor Ziegler
 * 11/24/19
 * HW 7: Bank Accounts  Using Inheritance, Polymorphism, AbstractClasses and Methods 
 */
import java.util.Calendar;
import java.util.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat; 

public class Check{
  
  private int acctNum;
  private double checkAmount;
  private Calendar dateOfCheck; 
  
  //copy constructor
  public Check(Check c){
    acctNum = c.acctNum;
    checkAmount = c.checkAmount;
    dateOfCheck = c.dateOfCheck;
  }
  
  //parametized constructor
  public Check(int a, double b, Calendar c){
    acctNum = a;
    checkAmount = b;
    dateOfCheck= c; 
  }
  
  //toString() method - uses String static method .format()
  public String toString(){
    
    Date date = getDateOfCheck().getTime();
    /* formatted string:
       Account Number: xxxxxxx
       Check Amount: xx.xx
       Date of Check: MM/dd/yyyy
    */   
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
    String strDate = dateFormat.format(date);
    String str = String.format
      ("Account Number: %d\nCheck Amount: $%.2f\nDate of Check: %s", 
       getAcctNum(),
       getCheckAmount(), 
       strDate
       );
    return str;
  }
  
  public int getAcctNum(){
    return acctNum;
  }
  
  public double getCheckAmount(){
    return checkAmount;
  }
  
  public Calendar getDateOfCheck(){
    return dateOfCheck;
  }
}