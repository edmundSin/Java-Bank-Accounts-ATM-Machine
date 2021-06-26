/* Edmund Sin 
 * Bank Accounts  Using Inheritance, Polymorphism, AbstractClasses and Methods 
 */


import java.io.*;
import java.util.*;

public class Bank{
 
  private ArrayList<Account> account; 
  
  private static double totalAmountInSavingsAccts = 0.0;
  private static double totalAmountInCheckingAccts = 0.0;
  private static double totalAmountInCDAccts = 0.0;
  private static double totalAmountInAllAccts = 0.0; 
   
  
  //No-Arg constructor
  public Bank(){
   
    account = new ArrayList<Account>();
  }
  
  public static void readDataTotals() throws IOException
  {
     Scanner reader = new Scanner(new File("myinputs4.txt"));
       
       while (reader.hasNextLine()) 
       {
          String line = reader.nextLine();
          String[] tokens = line.split(" ");
            
          //checks account type from input file 
           if(tokens[4].equals("Savings"))
          //adds balances to static members accordingly   
              addSavingsTotal(Double.parseDouble(tokens[5]));
           else 
             if(tokens[4].equals("Checking"))
              addCheckingTotal(Double.parseDouble(tokens[5]));
           else   //account type is CD
              addCDTotal(Double.parseDouble(tokens[5]));
           
           //add all balances to static member: totalAmountInAllAccts
           addAllTotal(Double.parseDouble(tokens[5]));
               
        }
        
        reader.close();
    } 
  
  
   //static get-methods
  public static double getSavingsTotal() 
  {
   return totalAmountInSavingsAccts;
  }
  
   public static double getCheckingTotal() 
  {
   return totalAmountInCheckingAccts;
  }
   
   public static double getCDTotal() 
  {
   return totalAmountInCDAccts;
  }
   
   public static double getAllTotal() 
  {
   return totalAmountInAllAccts;
  } 
  
  //static add-methods 
  public static void addSavingsTotal(double amount)
  {
   totalAmountInSavingsAccts += amount;
  }
  
  public static void addCheckingTotal(double amount)
  {
   totalAmountInCheckingAccts += amount;
  }
  
  public static void addCDTotal(double amount)
  {
   totalAmountInCDAccts += amount;
  }
  
  public static void addAllTotal(double amount)
  {
   totalAmountInAllAccts += amount;
  }

 
  /* Method openNewAcct:
   * Input: 
   *  tic - TransactionTicket reference
   *  rcpt - TransactionReceipt reference
   *  bank - Bank class reference
   * Process: 
   *  varable declarations
   *  check if accountType equals CD
   *  add cdTerm to calendar to set maturity date
   *  set TransactionTicket and TransactionReceipt with parametized const.
   *  add to TransactionReceipt arraylist with recptCD object
   *  return recptCD
   *  set TransactionTicket and TransactionReceipt with parametized const.
   *  this is for regular accounts without a cdTerm
   *  add to TransactionReceipt arraylist with recpt object
   *  return recpt
   * Output: 
   *  New account transaction receipt is created and added into arraylist
   */ 
  
 public TransactionReceipt openNewAcct(TransactionTicket tic, 
                                       TransactionReceipt rcpt,
                                       Bank bank
                                       )
                                       
  {
     String type = "New Account";
     Calendar today = Calendar.getInstance();
      
     Account acct;
     int index = getNumAccts() -1;
     acct = bank.getAcct(index);
     double balance = rcpt.getPostTransactionBalance();
     double depositAmount = tic.getTransactionAmount();
     int acctNum = acct.getAcctNum();
     int cdTerm = tic.getTermOfCD();
     String acctType = acct.getAcctType();
     
     //check if accountType equals CD
     if (acctType.equals("CD")){
       Calendar calendar2 = Calendar.getInstance();
       //set new maturity date
       calendar2.add(Calendar.MONTH, cdTerm);
       
       TransactionTicket ticCD = new TransactionTicket
       (today, type, depositAmount,cdTerm);
     TransactionReceipt recptCD = new TransactionReceipt
       (0.0,balance,ticCD,calendar2);
     
     
     //add to TransactionReceipt arraylist
     acct.addTransaction(recptCD);
     
     return recptCD;
     }
     
     //set regular objects without cdTerm
     tic = new TransactionTicket
       (today, type, depositAmount,acctNum);
     TransactionReceipt recpt = new TransactionReceipt
       (0.0,balance,tic);
     
     //add to TransactionReceipt arraylist
     acct.addTransaction(recpt);
     
     return recpt;
}
 
 public TransactionTicket deleteAccount
   (TransactionTicket tic, int index)
 {
   
   //delete account at array list index
   account.remove(index);
   
   return tic;
 }
 
 //find Account by comparing objects
  public int findAccount(Account reqAccount)
 { 
   for (int index = 0; index < account.size(); index++){
      if (account.get(index).equals(reqAccount))
           return index;
   }
   return -1;
   
 }
 
 //find Account by comparing account numbers
  public int findAcct(int reqAccount)
 { 
   int acct;
   
   for (int index = 0; index < account.size(); index++){
     acct = account.get(index).getAcctNum();
     
     if (acct == reqAccount)
           return index;
   }
   return -1;
   
 }
  
  
  public void addAccount( Account newAcct){
   account.add(newAcct);
  }
  
 //getters
  public Account getAcct(int index){
    return account.get(index);
  }
  
  public int getNumAccts(){
    return account.size();
  }
}
