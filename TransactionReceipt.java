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

public class TransactionReceipt{
  
  private TransactionTicket transTic; 
  private boolean successIndicatorFlag; 
  private String reasonForFailure;
  private double preTransactionBalance;
  private double postTransactionBalance;
  private Calendar postTransactionMaturityDate;
  Calendar calendar = Calendar.getInstance();
  
  //no arg constructor
  public TransactionReceipt(){
    preTransactionBalance = 0.0;
    postTransactionBalance = 0.0;
    postTransactionMaturityDate = null;
    reasonForFailure = null;
    successIndicatorFlag = true;
    
  }
  
  //copy constructor
  public TransactionReceipt(TransactionReceipt tReceipt){
    transTic = new TransactionTicket(tReceipt.transTic);
    successIndicatorFlag = tReceipt.successIndicatorFlag;
    reasonForFailure = tReceipt.reasonForFailure;
    preTransactionBalance = tReceipt.preTransactionBalance;
    postTransactionBalance = tReceipt.postTransactionBalance;
    postTransactionMaturityDate = 
      tReceipt.postTransactionMaturityDate;

  }
  
  
  //parametized constructor
  public TransactionReceipt(double pre, double post, Calendar c, boolean b,
                                           TransactionTicket t){
                          
    preTransactionBalance = pre;
    postTransactionBalance = post;
    postTransactionMaturityDate = c;
    successIndicatorFlag = b;
    transTic = t;
    
  }
  
  public TransactionReceipt(String r, boolean b, Calendar c){
    reasonForFailure = r;
    successIndicatorFlag = b;
    postTransactionMaturityDate = c;
  }
  
  public TransactionReceipt(double pre, double post, TransactionTicket t){
    preTransactionBalance = pre;
    postTransactionBalance = post;
    transTic = t;
    successIndicatorFlag = true;
    postTransactionMaturityDate = null;
    
  }
  
  public TransactionReceipt
    (double pre, double post, TransactionTicket t, Calendar c){
    preTransactionBalance = pre;
    postTransactionBalance = post;
    transTic = t;
    successIndicatorFlag = true;
    postTransactionMaturityDate = c;
    
  }
  
  //toString() method
  public String toString(){
    
    Date date = transTic.getDateOfTransaction().getTime();
    /* formatted string:
       Date of Transaction: MM/dd/yyyy
       Type of Transaction: xxxx
       Amount of Transaction: xx.xx
       Pre-Transaction Balance: xx.xx
       Post-Transaction Balance: xx.xx
    */   
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
    String strDate = dateFormat.format(date);
    String str = String.format
      ("Date of Transaction: %s\nAmount of Transaction: $%.2f\nPre-Transaction Balance: $%.2f\nPost-Transaction Balance: $%.2f\n",
       strDate,
       transTic.getTransactionAmount(),
       getPreTransactionBalance(),
       getPostTransactionBalance()
  
       );
       
       return str;  
  }
  
  //getters
  public Calendar getPostTransactionMaturityDate(){
    
    return postTransactionMaturityDate;
  }
  
  public TransactionTicket getTransactionTicket(){
    TransactionTicket tCopy = new TransactionTicket(transTic);
    return tCopy;
  }
  
  public boolean getTransactionSuccessIndicatorFlag(){
    return successIndicatorFlag;
  }
  
  public String getTransactionFailureReason(){
    return reasonForFailure;
  }
  
  public double getPreTransactionBalance(){
    return preTransactionBalance;
  }
  
  public double getPostTransactionBalance(){
    return postTransactionBalance;
  }
 

}