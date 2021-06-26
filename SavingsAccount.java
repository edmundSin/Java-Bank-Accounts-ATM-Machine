/* Edmund Sin 
 * Bank Accounts  Using Inheritance, Polymorphism, AbstractClasses and Methods 
 */

public class SavingsAccount extends Account{
  
  
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
    
  
}
