/* Edmund Sin 
 * CISC 3115
 * Professor Ziegler
 * 11/24/19
 * HW 7: Bank Accounts  Using Inheritance, Polymorphism, AbstractClasses and Methods 
 */

import java.util.Calendar;

public class CheckingAccount extends Account{
  
  //uses Account class 1 parameter copy constructor 
  public CheckingAccount(Account a){
    super(a);
  }
  
  //no-arg constructor 
  public CheckingAccount(){
  }
  
  public TransactionReceipt makeDeposit(TransactionTicket tic, 
                                  TransactionReceipt rcpt){
    
    double preBalance, postBalance, amount, temp;
    
    amount = tic.getTransactionAmount();
    preBalance = rcpt.getPreTransactionBalance();
    temp = preBalance;
    temp += amount;
    postBalance = temp;
    
    TransactionReceipt recptW = new TransactionReceipt
      (preBalance, postBalance, tic);
    
    //create copy of object with copy constructor
    TransactionReceipt rcptCopy = new TransactionReceipt
      (recptW);
    
    //return a copy
    return rcptCopy;
    }
  
  public TransactionReceipt makeWithdrawal(TransactionTicket tic, 
                                  TransactionReceipt rcpt){
    
    double preBalance, postBalance, amount, temp;
    
    amount = tic.getTransactionAmount();
    preBalance = rcpt.getPreTransactionBalance();
    temp = preBalance;
    temp -= amount;
    postBalance = temp;
    
    TransactionReceipt recptW = new TransactionReceipt
      (preBalance, postBalance, tic);
    
    //create copy of object with copy constructor
    TransactionReceipt rcptCopy = new TransactionReceipt
      (recptW);
    
    //return a copy
    return rcptCopy;
    }
  
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
  
}