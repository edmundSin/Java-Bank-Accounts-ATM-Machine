/* Edmund Sin 
 * Bank Accounts  Using Inheritance, Polymorphism, AbstractClasses and Methods 
 */

import java.util.Calendar;

public class CDAccount extends Account{
  
  protected Calendar maturityDate = Calendar.getInstance();
  
  public TransactionReceipt makeDeposit(TransactionTicket tic, 
                                TransactionReceipt rcpt, int newMaturity)
  {
    double preBalance, postBalance, amount, temp;
    boolean flag;
    Calendar today = Calendar.getInstance();
    
    maturityDate.add(Calendar.SECOND, -1);
    
  //make CD deposit if maturity date is met 
    if(maturityDate.before(today) == true && 
       tic.getTransactionType().equals("CD Deposit"))
   {
    flag = true;
    amount = tic.getTransactionAmount();
    preBalance = rcpt.getPreTransactionBalance();
    temp = preBalance;
    temp += amount;
    postBalance = temp;
    
    //add to CD total static member in Bank class
    Bank.addCDTotal(amount);
    //add to All Total static member in Bank class
    Bank.addAllTotal(amount);
    
    //add new maturity term to current date
    maturityDate.add(Calendar.MONTH, newMaturity);
    
    
    TransactionReceipt rcpt2 = new TransactionReceipt
      (preBalance, postBalance, maturityDate, flag, tic);
    
    //create copy of object with copy constructor 
    TransactionReceipt rcptCopy = new TransactionReceipt
      (rcpt2);
    
    //return a copy 
    return rcptCopy;
    } 
    else if(maturityDate.before(today) == false)
    {
      String reason1;
      
      flag = false;
      reason1 = "Error: The maturity date for this account has not been met";
      rcpt = new TransactionReceipt(reason1, flag, maturityDate);
      history.add(rcpt);
    }
    else{
      
    }
    return rcpt;
}
  
  public TransactionReceipt makeWithdrawal(TransactionTicket tic, 
                                TransactionReceipt rcpt, int newMaturity)
  {
    double preBalance, postBalance, amount, temp;
    boolean flag;
    Calendar today = Calendar.getInstance();
    
    maturityDate.add(Calendar.SECOND, -1);
    
  //make CD withdrawal if maturity date is met 
    if(maturityDate.before(today) == true && 
       tic.getTransactionType().equals("CD Withdrawal"))
   {
    flag = true;
    amount = tic.getTransactionAmount();
    preBalance = rcpt.getPreTransactionBalance();
    temp = preBalance;
    temp -= amount;
    postBalance = temp;
    
    //subtract CD total static member in Bank class
    Bank.addCDTotal(-amount);
    //subtract All Total static member in Bank class
    Bank.addAllTotal(-amount);
    
    //add new maturity term to current date
    maturityDate.add(Calendar.MONTH, newMaturity);
    
    
    TransactionReceipt rcpt2 = new TransactionReceipt
      (preBalance, postBalance, maturityDate, flag, tic);
    
    //create copy of object with copy constructor 
    TransactionReceipt rcptCopy = new TransactionReceipt
      (rcpt2);
    
    //return a copy 
    return rcptCopy;
    } 
    else if(maturityDate.before(today) == false)
    {
      String reason1;
      
      flag = false;
      reason1 = "Error: The maturity date for this account has not been met";
      rcpt = new TransactionReceipt(reason1, flag, maturityDate);
      history.add(rcpt);
    }
    else{
      
    }
    return rcpt;
}
  
}
