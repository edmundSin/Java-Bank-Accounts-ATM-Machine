/* Edmund Sin 
 * Bank Accounts  Using Inheritance, Polymorphism, AbstractClasses and Methods 
 */

import java.util.Calendar;
import java.util.ArrayList;

public class Account { 
  
  protected Depositor depositor; 
  protected int acctNum;
  protected String acctType;
  protected String status;
  protected double acctBalance;
  protected ArrayList<TransactionReceipt> history;
 
  protected Calendar calendar = Calendar.getInstance();
  
  
  //no-arg constructor
  public Account(){
    depositor = new Depositor();
    acctNum = 0; 
    acctType = " ";
    acctBalance = 0.0; 
    history = new ArrayList<TransactionReceipt>();
  }
  
  //copy constructor 
  public Account(Account acct){
    depositor = new Depositor(acct.depositor);
    acctNum = acct.acctNum;
    status = acct.status;
    acctBalance = acct.acctBalance;
    history = new ArrayList<TransactionReceipt>();
  }
  
  //parametized constructor 
  
  public Account(Depositor d, int n, String t, double b, String s) {
    depositor = d;
    acctNum = n; 
    acctType = t;
    acctBalance = b; 
    status = s;
    history = new ArrayList<TransactionReceipt>();
    
  }
  
  
 /*  Method makeDeposit:
  *Input:
  * tic - TransactionTicket reference
  * rcpt - TransactionReceipt reference 
  * newMaturity - int of maturity term
  *Process:
  * check if initial maturity is before today's date 
  * and if transactionType is CD Deposit
  * set successIndicatorFlag to true and make deposit
  * protected setter for new account balance
  * add new maturity term to calendar 
  * set TransactionReceipt object with new values
  * add to TransactionReceipt arraylist 
  * else, set error message
  * this method also makes regular deposit (not CD)
  * return TransactionReceipt object
  *Output:
  * Given objects with valid information passed through parameters,
  *  CD deposit or regular deposit is made
  *  updated object is returned
  * Otherwise, error is set 
  *  updated object is returned
  * 
  */ 
  
 public TransactionReceipt makeDeposit(TransactionTicket tic, 
                                TransactionReceipt rcpt, int newMaturity)
  {
    double preBalance, postBalance, amount, temp;
    boolean flag;
    Calendar today = Calendar.getInstance();
    
    if (getAcctType().equals("Savings")){
      
      //create subclass reference object 
      SavingsAccount sAccount = new SavingsAccount();
      
      /* update rcpt object with returned reciept from 
         makeDeposit() method in SavingsAccount class
       */ 
      rcpt = sAccount.makeDeposit(tic,rcpt);
      
      //uses protected setter to update post balance
      setAcctBalance(rcpt.getPostTransactionBalance());
      //add to TransactionReceipt arraylist
      history.add(rcpt);
      
      return rcpt;
    } 
    else if(getAcctType().equals("Checking")){
      
       //create subclass reference object 
      CheckingAccount cAccount = new CheckingAccount();
      
      /* update rcpt object with returned reciept from 
         makeDeposit() method in CheckingAccount class
       */ 
      rcpt = cAccount.makeDeposit(tic,rcpt);
      
      //uses protected setter to update post balance
      setAcctBalance(rcpt.getPostTransactionBalance());
      //add to TransactionReceipt arraylist
      history.add(rcpt);
      
      return rcpt;
    }
    
   //CD Deposit
    if(calendar.before(today) == true &&
       tic.getTransactionType().equals("CD Deposit"))
   {
      //create subclass reference object 
     CDAccount cdAccount = new CDAccount();
     
     /* update rcpt object with returned reciept from 
         makeDeposit() method in CheckingAccount class
       */ 
     rcpt = cdAccount.makeDeposit(tic,rcpt,newMaturity);
     
     //uses protected setter to update post balance
      setAcctBalance(rcpt.getPostTransactionBalance());
      //add new maturity term to current date
      calendar.add(Calendar.MONTH, newMaturity);
      //add to TransactionReceipt arraylist
      history.add(rcpt);
      
      return rcpt;
    }
    
    else{
      String reason1;
      
      flag = false;
      reason1 = "Error: The maturity date for this account has not been met";
      rcpt = new TransactionReceipt(reason1, flag, calendar);
      history.add(rcpt);
    
    }
    return rcpt;
    
  }
 
  
 /*  Method makeWithdrawal:
  *Input:
  * tic - TransactionTicket reference
  * rcpt - TransactionReceipt reference 
  * newMaturity - int of maturity term
  *Process:
  * check if initial maturity is before today's date 
  * and if transactionType is CD Withdrawal
  * set successIndicatorFlag to true and make withdrawal
  * protected setter for new account balance
  * add new maturity term to calendar 
  * set TransactionReceipt object with new values
  * add to TransactionReceipt arraylist 
  * else, set error message
  * this method also makes regular withdrawal (not CD)
  * return TransactionReceipt object
  *Output:
  * Given objects with valid information passed through parameters,
  *  CD withdrawal or regular withdrawal is made
  *  updated object is returned
  * Otherwise, error is set 
  *  updated object is returned
  * 
  */ 
 
  public TransactionReceipt makeWithdrawal(TransactionTicket tic, 
                                TransactionReceipt rcpt, int newMaturity)
  {
    double preBalance, postBalance, amount, temp;
    boolean flag;
    Calendar today = Calendar.getInstance();
    
    if (getAcctType().equals("Savings")){
      
       //create subclass reference object 
       SavingsAccount sAccount = new SavingsAccount();
      
      /* update rcpt object with returned reciept from 
         makeWithdrawal() method in SavingsAccount class
       */ 
      rcpt = sAccount.makeWithdrawal(tic,rcpt);
      
      //uses protected setter to update post balance
      setAcctBalance(rcpt.getPostTransactionBalance());
      //add to TransactionReceipt arraylist
      history.add(rcpt);
      
      return rcpt;
    } 
    else if(getAcctType().equals("Checking")){
      
       //create subclass reference object 
      CheckingAccount cAccount = new CheckingAccount();
      
      /* update rcpt object with returned reciept from 
         makeWithdrawal() method in CheckingAccount class
       */ 
      rcpt = cAccount.makeWithdrawal(tic,rcpt);
      
      //uses protected setter to update post balance
      setAcctBalance(rcpt.getPostTransactionBalance());
      //add to TransactionReceipt arraylist
      history.add(rcpt);
      
      return rcpt;
    }
 
    //CD Withdrawal
    if(calendar.before(today) == true &&
       tic.getTransactionType().equals("CD Withdrawal"))
   {
      //create subclass reference object 
     CDAccount cdAccount = new CDAccount();
     
     /* update rcpt object with returned reciept from 
         makeDeposit() method in CheckingAccount class
       */ 
     rcpt = cdAccount.makeWithdrawal(tic,rcpt,newMaturity);
     
     //uses protected setter to update post balance
      setAcctBalance(rcpt.getPostTransactionBalance());
      //add new maturity term to current date
      calendar.add(Calendar.MONTH, newMaturity);
      //add to TransactionReceipt arraylist
      history.add(rcpt);
      
      return rcpt;
    }
    
    else{
      String reason1;
      
      flag = false;
      reason1 = "Error: The maturity date for this account has not been met";
      rcpt = new TransactionReceipt(reason1, flag, calendar);
      history.add(rcpt);
    
    }
    return rcpt;
    
  }
  
  /* Method clearCheck:
   *Input: 
   * c - Check class reference
   * bank - Bank class reference
   *Process:
   * declare variables
   * check if account type doesnt equal Checking
   * sets error message
   * checks if Check date is valid 
   * sets error message
   * checks if checkAmount is greater than balance
   * sets error message 
   * incurs penalty for bouncing check
   * else, clears check and sets account balance
   * add receipt object to arraylist
   *Output:
   * Given valid account type, calendar date, and check amount, 
   *  check is cleared and balance set
   * Otherwise, error message is set and
   *  TransactionReceipt object returned
   */ 
  public TransactionReceipt clearCheck(Check c, Bank bank)
  {
    Account acct;
    int index;
    int acctNum = c.getAcctNum();
    
    index = bank.findAcct(acctNum);
    acct = bank.getAcct(index);
    
    double checkAmount = c.getCheckAmount();
    double balance = acct.getAcctBalance();
    double temp, postBalance;
    TransactionReceipt tRcpt;
    Calendar calendar = c.getDateOfCheck();
    Calendar today = Calendar.getInstance();
    Calendar sixBefore = Calendar.getInstance();
   
    //six months ago from today's date
    sixBefore.add(Calendar.MONTH, -6);
    
    if( !acct.getAcctType().equals("Checking")){
       String error3 = "Error: This is not a Checking Account";
       //create tRcpt object with constructor 
        tRcpt = new TransactionReceipt(error3,false,null);
        
    }
     else if(calendar.after(today) || calendar.before(sixBefore)) 
     {
       
       String error2 = "Error: This is an invalid check date";
       //create tRcpt object with constructor 
        tRcpt = new TransactionReceipt(error2,false,null); 
       
     } else if (checkAmount > balance)
         {
          String error1 = 
            "Error: Insufficient Funds. $2.5 Penalty Incurred"; 
          //create tRcpt object with constructor 
          tRcpt = new TransactionReceipt(error1,false,null);
          
          temp = balance;
          //make penalty
          temp -= 2.5;
          postBalance = temp;
         
          acct.setAcctBalance(postBalance);
          
         }
        //make check withdrawal
          else {
         temp = balance;
          
        //incur penalty    
        if(balance < 2500){
         temp = balance - 1.5;
         
            }
         temp -= checkAmount;
         postBalance = temp;
        
         String type = "Clear Check";
         
         //SUBTRACT from Bank static member
         Bank.addCheckingTotal(-checkAmount);
         //SUBTRACT from Bank static member
         Bank.addAllTotal(-checkAmount);
         
         //protected setter, set post transaction balance
         acct.setAcctBalance(postBalance);
         
         TransactionTicket tic = new TransactionTicket
           (today, type, checkAmount,acctNum);
         TransactionReceipt recptC = new TransactionReceipt
           (balance, postBalance, tic);
         
         //add receipt into history arraylist
         acct.history.add(recptC);
         
        //create copy of object with copy constructor 
        TransactionReceipt rcptCopy = new TransactionReceipt
         (recptC);
    
       //return a copy 
        return rcptCopy;
         
       }
          
      return tRcpt;
  }
 
 
  
  public TransactionReceipt closeAccount(TransactionTicket tic,
                                        TransactionReceipt recpt)
  {
    //protected setter in Account class, set status to CLOSED
    setStatus("CLOSED");
    //copies over all values from parameter object to receipt array
    addTransaction(recpt);
    
    //create copy of object with copy constructor 
    TransactionReceipt rcptCopy = new TransactionReceipt
      (recpt);
    
    //return a copy 
    return rcptCopy;
  }
  
  public TransactionReceipt reopenAccount(TransactionTicket tic,
                                        TransactionReceipt recpt)
  {
    //protected setter in Account class, set status to CLOSED
    setStatus("OPEN");
    //copies over all values from parameter object to receipt array
    addTransaction(recpt);
    
    //create copy of object with copy constructor 
    TransactionReceipt rcptCopy = new TransactionReceipt
      (recpt);
    
    //return a copy 
    return rcptCopy;
  }
  
    //toString() method - uses String static method .format()
  public String toString() {
  
    Name name = depositor.getName();
  
    String str = String.format("%-10s%-10s%-14s%-11s%-15s%-8s$%.2f",
         
         name.getFirst(),
         name.getLast(),
         depositor.getSsn(),
         getAcctNum(),                    
         getAcctType(),
         getStatus(),
         getAcctBalance()
         );                      
        
    return str;
  }
  
  //equals() method
 public boolean equals(Account acct) {
  
   if(depositor.equals(acct.depositor))
   return true;   //acct found
  else
   return false;   //acct not found
 }
  
  //protected setters
      
 protected void setStatus(String s){
    status = s;
  }
 
 protected void setAcctBalance(double b) {
   acctBalance = b;
  
 }
 
    //getters
 
  public Depositor getDepositor() {
    Depositor copy = new Depositor(depositor);
    return copy;
  }
    
  public int getAcctNum() {
    return acctNum;
  }
  public String getAcctType() {
    return acctType;
  }
  
  public double getAcctBalance() {
    return acctBalance;
  }
  
  public String getStatus(){
    return status;
  }
  
  
   
   public void addTransaction( TransactionReceipt newRcpt){
   history.add(newRcpt);
  }
   
   public TransactionReceipt getReceipt(int index){
    return history.get(index);
  }
   public int getReceiptSize(){
    return history.size();
  }
   
   public ArrayList<TransactionReceipt> getTransactionHistory
                        (TransactionTicket tic){
    return history;
  }
   
}
  
  
